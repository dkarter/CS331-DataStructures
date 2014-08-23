// $Id: TestList.java 75 2005-10-09 14:19:45Z beckman $

import junit.framework.*;

public class DListTest extends TestCase {
    public Integer i1 = new Integer(1);
    public Integer i2 = new Integer(2);
    public Integer i5 = new Integer(5);
    public Integer i10 = new Integer(10);
    public Integer i15 = new Integer(15);
    public Integer i20 = new Integer(20);
    public Integer i25 = new Integer(25);

    public DListTest(String name) {
        super(name);
    }

    public void testCreate() {
        DList<Integer> d = new DList<Integer>();

        assertEquals("1. create",0,d.size());
    }

    public void testSize() {
        DList<Integer> d = new DList<Integer>();

        assertEquals("1. Empty",0,d.size());

        d.insertFront(i1);
        d.insertFront(i5);
        assertEquals("2. Elements added",2,d.size());

        d.deleteFront();
        assertEquals("3. Element removed",1,d.size());

        d.deleteFront();
        assertEquals("4. all elements removed",0,d.size());

        d.deleteFront();
        assertEquals("5. removed too many elements",0,d.size());

    }

    public void testInsertFront() {
        DList<Integer> d = new DList<Integer>();
        Iterator<Integer> fwdIt;
        Iterator<Integer> revIt;

        assertEquals("1. Empty",0,d.size());

        d.insertFront(i1);

        revIt = d.makeRevIterator();
        fwdIt = d.makeFwdIterator();

        assertEquals("2. Element added check size increase",1,d.size());
        assertEquals(i1,fwdIt.get());
        assertEquals(true,fwdIt.isValid());
        assertEquals(i1,revIt.get());
        assertEquals(true,revIt.isValid());

        d.insertFront(i5);

        revIt = d.makeRevIterator();
        fwdIt = d.makeFwdIterator();

        assertEquals("2. Element added check size increase",2,d.size());
        assertEquals(true,fwdIt.isValid());
        assertEquals(i5,fwdIt.get());

        assertEquals(true,revIt.isValid());
        revIt.next();
        assertEquals(i5,revIt.get());

    }

    public void testInsertEnd() {
        DList<Integer> d = new DList<Integer>();
        Iterator<Integer> fwdIt;
        Iterator<Integer> revIt;

        assertEquals("1. Empty",0,d.size());

        d.insertEnd(i1);

        revIt = d.makeRevIterator();
        fwdIt = d.makeFwdIterator();

        assertEquals("2. Element added check size increase",1,d.size());
        assertEquals(i1,fwdIt.get());
        assertEquals(true,fwdIt.isValid());
        assertEquals(i1,revIt.get());
        assertEquals(true,revIt.isValid());

        d.insertEnd(i5);

        revIt = d.makeRevIterator();
        fwdIt = d.makeFwdIterator();

        assertEquals("2. Element added check size increase",2,d.size());
        fwdIt.next();
        assertEquals(i5,fwdIt.get());
        assertEquals(true,fwdIt.isValid());
        assertEquals(i5,revIt.get());
        assertEquals(true,revIt.isValid());
    }

    public void testDeleteFront() {
        DList<Integer> d = new DList<Integer>();
        Iterator<Integer> fwdIt;
        Iterator<Integer> revIt;


        d.insertEnd(i1);
        d.insertEnd(i5);
        d.insertEnd(i15);

        revIt = d.makeRevIterator();
        fwdIt = d.makeFwdIterator();

        assertEquals("2. Element added check size increase",3,d.size());
        assertEquals(i1,fwdIt.get());
        revIt.next();
        revIt.next();
        assertEquals(i1,revIt.get());

        d.deleteFront();

        revIt = d.makeRevIterator();
        fwdIt = d.makeFwdIterator();


        assertEquals("2. Element added check size increase",2,d.size());
        assertNotSame(i1,fwdIt.get());
        revIt.next();
        revIt.next();
        assertNotSame(i1,revIt.get());

        
    }

    public void testDeleteEnd() {
        DList<Integer> d = new DList<Integer>();
        Iterator<Integer> fwdIt;
        Iterator<Integer> revIt;


        d.insertEnd(i1);
        d.insertEnd(i5);
        d.insertEnd(i15);

        revIt = d.makeRevIterator();
        fwdIt = d.makeFwdIterator();

        assertEquals("2. Element added check size increase",3,d.size());
        fwdIt.next();
        fwdIt.next();
        assertEquals(i15,fwdIt.get());
        assertEquals(i15,revIt.get());

        d.deleteEnd();

        revIt = d.makeRevIterator();
        fwdIt = d.makeFwdIterator();


        assertEquals("2. Element added check size increase",2,d.size());
        fwdIt.next();
        fwdIt.next();
        assertNotSame(i15,fwdIt.get());
        assertNotSame(i15,revIt.get());


    }

