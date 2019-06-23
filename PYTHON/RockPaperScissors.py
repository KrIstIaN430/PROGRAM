from os import system

wins = {'Rock': 'Scissors', 'Paper': 'Rock', 'Scissors': 'Paper'}
options = {'R': 'Rock', 'P': 'Paper', 'S': 'Scissors'}
new_game = 'Y'
while new_game == 'Y':
    system('cls')
    p1 = ''
    while p1 not in options:
        print("PLAYER 1")
        p1 = input('Choose (R)ock, (P)aper or (S)cissors: ').upper()
        if p1 not in options:
            p1 = ''
            continue
    system('cls')
    p1 = options[p1]
    p2 = ''
    while p2 not in options:
        print("PLAYER 2")
        p2 = input('Choose (R)ock, (P)aper or (S)cissors: ').upper()
        if p2 not in options:
            p2 = ''
            continue
    system('cls')
    p2 = options[p2]
    print('{} vs {}'.format(p1, p2))
    if p1 == p2:
        print('Draw!!\n')
        new_game = input("Do you want to Start a new game? Y/N: ").upper()
        continue
    else:
        if wins[p1] == p2:
            winner = 1
        elif wins[p2] == p1:
            winner = 2
        print('PLAYER {} wins!!\n'.format(winner))
        new_game = input("Do you want to Start a new game? Y/N: ").upper()
