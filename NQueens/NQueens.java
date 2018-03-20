
/**
 *
 * @author Clifton(Mekhal) Dixon-Ferrell
 */
import java.io.*;
import java.util.Scanner;
import java.util.Stack;

public class NQueens {

    public static int size;//Board Size
    public static Stack<Queen> queens;//Stack of queens

    public static void main(String[] args) throws IOException {
        queens = new Stack<>();

        /*Exits for improper amount of arguments*/
        if (args.length < 2 || args.length > 2) {
            System.out.println("Not enough inputs. Now closing...");
            System.exit(0);
        }

        //Begins taking inputs
        Scanner in = new Scanner(new File(args[0]));
        PrintWriter out = new PrintWriter(new FileWriter(args[1]));
        while (in.hasNextLine()) {
            int inputQ = 0;
            String line = in.nextLine().trim() + " ";
            String[] token = line.split("\\s+");            
            size = Integer.parseInt(token[0]);
            for (int i = 1; i < token.length; i += 2) {//Loop that adds in Queens
                int col = Integer.parseInt(token[i]);
                int row = Integer.parseInt(token[i + 1]);
                Queen q = new Queen(col, row);
                queens.push(q);
                inputQ++;
            }
            /*System.out.print(size+" ");
            for (int i = 0; i < queens.size(); i++) {
                System.out.print(queens.get(i).col+" "+queens.get(i).row+" ");
            }
            System.out.println(inputQ);*/
            if (!nQueens(size - inputQ, inputQ)) {
                System.out.println("No solution");
                out.println("No solution");
            } else {
                printQueens(out);
                out.println();
            }
            queens.removeAllElements();
        }
        in.close();
        out.close();
    }

    /*Bulk of Method*/
    private static boolean nQueens(int n, int inputQ) {
        if (!InputConflict()) {//InputQueens attack eachother.
            return false;
        }
        while (n > inputQ && n <= size) {
            boolean freeCol;
            int col = 1, row = 1;
            int lastpush = 1;
            for (; col <= size; col++) {//Col iterator
                freeCol = true;
                for (int i = 0; i < queens.size(); i++) {//Iterats from 0th to Last queen in qList
                    if (queens.get(i).col == col) {//Col isn't free if i.col==col.
                        freeCol = false;
                        break;
                    }
                }
                if (freeCol == false) {//Iterates col if column isn't free.                    
                    continue;
                }
                //System.out.println(col);
                for (; row <= size; row++) {//Row iterator
                    Queen next = new Queen(col, row);
                    boolean freeSpot = true;
                    for (int i = 0; i < queens.size(); i++) {//Checks if queen gets attacked.
                        if (queens.get(i).attacks(next)) {
                            freeSpot = false;
                            // System.out.println(queens.get(i).col+" "+queens.get(i).row+" attacks "+next.col+" "+next.row);
                            break;
                        }
                    }
                    if (freeSpot) {
                        //System.out.println("pushed! " + next.col + " " + next.row);
                        queens.push(next);
                        n--;
                        break;
                    }
                }
                if (row > size) {//If there's no free room in the column,                    
                    if (queens.size() == inputQ) {//If the only queens left in the stack are the input,
                        return false;//Return 'No Solution'...
                    }
                    Queen dummy = queens.pop();//remove the last queen from the stack
                    n++;
                    col = dummy.col - 1;
                    row = dummy.row + 1;
                    //System.out.println("popped! " + dummy.col + " " + dummy.row);
                    continue;
                }
                col = 0;
                row = 1;
            }
            if (queens.size() == size) {
                return true;
            }
        }
        //printQueens();
        return false;
    }

    /*Utitlity methods begin here:*/
    private static void printQueens(PrintWriter out) {//Prints Stack
        for (int i = 1; i <= size; i++) {
            for (int j = 0; j < queens.size(); j++) {
                if (queens.get(j).col == i) {
                    out.print(queens.get(j).col + " " + queens.get(j).row + " ");
                    System.out.print(queens.get(j).col + " " + queens.get(j).row + " ");
                }
            }
        }
        System.out.println();
    }

    private static boolean InputConflict() {//Checks if the queens in input stack attack eachother.
        for (int i = 0; i < queens.size(); i++) {
            for (int j = i + 1; j < queens.size(); j++) {
                if (queens.get(i).attacks(queens.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
