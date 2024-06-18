package Project_5;

//****************************************************************
//
// DLList Class
//
//****************************************************************
class DLList<T> {

    //****************************************************************
    //
    // DLListNode nested class
    //
    //****************************************************************
    private static class DLListNode<T> {
        //****************************************************************
        // DLList node private data members
        //****************************************************************
        T data;
        DLListNode<T> prev;
        DLListNode<T> next;

        //****************************************************************
        // Constructor
        //****************************************************************
        DLListNode(T item, DLListNode<T> p, DLListNode<T> n) {
            data = item;
            prev = p;
            next = n;
        }
    }

    //****************************************************************
    // DLList private data members
    //****************************************************************
    private DLListNode<T> header;
    private DLListNode<T> trailer;
    //****************************************************************
    // !!!IMPORTANT!!!
    // Node current acts as an iterator.
    // We define that a node with non-null data field is a real node.
    // Node current should always point to a real node if there is
    // at least one real node in the list.
    // Otherwise, node current is null.
    //****************************************************************
    private DLListNode<T> current;
    private int size = 0;

    //****************************************************************
    // Default constructor
    //****************************************************************
    DLList() {
        header = new DLListNode<>(null, null, null);
        trailer = new DLListNode<>(null, header, null);
        header.next = trailer;
    }

    //****************************************************************
    // Construct a list by shallow copying an existing list.
    // Set node current of the new list to the first node if the list
    // is not empty.
    // Difference between shallow copy and deep copy:
    // www.geeksforgeeks.org/copy-python-deep-copy-shallow-copy
    //****************************************************************
    DLList(DLList<T> other) {
        this();
        DLListNode<T> node = other.header.next;
        while(node != other.trailer) {
            insertLast(node.data);
            node = node.next;
        }
        first();
    }

    //****************************************************************
    // Delete all the nodes in the list.
    //****************************************************************
    void clear() {
        header.next = trailer;
        trailer.prev = header;
        size = 0;
        current = null;
    }

    //****************************************************************
    // Return the number of nodes in the list.
    //****************************************************************
    int size() {
        return size;
    }

    //****************************************************************
    // Return whether the list is empty.
    //****************************************************************
    boolean isEmpty() {
        return size == 0;
    }

    //****************************************************************
    // Return whether node current points to the first node.
    //****************************************************************
    boolean atFirst() {
        return current == header.next;
    }

    //****************************************************************
    // Return whether node current points to the last node.
    //****************************************************************
    boolean atLast() {
        return current == trailer.prev;
    }

    //****************************************************************
    // Set node current to the first node, and return true.
    // Or return false if the list is empty.
    //****************************************************************
    boolean first() {
        if(size == 0) return false;
        current = header.next;
        return true;
    }

    //****************************************************************
    // Set node current to the last node, and return true.
    // Or return false if the list is empty.
    //****************************************************************
    boolean last() {
        if(size == 0) return false;
        current = trailer.prev;
        return true;
    }

    //****************************************************************
    // Set node current to its next node, and return true.
    // Or return false if no such node.
    //****************************************************************
    boolean next() {
        if(size == 0 || current.next == trailer) return false;
        current = current.next;
        return true;
    }

    //****************************************************************
    // Set node current to its previous node, and return true.
    // Or return false if no such node.
    //****************************************************************
    boolean previous() {
        if(size == 0 || current.prev == header) return false;
        current = current.prev;
        return true;
    }

    //****************************************************************
    // The index of the first real node is 0.
    // Set node current to the node at the specified index, and return
    // true.
    // Or return false if no such node.
    //****************************************************************
    boolean seek(int loc) {
        if(loc < 0 || loc >= size) return false;
        first();
        int index = 0;
        while(index < loc) {
            next();
            index++;
        }
        return true;
    }

    //****************************************************************
    // Return the content of node current.
    // Or return null if the list is empty.
    //****************************************************************
    T dataRead() {
        if(size == 0) return null;
        return current.data;
    }

    //****************************************************************
    // Rewrite the content of node current if the list is not empty.
    //****************************************************************
    void dataWrite(T item) {
        if(size != 0) current.data = item;
    }

    //****************************************************************
    // Insert a new node to the front of the list, and set node
    // current to the new node.
    //****************************************************************
    void insertFirst(T item) {
        current = header.next;
        insertAtCurrent(item);
    }

    //****************************************************************
    // Insert a new node to the end of the list, and set node current
    // to the new node.
    //****************************************************************
    void insertLast(T item) {
        current = trailer;
        insertAtCurrent(item);
    }

    //****************************************************************
    // Insert a new node before where node current points to if the
    // list is not empty; or insert a new node if the list is empty.
    // Set node current to the new node.
    //****************************************************************
    void insertAtCurrent(T item) {
        if(size == 0) current = trailer;
        DLListNode<T> node = new DLListNode<>(item, current.prev, current);
        current.prev.next = node;
        current.prev = node;
        size++;
        current = node;
    }

    //****************************************************************
    // Delete the first node in the list if the list is not empty.
    // Set node current to the new first node; or set node current to
    // null if the list becomes empty.
    //****************************************************************
    void deleteFirst() {
        current = header.next;
        deleteAtCurrent();
    }

    //****************************************************************
    // Delete the last node in the list if the list is not empty.
    // Set node current to the new last node; or set node current to
    // null if the list becomes empty.
    //****************************************************************
    void deleteLast() {
        current = trailer.prev;
        deleteAtCurrent();
    }

    //****************************************************************
    // Delete the node where node current points to if the list is not
    // empty.
    // Set node current to its next node; or set node current to its
    // previous node if node current was at the end of the list; or
    // set node current to null if the list becomes empty.
    //****************************************************************
    void deleteAtCurrent() {
        if(size == 0) return;
        DLListNode<T> predecessor = current.prev;
        DLListNode<T> successor = current.next;
        predecessor.next = successor;
        successor.prev = predecessor;
        size--;
        if(successor != trailer) current = successor;
        else if(size != 0) current = predecessor;
        else current = null;
    }
}
