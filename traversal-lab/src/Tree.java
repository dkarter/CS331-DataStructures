
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Dor
 */
public class Tree {
    private class Node {
        public Node right;
        public Node left;
        public int key;

        public Node()
        {}

        public Node(int key, Node left, Node right)
        {
            this.key = key;
            this.left = left;
            this.right = right;
        }

        public void setNode(int key, Node left, Node right)
        {
            this.key = key;
            this.left = left;
            this.right = right;
        }
    }

    private int size;
    private Node root;

    public Tree()
    {
        size = 0;
    }

    public int size() {
        return this.size;
    }

    public void add(int key)
    {
        if (root == null)
        {
            root = new Node(key, null, null);
            size++;
        }
        else
            add(key, root);
    }

    private void add(int key, Node node)
    {
        if (node==null) return;

        if (node.key < key)
        {
            //go to the right
            if (node.right == null)
            {
                node.right = new Node(key, null, null);
                //increment size
                size++;
            }
            else
                add(key, node.right);
        }
        else if (node.key > key)
        {
            //go to the left
            if (node.left == null)
            {
                node.left = new Node(key, null, null);
                //increment size
                size++;
            }
            else
                add(key, node.left);
        }
        else
            //key already exists - ignore
            return;

    }

    private class BFSIterator implements Iterator
    {
        private Node cursor;
        private LinkedList<Node> q;

        public BFSIterator() {
            q = new LinkedList<Node>();

            cursor = root;
            enqueueChildren();
        }

        public int get() {
            return isValid()? cursor.key : null;
        }

        public void next() {
            cursor = q.poll();
            enqueueChildren();
        }

        private void enqueueChildren()
        {
            if (!isValid()) return;

            if (cursor.left != null)
                q.offer(cursor.left);

            if (cursor.right != null)
                q.offer(cursor.right);
        }

        public boolean isValid() {
            return (cursor != null);
        }


    }

    public Iterator mkBFSIterator()
    {
        return new BFSIterator();
    }

    ////////////////////////////////////////////////////////////////////
    //================================================================//
    ////////////////////////////////////////////////////////////////////

    private class DFSIterator implements Iterator
    {
        private LinkedList<Node> nextQueue = new LinkedList<Node>();

        private void dfsEnqueue(Node node)
        {
            if (node != null)
            {
                nextQueue.offer(node);
                dfsEnqueue(node.left);
                dfsEnqueue(node.right);

            }
        }

        private Node cursor;
        

        public DFSIterator() {
            dfsEnqueue(root);
            next();
        }

        public int get() {
            return isValid()? cursor.key : null;
        }

        public void next() {
            cursor = nextQueue.poll();
        }

        public boolean isValid() {
            return (cursor != null);
        }


    }

    public Iterator mkDFSIterator()
    {
        return new DFSIterator();
    }

    ////////////////////////////////////////////////////////////////////
    //================================================================//
    ////////////////////////////////////////////////////////////////////

    private class PreorderIterator implements Iterator
    {
        private Node cursor;
        private LinkedList<Node> q;



        public PreorderIterator() {
            q = new LinkedList<Node>();

            cursor = root;
            enqueueChildren();
        }

        public int get() {
            return isValid()? cursor.key : null;
        }

        public void next() {
            cursor = q.poll();
            enqueueChildren();
        }

        private void enqueueChildren()
        {
            if (!isValid()) return;

            if (cursor.left != null)
                q.offer(cursor.left);

            if (cursor.right != null)
                q.offer(cursor.right);
        }

        public boolean isValid() {
            return (cursor != null);
        }


    }

    public Iterator mkPreorderIterator()
    {
        return new DFSIterator();
    }

    ////////////////////////////////////////////////////////////////////
    //================================================================//
    ////////////////////////////////////////////////////////////////////

    private class InorderIterator implements Iterator
    {
        private LinkedList<Node> nextQueue = new LinkedList<Node>();

        private void enqueue(Node node)
        {
            if (node != null)
            {
                enqueue(node.left);
                nextQueue.offer(node);
                enqueue(node.right);
            }
        }

        private Node cursor;


        public InorderIterator() {
            enqueue(root);
            next();
        }

        public int get() {
            return isValid()? cursor.key : null;
        }

        public void next() {
            cursor = nextQueue.poll();
        }

        public boolean isValid() {
            return (cursor != null);
        }


    }

    public Iterator mkInorderIterator()
    {
        return new InorderIterator();
    }

    ////////////////////////////////////////////////////////////////////
    //================================================================//
    ////////////////////////////////////////////////////////////////////

    private class PostorderIterator implements Iterator
    {
        private LinkedList<Node> nextQueue = new LinkedList<Node>();

        private void enqueue(Node node)
        {
            if (node != null)
            {
                enqueue(node.left);
                enqueue(node.right);
                nextQueue.offer(node);
            }
        }

        private Node cursor;


        public PostorderIterator() {
            enqueue(root);
            next();
        }

        public int get() {
            return isValid()? cursor.key : null;
        }

        public void next() {
            cursor = nextQueue.poll();
        }

        public boolean isValid() {
            return (cursor != null);
        }


    }

    public Iterator mkPostorderIterator()
    {
        return new PostorderIterator();
    }

    ////////////////////////////////////////////////////////////////////
    //================================================================//
    ////////////////////////////////////////////////////////////////////
    
    private class FrontierIterator implements Iterator
    {
        private Node cursor;
        private LinkedList<Node> q;

        public FrontierIterator() {
            q = new LinkedList<Node>();

            cursor = root;
            enqueueChildren();
        }

        public int get() {
            return isValid()? cursor.key : null;
        }

        public void next() {
            cursor = q.poll();
            enqueueChildren();
        }

        private void enqueueChildren()
        {
            if (!isValid()) return;

            if (cursor.left != null)
                q.offer(cursor.left);

            if (cursor.right != null)
                q.offer(cursor.right);
        }

        public boolean isValid() {
            return (cursor != null);
        }


    }

    public Iterator mkFrontierIterator()
    {
        return new BFSIterator();
    }



}
