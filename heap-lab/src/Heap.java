public class Heap<E extends Comparable> {
   private int size;
   private Object data[];

   public Heap() {
      data = new Object[64];
      size = 0;
   }

   // Helper functions

   private int left(int i) {
       return 2*i+1;
   }

   private int right(int i) {
      return (2*i+2);
   }

   private int parent(int i) {
      return (int)Math.floor((i-1)/2);
   }

   private E getData(int i)
   {
       return (data.length >= i)? ((E)data[i]) : null;
   }
   
   

   // Main functions

   public int size() {
      return size;
   }

   private void resizeArray()
   {
       Object[] arr = new Object[data.length+64];
       for(int i = 0; i<size; i++)
            arr[i] = data[i];
       data = arr;
   }

   public void enqueue(E elt)
   {
       //is it time to resize the array?
       if (data.length <= size)
           resizeArray();

       data[size] = elt;
       
       if (size >= 1)
       {
           //if bigger then parent than we have to move some stuff around
           boolean unbalanced = (getData(size).compareTo(getData(parent(size))) < 0);

           int index = size;
           while (unbalanced && index > 0)
           {
               unbalanced = (getData(index).compareTo(getData(parent(index))) < 0);
               if (unbalanced)
               {
                   Object temp = data[index];
                   data[index] = data[parent(index)];
                   data[parent(index)] = temp;
                   index = parent(index);
               }
                
           }
       }

       size++;
   }

   public E dequeue()
   {
       //delete top and return (delete according to heap spec;
       E temp = top();
       delete();
       return temp;
   }

   private void delete()
   {
       if (size > 1)
       {
           data[0] =  data[size-1];
           data[size-1] = null;

           //if bigger then parent than we have to move some stuff around
           boolean biggerLeft = (getData(left(0)) !=null &&
                   getData(0).compareTo(getData(left(0))) > 0);
           boolean biggerRight = (getData(right(0)) != null &&
                   getData(0).compareTo(getData(right(0))) > 0);

           int index = 0;
           while ((biggerLeft || biggerRight) && index < Math.floor(size/3))
           {
               biggerLeft = (getData(left(index)) != null &&
                       getData(index).compareTo(getData(left(index))) > 0);
               biggerRight = (getData(right(index)) != null &&
                       getData(index).compareTo(getData(right(index))) > 0);

               int targetIndex;

               if (biggerLeft && biggerRight)
               {
                   //decide which one is smaller
                   targetIndex =
                           (getData(left(index)).compareTo(getData(right(index))) < 0)?
                               left(index) : right(index);
               }
               else if (biggerLeft)
                    //take left and interchange with parent - keep on iterating
                    targetIndex = left(index);
               else if (biggerRight)
                   //take right and interchange with parent - keep on iterating
                   targetIndex = right(index);
               else
                   //none exit loop
                   break;

               Object temp = data[index];
               data[index] = data[targetIndex];
               data[targetIndex] = temp;
               index = targetIndex;
           }

           size--;
       }
       else
       {
           data[0]=null;
           size = 0;
       }

       
   }

   public E top () {
      return (E)(data[0]);
   }
}
