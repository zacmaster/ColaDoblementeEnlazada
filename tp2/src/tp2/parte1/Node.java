package tp2.parte1;

public class Node<T> {
	public T element;
	public Node<T> next;
	public Node<T> previous;
	public Node(T element, Node<T> next){
		this.element = element;
		this.next = next;		
	}
	public Node(T element){
		this.element = element;
		this.next = null;
		this.previous = null;
	}
	public Node(){
		this.element = null;
		this.next = null;
	}

}
