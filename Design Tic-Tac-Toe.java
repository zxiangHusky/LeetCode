/*
Design a Tic-tac-toe game that is played between two players on a n x n grid.

You may assume the following rules:

A move is guaranteed to be valid and is placed on an empty block.
Once a winning condition is reached, no more moves is allowed.
A player who succeeds in placing n of their marks in a horizontal, vertical, or diagonal row wins the game.
Example:
Given n = 3, assume that player 1 is "X" and player 2 is "O" in the board.

TicTacToe toe = new TicTacToe(3);

toe.move(0, 0, 1); -> Returns 0 (no one wins)
|X| | |
| | | |    // Player 1 makes a move at (0, 0).
| | | |

toe.move(0, 2, 2); -> Returns 0 (no one wins)
|X| |O|
| | | |    // Player 2 makes a move at (0, 2).
| | | |

toe.move(2, 2, 1); -> Returns 0 (no one wins)
|X| |O|
| | | |    // Player 1 makes a move at (2, 2).
| | |X|

toe.move(1, 1, 2); -> Returns 0 (no one wins)
|X| |O|
| |O| |    // Player 2 makes a move at (1, 1).
| | |X|

toe.move(2, 0, 1); -> Returns 0 (no one wins)
|X| |O|
| |O| |    // Player 1 makes a move at (2, 0).
|X| |X|

toe.move(1, 0, 2); -> Returns 0 (no one wins)
|X| |O|
|O|O| |    // Player 2 makes a move at (1, 0).
|X| |X|

toe.move(2, 1, 1); -> Returns 1 (player 1 wins)
|X| |O|
|O|O| |    // Player 1 makes a move at (2, 1).
|X|X|X|
Follow up:
Could you do better than O(n2) per move() operation?
*/
public class TicTacToe {

    /** Initialize your data structure here. */
    public int[][] board = null;
    public TicTacToe(int n) {
        board = new int[n][n];
    }
    
    /** Player {player} makes a move at ({row}, {col}).
        @param row The row of the board.
        @param col The column of the board.
        @param player The player, can be either 1 or 2.
        @return The current winning condition, can be either:
                0: No one wins.
                1: Player 1 wins.
                2: Player 2 wins. */
    public int move(int row, int col, int player) {
        board[row][col] = player;
        if (isWin(player)) return player;
        return 0;
    }

    private boolean isWin(int player) {
        int n = board.length;
        for (int i = 0; i < n ; i++) {
            boolean isWinRow = true;
            boolean isWinColumn = true;
            for (int j = 0; j < n; j++) {
                if (board[i][j] != player) {
                    isWinRow = false;
                }
                if (board[j][i] != player) {
                    isWinColumn = false;
                }
            }
            if (isWinColumn || isWinRow) {
                return true;
            }
        }
        boolean isWinDiagonalOne = true;
        boolean isWinDiagonalTwo = true;
        for (int i = 0; i < n; i++) {
            if (board[i][i] != player) {
                isWinDiagonalOne = false;
            }
            if (board[i][n - i - 1] != player) {
                isWinDiagonalTwo = false;
            }
        }
        if (isWinDiagonalOne || isWinDiagonalTwo) {
            return true;
        }
        return false;
    }


}


// Better Solution

public class TicTacToe {

    /** Initialize your data structure here. */
    public int[][] board = null;
    public TicTacToe(int n) {
        board = new int[n][n];
    }
    
    /** Player {player} makes a move at ({row}, {col}).
        @param row The row of the board.
        @param col The column of the board.
        @param player The player, can be either 1 or 2.
        @return The current winning condition, can be either:
                0: No one wins.
                1: Player 1 wins.
                2: Player 2 wins. */
    public int move(int row, int col, int player) {
        board[row][col] = player;
        if (isWin(player, row, col)) return player;
        return 0;
    }

    private boolean isWin(int player, int row, int col) {
        if (isWinCore(player, row, 0, 0, 1)) {
            return true;
        }
        else if (isWinCore(player, 0, col, 1, 0)) {
            return true;
        }
        if (row == col) {
            if (isWinCore(player, 0, 0, 1, 1)) {
                return true;
            }
        }
        if ((row + col) == board.length - 1) {
            if (isWinCore(player, 0, board.length - 1, 1, -1)) {
                return true;
            }
        }
        return false;
    }

    private boolean isWinCore(int player, int rowIndex, int columnIndex, int dx, int dy) {
        boolean isWin = true;
        for (int i = 0; i < board.length; i++) {
            if (board[rowIndex][columnIndex] != player) {
                isWin = false;
                break;
            }
            rowIndex += dx;
            columnIndex += dy;
        }
        return isWin;
    }


}
