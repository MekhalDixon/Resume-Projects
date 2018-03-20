
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Scanner;
import java.util.Hashtable;
import java.util.Set;

public class Bard {

    public static Hashtable<String, Integer> table = new Hashtable<String, Integer>();
    public static ArrayList<ArrayList<Key>> list = new ArrayList<ArrayList<Key>>();

    public static void main(String[] args) throws IOException {

        /*Exits for improper amount of arguments*/
        if (args.length < 2 || args.length > 2) {
            System.out.println("Not enough inputs. Now closing...");
            System.exit(0);
        }

        //Begins taking inputs
        Scanner in = new Scanner(new File(args[0]));
        PrintWriter out = new PrintWriter(new FileWriter(args[1]));
        Scanner text = new Scanner(new File("shakespeare.txt"));
        fillTable(text);//Fills Hash Table w/ Shakespeare.
        Set<String> keys = table.keySet();//Holds my set of keys from the table.
        for (String word : keys) {
            Key key = new Key(word, word.length(), table.get(word));//Remakes the key from the hashtable.
            addToList(list, key);
        }
//        for (int i = 0;i<list.size();i++) {//Print Statement for debugging.
//            System.out.println(list.get(i).toString());
//        }
        for (int i = 0; i < list.size(); i++) {
            ArrayList<Key> arr = list.get(i);
            Collections.sort(arr);
            Collections.sort(arr, new Key());

        }
//        for (int i = 0; i < list.size(); i++) {//Print Statement for debugging.
//            System.out.println(list.get(i).toString());
//        }

        while (in.hasNextLine()) {
            String line = in.nextLine().trim() + " ";
            String[] tokens = line.split("\\s+");
            int l = Integer.parseInt(tokens[0]);
            int k = Integer.parseInt(tokens[1]);
            out.println(find(l, k));
        }

        text.close();
        out.close();
    }

    public static void fillTable(Scanner text) {
        while (text.hasNextLine()) {//While There is a line left in the file
            String line = text.nextLine();//Takes in the 1st line,
            line = line.replaceAll("\\:", " ");
            line = line.replaceAll("\\?", " ");
            line = line.replaceAll("\\,", " ");
            line = line.replaceAll("\\.", " ");
            line = line.replaceAll("\\!", " ");
            line = line.replaceAll("\\:", " ");
            line = line.replaceAll("\\;", " ");
            line = line.replaceAll("\\[", " ");
            line = line.replaceAll("\\]", " ");
            String[] token = line.toLowerCase().trim().split("\\s+");//Break it into tokens,

            for (int i = 0; i < token.length; i++) {//And add each word(length,word) into the table.
                Key k = new Key(token[i], token[i].length(), 0);
                if (table.containsKey(k.word)) {//Checks if key already exists.
                    int val = table.get(k.word);//Gets the old freq to increment.
                    table.replace(k.word, val, val + 1);//Increments
                } else {//Else place word
                    table.put(k.word, 1);
                }
            }
        }
//        System.out.println(table);
//        System.out.println(table.size());
    }

    /*Returns proper word of (Length,Rank)*/
    public static String find(int length, int rank) {
        if (list.size() <= length) {//The word length is larger than the Outer ArrayList
            return "-";
        } else if (length == 0) {//The word length is 0
            return "-";
        } else if (list.get(length).size() <= rank) {//The rank of the word is higher than the amount of words of the length
            return "-";
        } else {
            return list.get(length).get(rank).word;//Returns word at (length,rank) in the ArrayList.
        }
    }

    public static void addToList(ArrayList<ArrayList<Key>> aList, Key word) {//adds the key to the Double ArrayList
        while (aList.size() <= word.l) {//If the DoubleArray lists has less rows than the word length.
            aList.add(new ArrayList<Key>());//Increase the size with empty lists.
        }
        //Grabs the inner list corresponding to the word length.(i.e. Grabs List 4 for word 'word')
        ArrayList<Key> CorrectList = aList.get(word.l);
        CorrectList.add(word);
    }
}
