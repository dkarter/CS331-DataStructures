
// $Id: TestList.java 75 2005-10-09 14:19:45Z beckman $

import junit.framework.*;

public class BSTTest extends TestCase {
    public String n1 = "Jenny";
    public String n2 = "Empire";
    public String n3 = "Emergency";
    public String n4 = "Helpdesk";
    public String n5 = "Information";

    public Integer i1 = new Integer(8675309);
    public Integer i2 = new Integer(5882300);
    public Integer i3 = new Integer(911);
    public Integer i4 = new Integer(5673375);
    public Integer i5 = new Integer(5551212);


    public BSTTest(String name) {
        super(name);
    }

    public void testCreate() {
        BST<String,Integer> bst = new BST<String,Integer>();

        assertEquals("1. create",0,bst.size());
    }

    public void testSize() {
        BST<Integer,String> bst = new BST<Integer,String>();

        assertEquals(0,bst.size());

        bst.add(i1, n1);

        assertEquals(1,bst.size());

        bst.add(i2, n2);

        assertEquals(2,bst.size());

        bst.add(i3, n3);

        assertEquals(3,bst.size());

        bst.delete(i2);

        assertEquals(2,bst.size());


    }

    /**
     * Test of add method, of class BST.
     */    
    public void testAdd()
    {
        BST<Integer, String> instance = new BST<Integer, String>();

        assertEquals(0, instance.size());
        assertEquals(null, instance.find(i1));
        instance.add(i1, n1);

        assertEquals(1, instance.size());
        assertEquals(n1, instance.find(i1));
        assertEquals(i1, instance.revFind(n1));

        instance.add(i2, n2);

        assertEquals(2, instance.size());
        assertEquals(n2, instance.find(i2));
        assertEquals(i2, instance.revFind(n2));

        instance.add(i1, n3);

        assertEquals(2, instance.size()); //no increase
        assertEquals(n3, instance.find(i1));
        assertEquals(i1, instance.revFind(n3));


        //TODO: test that item was replaced + no size increase
        //TODO: test add multiple items than find

    }

    /**
     * Test of find method, of class BST.
     */
    public void testFind() {
       
        BST<Integer, String> instance = new BST<Integer, String>();
        
        assertEquals(null, instance.find(i1));
        instance.add(i1, n1);
        assertEquals(n1, instance.find(i1));
        //find something that does not exist
        assertEquals(null, instance.find(i5));

        instance.add(i2, n2);
        instance.add(i3, n3);
        instance.add(i4, n4);
        instance.add(i5, n5);

        assertEquals(n5, instance.find(i5));

    }

    /**
     * Test of revFind method, of class BST.
     */
    public void testRevFind() {
       
        BST<Integer, String> instance = new BST<Integer, String>();
        assertEquals(null, instance.revFind(n1));

        instance.add(i1, n1);
        assertEquals(i1, instance.revFind(n1));

        instance.add(i2, n2);
        instance.add(i3, n3);
        instance.add(i4, n4);

        assertEquals(i4, instance.revFind(n4));
        assertEquals(i3, instance.revFind(n3));
        assertEquals(i2, instance.revFind(n2));
        assertEquals(null, instance.revFind(n5));


        
    }

    /**
     * Test of delete method, of class BST.
     */
    public void testDelete() {
        BST<Integer, String> instance;

        //1. delete leaf - no children
        instance = new BST<Integer, String>();

        instance.add(i3, n3);
        instance.add(i1, n2);


        assertEquals(n2, instance.find(i1));
        assertEquals(2, instance.size());

        instance.delete(i1);

        assertEquals(1, instance.size());
        assertEquals(null, instance.find(i1));

        //2. delete root - no children
        instance = new BST<Integer, String>();

        instance.add(i1, n2);

        assertEquals(n2, instance.find(i1));
        assertEquals(1, instance.size());

        instance.delete(i1);

        assertEquals(0, instance.size());
        assertEquals(null, instance.find(i1));

        //3. delete parent with one child
        instance = new BST<Integer, String>();

        instance.add(i2, n2); //root
        instance.add(i3, n3);
        instance.add(i4, n4);
        instance.add(i1, n2);
        
        assertEquals(n3, instance.find(i3));
        assertEquals(4, instance.size());

        instance.delete(i3);

        assertEquals(null, instance.find(i3));
        assertEquals(3, instance.size());
        assertNotNull(instance.find(i2));
        assertNotNull(instance.find(i4));
        assertNotNull(instance.find(i1));



        //check order after delete



        //4. delete root with one child
        instance = new BST<Integer, String>();

        instance.add(i2, n2); //root
        instance.add(i3, n3);
        instance.add(i5, n4);
        instance.add(i4, n2);

        assertEquals(n2, instance.find(i2));
        assertEquals(4, instance.size());

        instance.delete(i2);

        assertEquals(null, instance.find(i2));
        assertEquals(3, instance.size());
        assertNotNull(instance.find(i3));
        assertNotNull(instance.find(i4));
        assertNotNull(instance.find(i5));


        //check order after delete


        //5. delete parent with two children
        //- scenario C
        instance = getScenerioC();

        assertEquals(n4, instance.find(i2));
        assertEquals(5, instance.size());

        instance.delete(i2);

        assertEquals(null, instance.find(i2));
        assertEquals(4, instance.size());
        assertNotNull(instance.find(i5));
        assertNotNull(instance.find(i4));
        assertNotNull(instance.find(i3));
        assertNotNull(instance.find(i1));

        //- scenario G
        instance = getScenerioG();

        assertEquals(n4, instance.find(i2));
        assertEquals(5, instance.size());

        instance.delete(i2);

        assertEquals(null, instance.find(i2));
        assertEquals(4, instance.size());
        assertNotNull(instance.find(i5));
        assertNotNull(instance.find(i4));
        assertNotNull(instance.find(i3));
        assertNotNull(instance.find(i1));

        //- scenario H
        instance = getScenerioH();

        assertEquals(n4, instance.find(i2));
        assertEquals(5, instance.size());

        instance.delete(i2);

        assertEquals(null, instance.find(i2));
        assertEquals(4, instance.size());
        assertNotNull(instance.find(i5));
        assertNotNull(instance.find(i4));
        assertNotNull(instance.find(i3));
        assertNotNull(instance.find(i1));


        //check order after delete
        
        //6. delete root with two children
        //- scenario A
        instance = getScenerioA();

        assertEquals(n2, instance.find(i4));
        assertEquals(5, instance.size());

        instance.delete(i4);

        assertEquals(null, instance.find(i4));
        assertEquals(4, instance.size());
        assertNotNull(instance.find(i5));
        assertNotNull(instance.find(i2));
        assertNotNull(instance.find(i3));
        assertNotNull(instance.find(i1));

        //- scenario B
        instance = getScenerioB();

        assertEquals(n2, instance.find(i4));
        assertEquals(5, instance.size());

        instance.delete(i4);

        assertEquals(null, instance.find(i4));
        assertEquals(4, instance.size());
        assertNotNull(instance.find(i5));
        assertNotNull(instance.find(i2));
        assertNotNull(instance.find(i3));
        assertNotNull(instance.find(i1));

        //- scenario C
        instance = getScenerioC();

        assertEquals(n2, instance.find(i5));
        assertEquals(5, instance.size());

        instance.delete(i5);

        assertEquals(null, instance.find(i5));
        assertEquals(4, instance.size());
        assertNotNull(instance.find(i4));
        assertNotNull(instance.find(i2));
        assertNotNull(instance.find(i3));
        assertNotNull(instance.find(i1));

        //check order after delete
        


    }

