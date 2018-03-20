
//Class for LinkedList

public class LinkedList {

    Node head;

    void add(Chesspiece v) {/*adds a node to top of the list*/
        Node n = new Node(v);//Makes new node            
        n.next = head;
        head = n;
    }

    Node delete(Chesspiece v) {/*finds and deletes node with 'v' piece*/
        if (head == null) {//base case 1: Empty List
            return null;
        }
        if (head.piece == v) {//base case 2: Delete Head
            head = head.next;
            return head;
        }
        Node curr = head;
        Node prev = null;
        while (curr != null) {//Iterates through list to find Node 'v'
            if (v == curr.piece) {
                prev.next = curr.next;//Skips over Node Curr.
                return curr;
            }
            prev = curr;
            curr = curr.next;
        }
        return null;//Code has reached List End
    }

    Node find(int col, int row) {/*Finds node with value v and returns true if it exists*/
        Node curr = head;
        while (curr != null) {//Iterates through list to find Node 'v'
            if (col == curr.piece.col && row == curr.piece.row) {
                return curr;
            } else {
                curr = curr.next;
            }
        }
        return null;
    }

    void pList() {/*Prints List. Mostly in place for checking problems.*/
        Node curr = head;
        for (int i = 0; curr != null; i++) {
            System.out.println(curr.piece.type + " " + curr.piece.col + " " + curr.piece.row);
            curr = curr.next;
        }
    }

    /*Properly checks if two pieces occupy the same spot in a LinkedList */
    boolean validity() {
        Node n1 = head; //Original Node, is checked against by n2            
        while (n1 != null) {
            Node n2 = n1.next;//Checks each node against n1
            while (n2 != null) {
                if (n1.piece.col == n2.piece.col && n1.piece.row == n2.piece.row) {
                    return false;//2 Pieces found with matching (x,y).
                } else {
                    n2 = n2.next;
                }
            }
            n1 = n1.next;
        }
        return true; //No piece in the list has a matching (x,y).
    }

    /*Checks if king comes under attack by any piece on the board*/
    boolean inCheck(LinkedList l, Chesspiece c) {
        Node curr = l.head;
        while (curr != null) {
            if (curr.piece.attacks(c) == true && curr.piece.safeMove(l, c.col, c.row)) {
                //System.out.println(curr.piece.col + " " + curr.piece.row);
                //System.out.println(c.col + " " + c.row);
                return true;
            }
            curr = curr.next;
        }
        return false;
    }

    //Returns king of respective color
    Chesspiece findKing(LinkedList l, boolean color) {
        Node curr = l.head;
        if (color == true) {
            while (curr != null) {
                if (curr.piece.type == 'k') {
                    return curr.piece;
                }
                curr = curr.next;
            }
        }
        if (color == false) {
            while (curr != null) {
                if (curr.piece.type == 'K') {
                    return curr.piece;
                }
                curr = curr.next;
            }
        }
        return null;
    }
}
