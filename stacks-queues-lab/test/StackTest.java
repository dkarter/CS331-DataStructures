import junit.framework.*;

public class StackTest extends TestCase {
    public Integer i1 = new Integer(1);
    public Integer i2 = new Integer(2);
    public Integer i5 = new Integer(5);
    public Integer i10 = new Integer(10);
    public Integer i15 = new Integer(15);
    public Integer i20 = new Integer(20);
    public Integer i25 = new Integer(25);

    public StackTest(String name) {
        super(name);
    }

    public void testEmptyStack()
    {
        Stack<Integer> instance = new Stack<Integer>();

        //test size
        assertEquals(0, instance.size());

        //test pop and top
        assertEquals(null, instance.top());
        assertEquals(null, instance.pop());

        //check that size haven't changed
        assertEquals(0, instance.size());
    }


    public void testPush() {

        Stack<Integer> instance = new Stack<Integer>();

        assertEquals(0, instance.size());

        //test first item
        instance.push(i1);
        assertEquals(i1, instance.top());
        assertEquals(1, instance.size());

        //test second item
        instance.push(i2);
        assertEquals(i2, instance.top());
        assertEquals(2, instance.size());

    }

   
    public void testPop() {
        
        Stack<Integer> instance = new Stack<Integer>();

        instance.push(i1);
        instance.push(i2);
        instance.push(i5);

        //test size
        Integer result3 = instance.pop();
        assertEquals(2, instance.size());

        Integer result2 = instance.pop();
        assertEquals(1, instance.size());

        Integer result1 = instance.pop();
        assertEquals(0, instance.size());

        //test poping order
        assertEquals(i1, result1);
        assertEquals(i2, result2);
        assertEquals(i5, result3);

    }

   

   

}
