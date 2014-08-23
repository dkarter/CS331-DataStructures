
import java.util.LinkedList;




public class BST<K extends Comparable, V extends Comparable> {

    private class Node {
        public Node right;
        public Node left;
        public K key;
        public V value;

        public Node()
        {}

        public Node(K key, V value, Node left, Node right)
        {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }

        public void setNode(K key, V value, Node left, Node right)
        {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

    private class BFSIterator implements Iterator<K>
    {
        private Node cursor;
        private LinkedList<Node> q;

        public BFSIterator() {
            q = new LinkedList<Node>();

            cursor = root;
            enqueueChildren();
        }



        /*
         * Queue<E> q = new Queue<E>();

	if (root == null)
		return false;

	q.enqueue(root);

	while (q.size() > 0)
	{
		Node currNode = q.dequeue();

		if (currNode.data.equals(data))
			return true; //found

		//enqueue chidlren
		if (currNode.left != null)
			q.enqueue(currNode.left);

		if (currNode.right != null)
			q.enqueue(currNode.right);
	}

	return false;
         */

        public K get() {
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

        public void delete() {
            if (isValid())
                deleteInvoker(cursor.key);
        }

    }

    private int size;
    private Node root;
    Node prev;

    public int size() {
        return this.size;
    }

    public void add(K key, V value)
    {
        if (root == null)
        {
            root = new Node(key, value, null, null);
            size++;
        }
        else
            add(key, value, root);
    }

    private void add(K key, V value, Node node)
    {
        if (node==null) return;
            
        if (node.key.compareTo(key) < 0)
        {
            //go to the right
            if (node.right == null)
            {
                node.right = new Node(key, value, null, null);
                //increment size
                size++;
            }
            else
                add(key, value, node.right);
        }
        else if (node.key.compareTo(key) > 0)
        {
            //go to the left
            if (node.left == null)
            {
                node.left = new Node(key, value, null, null);
                //increment size
                size++;
            }
            else
                add(key, value, node.left);
        }
        else
            //key is identical - replace node data
            node.setNode(key, value, node.left, node.right);

    }

    public Vfind(K key)
    {
        Node result = find(key, root);
        return (result != null)? result.value : null;
    }

    private Node find(K key, Node node)
    {
        if (node == null)
            return null;

        if (node.key.compareTo(key) < 0)
        {
            prev = node;
            //go to right
            return find(key, node.right);
        }
        else if (node.key.compareTo(key) > 0)
        {
            prev = node;
            return find(key, node.left);
        }
        else
            return node;
    }

    public K revFind(V value)
    {
        return revFind(value, root);
    }

    private K revFind(V value, Node node)
    {
        //Depth First Search
        if (node == null)
		return null;

        if (node.value.compareTo(value) == 0)
            return node.key;

        K result =  revFind(value, node.left);
        return (result != null) ? result : revFind(value, node.right);
    }

    //public void delete(K key)
    //{
    //   delete(key, root);
    //}

    public void deleteInvoker(K key)
    {
        delete(key);
    }

    //not thread-safe, but this is not the objective of this course anyway
    public void delete(K key)
    {
        prev = null;
        Node target = find(key, root);

       
        if (target == null)
            return; //not found

        //delete leaf
        if (childCount(target) == 0)
            if (target == root)
                root = null;
            else if (target.key.compareTo(prev.key) > 0)
                prev.right = null;
            else
                prev.left = null;

        //delete branch node with one child
        else if (childCount(target) == 1)
            if (target == root)
                if (target.left != null)
                    root = target.left;
                else
                    root = target.right;
            //from left?
            else if (target.key.compareTo(prev.key) > 0)
                if (target.left != null)
                    prev.right = target.left;
                else
                    prev.right = target.right;
            //from right?
            else
                if (target.left != null)
                    prev.left = target.left;
                else
                    prev.left = target.right;

        //two children
        else if (childCount(target) == 2)
        {
            //get min element from right side
            Node temp = inord(target);

            //do i need to remove the leaf node "temp" from the tree after subtituting
            if (target == root)
                root.setNode(temp.key, temp.value, root.left, root.right);
            else
                if (target.key.compareTo(prev.key) > 0)
                  prev.right.key = temp.key;
                else
                  prev.left.key = temp.key;

         }


          size--;
    }

    private Node inord(Node node) {
        int t = 0;
        Node ret, prev = new Node();
        prev = node;
        node = node.right;
        
        while (node.left != null) {
          prev = node;
          node = node.left;
          t = 1;
        }
        
        ret = node;

        if (t == 0)
          prev.right = node.right;
        else
          prev.left = node.left;//null;

        //node = null;

        return ret;
    }

    //count how many children a node has
    private int childCount(Node node) {
        int ret;
        if ( (node.left != null) && (node.right != null))
            ret = 2;
        else if ((node.left == null) && (node.right == null))
            ret = 0;
        else
            ret = 1;

        return ret;
    }

            
            /*{
            if(node == null)
                return null;   // Item nonode found; do nothing

            if(key.compareTo(node.key) < 0)
                node.left = delete(key, node.left);
            else if(key.compareTo(node.key) > 0)
                node.right = delete(key, node.right);
            else if(node.left != null && node.right != null) // Two children
            {
                Node min = findMin(node.right);
                node.key = min.key;
                node.value = min.value;
                
                node.right = delete(node.key, node.right);
            }
            else
                node = (node.left != null) ? node.left : node.right;
            return node;
     }


    private Node findMin(Node node )
    {
        if(node == null)
            return null;
        else if(node.left == null)
            return node;
        return findMin(node.left);
    }*/

    public Iterator<K> mkBFSIterator()
    {
        return new BFSIterator();
    }

    
}
