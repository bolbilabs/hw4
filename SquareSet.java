import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 * Homework 4
 * CS1331
 * @author dfriedman32
 * @version 1.1
 */
public class SquareSet implements Set<Square>, Iterable<Square> {
    private Collection<Square> list;
    private Square[] backingarr;
    private int arrSize;
    private int indexPointer = 0;
    private int nextIndex = 0;
    private boolean preset = false;

    /**
     * Creates empty Backing Array that represents Set.
     */
    public SquareSet() {
        backingarr = new Square[10];
        arrSize = backingarr.length;
    }
    /**
     * Transfers a collection of unspecified type to backing
     * array.
     * @param  c             Collection of a Set of Squares
     */
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
        if (e.getFile() < 'a' || e.getFile() > 'h'
            ||
            e.getRank() < '1' || e.getRank() > '8') {
            throw new InvalidSquareException("" + e.getFile() + e.getRank());
        }
        ensureCapacity(indexPointer + 1);
        for (Square item: backingarr) {
            if (e.equals(item)) {
                return false;
            }
        }
        backingarr[indexPointer] = e;
        indexPointer++;
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
            if (arrSize - cursor <= 2
                ||
                backingarr[cursor] == null) {
                return false;
            }
            return cursor < backingarr.length;
        }
    }

    @Override
    public boolean contains(Object o) {
        for (Square item: backingarr) {
            if (o.equals(item)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Set) {
            return this.hashCode() == o.hashCode();
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        int total = 0;
        for (Square item: this) {
            total += item.hashCode();
        }
        return total;
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
        int trueCounter = 0;
        int totalLoop = 0;

        if (c.size() > 0) {
            for (Square item : c) {
                totalLoop++;
                foundSame = false;
                if (c == null) {
                    throw new NullPointerException();
                }
                if (item.getFile() < 'a' || item.getFile()
                    > 'h'
                    ||
                    item.getRank() < '1' || item.getRank()
                    > '8') {
                    throw new InvalidSquareException(""
                    + item.getFile() + item.getRank());
                }
                for (Square item2: backingarr) {
                    if (item.equals(item2)) {
                        foundSame = true;
                        trueCounter++;
                    }
                }
                if (!foundSame) {
                    ensureCapacity(indexPointer + 1);
                    backingarr[indexPointer] = item;
                    indexPointer++;
                }
            }
        }
        if (trueCounter < totalLoop) {
            return true;
        }
        return false;
    }



    @Override
    public Object[] toArray() {
        int toArrSize = 0;
        for (Square item: this) {
            toArrSize++;
        }
        Square[] squareArr = new Square[toArrSize];
        int squareArrIndex = 0;
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
        int nonNullCounter = 0;
        if (o == null) {
            throw new NullPointerException();
        }
        for (Square item: backingarr) {
            if (o.equals(item)) {
                backingarr[removalIndex] = null;
                for (Square item2: backingarr) {
                    if (item2 != null) {
                        nonNullCounter++;
                    }
                }
                Square[] newList = new Square[nonNullCounter];
                int itemIndex = 0;
                for (Square item2: backingarr) {
                    if (item2 != null) {
                        newList[itemIndex] = item2;
                        itemIndex++;
                    }
                }
                backingarr = new Square[nonNullCounter];
                itemIndex = 0;
                for (Square item2: newList) {
                    backingarr[itemIndex] = item2;
                    itemIndex++;
                }
                System.out.println("Array Size: " + backingarr.length);
                arrSize = backingarr.length;
                indexPointer--;
                return true;
            }
            removalIndex++;
        }
        return false;
    }

    /**
     * Removes all Nulls from an array.
     * @param  a             Array of Squares
     * @return          Array of Squares without null elements
     */
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
                System.out.println(squareList[i - counter2]);
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
}
