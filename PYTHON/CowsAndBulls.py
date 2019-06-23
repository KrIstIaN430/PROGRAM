import random
#Guess the 4 digits in their right place
# cows = right number, right place
# bulls = right number, wrong place


number_to_guess = [int(x) for x in str(random.randint(1000, 9999))] #Generate the number to guess
ctr = 1


def check_size(number_to_check):
    temp_list = [int(x) for x in number_to_check]
    global ctr
    if len(temp_list) != 4:
        print("Number must be 4 digits.. Try again.")
        temp_list = check_size(input("Enter a 4 digit number: "))
        ctr += 1
    return temp_list


def check_cows_bulls(number_to_check):
    temp_list = check_size(number_to_check)
    temp_list2 = number_to_guess.copy()
    cows = 0
    bulls = 0
    for num in range(4):
        if temp_list[num] == number_to_guess[num]:
            cows += 1
            temp_list2[num] = None
        elif temp_list[num] in temp_list2:
            bulls += 1
    print(str(cows) + " cows, " + str(bulls) + " bulls")
    if cows == 4:
        print("Guesses: " + str(ctr))
    return cows


print("Welcome to the Cows and Bulls Game!")
#print("Number to guess: " + str(number_to_guess)) #Print the generated number to guess

while check_cows_bulls(input("Enter a 4 digit number: ")) != 4:
    ctr += 1
