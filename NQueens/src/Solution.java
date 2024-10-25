import java.util.ArrayList;
import java.util.List;

public class Solution {
    public List<List<String>> solveNQueens(int n) {
        ChessSolver chessSolver = new ChessSolver(n);
        chessSolver.solve(0);
        return chessSolver.getSolutions();
    }
}

class ChessSolver {
    private int n;
    private ChessBoard chessBoard;
    private List<List<String>> solutions;

    public ChessSolver(int n) {
        this.n = n;
        this.chessBoard = new ChessBoard(n);
        this.solutions = new ArrayList<>();
    }

    public void solve(int row) {
        if ( row == n ) {
            solutions.add(constructBoard());
            return;
        }


        for (int col = 0; col < n; col++) {
            if (this.chessBoard.isValidForQueen(row, col)) {
                this.chessBoard.placeQueen(row, col);
                this.solve(row + 1);
                this.chessBoard.removeQueen(row, col);
            }
        }

    }

    private List<String> constructBoard() {
        List<String> board = new ArrayList<>();
        for (int row = 0; row < n; row++) {
            StringBuilder sb = new StringBuilder();
            for (int col = 0; col < n; col++) {
                sb.append(this.chessBoard.getChessBoardCell(row, col));  // Use getChessBoardCell to get 'Q' or '.'
            }
            board.add(sb.toString());
        }
        return board;
    }

    public List<List<String>> getSolutions() {
        return solutions;
    }
}


class ChessBoardCell {
    private int col;
    private int row;
    boolean occupiedByQueen;

    public ChessBoardCell(int col, int row) {
        this.col = col;
        this.row = row;
    }

    public ChessBoardCell(int col, int row, boolean occupiedByQueen) {
        this.col = col;
        this.row = row;
        this.occupiedByQueen = occupiedByQueen;
    }

    public int getCol() {
        return this.col;
    }

    public int getRow() {
        return this.row;
    }

    public boolean isOccupiedByQueen() {
        return this.occupiedByQueen;
    }

}

class ChessBoard {
    private ChessBoardCell[][] chessBoard;
    private int nQueens;

    public ChessBoard(int n) {
        this.chessBoard = new ChessBoardCell[n][n];
        this.nQueens = n;
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                this.chessBoard[row][col] = new ChessBoardCell(col, row, false);
            }
        }
    }
    /*
    After taking a look back into Lines, Slopes, Diagonals, Anti-diagonals, Equations of lines
    i realized that if a line has an equation of the form y = mx + b, the slope is m, and b is a constant
    in case of a diagonal, the slope is +1, and in case of an anti-diagonal, the slope is -1.
    So, we can use the equation to deduct that for 2 different points in a cartesian plane, suppose we have 2 points
    (x1, y1) and (x2, y2), we know that y1 = m x1 + c and y2 = m x2 + c -> in case of diagonal, m = 1, and in case of anti-diagonal, m = -1.
    so we have the 2 equations y1 = x1 + c and y2 = x2 + c, so y1 - x1 = c and y2 - x2 = c. So y1 - x1 = y2 - x2
    similarly, we can deduct that for 2 different points to be on the anti diagonal, we need to have y1 + x1 = y2 + x2
    therefore, we can use these 2 equations to check if a queen can be placed on a cell or not.
    To cover both options, we can use the Absolute value and that would give us the answer for both cases.
     */

    public boolean isValidForQueen(int row, int col) {
        // Check the column for any queens placed in previous rows
        for (int i = 0; i < row; i++) {
            if (this.chessBoard[i][col].isOccupiedByQueen()) {
                return false; // A queen is already in this column
            }

            // Check the diagonals
            int queenCol = -1;
            for (int j = 0; j < chessBoard[i].length; j++) {
                if (this.chessBoard[i][j].isOccupiedByQueen()) {
                    queenCol = j; // Found a queen in row i
                    break;
                }
            }

            // If we found a queen in row i, check if it's on the diagonal
            if (queenCol != -1) {
                if (Math.abs(row - i) == Math.abs(col - queenCol)) {
                    return false; // This queen is on the diagonal
                }
            }
        }
        return true; // No conflicts, it's safe to place the queen
    }

    public boolean placeQueen(int row, int col) {
        if (this.isValidForQueen(row, col)) {
            this.chessBoard[row][col].occupiedByQueen = true;
            return true;
        }
        return false;
    }

    public boolean removeQueen(int row, int col) {
        try {
            this.chessBoard[row][col].occupiedByQueen = false;
            return true;
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
    }

    public char getChessBoardCell(int row, int col) {
        if (this.chessBoard[row][col].occupiedByQueen) return 'Q';
        return '.';
    }
}