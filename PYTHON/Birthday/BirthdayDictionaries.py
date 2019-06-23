birthday_dict = {}
with open("Birthday.txt", 'r') as open_file:
    line = open_file.read().split("\n")
    for x in line:
        if x is not "":
            y = x.split(" : ")
            birthday_dict[y[0]] = y[1]
print("Welcome to the birthday dictionary. We know the birthdays of:")
for x in birthday_dict:
    print(x)
name = input("Who's birthday do you want to look up?: ")
print("{}'s birthday is {}.".format(name, birthday_dict[name]))

