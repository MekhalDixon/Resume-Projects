

public class Chesspiece {

    boolean color;//True = White; False = Black;
    int col;
    int row;
    char type;

    public Chesspiece(boolean c, int x, int y, char type) {
        color = c;
        this.col = x;
        this.row = y;
        this.type = type;
    }

    public boolean attacks(Chesspiece c) {
        return true;
    }

    public boolean safeMove(LinkedList l, int col, int row) {
        if (l.find(this.col, this.row) == null) {//Base Case: Piece Not Found!
            return false;
        }
        return true;
    }
}
