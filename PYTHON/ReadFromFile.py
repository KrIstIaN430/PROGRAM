cat_dict = {}
# how many of each “category” of each image there are in Training_01.txt
with open('Training_01.txt', 'r') as open_file:
    all_text = open_file.read().split("\n")
    for x in all_text:
        y = x.split("/")
        if len(y) > 2:
            while not y[2 + 1].__contains__("."):
                y[2] = y[2] + "/" + y[2 + 1]
                y.pop(2 + 1)
            cat_dict[y[2]] = cat_dict[y[2]] + 1 if y[2] in cat_dict else 1
print(cat_dict)
