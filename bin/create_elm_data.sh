#! /bin/sh

#mongodb://caleb:******@localhost:27017/chem?authSource=admin
#mongodb://localhost:27017/chem
#mongoimport --collection=elms --file=elements.json --jsonArray
#mongoimport PubChemElements_all.json  -d chem -c elms --drop --jsonArray
mongoimport PubChemElements_all.json  -d chem -c elms --drop

