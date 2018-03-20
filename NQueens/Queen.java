/*Queen Class:
-Holds ChessBoard coordinates for Queen
-Checks if it is under attack from enemy Queens.
*/
public class Queen {

    public int col;
    public int row;

    public Queen(int c, int r) {//Constructor: sets (x,y) coordinates to col,row
        col = c;
        row = r;
    }

    public boolean attacks(Queen q) {//Checks if queen gets attacked

        if (row == q.row || col == q.col) {//Horizontal and Vertical check
            return true;
        } else if (Math.abs(row - q.row) == Math.abs(col - q.col)) {//Diagonal check
            return true;
        }
        return false;//Queen goes un-attacked!
    }

}