    private BST<Integer, String> getScenerioA()
    {
        BST<Integer, String> instance = new BST<Integer, String>();

        instance.add(i4, n2); //root
        instance.add(i5, n3);
        instance.add(i2, n4);
        instance.add(i3, n2);
        instance.add(i1, n2);

        return instance;
    }

    private BST<Integer, String> getScenerioB()
    {
        BST<Integer, String> instance = new BST<Integer, String>();

        instance.add(i4, n2); //root
        instance.add(i5, n3);
        instance.add(i3, n4);
        instance.add(i1, n2);
        instance.add(i2, n2);

        return instance;
    }

    private BST<Integer, String> getScenerioC()
    {
        BST<Integer, String> instance = new BST<Integer, String>();

        instance.add(i5, n2); //root
        instance.add(i3, n3);
        instance.add(i2, n4);
        instance.add(i4, n2);
        instance.add(i1, n2);

        return instance;
    }

    private BST<Integer, String> getScenerioG()
    {
        BST<Integer, String> instance = new BST<Integer, String>();

        instance.add(i3, n2); //root
        instance.add(i2, n4);
        instance.add(i4, n4);
        instance.add(i1, n2);
        instance.add(i5, n2);

        return instance;
    }

    private BST<Integer, String> getScenerioH()
    {
        BST<Integer, String> instance = new BST<Integer, String>();

        instance.add(i3, n2); //root
        instance.add(i2, n4);
        instance.add(i5, n4);
        instance.add(i1, n2);
        instance.add(i4, n2);

        return instance;
    }

    public void testMkBFSIterator() {
        BST<Integer, String> instance = new BST<Integer, String>();

        // 1. Scenario A
        instance.add(i2, n2);
        instance.add(i3, n3);
        instance.add(i1, n4);
        instance.add(i4, n2);
        instance.add(i5, n2);

        Iterator<Integer> it = instance.mkBFSIterator();

        assertTrue(it.isValid());
        assertEquals(i2, it.get());

        it.next(); //root -> left
        assertTrue(it.isValid());
        assertEquals(i3, it.get());

        it.next(); //root -> right
        assertTrue(it.isValid());
        assertEquals(i1, it.get());

        it.next(); //root -> left -> right
        assertTrue(it.isValid());
        assertEquals(i4, it.get());

        it.next(); //root -> left -> right -> left
        assertTrue(it.isValid());
        assertEquals(i5, it.get());
        
        it.next(); //done
        assertFalse(it.isValid());

        instance = new BST<Integer, String>();

        // 2. Scenario B
        instance.add(i5, n2);
        instance.add(i3, n3);
        instance.add(i4, n4);
        instance.add(i2, n2);
        instance.add(i1, n2);

        it = instance.mkBFSIterator();

        assertTrue(it.isValid());
        assertEquals(i5, it.get());

        it.next(); //root -> left
        assertTrue(it.isValid());
        assertEquals(i3, it.get());

        it.next(); //root -> right
        assertTrue(it.isValid());
        assertEquals(i4, it.get());

        it.next(); //root -> right -> right
        assertTrue(it.isValid());
        assertEquals(i2, it.get());

        it.next(); //root -> right -> right -> right
        assertTrue(it.isValid());
        assertEquals(i1, it.get());

        it.next(); //done
        assertFalse(it.isValid());

    }

    

}
