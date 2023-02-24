import java.util.*;

public class myLinkedList<T> implements List<T> {
    boolean nextIsNull = false;
    boolean prevIsNull = false;
    int count = 0;
    int currentPos;
    Point first = new Point(null, null, null);
    Point last = first;
    Point current = first;
    Point temp;

    public myLinkedList () {}
    ////////////////////////////////////////////ADDERS////////////////////////////////////////////

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

    public void addFirst (T value) {
        temp = first;
        first = new Point(null, temp, value);
        temp.prev = first;
    }
    public void addLast (T value) {
        temp = last;
        last = new Point(temp, null, value);
        temp.next = last;
    }
    public void addAll (myLinkedList<T> otherList) { //second list is united with first list. We are starting with first element of second list,
        if (!otherList.isEmpty()) {
            this.count += otherList.count;
            this.last.next = otherList.first;
            otherList.first.prev = this.last;
            this.last = otherList.last;
        }
    }
    ////////////////////////////////////////////TAKERS////////////////////////////////////////////
    public Point takeNext () {
        if (nextIsNull) {
            throw new NoSuchElementException();
        }
        Point returnedCurrent = current;
        if (current.next != null) {
            current = current.next;
            currentPos++;
        } else {
            nextIsNull = true;
        }
        return returnedCurrent;
    }
    public Point takePrevious () throws Exception {
        if (prevIsNull) {
            throw new NoSuchElementException();
        }
        Point returnedCurrent = current;
        if (current.prev != null) {
            current = current.prev;
            currentPos--;
        } else {
            prevIsNull = true;
        }
        return returnedCurrent;
    }
    ////////////////////////////////////////////GETTERS////////////////////////////////////////////
    public Point getFirst () {
        return first;
    }
    public Point getLast () {
        return last;
    }

    public Point getMy (int index) {
        int i = 0;
        Point forGet = this.current;
        while (i <= count) {
            if (i != index) {
                forGet = forGet.next;
                i++;
            } else {
                return forGet;
            }
        }
        throw new NoSuchElementException();
    }

    @Override
    public T get (int index) {
        return getMy(index).value;
    }

    @Override
    public T set(int index, T element) {
        T oldEl = getMy(index).value;
        getMy(index).value = element;
        return oldEl;
    }
    @Override
    public boolean add(T t) {
        if (count == 0) {
            count++;
            currentPos = 1;
            first.value = t;
        } else if (count == 1) {
            count++;
            last = new Point(first, null, t);
            first.changeNext(last);
        } else {
            count++;
            temp = last;
            last = new Point(temp, null, t);
            temp.changeNext(last);
        }
        return true;
    }
    @Override
    public void add(int index, T element) {
        Point tempForAdd = new Point(getMy(index-1), getMy(index), element); //новый элемент списка с сслками
        getMy(index-1).next = tempForAdd; // ссылка предыдущего элемента меняется на ссылку на новый элемент
        getMy(index).prev = tempForAdd; // ссылка следующего элемента меняется на ссылку на предыдущий элемент
    }
    @Override
    public T remove(int index) {
        Point temp = getMy(index);
        temp.next.prev = temp.prev;
        temp.prev.next = temp.next;
        return temp.value;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<T> listIterator() {
        return new myListItr(current);
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return null;
    }

    private class myListItr implements ListIterator<T> {

        private int itrCount;
        private Point itrCurrent;
        public myListItr(Point current) {
            if (current == null) {
                throw new IndexOutOfBoundsException();
            }
            this.itrCurrent = current;
            this.itrCount = currentPos;
        }
        @Override
        public boolean hasNext() {
            return count >= itrCount++;
        }

        @Override
        public T next() {
            Point temp = itrCurrent;
            itrCurrent = itrCurrent.next;
            return temp.value;
        }

        @Override
        public boolean hasPrevious() {
            return false;
        }

        @Override
        public T previous() {
            return null;
        }

        @Override
        public int nextIndex() {
            return 0;
        }

        @Override
        public int previousIndex() {
            return 0;
        }

        @Override
        public void remove() {

        }

        @Override
        public void set(Object o) {

        }

        @Override
        public void add(Object o) {

        }
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        Point from = getMy(fromIndex);
        Point to = getMy(toIndex);
        Point currentWhile = from;
        List<T> returnList = new ArrayList<>();
        while (currentWhile != to.next) {
            returnList.add(currentWhile.value);
            currentWhile = currentWhile.next;
        }
        return returnList;
    }

    @Override
    public int size() {
        return count;
    }

    ////////////////////////////////////////////IsEMPTY////////////////////////////////////////////
    public boolean isEmpty () {
        return this.count == 0;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return null;
    }
    public void rebootIterator () {
        nextIsNull = false;
        prevIsNull = false;
        current = first;
    }
    /////////////////////////////////////ELEMENT CLASS//////////////////////////////////////////////
    /////////////////////////////////////ELEMENT CLASS//////////////////////////////////////////////
    /////////////////////////////////////ELEMENT CLASS//////////////////////////////////////////////
    /////////////////////////////////////ELEMENT CLASS//////////////////////////////////////////////
    private class Point {
        Point prev;
        Point next;
        T value;
        public Point (Point prev, Point next, T value) {
            this.prev = prev;
            this.next = next;
            this.value = value;
        }

        public void changeNext (Point newNext) {
            this.next = newNext;
        }
        public void changePrev (Point newPrev) {
            this.prev = newPrev;
        }

        @Override
        public String toString () {
            return value.toString();
        }
    }
    /////////////////////////////////////ELEMENT CLASS//////////////////////////////////////////////
    /////////////////////////////////////ELEMENT CLASS//////////////////////////////////////////////
    /////////////////////////////////////ELEMENT CLASS//////////////////////////////////////////////
    /////////////////////////////////////ELEMENT CLASS//////////////////////////////////////////////
    @Override
    public String toString () {
        StringBuilder stringBuilder = new StringBuilder();
        rebootIterator();
        while (true) {
            try {
                stringBuilder.append(takeNext().toString());
            } catch (Exception e) {
                break;
            }
        }
        return stringBuilder.toString();
    }
}