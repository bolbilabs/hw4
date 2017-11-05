import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;


public class SquareSet implements Set<Square>, Iterable<Square> {
    private Collection<Square> list;
    private Square[] backingarr;
    private int arrSize;
    private int indexPointer = 0;
    private int nextIndex = 0;
    private boolean preset = false;



    public static void main(String[] args) {
        // SquareSet set = new SquareSet();

        Collection<Square> arr = new ArrayList<Square>();
        try {
            arr.add(new Square("a1"));
            arr.add(new Square("a1"));
            arr.add(new Square("a2"));
            arr.add(new Square("a3"));
            arr.add(new Square("a4"));
        } catch (InvalidSquareException e) {
            System.out.println("bad 1: " + e.getMessage());
        }
        SquareSet set = new SquareSet(arr);

        Object[] squareList = set.toArray();
        for (Object item: squareList) {
            System.out.println(item);
        }
        try {
            System.out.println(set.add(new Square("a1")));
            System.out.println(set.add(new Square("a1")));
            System.out.println(set.add(new Square("a2")));
            System.out.println(set.add(new Square("a3")));
            System.out.println(set.add(new Square("a4")));
            System.out.println(set.add(new Square("a5")));
            System.out.println(set.add(new Square("a6")));
            System.out.println(set.add(new Square("a7")));
            System.out.println(set.add(new Square("a7")));
            System.out.println(set.add(new Square("a8")));
            System.out.println(set.add(new Square("b1")));
            System.out.println(set.add(new Square("b2")));
            System.out.println(set.add(new Square("b3")));
            System.out.println(set.add(new Square("b4")));
            System.out.println(set.add(new Square("b5")));
            System.out.println(set.add(new Square("b6")));
            System.out.println(set.add(new Square("b7")));
            System.out.println(set.add(new Square("c1")));
            System.out.println(set.add(new Square("c2")));
            System.out.println(set.add(new Square("c3")));
            System.out.println(set.add(new Square("c4")));
            System.out.println(set.add(new Square("c5")));
            System.out.println(set.add(new Square("c6")));
            System.out.println(set.add(new Square("c7")));
        } catch (InvalidSquareException e) {
            System.out.println("bad 1: " + e.getMessage());
        }
        for (Square item: set) {
            System.out.println(item.toString());
        }
        try {
            System.out.println(set.contains(new Square("c5")));
        } catch (InvalidSquareException e) {
            System.out.println("bad 1: " + e.getMessage());
        }
        System.out.println(set.size());
        System.out.println(set.isEmpty());

        Iterator daIter = set.iterator();
        try {
            while (daIter.hasNext()) {
                if (daIter.next().equals(new Square("c5"))) {
                    System.out.println("Found the Square!");
                }
            }
        } catch (InvalidSquareException e) {
            System.out.println("bad 1: " + e.getMessage());
        }

        Square[] squareList2 = set.toArray(new Square[1]);
        for (Object item: squareList2) {
            System.out.println(item);
        }


        Collection<Square> arr2 = new ArrayList<Square>();
        try {
            arr2.add(new Square("a1"));
            arr2.add(new Square("a3"));
            arr2.add(new Square("c8"));
        } catch (InvalidSquareException e) {
            System.out.println("bad 1: " + e.getMessage());
        }

        System.out.println(arr2);
        System.out.println(set.containsAll(arr2));

        try {
            System.out.println(set.remove(new Square("a1")));
            System.out.println(set.remove(new Square("b2")));
            System.out.println(set.remove(new Square("c3")));
            System.out.println(set.remove(new Square("d3")));

        } catch (InvalidSquareException e) {
            System.out.println("bad 1: " + e.getMessage());
        }
        for (Square item: set) {
            System.out.println(item.toString());
        }
    }


    public SquareSet() {
        backingarr = new Square[10];
        arrSize = backingarr.length;
    }
    public SquareSet(Collection<Square> c) {
        backingarr = new Square[c.size()];
        addAll(c);
        preset = true;
        arrSize = backingarr.length;
        this.list = c;
    }

    @Override
    public boolean add(Square e) {
        if (e == null) {
            throw new NullPointerException();
        }
        // if (preset) {
        //     if (list.size() > 0) {
        //         for (Square item : list) {
        //             //System.out.println(item);
        //             if (e.equals(item)) {
        //                 return false;
        //             }
        //         }
        //     }
        //     list.add(e);
        //     for (Square item : list) {
        //         System.out.println(item);
        //     }
        //     return true;
        // } else {
        ensureCapacity(indexPointer + 1);
        for (Square item: backingarr) {
            // System.out.println(item);
            if (e.equals(item)) {
                return false;
            }
        }
        backingarr[indexPointer] = e;
        indexPointer++;
        // for (Square item: backingarr) {
        //     System.out.println(item);
        // }
        return true;
    }

    @Override
    public Iterator<Square> iterator() {
        return new DynamicArrayIterator();
    }

