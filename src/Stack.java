
import org.w3c.dom.Node;

import java.util.Iterator;

public class Stack<E>{ // thêm vào cuối cùng lấy ra đầu tiên
    private Node<E> top;
    private int size;
    private static class Node<E>{
        E element;
        Node<E> previous;
        public Node( E element){
            this(element,null);
        }
        public Node(E element, Node<E> previous){
            this.element = element;
            this.previous = previous;
        }
    }
    public Stack(){
    }

    public void push(E element) { //nhập dô
        Node<E> newNode = new Node<>(element);
        newNode.previous = top;
        top = newNode;
        this.size++;
    }
    public E pop() {  //lấy từng cái ra
        ensureNonEmpty();
        E element = this.top.element;
        Node<E> temp = this.top.previous;
        this.top.previous = null;
        this.top = temp;
        this.size--;
        return element;
    }
    private void ensureNonEmpty() {
        if (size == 0) throw new IllegalStateException("Empty!");
    }

    public E peek() {
        ensureNonEmpty();
        return this.top.element;  //lấy element trên cùng
//        return null;
    }

    @Override
    public String toString() {
        Node<E> current = top;
        StringBuilder result = new StringBuilder();
        while(current != null){
            result.append(current.element+ "\n");
            current = current.previous;
        }
        return result.toString();
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean clear() {
        while (!this.isEmpty()) {
            this.pop();
        }
        return this.isEmpty();
    }



    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private Stack.Node<E> current = top;

            @Override
            public boolean hasNext() {
                return current != null;
//                return false;
            }

            @Override
            public E next() {
                E element = current.element;
                current = current.previous;
                return element;
            }
        };
    }
}
