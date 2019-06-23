from CheckTicTacToe import check_winner, game_board, return_size
import getpass
from DrawABoardGame import whole_row


def get_placement(board_size):
    player = "O"
    while not check_winner(board_size, player) \
            and sum(x.count(' ') for x in game_board) > 0:
        player = "X" if player == "O" else "O"
        coord = input("Where to place?(row,column): ").split(",")
        while game_board[int(coord[0]) - 1][int(coord[1]) - 1] != " ":
            print("Position is already taken. Choose another one")
            coord = input("Where to place?(row,column): ").split(",")
        game_board[int(coord[0]) - 1][int(coord[1]) - 1] = player
        whole_row(1)
    if sum(x.count(' ') for x in game_board) == 0:
        print("ITS A DRAW!")
    else:
        print(player + " WINS THE GAME!")
    getpass.getpass("Press Enter to Continue...")


if __name__ == "__main__":
    get_placement(return_size())