    public void testFwdFindIterator() {
        DList<Integer> d = new DList<Integer>();
        Iterator<Integer> fwdIt;

        d.insertEnd(i1);
        d.insertEnd(i5);
        d.insertEnd(i15);

        //item is not in list
        fwdIt = d.makeFwdFindIterator(i10);

        assertEquals(false,fwdIt.isValid());
        assertEquals(null,fwdIt.get());
        fwdIt.next();
        assertEquals(false,fwdIt.isValid());
        assertEquals(null,fwdIt.get());

        fwdIt = d.makeFwdFindIterator(i1);

        assertEquals(true,fwdIt.isValid());
        assertEquals(i1,fwdIt.get());
        fwdIt.next();
        assertEquals(false,fwdIt.isValid());
        assertEquals(null,fwdIt.get());

        d.insertEnd(i5);

        fwdIt = d.makeFwdFindIterator(i5);

        assertEquals(true,fwdIt.isValid());
        assertEquals(i5,fwdIt.get());
        fwdIt.next();
        assertEquals(true,fwdIt.isValid());
        assertEquals(i5,fwdIt.get());
        fwdIt.next();
        assertEquals(false,fwdIt.isValid());
        assertEquals(null,fwdIt.get());

    }

    public void testRevFindIterator() {
        DList<Integer> d = new DList<Integer>();
        Iterator<Integer> revIt;

        d.insertEnd(i1);
        d.insertEnd(i5);
        d.insertEnd(i15);

        //item is not in list
        revIt = d.makeRevFindIterator(i10);

        assertEquals(false,revIt.isValid());
        assertEquals(null,revIt.get());
        revIt.next();
        assertEquals(false,revIt.isValid());
        assertEquals(null,revIt.get());

        revIt = d.makeRevFindIterator(i1);

        assertEquals(true,revIt.isValid());
        assertEquals(i1,revIt.get());
        revIt.next();
        assertEquals(false,revIt.isValid());
        assertEquals(null,revIt.get());

        d.insertEnd(i5);

        revIt = d.makeRevFindIterator(i5);

        assertEquals(true,revIt.isValid());
        assertEquals(i5,revIt.get());
        revIt.next();
        assertEquals(true,revIt.isValid());
        assertEquals(i5,revIt.get());
        revIt.next();
        assertEquals(false,revIt.isValid());
        assertEquals(null,revIt.get());

    }

     public void testDeleteFromIterators() {
        DList<Integer> d;
        Iterator<Integer> fwdIt;
        Iterator<Integer> revIt;

        //single item in the list
        d = new DList<Integer>();
        d.insertEnd(i15);

        fwdIt = d.makeFwdIterator();

        assertEquals(i15,fwdIt.get());

        fwdIt.delete();

        assertEquals(null,fwdIt.get());
        assertEquals(0,d.size());

        fwdIt = d.makeFwdIterator();
        while (fwdIt.isValid())
        {
            assertNotSame(i15,fwdIt.get());
            fwdIt.next();

        }

        //delete from front
        d = new DList<Integer>();
        d.insertEnd(i1);
        d.insertEnd(i5);
        d.insertEnd(i15);

        fwdIt = d.makeFwdIterator();

        assertEquals(i1,fwdIt.get());

        fwdIt.delete();

        assertEquals(i5,fwdIt.get());
        assertEquals(2,d.size());

        fwdIt = d.makeFwdIterator();
        while (fwdIt.isValid())
        {
            assertNotSame(i1,fwdIt.get());
            fwdIt.next();

        }



        //delete from middle
        d = new DList<Integer>();
        d.insertEnd(i1);
        d.insertEnd(i5);
        d.insertEnd(i15);

        fwdIt = d.makeFwdIterator();

        fwdIt.next();

        assertEquals(i5,fwdIt.get());

        fwdIt.delete();

        assertEquals(i15,fwdIt.get());
        assertEquals(2,d.size());

        fwdIt = d.makeFwdIterator();
        while (fwdIt.isValid())
        {
            assertNotSame(i5,fwdIt.get());
            fwdIt.next();
        }

        //delete from back
        d = new DList<Integer>();
        d.insertEnd(i1);
        d.insertEnd(i5);
        d.insertEnd(i20);

        fwdIt = d.makeFwdIterator();

        fwdIt.next();
        fwdIt.next();
        assertEquals(i20,fwdIt.get());

        fwdIt.delete();

        assertEquals(null,fwdIt.get());
        assertEquals(2,d.size());

        fwdIt = d.makeFwdIterator();
        while (fwdIt.isValid())
        {
            assertNotSame(i20,fwdIt.get());
            fwdIt.next();
        }

    }

}
