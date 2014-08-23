
// $Id: TestList.java 75 2005-10-09 14:19:45Z beckman $

import junit.framework.*;

public class TreeTest extends TestCase {


    private int i1 = 1;
    private int i2 = 2;
    private int i3 = 3;
    private int i4 = 4;
    private int i5 = 5;
    private int i6 = 6;
    private int i7 = 7;
    private int i8 = 8;
    private int i9 = 9;


    public TreeTest(String name) {
        super(name);
    }

    public void testCreate() {
        Tree tree = new Tree();

    }



    private Tree getScenerioA()
    {
        Tree instance = new Tree();

        instance.add(i3); //root
        instance.add(i2);
        instance.add(i4);
        instance.add(i1);
        instance.add(i5);

        return instance;
    }

    private Tree getScenerioB()
    {
        Tree instance = new Tree();

        instance.add(i3); //root
        instance.add(i2);
        instance.add(i5);
        instance.add(i1);
        instance.add(i4);

        return instance;
    }

    private Tree getScenerioC()
    {
        Tree instance = new Tree();

        instance.add(i2); //root
        instance.add(i1);
        instance.add(i4);
        instance.add(i3);
        instance.add(i5);

        return instance;
    }

    private Tree getScenerioE()
    {
        Tree instance = new Tree();

        instance.add(i4); //root
        instance.add(i1);
        instance.add(i5);
        instance.add(i3);
        instance.add(i2);

        return instance;
    }

    private Tree getScenerioF()
    {
        Tree instance = new Tree();

        instance.add(i2); //root
        instance.add(i1);
        instance.add(i3);
        instance.add(i4);
        instance.add(i5);

        return instance;
    }

    private Tree getScenerioG()
    {
        Tree instance = new Tree();

        instance.add(i1); //root
        instance.add(i4);
        instance.add(i3);
        instance.add(i5);
        instance.add(i2);

        return instance;
    }

    private Tree getScenerioH()
    {
        Tree instance = new Tree();

        instance.add(i1); //root
        instance.add(i4);
        instance.add(i2);
        instance.add(i5);
        instance.add(i3);

        return instance;
    }

    private Tree getScenerioI()
    {
        Tree instance = new Tree();

        instance.add(i4); //root
        instance.add(i2);
        instance.add(i5);
        instance.add(i1);
        instance.add(i3);

        return instance;
    }

    private Tree getScenerioJ()
    {
        Tree instance = new Tree();

        instance.add(i4); //root
        instance.add(i2);
        instance.add(i6);
        instance.add(i1);
        instance.add(i3);
        instance.add(i5);
        instance.add(i7);

        return instance;
    }

    public void testMkBFSIterator() {
        Tree instance = getScenerioE();

        Iterator it = instance.mkBFSIterator();

        assertTrue(it.isValid());
        assertEquals(i4, it.get());

        it.next(); //root -> left
        assertTrue(it.isValid());
        assertEquals(i1, it.get());

        it.next(); //root -> right
        assertTrue(it.isValid());
        assertEquals(i5, it.get());

        it.next(); //root -> left -> right
        assertTrue(it.isValid());
        assertEquals(i3, it.get());

        it.next(); //root -> left -> right -> left
        assertTrue(it.isValid());
        assertEquals(i2, it.get());
        
        it.next(); //done
        assertFalse(it.isValid());


        //scenario F
        instance = getScenerioF();

        it = instance.mkBFSIterator();

        assertTrue(it.isValid());
        assertEquals(i2, it.get());

        it.next(); //root -> left
        assertTrue(it.isValid());
        assertEquals(i1, it.get());

        it.next(); //root -> right
        assertTrue(it.isValid());
        assertEquals(i3, it.get());

        it.next(); //root -> right -> right
        assertTrue(it.isValid());
        assertEquals(i4, it.get());

        it.next(); //root -> right -> right -> right
        assertTrue(it.isValid());
        assertEquals(i5, it.get());

        it.next(); //done
        assertFalse(it.isValid());

    }

