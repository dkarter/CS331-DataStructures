// $Id: TestListFwdIterator.java 79 2005-10-10 11:55:39Z beckman $

import junit.framework.*;

public class ListFwdIteratorTest extends TestCase {
    public Integer i1 = new Integer(1);
    public Integer i2 = new Integer(2);
    public Integer i5 = new Integer(5);
    public Integer i10 = new Integer(10);
    public Integer i15 = new Integer(15);
    public Integer i20 = new Integer(20);
    public Integer i25 = new Integer(25);

    public ListFwdIteratorTest(String name) {
        super(name);
    }

    public void testEmpty() {
        List<Integer> foo = new List<Integer>();
        assertEquals("1. Empty Size",0,foo.size());
        assertEquals("2. Empty Remove",null,foo.removeFromFront());
        assertEquals("3. Empty Size",0,foo.size());

        Iterator<Integer> it = foo.makeForwardIterator();

        assertEquals("4. Empty Iterator isValid",false,it.isValid());
        assertEquals("5. Empty Iterator get()",null,it.get());
        it.next();
        assertEquals("6. Empty Iterator next, isValid",false,it.isValid());
        assertEquals("7. Empty Iterator next, get",null,it.get());
    }

     public void testOneElement() {
        List<Integer> foo = new List<Integer>();
        foo.insertAtFront(i1);

        Iterator<Integer> it = foo.makeForwardIterator();

        assertEquals("1. Iterator isValid one item",true,it.isValid());
        assertEquals("5. Iterator get() one item",i1,it.get());
        it.next();
        assertEquals("6. Iterator next - no item, isValid",false,it.isValid());
        assertEquals("7. Iterator next - no item, get",null,it.get());
    }

    public void testMultipleElements() {
        List<Integer> foo = new List<Integer>();
        foo.insertAtFront(i1);
        foo.insertAtFront(i2);
        foo.insertAtFront(i5);
        foo.insertAtFront(i10);
        foo.insertAtFront(i15);

        Iterator<Integer> it = foo.makeForwardIterator();

        assertEquals("1. Iterator isValid one item",true,it.isValid());
        assertEquals("5. Iterator get() one item",i15,it.get());

        it.next();
        assertEquals("6. Iterator next - no item, isValid",true,it.isValid());
        assertEquals("7. Iterator next - no item, get",i10,it.get());

        it.next();
        assertEquals("6. Iterator next - no item, isValid",true,it.isValid());
        assertEquals("7. Iterator next - no item, get",i5,it.get());

        it.next();
        assertEquals("6. Iterator next - no item, isValid",true,it.isValid());
        assertEquals("7. Iterator next - no item, get",i2,it.get());

        it.next();
        assertEquals("6. Iterator next - no item, isValid",true,it.isValid());
        assertEquals("7. Iterator next - no item, get",i1,it.get());

        it.next();
        assertEquals("6. Iterator next - no item, isValid",false,it.isValid());
        assertEquals("7. Iterator next - no item, get",null,it.get());
    }
   
}
