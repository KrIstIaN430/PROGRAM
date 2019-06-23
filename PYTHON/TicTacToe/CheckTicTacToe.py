import DrawABoardGame


def check_winner(board_size, player):
    ctr = 0
    for i in range(board_size):
        for j in range(board_size):
            if game_board[i][j] == player:
                ctr += 1
        if ctr == board_size:
            return True
        else:
            ctr = 0
        for j in range(board_size):
            if game_board[j][i] == player:
                ctr += 1

        if ctr == board_size:
            return True
        else:
            ctr = 0
    for i in range(board_size):
        if game_board[i][i] == player:
            ctr += 1
    if ctr == board_size:
        return True
    else:
        ctr = 0
    for i in range(board_size):
        if game_board[i][board_size - 1 - i] == player:
            ctr += 1
    if ctr == board_size:
        return True
    else:
        return False


def return_size():
    global size
    return size


size = DrawABoardGame.whole_row(None)
game_board = [[" " for num_row in range(size)] for num_row in range(size)]
if __name__ == "__main__":
    check_winner(size, "X")
    print(check_winner(size, "X"))
