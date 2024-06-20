//****************************************************************
// EVAN EISSLER
//
// Java Templated Doubly Linked List Class
//****************************************************************

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
		private T data;
		private DLListNode<T> prev;
		private DLListNode<T> next;

		//****************************************************************
		// Constructor
		//****************************************************************
		DLListNode(T item, DLListNode<T> p, DLListNode<T> n) {
			data = item;
			prev = p;
			next = n;
		}

		//****************************************************************
		// Get element.
		//****************************************************************
		T getElement() {
			return data;
		}

		//****************************************************************
		// Set element.
		//****************************************************************
		void setElement(T item) {
			data = item;
		}

		//****************************************************************
		// Get previous.
		//****************************************************************
		DLListNode<T> getPrev() {
			return prev;
		}
		
		//****************************************************************
		// Set previous.
		//****************************************************************
		void setPrev(DLListNode<T> node) {
			prev = node;
		}

		//****************************************************************
		// Get Next.
		//****************************************************************
		DLListNode<T> getNext() {
			return next;
		}

		//****************************************************************
		// Set Next.
		//****************************************************************
		void setNext(DLListNode<T> node) {
			next = node;
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
		// Initialize an empty list
		header = new DLListNode<>(null, null, null);
		trailer = new DLListNode<>(null, header, null);
		header.setNext(trailer);
		// Set starting current node and size
		current = null;
		size = 0;
	}

	//****************************************************************
	// Construct a list by shallow copying an existing list.
	// Set node current of the new list to the first node if the list 
	// is not empty.
	// Difference between shallow copy and deep copy:
	// www.geeksforgeeks.org/copy-python-deep-copy-shallow-copy
	//****************************************************************
	DLList(DLList<T> other) {
		// Initialize an empty list
        header = new DLListNode<>(null, null, null);
        trailer = new DLListNode<>(null, header, null);
        header.setNext(trailer);
        current = null;
        size = 0;

        // Shallow copy to the new list
        DLListNode<T> next = other.header.getNext();
        while (next != other.trailer) {
            insertLast(next.getElement());
            next = next.getNext();
        }

        // Set current to first node
        if (!isEmpty()) {
            first();
        }
	}

	//****************************************************************
	// Delete all the nodes in the list.
	//****************************************************************
	void clear() {
		// Reset list to empty by pointing header to trailer and vice versa
		header.setNext(trailer);
        trailer.setPrev(header);
        current = null;
        size = 0;
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
		// If header and trailer point to each other, it means there are no nodes in the list
		if (trailer.getPrev() == header) {
			return true;
		}
		return false;
	}

	//****************************************************************
	// Return whether node current points to the first node.
	//****************************************************************
	boolean atFirst() {
		// If the current node immediately follows header, then it must be the first node
		if (!isEmpty() && current.getPrev() == header) {
			return true;
		}
		return false;
	}

	//****************************************************************
	// Return whether node current points to the last node.
	//****************************************************************
	boolean atLast() {
		// If the current node is immediately successed by trailer, then it must be the last 
		if (!isEmpty() && current.getNext() == trailer) {
			return true;
		}
		return false;
	}

	//****************************************************************
	// Set node current to the first node, and return true.
	// Or return false if the list is empty.
	//****************************************************************
	boolean first() {
		// If list is not empty, set current node to the node immediately succeeding header
		if (!isEmpty()) {
			current = header.getNext();
			return true;
		}
		return false;
	}

	//****************************************************************
	// Set node current to the last node, and return true.
	// Or return false if the list is empty.
	//****************************************************************
	boolean last() {
		// If the list if not empty set current node to the node immediately preceeding trailer
		if (!isEmpty()) {
			current = trailer.getPrev();
			return true;
		}
		return false;
	}
	
	//****************************************************************
	// Set node current to its next node, and return true.
	// Or return false if no such node.
	//****************************************************************
	boolean next() {
		// If the next node exists, set current node to the next node
		if (current.getNext() != trailer) {
            current = current.getNext();
            return true;
        }
        return false;
	}

	//****************************************************************
	// Set node current to its previous node, and return true.
	// Or return false if no such node.
	//****************************************************************
	boolean previous() {
		// If the previous node exists, set current node to the previous node
        if (current.getPrev() != header) {
            current = current.getPrev();
            return true;
        }
        return false;
    }

	//****************************************************************
	// The index of the first real node is 0.
	// Set node current to the node at the specified index, and return
	// true.
	// Or return false if no such node.
	//****************************************************************
	boolean seek(int loc) {
		// Iterate through the linked list until the input index is reached and set current node to that node
        if (loc >= 0 && loc < size) {
            current = header.getNext();
            for (int i = 0; i < loc; i++) {
                current = current.getNext();
            }
            return true;
        }
        return false;
    }

	//****************************************************************
	// Return the content of node current.
	// Or return null if the list is empty.
	//****************************************************************
	T dataRead() {
		// If the list is not empty, call the getElement() method on the current node to return it's data
		if (!isEmpty()) {
			return current.getElement();
		}
		return null;
	}
	
	//****************************************************************
	// Rewrite the content of node current if the list is not empty.
	//****************************************************************
	void dataWrite(T item) {
		// Set the data of the current node
		if (!(isEmpty())) {
			current.setElement(item);
		}
	}
	
	//****************************************************************
	// Insert a new node to the front of the list, and set node
	// current to the new node.
	//****************************************************************
	void insertFirst(T item) {
		// Create a new node that points to header and the current successor of header
		DLListNode<T> node = new DLListNode<>(item, header, header.getNext());
		// Set header and the previous successor of header to point to the new node
        header.getNext().setPrev(node);
        header.setNext(node);
        current = node;
        size++;
	}

	//****************************************************************
	// Insert a new node to the end of the list, and set node current
	// to the new node.
	//****************************************************************
	void insertLast(T item) {
		// Create a new node that points to trailer and the current predecessor of trailer
		DLListNode<T> node = new DLListNode<>(item, trailer.getPrev(), trailer);
		// Set trailer and the previous predecessor of trailer to point to the new node
        trailer.getPrev().setNext(node);
        trailer.setPrev(node);
        current = node;
        size++;
	}
    
	//****************************************************************
	// Insert a new node before where node current points to if the
	// list is not empty; or insert a new node if the list is empty.
	// Set node current to the new node.
	//****************************************************************
	void insertAtCurrent(T item) {
		// Create a new node that points to current and the predecessor of current
		DLListNode<T> node = new DLListNode<>(item, current.getPrev(), current);
		// Set current and the previous predeccessor of current to point to new node
        current.getPrev().setNext(node);
        current.setPrev(node);
        current = node;
        size++;
	}

	//****************************************************************
	// Delete the first node in the list if the list is not empty.
	// Set node current to the new first node; or set node current to
	// null if the list becomes empty.
	//****************************************************************
	void deleteFirst() {
		if (!isEmpty()) {
			// Check if there's only one node in the list, if so set list to empty
			if (header.getNext() == trailer.getPrev()) {
				current = null;
				trailer.setPrev(header);
				header.setNext(trailer);
			} else {
				// Point header to successor of first and vice verse
				current = header.getNext().getNext();
				current.setPrev(header);
				header.setNext(current);
			}
			size--;
		}
	}

	//****************************************************************
	// Delete the last node in the list if the list is not empty.
	// Set node current to the new last node; or set node current to
	// null if the list becomes empty.
	//****************************************************************
	void deleteLast() {
		if (!isEmpty()) {
			// Check if there's only one node in the list, if so set list to empty
			if (header.getNext() == trailer.getPrev()) {
				current = null;
				trailer.setPrev(header);
				header.setNext(trailer);
			} else {
				// Point trailer to predecessor of last and vice versa
				current = trailer.getPrev().getPrev();
				current.setNext(trailer);
				trailer.setPrev(current);
			}
			size--;
		}
	}

	//****************************************************************
	// Delete the node where node current points to if the list is not
	// empty.
	// Set node current to its next node; or set node current to its
	// previous node if node current was at the end of the list; or
	// set node current to null if the list becomes empty.
	//****************************************************************
	void deleteAtCurrent() {
		if (!isEmpty() && current != null) {
			// Get successor and predecessor of the node being deleted and point them to each other
            DLListNode<T> prevNode = current.getPrev();
            DLListNode<T> nextNode = current.getNext();
            prevNode.setNext(nextNode);
            nextNode.setPrev(prevNode);
			// If deleted node was at the end of the list, set current to prevous, if not then set to next
            current = nextNode != trailer ? nextNode : prevNode;
            size--;
        }
	}
}