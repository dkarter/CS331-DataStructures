public class Stack<E> {

    List<E> items;

    public Stack() {
        items = new List<E>();
    }

    //adds an element to the top of the stack
    public void push(E data)
    {
        items.insertAtFront(data);
    }

    //returns and removes the element at the top of the stack,
    //no action if the stack is empty
    public E pop()
    {
        return items.removeFromFront();
    }

    //the element at the top of the stack
    //null if stack is empty
    public E top()
    {
        return items.first();
    }

    //the number of elements in the stack
    public int size()
    {
        return items.size();
    }




}
