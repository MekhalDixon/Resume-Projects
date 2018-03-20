
public class Rook extends Chesspiece {

    public Rook(boolean color, int col, int row, char type) {
        super(color, col, row, type);
    }

    public boolean attacks(Chesspiece c) {
        if (row == c.row || col == c.col) {
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
        
        //Base Case: Rooks don't move that way!
        if (row != r && col != c) {
            return false;
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
        //All Moves Covered. No Blocking Pieces!
        return true;
    }
}
