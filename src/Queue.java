

import org.w3c.dom.Node;

import java.util.Iterator;

public class Queue<E>{ //thêm vào đầu tiên lấy ra đầu tiên
    private Node<E> head;
    private int size;
    private static class Node<E>{
        E element;
        Node<E> next;
        public Node( E element){
            this(element,null);
        }
        public Node(E element, Node<E> next){
            this.element = element;
            this.next = next;
        }
    }

    public void offer(E element) { //nhập
        Node<E> newNode = new Node<>(element);
        if (this.head == null) {
            this.head = newNode;
        } else {
            Node<E> current = this.head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        this.size++;
    }
    public E poll() { //lấy 1 cái ra
        ensureNonEmpty();
        E element = this.head.element;
        if (this.size == 1){
            this.head = null;
        } else  {
            Node<E> temp = this.head.next;
            this.head.next = null;
            this.head = temp;
        }
        this.size--;
        return element;
    }
    private void ensureNonEmpty() {
        if (size == 0) throw new IllegalStateException("Empty!");
    }

    public String toString() {
        Node<E> current = head;
        StringBuilder result = new StringBuilder();
        while(current != null){
            result.append(current.element+ " ");
            current = current.next;
        }
        return result.toString();
    }


    public E peek() {
        ensureNonEmpty();
        return this.head.element;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }


    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private Queue.Node<E> current = head;

            @Override
            public boolean hasNext() {
                return current != null;
//                return false;
            }

            @Override
            public E next() {
                E element = current.element;
                current = current.next;
                return element;
            }
        };
    }
}

