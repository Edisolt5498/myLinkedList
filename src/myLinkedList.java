import java.util.NoSuchElementException;
public class myLinkedList<T> {
    boolean nextIsNull = false;
    boolean prevIsNull = false;
    int count = 0;

    Point first = new Point(null, null, null);
    Point last = first;
    Point current = first;
    Point temp;

    public myLinkedList () {}
    ////////////////////////////////////////////ADDERS////////////////////////////////////////////
    public void add (T value) {
        if (count == 0) {
            count++;
            first.value = value;
        } else if (count == 1) {
            count++;
            last = new Point(first, null, value);
            first.changeNext(last);
        } else {
            count++;
            temp = last;
            last = new Point(temp, null, value);
            temp.changeNext(last);
        }
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
        } else {
            prevIsNull = true;
        }
        return returnedCurrent;
    }
    ////////////////////////////////////////////GETTERS////////////////////////////////////////////
    public Point getFirst () {return first;}
    public Point getLast () {return last;}
    public Point get (int index) throws Exception {
        int i = 0;
        Point forGet = this.current;
        while (i <= count) {
            if (i != index) {
                forGet = forGet.next;
                i++;
            } else {return forGet;}
        }
        throw new NoSuchElementException();
    }
    ////////////////////////////////////////////IsEMPTY////////////////////////////////////////////
    public boolean isEmpty () {
        return this.count == 0;
    }
    /////////////////////////////////////ELEMENT CLASS//////////////////////////////////////////////
    /////////////////////////////////////ELEMENT CLASS//////////////////////////////////////////////
    /////////////////////////////////////ELEMENT CLASS//////////////////////////////////////////////
    /////////////////////////////////////ELEMENT CLASS//////////////////////////////////////////////
    private class Point {
        Point prev, next;
        T value;
        public Point (Point prev, Point next, T value) {
            this.prev = prev;
            this.next = next;
            this.value = value;
        }

        public void changeNext (Point newNext) {this.next = newNext;}
        public void changePrev (Point newPrev) {this.prev = newPrev;}

        @Override
        public String toString () {return value.toString();}
    }
}