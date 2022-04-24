#!/bin/bash

# setup variables
green() {
  echo -e '\e[32m'$1'\e[m';
}
red() {
  echo -e '\e[1;31m'$1'\e[m';
}
cyan() {
  echo -e '\e[36m'$1'\e[m';
}
readonly MYSQLD=`which mysqld_safe`
readonly MYSQL=`which mysql`
readonly HTTPD=`which httpd`
readonly PHPFPM=`which php-fpm`
readonly DOVECOT=`which dovecot`

green "Configuring mail server with hostname: $APP_HOST.$APP_DOMAIN."
sleep 1
#echo "full hostname: $APP_HOST.$APP_DOMAIN"

#export HOST_IP=$(/sbin/ip route|awk '/default/ { print $3 }')
#echo "$HOST_IP dockerhost" >> /etc/hosts
echo "127.0.0.1     $APP_HOST.$APP_DOMAIN" >> /etc/hosts
echo "127.0.0.1     $APP_DOMAIN" >> /etc/hosts

# roundcube
# cp -p /etc/roundcubemail/defaults.inc.php /etc/roundcubemail/config.inc.php
sed -i "s/{{APP_HOST}}/$APP_HOST/g" /etc/roundcubemail/config.inc.php
sed -i "s/{{APP_DOMAIN}}/$APP_DOMAIN/g" /etc/roundcubemail/config.inc.php
sed -i "s/{{SMTP_PORT}}/$SMTP_PORT/g" /etc/roundcubemail/config.inc.php
# roundcube database
sed -i "s/{{RC_DB_USERNAME}}/$RC_DB_USERNAME/g" /etc/roundcubemail/config.inc.php
sed -i "s/{{RC_DB_PASSWD}}/$RC_DB_PASSWD/g" /etc/roundcubemail/config.inc.php
sed -i "s/{{RC_DB_NAME}}/$RC_DB_NAME/g" /etc/roundcubemail/config.inc.php

# start the php service
mkdir /run/php-fpm
$PHPFPM

# start the httpd service
$HTTPD
# /etc/httpd/conf.d/roundcubemail.conf
# <IfModule mod_authz_core.c>
#     # Apache 2.4
#     Require local
#     Require all granted
# </IfModule>
# <IfModule mod_authz_core.c>
#     # Apache 2.4
#     Require local
#     Require all granted
# </IfModule>

# setup mariadb mysql
echo "user=root" >> /etc/my.cnf
mysql_install_db
$MYSQLD > /dev/null &
cyan "Please wait, doing more stuff ..."
sleep 1
# mysql_secure_installation

# setup roundcube database
# credit: https://clubmate.fi/shell-script-to-create-mysql-database
# Construct the MySQL query
readonly Q1="CREATE DATABASE IF NOT EXISTS $RC_DB_NAME CHARACTER SET utf8 COLLATE utf8_bin;"
readonly Q2="GRANT ALL PRIVILEGES ON $RC_DB_NAME.* TO $RC_DB_USERNAME@'localhost' IDENTIFIED BY '$RC_DB_PASSWD';"
readonly Q3="FLUSH PRIVILEGES;"
readonly SQL="${Q1}${Q2}${Q3}"
# Run the actual command
$MYSQL -uroot -e "$SQL"
# Let the user know the database was created
# initalize roundcube
$MYSQL -h "localhost" -u $RC_DB_USERNAME "-p$RC_DB_PASSWD" "$RC_DB_NAME" < "/usr/share/roundcubemail/SQL/mysql.initial.sql"

# dovecot config
sed -i "s/disable_plaintext_auth = yes/disable_plaintext_auth = no/g" /etc/dovecot/conf.d/10-auth.conf
sed -i "s/#mail_location =/mail_location = maildir\:\~\/Maildir/g" /etc/dovecot/conf.d/10-mail.conf
sed -i "s/ssl = required/ssl = no/g" /etc/dovecot/conf.d/10-ssl.conf
sed -i "s/ssl_cert = <\/etc\/pki\/dovecot\/certs\/dovecot\.pem/#ssl_cert = <\/etc\/pki\/dovecot\/certs\/dovecot\.pem/g" /etc/dovecot/conf.d/10-ssl.conf
sed -i "s/ssl_key = <\/etc\/pki\/dovecot\/private\/dovecot\.pem/#ssl_key = <\/etc\/pki\/dovecot\/private\/dovecot\.pem/g" /etc/dovecot/conf.d/10-ssl.conf
# start dovecot
$DOVECOT

# postfix
sed -i "s/{{APP_HOST}}/$APP_HOST/g" /etc/postfix/main.cf
sed -i "s/{{APP_DOMAIN}}/$APP_DOMAIN/g" /etc/postfix/main.cf
# number option to `s' command may not be zero
# sed -i "s/{{MY_NETWORKS}}/$MY_NETWORKS/g" /etc/postfix/main.cf

# support user receive at root
#echo -e "support:\troot" >> /etc/aliases
newaliases
postfix start

# create a user
useradd -m -s /bin/bash user1 && echo $USER1_PSWD | passwd user1 --stdin

./configinfo.sh
