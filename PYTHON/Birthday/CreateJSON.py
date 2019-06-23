import json
birthday_dict = {}
with open("Birthday.txt", 'r') as open_file:
    line = open_file.read().split("\n")
    for x in line:
        if x is not "":
            y = x.split(" : ")
            birthday_dict[y[0]] = y[1]
with open("info.json", "w") as open_file:
    json.dump(birthday_dict, open_file)

