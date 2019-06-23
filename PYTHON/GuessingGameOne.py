import random


number_to_guess = random.randint(1, 9)
guess_count = 0
guess = ''
while guess != number_to_guess and guess != "exit":
    guess = input("Enter your guess number or type \"exit\" to exit: ")
    guess_count += 1
    if guess == "exit":
        continue
    elif int(guess) < number_to_guess:
        print("You guessed too low, try again.")
    elif int(guess) > number_to_guess:
        print("You guessed too high, try again.")
    elif int(guess) == number_to_guess:
        print("You guessed it!")
        print("Number of times you guessed: " + str(guess_count))
