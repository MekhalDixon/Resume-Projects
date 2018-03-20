

public class Queen extends Chesspiece {

    public Queen(boolean color, int col, int row, char type) {

        super(color, col, row, type);
        //this.type = type;
    }

    public boolean attacks(Chesspiece c) {
        if (row == c.row || col == c.col) {//Horizontal and Vertical check                           
            if (color != c.color) {
                return true;
            }
        } else if (Math.abs(row - c.row) == Math.abs(col - c.col)) {//Diagonal check
            if (color != c.color) {
                return true;
            }
        }
        return false;
    }

    public boolean safeMove(LinkedList l, int c, int r) {

        if (l.find(col, row) == null) {//Base Case: Piece Not Found!
            return false;
        }

        //Base Case: Queens Don't Move That Way!\\
        if (Math.abs(row - r) != Math.abs(col - c)) {
            if (row != r && col != c) {
                return false;
            }
        }

        //Desired Square(c,r) is above the piece
        if (c == col && r > row) {
            for (int i = row + 1; i < r; i++) {
                if (l.find(col, i) != null) {//Piece found blocking!
                    return false;
                }
            }
        }
        //Desired Square(c,r) is below the piece
        if (c == col && r < row) {
            for (int i = row - 1; i > r; i--) {
                if (l.find(col, i) != null) {//Piece found blocking!
                    return false;
                }
            }
        }
        //Desired Square(c,r) is left of the piece
        if (c < col && r == row) {
            for (int i = col - 1; i > c; i--) {
                if (l.find(i, row) != null) {//Piece found blocking!
                    return false;
                }
            }
        }
        //Desired Square(c,r) is right of the piece
        if (c > col && r == row) {
            for (int i = col + 1; i < c; i++) {
                if (l.find(i, row) != null) {//Piece found blocking!
                    return false;
                }
            }
        }

        //Desired Square(c,r) is up and to the right of the piece
        if (c > col && r > row) {
            for (int i = col + 1, j = row + 1; i < c && j < r; i++, j++) {
                if (l.find(i, j) != null) {//Translation: if a piece is found at (i,j)
                    return false;
                }
            }
        }

        //Desired Square(c,r) is up and to the left of the piece
        if (c < col && r > row) {
            for (int i = col - 1, j = row + 1; i > c && j < r; i--, j++) {
                if (l.find(i, j) != null) {//Translation: if a piece is found at (i,j)
                    return false;
                }
            }
        }

        //Desired Square(c,r) is down and to the left of the piece
        if (c < col && r < row) {
            for (int i = col - 1, j = row - 1; i > c && j > r; i--, j--) {
                if (l.find(i, j) != null) {//Translation: if a piece is found at (i,j)
                    return false;
                }
            }
        }
        //Desired Square(c,r) is down and to the right of the piece
        if (c > col && r < row) {
            for (int i = col + 1, j = row - 1; i < c && j > r; i++, j--) {
                if (l.find(i, j) != null) {//Translation: if a piece is found at (i,j)
                    return false;
                }
            }
        }
        //All Movement covered. No Blocking Pieces
        return true;
    }
}
