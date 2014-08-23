public class Queue<E> {

    private List<E> items;

    public Queue() {
        items = new List<E>();
    }

    //adds the data to the back of the queue
    public void enqueue(E data)
    {
        items.insertAtEnd(data);
    }

    //returns and removes the data from the queue
    public E dequeue()
    {
        return items.removeFromFront();
    }

    //returns the front item or null if empty
    public E front()
    {
        return items.first();
    }
    
    //the number of elements in the queue
    public int size()
    {
        return items.size();
    }
}