    private class DynamicArrayIterator implements
        Iterator<Square> {
        private int cursor = 0;

        @Override
        public Square next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            int dex = cursor;
            cursor++;
            return backingarr[dex];
        }
        @Override
        public boolean hasNext() {
            if (arrSize - cursor <= 1
                ||
                backingarr[cursor] == null) {
                return false;
            }
            return cursor < backingarr.length;
        }
    }

    @Override
    public boolean contains(Object o) {
        //System.out.println(this);
        for (Square item: backingarr) {
            if (o.equals(item)) {
                return true;
            }
        }
        return false;
    }
    @Override
    public int hashCode() {
        return 1;
    }
    @Override
    public int size() {
        return indexPointer;
    }
    @Override
    public boolean isEmpty() {
        return indexPointer == 0;
    }
    /**
     * Increases the capacity of this <tt>ArrayList</tt> instance, if
     * necessary, to ensure that it can hold at least the number of elements
     * specified by the minimum capacity argument.
     *
     * @param   minCapacity   the desired minimum capacity
     */
    public void ensureCapacity(int minCapacity) {
        if (minCapacity > arrSize) {
            Square[] oldData = backingarr;
            backingarr = new Square[minCapacity + 10];
            arrSize = minCapacity + 10;
            int i = 0;
            for (Square item : oldData) {
                backingarr[i] = oldData[i];
                i++;
            }
        }
    }


    @Override
    public boolean addAll(Collection<? extends Square> c) {
        boolean foundSame = false;
        if (c.size() > 0) {
            for (Square item : c) {
                foundSame = false;
                //System.out.println(item);
                if (c.contains(null)) {
                    throw new NullPointerException();
                }
                for (Square item2: backingarr) {
                    if (item.equals(item2)) {
                        foundSame = true;
                    }
                }
                if (foundSame) {
                    //System.out.println("Already in.");
                } else {
                    backingarr[indexPointer] = item;
                    indexPointer++;
                    //System.out.println("Placed!");
                }
            }
        }
        // list.add(c);
        // for (Square item : list) {
        //     System.out.println(item);
        // }
        return true;
    }



    @Override
    public Object[] toArray() {
        int toArrSize = 0;
        for (Square item: this) {
            toArrSize++;
        }
        Square[] squareArr = new Square[toArrSize];
        int squareArrIndex = 0;
        // if (this.contains(null)) {
        //     throw new NullPointerException();
        // }
        //This method takes in a collection and converts it
        //To an array for easy use.
        for (Square item: this) {
            if (item.equals(null)) {
                throw new NullPointerException();
            }
            squareArr[squareArrIndex] = item;
            squareArrIndex++;
        }
        return squareArr;
    }

    // @SuppressWarnings("unchecked")
    // public <T> T[] toArray(T[] a) {
    //     int arrSize = 0;
    //     for (Square item: this) {
    //         arrSize++;
    //     }
    //     T[] squareArr = new T[arrSize];
    //     int squareArrIndex = 0;
    //     // if (this.contains(null)) {
    //     //     throw new NullPointerException();
    //     // }
    //     //This method takes in a collection and converts it
    //     //To an array for easy use.
    //     for (Square item: this) {
    //         if (item.equals(null)) {
    //             throw new NullPointerException();
    //         }
    //         squareArr[squareArrIndex] = item;
    //         squareArrIndex++;
    //     }
    //     return (T[]) squareArr;
    // }


    @SuppressWarnings("unchecked")
    @Override
    public <T> T[] toArray(T[] a) {
        int arrIndex = 0;
        if (a.length < arrSize) {
            // Make a new array of a's runtime type, but my contents:
            return (T[]) backingarr;
        }
        for (Square item: backingarr) {
            a[arrIndex] = (T) item;
            arrIndex++;
        }
        //System.arraycopy(backingarr, 0, a, 0, arrSize);
        if (a.length > arrSize) {
            a[arrSize] = null;
        }
        return a;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object item: c) {
            System.out.println(item);
            if (!this.contains(item)) {
                return false;
            }
        }

        return true;
    }



    @Override
    public boolean remove(Object o) {
        int removalIndex = 0;
        if (o == null) {
            throw new NullPointerException();
        }
        for (Square item: backingarr) {
            if (o.equals(item)) {
                backingarr[removalIndex] = null;
                backingarr = removeNull(backingarr);
                arrSize = backingarr.length + 1;
                return true;
            }
            removalIndex++;
        }
        return false;
    }

    public Square[] removeNull(Square[] a) {
        //Initializes Array Size
        int counter =  0;
        for (int i = 0; i < a.length; i++) {
            if (a[i] == (null)) {
                counter++;
            }
        }
        //Places each legal Square into Array
        int counter2 =  0;
        Square[] squareList =  new Square[a.length - counter];
        for (int i = 0; i < a.length; i++) {
            if ((a[i] != (null))) {
                squareList[i - counter2] = a[i];
                System.out.println(squareList[i-counter2]);
            } else {
                counter2++;
            }
        }
        return squareList;
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }
    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }
    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }
    // @Override
    // public boolean addAll(Collection<? extends Square> c) {
    //     throw new UnsupportedOperationException();
    // }
    // @Override
    // public boolean containsAll(Collection<?> c) {
    //     throw new UnsupportedOperationException();
    // }
    // @Override
    // public boolean remove(Object o) {
    //     throw new UnsupportedOperationException();
    // }
    // @Override
    // public boolean add(Square s) {
    //     throw new UnsupportedOperationException();
    // }
    // @Override
    // public <T> T[] toArray(T[] tList) {
    //     throw new UnsupportedOperationException();
    // }
    // @Override
    // public Object[] toArray() {
    //     throw new UnsupportedOperationException();
    // }
    // @Override
    // public Iterator<Square> iterator() {
    //     throw new UnsupportedOperationException();
    // }
    // @Override
    // public boolean contains(Object o) {
    //     throw new UnsupportedOperationException();
    // }
    // @Override
    // public boolean isEmpty() {
    //     throw new UnsupportedOperationException();
    // }
    // @Override
    // public int size() {
    //     throw new UnsupportedOperationException();
    // }
}
