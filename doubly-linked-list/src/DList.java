public class DList<E extends Comparable> {

    private class Node {
        public E data;
        public Node next;
        public Node prev;

        public Node(E data) {
            this.data = data;
        }

        public Node(Node prev,E data,Node next) {
            this.data = data;
            this.prev = prev;
            this.next = next;
        }
    }

    private int size;
    private Node first;
    private Node last;

    public DList() {
        size = 0;
        first = null;
        last = null;
    }

    public int size() {
        return size;
    }

    private abstract class AllIterator implements Iterator<E> {
        protected Node cursor;

        public E get() {
            if (cursor != null)
                return cursor.data;
            else
                return null;
        }

        public boolean isValid() {
            return (cursor != null);
        }

        public abstract void next();

        //will not work with find iterators
        public void delete() {
            if (isValid())
            {
                if ((cursor.next != null && cursor.prev == null) || 
                        (cursor.next == null && cursor.prev == null))
                {
                    //it's the first or only item
                    deleteFront();
                }
                else if (cursor.next == null && cursor.prev != null)
                {
                    //it's last
                    deleteEnd();
                }
                else if (cursor.next != null && cursor.prev != null)
                {
                    //it's the middle somewhere so let's write a new delete method
                    cursor.next.prev = cursor.prev;
                    cursor.prev.next = cursor.next;
                    size--;
                }

                next();
            }
        }
    }

    public class FwdIterator extends AllIterator {
        public FwdIterator() {
            cursor = first;
        }

        public void next() {
            if (cursor != null)
                cursor = cursor.next;
        }
    }

    public class RevIterator extends AllIterator {
        public RevIterator() {
            cursor = last;
        }

        public void next() {
            if (cursor != null)
                cursor = cursor.prev;
        }
    }

    public class RevFindIterator extends FwdIterator {
        Iterator<E> it;
        E targetData;

        public RevFindIterator(E data) {
            targetData = data;
            it = makeRevIterator();
            next();
        }

        private void findNext()
        {
            boolean found;
            found = false;
            cursor = null;

            while (it.isValid() && !found)
            {
                if (it.get().compareTo(targetData)==0)
                {
                    cursor = new Node(null, it.get(), null);
                    it.next();
                    found = true;
                }
                else
                {
                    it.next();
                }
            }
        }


        @Override
        public void next() {
            if (it.isValid())
                findNext();
            else
                cursor = null;
        }
    }

    public class FwdFindIterator extends FwdIterator {
        Iterator<E> it;
        E targetData;

        public FwdFindIterator(E data) {
            targetData = data;
            it = makeFwdIterator();
            next();
        }

        private void findNext()
        {
            boolean found;
            found = false;
            cursor = null;

            while (it.isValid() && !found)
            {
                if (it.get().compareTo(targetData)==0)
                {
                    cursor = new Node(null, it.get(), null);
                    it.next();
                    found = true;
                }
                else
                {
                    it.next();
                }
            }
        }


        @Override
        public void next() {
            if (it.isValid())
                findNext();
            else
                cursor = null;
        }
    }


    //inserts an element in the front of the list O(1)
    public void insertFront(E data)
    {
        if (first != null)
        {
            first.prev = new Node(null, data, first);
            first = first.prev;
        }
        else
        {
            first = new Node(null,data,null);
            last = first;
        }

        size++;
    }

    //inserts an element in the end of the list O(1)
    public void insertEnd(E data)
    {
        if (last != null)
        {
            last.next = new Node(last, data, null);
            last = last.next;
        }
        else
        {
            first = new Node(null,data,null);
            last = first;
        }

        size++;
    }

    //delete first element, if exists, O(1)
    public void deleteFront()
    {
        if(first != null)
        {
            //one item
            if (last == first)
                last = null;
            
            first = first.next;

            //reset prev
            if (first!= null)
                first.prev = null;


            size--;
        }
    }

    //delete last element, if exists, O(1)
    public void deleteEnd()
    {
        if(last != null)
        {
            if (first == last)
                first = null;

            last = last.prev;

            //after changing last make sure it has no next defined
            if (last != null)
                last.next = null;

            size--;
        }
    }

    public Iterator<E> makeFwdIterator()
    {
        return new FwdIterator();
    }

    public Iterator<E> makeRevIterator()
    {
        return new RevIterator();
    }

    public Iterator<E> makeFwdFindIterator(E data)
    {
        return new FwdFindIterator(data);
    }

    public Iterator<E> makeRevFindIterator(E data)
    {
        return new RevFindIterator(data);
    }
    
}
