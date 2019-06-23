list_to_reverse = input("Input a sentence: ")
reversed_list = list_to_reverse.split()
reversed_list.reverse()
reversed_list = " ".join(reversed_list)
print(reversed_list)

print(" ".join(list_to_reverse.split()[::-1]))