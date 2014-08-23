// $Id: TestListFindIterator.java 81 2005-10-10 12:29:23Z beckman $

import junit.framework.*;

public class ListFindIteratorTest extends TestCase {
    public Integer i1 = new Integer(1);
    public Integer i2 = new Integer(2);
    public Integer i5 = new Integer(5);
    public Integer i10 = new Integer(10);
    public Integer i15 = new Integer(15);
    public Integer i20 = new Integer(20);
    public Integer i25 = new Integer(25);

    public ListFindIteratorTest(String name) {
        super(name);
    }

    public void testEmpty() {
        List<Integer> foo = new List<Integer>();
        assertEquals("1. Empty Size",0,foo.size());
        assertEquals("2. Empty Remove",null,foo.removeFromFront());
        assertEquals("3. Empty Size",0,foo.size());

        Iterator<Integer> it = foo.makeFindIterator(i1);

        assertEquals("4. Empty Iterator isValid",false,it.isValid());
        assertEquals("5. Empty Iterator get()",null,it.get());
        it.next();
        assertEquals("6. Empty Iterator next, isValid",false,it.isValid());
        assertEquals("7. Empty Iterator next, get",null,it.get());
    }

     public void testOneElement() {
        List<Integer> foo = new List<Integer>();
        foo.insertAtFront(i1);
        
        //one element and try finding that one element
        Iterator<Integer> it = foo.makeFindIterator(i1);

        assertEquals("one existing element Iterator isValid",true,it.isValid());
        assertEquals("one existing element Iterator get()",i1,it.get());
        it.next();
        assertEquals("one existing element next, isValid",false,it.isValid());
        assertEquals("one existing element next, get",null,it.get());

        //one element and trying to find another element that's not in the list
        it = foo.makeFindIterator(i2);

        assertEquals("one existing element Iterator isValid",false,it.isValid());
        assertEquals("one existing element Iterator get()",null,it.get());
        it.next();
        assertEquals("one existing element next, isValid",false,it.isValid());
        assertEquals("one existing element next, get",null,it.get());

    }

    public void testMultipleElements() {
        List<Integer> foo = new List<Integer>();
        foo.insertAtFront(i1);
        foo.insertAtFront(i2);
        foo.insertAtFront(i5);
        foo.insertAtFront(i10);
        foo.insertAtFront(i15);

        //five elements and trying to find one of them
        Iterator<Integer> it = foo.makeFindIterator(i5);

        assertEquals("multiple elements Iterator isValid",true,it.isValid());
        assertEquals("multiple elements Iterator get()",i5,it.get());
        it.next();
        assertEquals("multiple elements next, isValid",false,it.isValid());
        assertEquals("multiple elements next, get",null,it.get());

        //five and trying to find something that's not in there
        it = foo.makeFindIterator(i20);

        assertEquals("multiple elements Iterator isValid",false,it.isValid());
        assertEquals("multiple elements Iterator get()",null,it.get());
        it.next();
        assertEquals("multiple elements next, isValid",false,it.isValid());
        assertEquals("multiple elements next, get",null,it.get());

        //seven elements and trying to find multiple occurance
        foo.insertAtEnd(i5);
        foo.insertAtEnd(i5);

        it = foo.makeFindIterator(i5);

        assertEquals("multiple elements Iterator isValid",true,it.isValid());
        assertEquals("multiple elements Iterator get()",i5,it.get());
        it.next();
        assertEquals("multiple elements next, isValid",true,it.isValid());
        assertEquals("multiple elements next, get",i5,it.get());
        it.next();
        assertEquals("multiple elements next, isValid",true,it.isValid());
        assertEquals("multiple elements next, get",i5,it.get());
        it.next();
        assertEquals("multiple elements next, isValid",false,it.isValid());
        assertEquals("multiple elements next, get",null,it.get());


    }


}
