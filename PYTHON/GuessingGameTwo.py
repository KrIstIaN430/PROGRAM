import random

guess_list = []


def generate_guess(start, end):
    guess = random.randint(start, end)
    if guess in guess_list:
        guess = generate_guess(start, end)
    return guess


start = 0
end = 100
ans = ""
guess_count = 1
while ans != "E":
    guess = generate_guess(start, end)
    guess_list.append(guess)
    print("My guess is: " + str(guess))
    ans = input("Your number is Higher, Lower, or Exact?[H/L/E]: ")
    if ans == "H":
        start = guess
    elif ans == "L":
        end = guess
    if ans == "E":
        print("It took me " + str(guess_count) + " guesses")
    guess_count += 1

