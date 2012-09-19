package me.yuan.bdd;

import java.util.Collection;
import java.util.Iterator;
import java.util.Stack;

public class MyStack<E> implements Collection<E> {

    private Stack<E> stack;
    private int capacity;

    public MyStack(int capacity) {
        stack = new Stack<E>();
        this.capacity = capacity;
    }

    public MyStack() {
        this(Integer.MAX_VALUE);
    }

    public boolean empty() {
        return stack.empty();
    }

    public void push(E item) {
        if (full()) {
            throw new StackOverflowException();
        }
        stack.push(item);
    }

    public boolean full() {
        return stack.size() == capacity;
    }

    public int size() {
        return stack.size();
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }

    public boolean contains(Object o) {
        return stack.contains(o);
    }

    public Iterator<E> iterator() {
        return stack.iterator();
    }

    public Object[] toArray() {
        return new Object[0];
    }

    public <T> T[] toArray(T[] ts) {
        return null;
    }

    public boolean add(E t) {
        return false;
    }

    public boolean remove(Object o) {
        return false;
    }

    public boolean containsAll(Collection<?> objects) {
        return false;
    }

    public boolean addAll(Collection<? extends E> ts) {
        return false;
    }

    public boolean removeAll(Collection<?> objects) {
        return false;
    }

    public boolean retainAll(Collection<?> objects) {
        return false;
    }

    public void clear() {

    }

    public E peek() {
        return stack.peek();
    }
}
