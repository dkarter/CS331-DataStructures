// $Id: List.java 76 2005-10-10 11:24:51Z beckman $

public class List<E extends Comparable<E>> {

    private class Node {
        public E data;
        public Node next;

        public Node(E data) {
            this.data = data;
        }

        public Node(E data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

    private int size;
    private Node first;
    private Node last;

    public class FindIterator implements Iterator<E> {
        private List<E> matches;
        private Iterator<E> matchesIterator;

        public FindIterator(E match) {
            matches = new List<E>();
            Iterator<E> it = makeForwardIterator();

            while (it.isValid())
            {
                if (it.get() != null && it.get().compareTo(match) == 0)
                {
                    matches.insertAtEnd(match);
                }

                it.next();
            } 
            

            //init iterator for matches
            matchesIterator = matches.makeForwardIterator();
        }

        public E get() {
            return matchesIterator.get();
        }

        public void next() {
            matchesIterator.next();
        }

        public boolean isValid() {
            return matchesIterator.isValid();
        }

    }

    public class ForwardIterator implements Iterator<E> {
        Node cursor;
        Node prev;

        public ForwardIterator() {
            cursor = first;
            prev = null;
        }

        public E get() {
            if (cursor != null)
                return cursor.data;
            return null;
        }

        public void next() {
            if (isValid())
                cursor = cursor.next;
            else
                cursor = null;
        }

        public boolean isValid() {
            return (cursor != null);
        }

    };

    public List() {
        size = 0;
        first = null;
        last = null;
    }

    public int size() {
        return size;
    }

    public int find(E data) {
        Node cursor = first;
        boolean found = false;
        int counter = 0;
        while (cursor != null && !found) {
            counter++;
            if (cursor.data != data)
                cursor = cursor.next;
            else
                found = true;
        }

        if (found)
            return counter;
        else
            return 0;
    }


    public Iterator<E> makeForwardIterator() {
        return new ForwardIterator();
    }

    public Iterator<E> makeFindIterator(E data) {
        return new FindIterator(data);
    }

    public void insertAtFront(E data) {
        first = new Node(data,first);
        if (last==null)
            last = first;
        size++;
    }

    public void insertAtEnd(E data) {
        Node tmp = new Node(data);

        size++;
        if (last == null) {
            first = tmp;
            last = tmp;
        } else {
            last.next = tmp;
            last = tmp;
        }
    }

    public E removeFromFront() {
        E tmp;

        if (first == null)
            return null;
        else {
            tmp = first.data;
            first = first.next;
            size--;
            if (first == null)
                last = null;
            return tmp;
        }
    }
}
