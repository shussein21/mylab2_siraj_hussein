package utils;

import java.util.Arrays;

public class ArrayList<E> extends AbstractList<E> {

    private Object[] data;
    private int capacity;

    public ArrayList() {
        capacity = 10;              // default capacity
        data = new Object[capacity];
    }

    @Override
    public void add(E element) {
        if (size == capacity) {
            grow();
        }
        data[size] = element;
        size++;
    }

    @Override
    public E get(int index) {
        checkIndex(index);
        return (E) data[index];
    }

    @Override
    public E remove(int index) {
        checkIndex(index);
        E removed = (E) data[index];

        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }

        data[size - 1] = null;
        size--;
        return removed;
    }

    private void grow() {
        capacity = capacity * 2;
        data = Arrays.copyOf(data, capacity);
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index);
        }
    }
}
