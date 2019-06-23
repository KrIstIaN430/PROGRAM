import CheckTicTacToe


# row x col
#  --- --- ---
# |   |   |   |
#  --- --- ---
# |   |   |   |
#  --- --- ---
# |   |   |   |
#  --- --- ---


def whole_row(show):
    if show != 1:
        size = input("Input number of row and column(row and column has same size): ")
    else:
        size = CheckTicTacToe.return_size()
    for x in range(int(size)):
        print(" ---" * int(size))
        print("|", end="")
        if show == 1:
            for y in range(int(size)):
                print(" " + str(CheckTicTacToe.game_board[x][y]) + " |", end="")
            print()
        else:
            print("   |" * int(size))
    print(" ---" * int(size))
    return int(size)


if __name__ == "__main__":
    whole_row(None)
