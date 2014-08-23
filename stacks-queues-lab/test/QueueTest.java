import junit.framework.*;

public class QueueTest extends TestCase {
    public Integer i1 = new Integer(1);
    public Integer i2 = new Integer(2);
    public Integer i5 = new Integer(5);
    public Integer i10 = new Integer(10);
    public Integer i15 = new Integer(15);
    public Integer i20 = new Integer(20);
    public Integer i25 = new Integer(25);

    public QueueTest(String name) {
        super(name);
    }


    /**
     * Test of enqueue method, of class Queue.
     */
    public void testEmptyQueue() {
        Queue<Integer> instance = new Queue<Integer>();

        assertEquals(0, instance.size());
        assertEquals(null, instance.front());
        assertEquals(null, instance.dequeue());

    }


    /**
     * Test of enqueue method, of class Queue.
     */
    public void testEnqueue() {
        Queue<Integer> instance = new Queue<Integer>();

        //test initial size
        assertEquals(0, instance.size());

        //preform enqueue
        instance.enqueue(i1);
        
        //test size increase
        assertEquals(1, instance.size());

        //test item at front
        assertEquals(i1, instance.front());

    }

    /**
     * Test of dequeue method, of class Queue.
     */
    public void testDequeue() {
        Queue<Integer> instance = new Queue<Integer>();

        instance.enqueue(i1);
        instance.enqueue(i2);
        instance.enqueue(i5);
        
        //initial size and front
        assertEquals(3, instance.size());
        assertEquals(i1, instance.front());
        
        //do dequeue and test size decrease        
        Integer result1 = instance.dequeue();
        assertEquals(2, instance.size());
        assertEquals(i2, instance.front());

        Integer result2 = instance.dequeue();
        assertEquals(1, instance.size());
        assertEquals(i5, instance.front());
        
        Integer result3 = instance.dequeue();
        assertEquals(0, instance.size());
        assertEquals(null, instance.front());

        //test order (FIFO)
        assertEquals(i1, result1);
        assertEquals(i2, result2);
        assertEquals(i5, result3);

    }

   


}
