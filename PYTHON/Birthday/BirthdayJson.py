import json

with open("info.json", "r") as open_file:
    birthday_dict = json.load(open_file)

print("Welcome to the birthday dictionary. We know the birthdays of:")
for x in birthday_dict:
    print(x)
name = input("Who's birthday do you want to look up?: ").title()
print("{}'s birthday is {}.".format(name, birthday_dict[name]))

name = input("Enter the name to add: ").title()
bday = input("Enter birthday: ").title()

birthday_dict[name] = bday

with open("info.json", "w") as open_file:
    json.dump(birthday_dict, open_file)
