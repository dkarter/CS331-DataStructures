/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import junit.framework.*;

/**
 *
 * @author Dor
 */
public class ListTest extends TestCase {

    public Integer i1 = new Integer(1);
    public Integer i2 = new Integer(2);
    public Integer i5 = new Integer(5);
    public Integer i10 = new Integer(10);
    public Integer i15 = new Integer(15);
    public Integer i20 = new Integer(20);
    public Integer i25 = new Integer(25);

    public ListTest(String name) {
        super(name);
    }

    // test list with nothing in it.

    public void testZero() {
        List foo = new List<Integer>();
        assertEquals("Size Test",0,foo.size());

    }


    /**
     * Test of size method, of class List.
     */
    public void testSize() {
        List<Integer> instance = new List<Integer>();
        instance.insert(i1);
        instance.insert(i2);
        int expResult = 2;
        int result = instance.size();
        assertEquals(expResult, result);
    }

    /**
     * Test of insert method, of class List.
     */
    public void testInsert() {
        
        
        List<Integer> instance = new List<Integer>();

        assertEquals(instance.find(i1), 0);
        
        instance.insert(i1);

        assertEquals(instance.find(i1), 1);
        
    }

    /**
     * Test of find method, of class List.
     */
    public void testFind() {
        List<Integer> instance = new List<Integer>();

        instance.insert(i1);
        instance.insert(i2);
        instance.insert(i5);
        instance.insert(i10);

        int expResult = 2;
        int result = instance.find(i5);
        assertEquals(expResult, result);

        
        //test mathematical equality - no error
        expResult = 2;
        result = instance.find(new Integer(5));
        assertEquals(expResult, result);

        Integer tmp = new Integer(5);
        result = instance.find(tmp);
        assertEquals(expResult, result);

    }

    /**
     * Test of find method, of class List.
     */
    public void testFind_ItemNotExists() {
        List<Integer> instance = new List<Integer>();

        instance.insert(i1);
        instance.insert(i2);
        instance.insert(i5);
        instance.insert(i10);

        int expResult = 0;
        int result = instance.find(i15);
        assertEquals(expResult, result);
    }

    /**
     * Test of find method, if similiar items exist in the list it should return
     * the first one it finds.
     */
    public void testFind_SimiliarItems() {
        List<Integer> instance = new List<Integer>();

        instance.insert(i1);
        instance.insert(i10);
        instance.insert(i5);
        instance.insert(i5);
        instance.insert(i10);


        int expResult = 2;
        int result = instance.find(i5);
        assertEquals(expResult, result);
    }

    /**
     * Test of find method, if similiar items exist in the list it should return
     * the first one it finds.
     */
    public void testFind_EmptyList() {
        List<Integer> instance = new List<Integer>();

        int expResult = 0;
        int result = instance.find(i5);
        assertEquals(expResult, result);
    }

    /**
     * Test of insertAtEnd method, of class List.
     */
    public void testInsertAtEnd() {
        List<Integer> instance = new List<Integer>();
        instance.insert(i2);
        instance.insert(i5);
        instance.insert(i10);
        
        int sizeBefore = instance.size();

        instance.insertAtEnd(i1);


        //test that added element is at the end
        assertEquals(instance.find(i1), instance.size());

        //test that size has increased
        assertEquals((instance.size() - sizeBefore), 1);
    }

    /**
     * Test of insertAtEnd method, of class List.
     */
    public void testInsertAtEnd_EmptyList() {
        System.out.println("insertAtEnd");
        List<Integer> instance = new List<Integer>();
        instance.insertAtEnd(i1);

        //test that element is the first in the list
        assertEquals(instance.find(i1), 1);
        
        //test size increase
        assertEquals(instance.size(), 1);
    }

    /**
     * Test of delete method, of class List.
     */
    public void testDeleteIndexZero() {
        System.out.println("delete");
        int index = 0;
        List<Integer> instance = new List<Integer>();
        instance.insert(i1);
        instance.insert(i2);
        instance.delete(index);

        assertEquals(instance.size(), 2);



    }

    public void testDeleteOutOfBounds() {
        System.out.println("delete");
        int index = 3;
        List<Integer> instance = new List<Integer>();
        instance.insert(i1);
        instance.insert(i2);
        instance.delete(index);

        assertEquals(instance.size(), 2);
    }

    public void testDeleteMathematicalEquality() {

        List<Integer> instance = new List<Integer>();
        instance.insert(i1);
        instance.insert(i2);

        instance.delete(new Integer(1));

        assertEquals(instance.size(), 1);
        assertEquals(instance.find(i2), 0);

    }

    /**
     * Test of delete method, of class List.
     */
    public void testDelete() {
        System.out.println("delete");

        List<Integer> instance = new List<Integer>();
        
        //first try deleting from an empty list
        instance.delete(1);

        assertEquals(instance.size(), 0);
        assertEquals(instance.find(i2), 0);

        //now try with some elements
        instance.insert(i1);
        instance.insert(i2);
        instance.delete(1);

        assertEquals(instance.size(), 1);
        assertEquals(instance.find(i2), 0);

        //delete last item (it's index should now be 1)
        instance.delete(1);

        assertEquals(instance.size(), 0);
        assertEquals(instance.find(i1), 0);

        //delete after everything has been deleted
        instance.delete(1);

        assertEquals(instance.size(), 0);
        assertEquals(instance.find(i1) + instance.find(i2), 0);


    }

    /**
     * Test of reverse method, of class List.
     */
    public void testReverse() {
        List<Integer> instance = new List<Integer>();
        //insert adds the element to the front of the list
        instance.insert(i1);
        instance.insert(i2);
        instance.insert(i5);
        instance.insert(i10);
        instance.insert(i15);
        instance.insert(i20);
        instance.insert(i25);

        assertEquals(instance.find(i1), instance.size());
        assertEquals(instance.find(i2), instance.size()-1);
        assertEquals(instance.find(i5), instance.size()-2);
        assertEquals(instance.find(i10), instance.size()-3);
        assertEquals(instance.find(i15), instance.size()-4);
        assertEquals(instance.find(i20), instance.size()-5);
        assertEquals(instance.find(i25), instance.size()-6);

        instance.reverse();

        assertEquals(instance.find(i1), 1);
        assertEquals(instance.find(i2), 2);
        assertEquals(instance.find(i5), 3);
        assertEquals(instance.find(i10), 4);
        assertEquals(instance.find(i15), 5);
        assertEquals(instance.find(i20), 6);
        assertEquals(instance.find(i25), 7);
    }
    
}