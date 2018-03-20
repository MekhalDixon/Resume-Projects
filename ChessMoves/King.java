

public class King extends Chesspiece {

    public King(boolean c, int x, int y, char type) {
        super(c, x, y, type);
    }

    public boolean attacks(Chesspiece c) {

        if (c.row == row + 1 && c.col == col - 1)//UP 1,LEFT 1
        {
            if (c.color != color) {
                System.out.println("1");
                return true;
            }
        }

        if (c.row == row + 1 && c.col == col)//UP 1
        {
            if (c.color != color) {
                System.out.println("2");
                return true;
            }
        }
        if (c.row == row + 1 && c.col == col + 1)//UP 1,RIGHT 1
        {
            if (c.color != color) {
                System.out.println("3");
                return true;
            }
        }
        if (c.row == row && c.col == col - 1)//LEFT 1
        {
            if (c.color != color) {
                System.out.println("4");
                return true;
            }
        }
        if (c.row == row + 1 && c.col == col)//UP 1
        {
            if (c.color != color) {
                System.out.println("5");
                return true;
            }
        }
        if (c.row == row && c.col == col + 1)//RIGHT 1
        {
            if (c.color != color) {
                System.out.println("6");
                return true;
            }
        }
        if (c.row == row - 1 && c.col == col - 1)//DOWN 1,LEFT 1
        {
            if (c.color != color) {
                System.out.println("7");
                return true;
            }
        }
        if (c.row == row - 1 && c.col == col + 1)//DOWN 1,RIGHT 1
        {
            if (c.color != color) {
                System.out.println("8");
                return true;
            }
        }
        if (c.row == row - 1 && c.col == col)//DOWN 1
        {
            if (c.color != color) {
                System.out.println("9");
                return true;
            }
        }
        return false;
    }

    public boolean safeMove(LinkedList l, int c, int r) {
        //Base Case: King's don't move that way!\\
        if (Math.abs(c - col) > 1 || Math.abs(r - row) > 1) {
            return false;
        }
        if (l.find(col, row) == null) {//Base Case: Piece Not Found!
            return false;
        } else if (l.find(c, r) != null && l.find(c, r).piece.color == this.color)//Team Piece in the way
        {
            return false;
        }
        if (l.find(c, r) != null) {
            l.delete(l.find(c, r).piece);
        }
        
        return true;
    }
}
