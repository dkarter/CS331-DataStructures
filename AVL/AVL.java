public class AVL<K extends Comparable<K>, V extends Comparable<V>> {

   private class Node {
      K key;
      V value;
      int height;

      Node left;
      Node right;

      public Node(K key, V value) {
         this.key = key;
         this.value = value;
         height = 1;
      }

      private int calcHeight() {
         // System.out.print("calcHeight ");
         // System.out.print(key);
         // System.out.print(" ");
         height = Math.max(height(left),height(right)) + 1;
         // System.out.println(height);
         return height;
      }

      private int height(Node x) {
         if (x==null)
            return 0;
         else return x.height;
      }

      private int balance() {
         return height(this.left) - height(this.right);
      }
   }

   int size;
   Node root;

   public AVL() {
      size = 0;
      root = null;
   }

   public int size() {
      return size;
   }

   public void add(K key, V value) {
      Node nu = new Node(key,value);

      if (root == null) {
         root = nu;
         size++;
         return;
      }

      addAux(root,nu);
   }


   // This helper returns whether or not we should keep
   // updating balances.


   private boolean addAux(Node current, Node nu) {
      boolean result;

      // This shouldn't happen, but just in case...
      if (current==null)
         return true;

      if (nu.key.compareTo(current.key) == 0) {
         current.value = nu.value;
         return false;
      }

      // Go right?
      if (nu.key.compareTo(current.key) > 0) {
         if (current.right==null) {
            current.right=nu;
            // System.out.print("Added right of ");
            // System.out.println(current.key);
            current.calcHeight();
            size++;
            return (current.balance() != 0);
         } else {
            result = addAux(current.right,nu);
            if (result) {
               current.calcHeight();
               if (current.balance() == -2) {
                  if (current.right.balance() == 1) {
                     doubleLeft(current);
                  } else {
                     singleLeft(current);
                  }
                  return false;
               }
               return (current.balance() != 0);
            } else
               return false;
         }
      } else { // go left
         if (current.left==null) {
            current.left=nu;
            current.calcHeight();
            size++;
            return (current.balance() != 0);
         } else {
            result = addAux(current.left,nu);
            if (result) {
               current.calcHeight();
               if (current.balance() == 2) {
                  if (current.left.balance() == -1) {
                     doubleRight(current);
                  } else {
                     singleRight(current);
                  }
                  return false;
               }
               return (current.balance() != 0);
            } else
               return false;
         }
      }
   }

   public V find(K desire) {
      Node current = root;

      while (current != null) {
         if (current.key.compareTo(desire) == 0)
            return current.value;
         if (desire.compareTo(current.key) > 0)
            current = current.right;
         else
            current = current.left;
      }
      return null;
   }

   public void delete(K key) {
      if (root==null) return;
      deleteAux(root,null,key);
   }

   private void cut(Node parent, Node child, Node adoptee) {
      if (parent==root) {
         root = adoptee;
         return;
      } else if (parent.left == child)
         parent.left = adoptee;
      else
         parent.right = adoptee;
   }

   private Node findPred(Node current) {
      Node tmp = current.left;
      while (tmp.right != null) {
         tmp=tmp.right;
      }
      return tmp;
   }

   private boolean deleteAux(Node current, Node parent, K key) {
      int tmp = 0;
      // Opso?
      if (current==null) return false;

      if (key.compareTo(current.key) == 0) {
         // Gotcha
         size--;
         if (current.left == null && current.right == null)
            cut(parent,current,null);
         else if (current.left == null)
            cut(parent,current,current.right);
         else if (current.right == null)
            cut(parent,current,current.left);
         else {  // Two children
            // Delete the predecessor
            Node pred = findPred(current);
            //System.out.print("Deleting predecessor: ");
            //System.out.println(pred.key);
            current.key = pred.key;
            current.value = pred.value;
            deleteAux(current.left,current,pred.key);
            current.calcHeight();
            tmp = current.balance();
            // We might need to rotate now.
            if (tmp == -2) { // Need to rotate
               if (current.right.balance() == 1)
                  doubleLeft(current);
               else
                  singleLeft(current);
               return true;
            }
            return current.balance() == 0;
         }
         return true;
      }

      // Go right?
      if (key.compareTo(current.key) > 0) {
         if (deleteAux(current.right,current,key)) {
            current.calcHeight();
            tmp = current.balance();
            if (tmp == 1)
               return false;
            if (tmp == 2) { // Need to rotate
               if (current.left.balance() == -1)
                  doubleRight(current);
               else
                  singleRight(current);
            }
            return true;
         } else
            return false;
      } else { // Go left
         if (deleteAux(current.left,current,key)) {
            current.calcHeight();
            tmp = current.balance();
            if (tmp == -1)
               return false;
            if (tmp == -2) { // Need to rotate
               if (current.right.balance() == 1)
                  doubleLeft(current);
               else
                  singleLeft(current);
            }
            return true;
         } else
            return false;
      }


   }
   // Rotate left
   private void singleLeft(Node center) {
      //System.out.print("Single Left ");
      //System.out.println(center.key);
      Node nu = new Node(center.key,center.value);

      nu.left = center.left;
      nu.right = center.right.left;

      center.key = center.right.key;
      center.value = center.right.value;

      center.left = nu;
      center.right = center.right.right;

      nu.calcHeight();
      center.calcHeight();
   }

   // Rotate right
   private void singleRight(Node center) {
      //System.out.println("Single Right");
      Node nu = new Node(center.key,center.value);

      nu.left = center.left.right;
      nu.right = center.right;

      center.key = center.left.key;
      center.value = center.left.value;

      center.left = center.left.left;
      center.right = nu;

      nu.calcHeight();
      center.calcHeight();
   }

   // Double Left
   private void doubleLeft(Node center) {
      //System.out.println("Double Left");
      singleRight(center.right);
      singleLeft(center);
   }

   // Double Right
   private void doubleRight(Node center) {
      //System.out.println("Double Right");
      singleLeft(center.left);
      singleRight(center);
   }

   // Iterators

   public class BFSIterator implements Iterator<K> {
      private Queue<Node> q;
      private AVL parent;

      public BFSIterator(AVL parent) {
         q = new Queue<Node>();
         this.parent = parent;
         if (parent.root != null)
            q.enqueue(root);
      }

      public K get() {
         Node t = q.front();
         if (t != null)
            return t.key;
         else
            return null;
      }

      public void next() {
         Node t = q.dequeue();
         if (t != null) {
            if (t.left != null)
               q.enqueue(t.left);
            if (t.right != null)
               q.enqueue(t.right);
         }
      }

      public boolean isValid() {
         return (q.front() != null);
      }

   }

   public Iterator<K> mkBFiterator() {
      return new BFSIterator(this);
   }
}