    public void testMkDFSIterator() {
        Tree instance = getScenerioE();

        Iterator it = instance.mkDFSIterator();

        assertTrue(it.isValid());
        assertEquals(i4, it.get());

        it.next(); //root -> left
        assertTrue(it.isValid());
        assertEquals(i1, it.get());

        it.next(); //root -> right
        assertTrue(it.isValid());
        assertEquals(i3, it.get());

        it.next(); //root -> left -> right
        assertTrue(it.isValid());
        assertEquals(i2, it.get());

        it.next(); //root -> left -> right -> left
        assertTrue(it.isValid());
        assertEquals(i5, it.get());

        it.next(); //done
        assertFalse(it.isValid());


        //scenario F
        instance = getScenerioF();

        it = instance.mkDFSIterator();

        assertTrue(it.isValid());
        assertEquals(i2, it.get());

        it.next(); //root -> left
        assertTrue(it.isValid());
        assertEquals(i1, it.get());

        it.next(); //root -> right
        assertTrue(it.isValid());
        assertEquals(i3, it.get());

        it.next(); //root -> right -> right
        assertTrue(it.isValid());
        assertEquals(i4, it.get());

        it.next(); //root -> right -> right -> right
        assertTrue(it.isValid());
        assertEquals(i5, it.get());

        it.next(); //done
        assertFalse(it.isValid());

    }

    public void testMkPreorderIterator() {
        Tree instance = getScenerioJ();

        Iterator it = instance.mkPreorderIterator();

        assertTrue(it.isValid());
        assertEquals(i4, it.get());

        it.next(); //root -> left
        assertTrue(it.isValid());
        assertEquals(i2, it.get());

        it.next(); //root -> right
        assertTrue(it.isValid());
        assertEquals(i1, it.get());

        it.next(); //root -> left -> right
        assertTrue(it.isValid());
        assertEquals(i3, it.get());

        it.next(); //root -> left -> right -> left
        assertTrue(it.isValid());
        assertEquals(i6, it.get());

        it.next(); //root -> left -> right -> left
        assertTrue(it.isValid());
        assertEquals(i5, it.get());

        it.next(); //root -> left -> right -> left
        assertTrue(it.isValid());
        assertEquals(i7, it.get());

        it.next(); //done
        assertFalse(it.isValid());

    }

    public void testMkInorderIterator() {
        Tree instance = getScenerioJ();

        Iterator it = instance.mkInorderIterator();

        assertTrue(it.isValid());
        assertEquals(i1, it.get());

        it.next(); //root -> left
        assertTrue(it.isValid());
        assertEquals(i2, it.get());

        it.next(); //root -> right
        assertTrue(it.isValid());
        assertEquals(i3, it.get());

        it.next(); //root -> left -> right
        assertTrue(it.isValid());
        assertEquals(i4, it.get());

        it.next(); //root -> left -> right -> left
        assertTrue(it.isValid());
        assertEquals(i5, it.get());

        it.next(); //root -> left -> right -> left
        assertTrue(it.isValid());
        assertEquals(i6, it.get());

        it.next(); //root -> left -> right -> left
        assertTrue(it.isValid());
        assertEquals(i7, it.get());

        it.next(); //done
        assertFalse(it.isValid());

    }
    
    public void testMkPostorderIterator() {
        Tree instance = getScenerioJ();

        Iterator it = instance.mkPostorderIterator();

        assertTrue(it.isValid());
        assertEquals(i1, it.get());

        it.next(); //root -> left
        assertTrue(it.isValid());
        assertEquals(i3, it.get());

        it.next(); //root -> right
        assertTrue(it.isValid());
        assertEquals(i2, it.get());

        it.next(); //root -> left -> right
        assertTrue(it.isValid());
        assertEquals(i5, it.get());

        it.next(); //root -> left -> right -> left
        assertTrue(it.isValid());
        assertEquals(i7, it.get());

        it.next(); //root -> left -> right -> left
        assertTrue(it.isValid());
        assertEquals(i6, it.get());

        it.next(); //root -> left -> right -> left
        assertTrue(it.isValid());
        assertEquals(i4, it.get());

        it.next(); //done
        assertFalse(it.isValid());

    }

}
