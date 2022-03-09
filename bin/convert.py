import json
with open("PubChemElements_all.json") as file: 
    data = json.load(file)

numElms = len(data["Table"]["Row"])
numAttrs = len(data["Table"]["Columns"]["Column"])

# initialize list of numElms empty dictionaries
myData = [dict() for x in range(numElms)]

for i in range(numElms):
    for j in range(numAttrs):
        key = data["Table"]["Columns"]["Column"][j]
        key = key[0].lower() + key[1:] 
        value = data["Table"]["Row"][i]["Cell"][j]
        myData[i].update({key:value})

with open("new_elms.json", 'w') as file:
    json.dump(myData, file, indent=4)
