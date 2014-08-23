/**
 *
 * @author Dor
 */

public class List<E> {

    // Node is accessible inside the class, but not outside.

    private class Node {
        public E data;
        public Node next;

        public Node(E data, Node next) {
            this.data = data;
            this.next = next;
        }

        public Node(E data) {
            this.data = data;
            this.next = null;
        }
    }

    // Member Variables

    private Node first;
    private int size;
    private Node cursor;
    private Node previous;

    // Constructor

    public List() {
        first = null;
        size = 0;
    }

    // ----------
    // size -- return the size of the list
    // Inputs: none
    // Output: int

    public int size() {
        return size; // an easy bug
    }

    // ----------
    // insert -- place an element at the front of the list
    // Inputs: E data
    // Output: none

    public void insert(E data) {
       first = new Node(data,first);
       size++;
    }

    // ----------
    // find -- find an element in the list
    // Inputs: E dat

    // Output: int -- the location of the element in the list.
    //         0 = not found
    //         1 = first element, etc.
    public int find(E data)
    {
        int counter = 0;
        previous = null;
        cursor = first;
        while(cursor != null) {
           counter++;
            if(cursor.data.equals(data)) {
                return counter;
           }
           else {
                previous = cursor;
                cursor = cursor.next;
           }
        }

        return 0;
    }


    // ----------
    // insertAtEnd -- place an element at the end of the list
    // Inputs: E data
    // Output: none
    public void insertAtEnd(E data)
    {
        Node front = first;

        //find the front:
        while(front != null && front.next != null)
            front=front.next;

        //change front if empty or set next to a new node
        if (front != null)
            front.next = new Node(data);
        else
            first = new Node(data);

        //increase list size counter
        size++;
    }


    // ----------
    // delete -- delete an element from the list.
    // Inputs: int -- the location of the element to be deleted.
    // Output: none
    public void delete(int index) {
        //not zero based
        if (index > 0 && index <= size)
        {
            //find the node at the index
            previous = null;
            cursor = first;
            int currIndex = 1;
            while (cursor != null && currIndex <= index)
            {
                if (currIndex == index)
                {
                    //delete by connecting next and previous
                    if (previous != null)
                        previous.next = cursor.next;
                    else
                        first = cursor.next;

                    size--;
                    cursor = null;
                }
                else
                {
                    previous = cursor;
                    cursor = cursor.next;
                    currIndex++;
                }

            }
        }
    }

    // ----------
    // reverse -- reverse the list
    // Inputs: none
    // Output: none
    public void reverse()
    {
        cursor = first;
        first = null;
        while (cursor != null)
        {

            Node tmp = cursor;

            //advance one
            cursor = cursor.next;

            //reconncet first
            tmp.next = first;
            first = tmp;

        }
    }
    


}

