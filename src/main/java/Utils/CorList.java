package Utils;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class CorList<F> implements List<F> {

    public CorNode<F> head;

    public void addElement(F e) { //Add element to head of list
        CorNode<F> fn = new CorNode<>();
        fn.setContents(e);
        fn.next = head;
        head = fn;
    }

    public void clear() { //Empty list
        head = null;
    }

    public boolean add(F node) {
        CorNode<F> newItem = new CorNode<>();
        newItem.setContents(node);
        if (head != null) {
            CorNode<F> temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newItem;
        } else {
            head = newItem;
        }
        return true;
    }

    @Override
    public boolean remove(Object o) {
        if (head != null) {
            if (head.getContents().equals(o)) {
                CorNode<F> node = head.next;
                head = node;
                return true;
            }
            CorNode<F> temp = head;
            while (temp.next != null) {
                if (temp.next.getContents().equals(o)) {
                    temp.next = temp.next.next;
                    return true;
                }
                temp = temp.next;
            }
            return false;
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends F> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends F> c) {
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

    public F get(int index) {
        if (head != null && index < size()) {
            CorNode<F> temp = head;
            for (int i = 0; i < index; i++) {
                temp = temp.next;
            }
            return (temp != null) ? temp.getContents() : null;
        }
        return null;
    }

    @Override
    public F set(int index, F element) {
        if (head != null) {
            CorNode<F> temp = head;
            for (int i = 0; i < index; ++i) {
                temp = temp.next;
            }
            CorNode<F> removedElement = new CorNode<>();
            removedElement.setContents(temp.getContents());
            temp.setContents(element);
            return removedElement.getContents();
        }
        return null;
    }

    @Override
    public void add(int index, F element) {
        if (head != null) {
            CorNode<F> node = new CorNode<>();
            node.setContents(element);
            CorNode<F> temp = head;
            if (index == 0) {
                node.next = head;
                head = node;
            } else {
                for (int i = 0; i < index - 1; ++i) {
                    temp = temp.next;
                }
                node.next = temp.next;
                temp.next = node;
            }
        }
    }

    public F remove(int index) {
        if (head != null) {
            CorNode<F> temp = head;
            if (index == 0) {
                head = head.next;
                return temp.getContents();
            } else {
                for (int i = 0; i < index; i++) {
                    temp = temp.next;
                }
                CorNode<F> removedNode = temp;
                temp.next = temp.next.next;
                return removedNode.getContents();
            }
        }
        return null;
    }

    @Override
    public int indexOf(Object o) {
        if (head != null) {
            CorNode<F> temp = head;
            for (int i = 0; i < size(); ++i) {
                if (temp.getContents().equals(o)) {
                    return i;
                }
                temp = temp.next;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        return -1;
    }


    @Override
    public ListIterator<F> listIterator() {
        return null;
    }

    @Override
    public ListIterator<F> listIterator(int index) {
        return null;
    }

    @Override
    public List<F> subList(int fromIndex, int toIndex) {
        return null;
    }

    public int size() {
        if (head != null) {
            int i = 0;
            for (F node : this){
                ++i;
            }
            return i;
        }
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return (head == null) ? true : false;
    }

    @Override
    public boolean contains(Object o) {
        if (head != null){
            for (F node : this){
                if (node.equals(o)) return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<F> iterator() {
        return new CorIterator<>(head);
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }


    public class CorNode<N> {
        public CorNode<N> next;
        private N contents;

        public N getContents() {
            return contents;
        }

        public void setContents(N c) {
            contents = c;
        }
    }

    public class CorIterator<F> implements Iterator<F> {

        private CorNode<F> pos;

        public CorIterator(CorNode<F> node) {
            pos = node;
        }

        @Override
        public boolean hasNext() {
            return pos != null;
        }

        @Override
        public F next() {
            CorNode<F> temp = pos;
            pos = pos.next;
            return temp.getContents();
        }
    }

}
