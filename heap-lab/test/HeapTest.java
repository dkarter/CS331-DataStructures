/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import junit.framework.*;

/**
 *
 * @author Dor
 */
public class HeapTest extends TestCase {

    public Integer i1 = new Integer(1);
    public Integer i2 = new Integer(2);
    public Integer i3 = new Integer(3);
    public Integer i4 = new Integer(4);
    public Integer i5 = new Integer(5);
    public Integer i6 = new Integer(6);
    public Integer i7 = new Integer(7);
    public Integer i8 = new Integer(8);
    public Integer i9 = new Integer(9);

    public HeapTest(String name) {
        super(name);
    }

    
    public void testSize() {
        Heap<Integer> instance = new Heap<Integer>();
        //add elements see if size increases
        assertEquals(0,instance.size());
        instance.enqueue(i1);
        assertEquals(1,instance.size());
        instance.enqueue(i2);
        assertEquals(2,instance.size());
        instance.enqueue(i3);
        assertEquals(3,instance.size());


        //dequeue elements size should decrease
        instance.dequeue();
        assertEquals(2,instance.size());

        instance.dequeue();
        assertEquals(1,instance.size());

        instance.dequeue();
        assertEquals(0,instance.size());

        //past zero
        instance.dequeue();
        assertEquals(0,instance.size());
        
    }

    public void testEnqueue() {
        Heap<Integer> instance = new Heap<Integer>();
        //enqueue decreasing numbers
        instance.enqueue(i7);
        assertEquals(i7, instance.top());
        assertEquals(1, instance.size());

        instance.enqueue(i6);
        assertEquals(i6, instance.top());
        assertEquals(2, instance.size());

        instance.enqueue(i5);
        assertEquals(i5, instance.top());
        assertEquals(3, instance.size());

        //enqueue a bigger number
        instance.enqueue(i8);
        assertEquals(i5, instance.top());
        assertEquals(4, instance.size());

        instance.enqueue(i9);
        assertEquals(i5, instance.top());
        assertEquals(5, instance.size());

        //now a smaller one
        instance.enqueue(i2);
        assertEquals(i2, instance.top());
        assertEquals(6, instance.size());

    }

    public void testDequeue() {
        Heap<Integer> instance = new Heap<Integer>();
        
        //build tree
        instance.enqueue(i1);
        instance.enqueue(i2);
        instance.enqueue(i3);
        instance.enqueue(i4);
        instance.enqueue(i5);
        instance.enqueue(i6);
        instance.enqueue(i7);

        assertEquals(7, instance.size());
        assertEquals(i1, instance.top());

        //both bigger 
        Integer actual = instance.dequeue();
        assertEquals(i1, actual);
        assertEquals(i2, instance.top());
        assertEquals(6, instance.size());

        //both bigger
        actual = instance.dequeue();
        assertEquals(i2, actual);
        assertEquals(i3, instance.top());
        assertEquals(5, instance.size());

        //left bigger
        actual = instance.dequeue();
        assertEquals(i3, actual);
        assertEquals(i4, instance.top());
        assertEquals(4, instance.size());

        //both bigger
        actual = instance.dequeue();
        assertEquals(i4, actual);
        assertEquals(i5, instance.top());
        assertEquals(3, instance.size());

        //only left exist and is smaller
        actual = instance.dequeue();
        assertEquals(i5, actual);
        assertEquals(i6, instance.top());
        assertEquals(2, instance.size());

        //no children
        actual = instance.dequeue();
        assertEquals(i6, actual);
        assertEquals(i7, instance.top());
        assertEquals(1, instance.size());


        //only root exist
        actual = instance.dequeue();
        assertEquals(i7, actual);
        assertEquals(null, instance.top());
        assertEquals(0, instance.size());


        //past zero
        actual = instance.dequeue();
        assertEquals(null, actual);
        assertEquals(null, instance.top());
        assertEquals(0, instance.size());

    }

    

}