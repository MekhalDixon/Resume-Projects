

public class wPawn extends Chesspiece {

    public wPawn(boolean c, int col, int row, char type) {
        super(c, col, row, type);
    }

    public boolean attacks(Chesspiece c) {
        if (c.row == row + 1 && c.col == col - 1)//UP 1,LEFT 1
        {
            if (color != c.color) {
                return true;
            }
        }
        if (c.row == row + 1 && c.col == col + 1)//UP 1,RIGHT 1
        {
            if (color != c.color) {
                return true;
            }
        }
        return false;
    }

    public boolean safeMove(LinkedList l, int c, int r) {
        if (l.find(col, row) == null) {//Base Case: Piece Not Found!
            System.out.println("aaa");
            return false;
        }
        if (r == row + 2 && row == 2 && c == col) {//Black Pawn is at start and can move two spaces.
            if (l.find(col, row + 1) == null && l.find(c, r) == null)//Is Path Safe?
            {
                return true;//Path Safe!
            } else {
                System.out.println("a1");
                return false;//Path Blocked!
            }
        }
        if (r != row + 1) {//Base Case: White Pawns don't move that way!
            return false;
        }
        if (Math.abs(col - c) == 1 && r == row + 1) {//There's a piece to attack
            if ( l.find(c, r) != null && l.find(c, r).piece.color != color) {
                return true;
            } else {
                return false;
            }
        }
        if (l.find(c, r) != null) {
            if (l.find(c, r).piece.color == color && Math.abs(c - col) == 1) {//Base Case: Don't attack the same color!
                System.out.println("a2");
                return false;
            }
            if (l.find(c, r).piece.color != color && col == c) {//Base Case: Blocked!
                System.out.println("a3");
                return false;
            }
        }

        if (Math.abs(c - col) > 1) {//Base Case: No Pawns move that way!
            System.out.println("a4");
            return false;
        }
        return true;
    }
}
