//Bishop Class


public class Bishop extends Chesspiece {

    public Bishop(boolean c, int col, int row, char type) {
        super(c, col, row, type);
    }

    public boolean attacks(Chesspiece c) {
        if (Math.abs(row - c.row) == Math.abs(col - c.col)) {//Diagonal check
            if (color != c.color) {
                return true;
            }
        }
        return false;
    }

    /*Checks for any pieces blocking a move to square (c,r)*/
    public boolean safeMove(LinkedList l, int c, int r) {
        //Base Case: Bishop's don't move that way!\\
        if (Math.abs(row - r) != Math.abs(col - c)) {
            return false;
        }
        if (l.find(col, row) == null) {//Base Case: Piece Not Found!
            return false;
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
