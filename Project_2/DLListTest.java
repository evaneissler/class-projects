public class DLListTest {

    public static void main(String[] args) {
        // Create a new DLList of integers
        DLList<Integer> myList = new DLList<>();

        // Test insertFirst and insertLast methods
        myList.insertFirst(2);
        myList.insertLast(6);
        myList.insertAtCurrent(4);
        myList.last();
        myList.previous();
        myList.previous();
        myList.next();
        myList.next();
        myList.next();

        DLList<Integer> myListCopy = new DLList<>(myList);
        myListCopy.next();
        myListCopy.dataWrite(17);
        myListCopy.insertAtCurrent(28);
        myListCopy.clear();
        myListCopy.insertFirst(2);
        myListCopy.insertFirst(1);
        //myListCopy.last();
        myListCopy.deleteAtCurrent();
        System.out.println(myListCopy.dataRead());
    }
}
