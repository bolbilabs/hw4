import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class SquareSet implements Set<Square>, Iterator<Square> {
    Collection<Square> list;
    Square[] backingarr = new Square[10];
    int arrSize;
    int indexPointer = 0;
    int nextIndex = 0;
    boolean preset = false;



    public static void main(String[] args) {
        SquareSet set = new SquareSet();

        // Collection<Square> arr = new ArrayList<Square>();
        // try {
        //     arr.add(new Square("a1"));
        //     arr.add(new Square("a1"));
        //     arr.add(new Square("a2"));
        // } catch (InvalidSquareException e) {
        //     System.out.println("bad 1: " + e.getMessage());
        // }
        // SquareSet set = new SquareSet(arr);
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
        // Square[] arrayed = set.toArray();
        // for (Square item: arrayed) {
        //     System.out.println(item);
        // }

    }


    public SquareSet () {
        arrSize = backingarr.length;
    }
    public SquareSet(Collection<Square> c) {
        preset = true;
        this.list = c;
    }

    @Override
    public boolean add(Square e) {
        if(e == null) {
            throw new NullPointerException();
        }
        if (preset) {
            if (list.size() > 0) {
                for (Square item : list) {
                    System.out.println(item);
                    if (e.equals(item)) {
                        return false;
                    }
                }
            }
            list.add(e);
            for (Square item : list) {
                System.out.println(item);
            }
            return true;
        } else {
            ensureCapacity(indexPointer + 1);
            for (Square item : backingarr) {
                //System.out.println(item);
                if (e.equals(item)) {
                    return false;
                }
            }
            backingarr[indexPointer] = e;
            indexPointer++;
            return true;
        }
    }

    @Override
    public Iterator<Square> iterator() {
        return this;
    }
    @Override
    public Square next() {
        int dex = nextIndex;
        nextIndex++;
        return backingarr[dex];
    }
    @Override
    public boolean hasNext() {
        if (backingarr[nextIndex] == null) {
            return false;
        }
        return nextIndex < backingarr.length;
    }

    @Override
    public boolean contains(Object o) {

        for (Square item: this) {
            System.out.println("this should print");
            if (item.equals(o)) {
                return true;
            }
        }
        return false;
    }
    @Override
    public int hashCode() {
        return 1;
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
    public Object[] toArray() {
        //This method takes in a collection and converts it
        //To an array for easy use.
        return this.backingarr;
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
    @Override
    public boolean addAll(Collection<? extends Square> c) {
        throw new UnsupportedOperationException();
    }
    @Override
    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }
    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException();
    }
    // @Override
    // public boolean add(Square s) {
    //     throw new UnsupportedOperationException();
    // }
    @Override
    public <T>T[] toArray(T[] tList) {
        throw new UnsupportedOperationException();
    }
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
    @Override
    public boolean isEmpty() {
        throw new UnsupportedOperationException();
    }
    @Override
    public int size() {
        throw new UnsupportedOperationException();
    }
}
