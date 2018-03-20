
import java.util.*;
//ADT: Key

public class Key implements Comparator<Key>, Comparable<Key> {

    String word;
    int freq = 0;//frequency
    int l;//length

    public Key(String word, int length, int frequency) {
        this.word = word;
        l = length;
        freq = frequency;
    }

    Key() {

    }

    public String toString() {
        return word;
    }

    public int compare(Key k1, Key k2) {//Compares Frequency
        return k2.freq - k1.freq;
    }

    public int compareTo(Key k) {//Compares Word Length
        return this.word.compareTo(k.word);
    }
}
