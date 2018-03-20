

public class Knight extends Chesspiece {

    public Knight(boolean c, int x, int y, char type) {
        super(c, x, y, type);
    }

    public boolean attacks(Chesspiece c) {
        if (c.row == row + 2 && c.col == col - 1)//UP 2,LEFT 1
        {
            if (color != c.color) {
                return true;
            }
        }
        if (c.row == row + 2 && c.col == col + 1)//UP 2, RIGHT 1
        {
            if (color != c.color) {
                return true;
            }
        }
        if (c.row == row + 1 && c.col == col - 2)//UP 1 , LEFT 2
        {
            if (color != c.color) {
                return true;
            }
        }
        if (c.row == row + 1 && c.col == col + 2)//UP 1, RIGHT 2
        {
            if (color != c.color) {
                return true;
            }
        }
        if (c.row == row - 2 && c.col == col + 1)// DOWN 2, RIGHT 1
        {
            if (color != c.color) {
                return true;
            }
        }
        if (c.row == row - 2 && c.col == col - 1)// DOWN 2, LEFT 1
        {
            if (color != c.color) {
                return true;
            }
        }
        if (c.row == row - 1 && c.col == col - 2)// DOWN 1, LEFT 2
        {
            if (color != c.color) {
                return true;
            }
        }
        if (c.row == row - 2 && c.col == col + 2)// DOWN 1, RIGHT 2
        {
            if (color != c.color) {
                return true;
            }
        }
        return false;
    }

    public boolean safeMove(LinkedList l, int c, int r) {
        if (l.find(col, row) == null) {//Base Case: Piece Not Found!
            return false;
        } else if (l.find(c, r) != null && l.find(c, r).piece.color == color) {//Same Team!
            return false;
        } else if (Math.abs(row - r) > 2 || Math.abs(col - c) > 2) {//Knights dont move that way!
            return false;
        } else if (Math.abs(col - c) < 1 || Math.abs(row - r) < 1) {//Knights dont move that way!
            return false;
        } else if (Math.abs(col - c) == Math.abs(row - r)) {//Knights dont move that way!
            return false;
        }        
        return true;
    }
}
