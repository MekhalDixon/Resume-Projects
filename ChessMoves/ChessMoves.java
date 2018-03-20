

import java.io.*;
import java.util.Scanner;

/* @author Mekhal Dixon-Ferrell */
public class ChessMoves {

    public static void main(String[] args) throws IOException {

        /*Exits for improper amount of arguments*/
        if (args.length < 2 || args.length > 2) {
            System.out.println("Not enough inputs. Now closing...");
            System.exit(0);
        }

        //Begins taking inputs
        Scanner in = new Scanner(new File(args[0]));
        PrintWriter out = new PrintWriter(new FileWriter(args[1]));

        while (in.hasNextLine()) {

            LinkedList l = new LinkedList();
            String[] lines = in.nextLine().split(":");//Splits Strings by colon.
            String line1 = lines[0].trim() + " ";//Line for creating board.            
            String[] token1 = line1.split("\\s+");//String array with LinkedList info.
            fill(token1, l);

            String line2 = lines[1].trim() + " ";//Line for moves on the board.
            String[] token2 = line2.split("\\s+");//String array with ChessMove info

            boolean legal = false;//True == Legal Move.
            boolean alternator = true;//True==WhiteMoves,False==BlackMoves.

            for (int i = 0; i < token2.length; i += 4) {
                Chesspiece c = null;
                int col = Integer.parseInt(token2[i]);
                int row = Integer.parseInt(token2[i + 1]);
                int dCol = Integer.parseInt(token2[i + 2]);//Holds col of desired square.
                int dRow = Integer.parseInt(token2[i + 3]);//Holds row of desired square.

                if (l.find(col, row) != null) {//Piece found!
                    c = l.find(col, row).piece;
                } else {//Piece Not Found!
                    System.out.println(col + " " + row + " " + dCol + " " + dRow + " illegal");
                    out.println(col + " " + row + " " + dCol + " " + dRow + " illegal");
                    legal = false;
                    break;
                }

                if (c.color != alternator) {//It is not 'x' Player's turn!
                    System.out.println(col + " " + row + " " + dCol + " " + dRow + " illegal");
                    out.println(col + " " + row + " " + dCol + " " + dRow + " illegal");
                    legal = false;
                    break;
                }

                if (!c.safeMove(l, dCol, dRow)) {//Checks for pieces in between c and desired square.
                    System.out.println(c.col + " " + c.row + " " + dCol + " " + dRow + " illegal");
                    out.println(c.col + " " + c.row + " " + dCol + " " + dRow + " illegal");
                    legal = false;
                    break;
                } else {
                    if (l.find(dCol, dRow) == null) {
                        //No piece at desired square!
                        legal = true;
                        c.col = dCol;
                        c.row = dRow;
                    } //There's a piece at (col, row) to be captured by c.
                    else if (l.find(dCol, dRow).piece != null && l.find(dCol, dRow).piece.color != c.color) {
                        l.delete(l.find(dCol, dRow).piece);//Piece Captured!                        
                        c.col = dCol;
                        c.row = dRow;
                        legal = true;
                    } else {
                        //There's a piece at(col,row) of the same color as c.
                        System.out.println(c.col + " " + c.row + " " + dCol + " " + dRow + " illegal");
                        out.println(c.col + " " + c.row + " " + dCol + " " + dRow + " illegal");
                        legal = false;
                        break;
                    }
                }
                Chesspiece king = l.findKing(l, alternator);               
                if (king != null && l.inCheck(l, king)) {//Player's king in check at end of move!
                     System.out.println(col + " " + row + " " + dCol + " " + dRow + " illegal");
                        out.println(col + " " + row + " " + dCol + " " + dRow + " illegal");
                        
                        legal = false;
                        break;
                }
                alternator =!alternator;//New Turn, Change Colors
            }
            if (legal == true) {
                System.out.println("legal");
                out.println("legal");
            }
        }
        in.close();
        out.close();
    }

    /*Fills the linked list with each object from the input line*/
    public static void fill(String[] token, LinkedList l) {
        for (int i = 0; i < token.length; i += 3) {
            switch (token[i]) {
                case "Q": {
                    Queen a = new Queen(false, Integer.parseInt(token[i + 1]), Integer.parseInt(token[i + 2]), 'Q');
                    l.add(a);
                    break;
                }
                case "q": {
                    Queen a = new Queen(true, Integer.parseInt(token[i + 1]), Integer.parseInt(token[i + 2]), 'q');
                    l.add(a);
                    break;
                }
                case "K": {
                    King a = new King(false, Integer.parseInt(token[i + 1]), Integer.parseInt(token[i + 2]), 'K');
                    l.add(a);
                    break;
                }
                case "k": {
                    King a = new King(true, Integer.parseInt(token[i + 1]), Integer.parseInt(token[i + 2]), 'k');
                    l.add(a);
                    break;
                }
                case "N": {
                    Knight a = new Knight(false, Integer.parseInt(token[i + 1]), Integer.parseInt(token[i + 2]), 'N');
                    l.add(a);
                    break;
                }
                case "n": {
                    Knight a = new Knight(true, Integer.parseInt(token[i + 1]), Integer.parseInt(token[i + 2]), 'n');
                    l.add(a);
                    break;
                }
                case "R": {
                    Rook a = new Rook(false, Integer.parseInt(token[i + 1]), Integer.parseInt(token[i + 2]), 'R');
                    l.add(a);
                    break;
                }
                case "r": {
                    Rook a = new Rook(true, Integer.parseInt(token[i + 1]), Integer.parseInt(token[i + 2]), 'r');
                    l.add(a);
                    break;
                }
                case "B": {
                    Bishop a = new Bishop(false, Integer.parseInt(token[i + 1]), Integer.parseInt(token[i + 2]), 'B');
                    l.add(a);
                    break;
                }
                case "b": {
                    Bishop a = new Bishop(true, Integer.parseInt(token[i + 1]), Integer.parseInt(token[i + 2]), 'b');
                    l.add(a);
                    break;
                }
                case "P": {
                    bPawn a = new bPawn(false, Integer.parseInt(token[i + 1]), Integer.parseInt(token[i + 2]), 'P');
                    l.add(a);
                    break;
                }
                case "p": {
                    wPawn a = new wPawn(true, Integer.parseInt(token[i + 1]), Integer.parseInt(token[i + 2]), 'p');
                    l.add(a);
                    break;
                }
            }
        }
    }

    public static void pArr(String[] t) {
        for (int i = 0; i < t.length; i++) {
            System.out.print(t[i] + " ");
        }
        System.out.println("");
    }

}
