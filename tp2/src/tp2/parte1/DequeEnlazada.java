//Alumno Zacarías Samaniego
package tp2.parte1;

import java.util.Collection;
import java.util.Deque;
import java.util.NoSuchElementException;

public class DequeEnlazada<T> extends DequeBase<T> implements Deque<T>, Iterable<T>{
	
	private Node<T> first; 
	private Node<T> last;
	private int size = 0;
	//
	// Métodos de Deque.
	//

	/**
	 * Añade un elemento en la cabeza del Deque. O(1)
	 */
	@Override
	public void addFirst(T e) {
		if(e != null){
			if (size == 0){
				first = new Node<T>(e,null);
				last = first;
			}
			else{
				Node<T> aux = new Node<T>(e,null);
				Node<T> bak = new Node<T>();
				first.previous = aux;
				bak = first;
				first = aux;
				first.next = bak;
			}
			size++;
		}
	}

	/**
	 * Añade un elemento en la cola del Deque. O(1)
	 */
	@Override
	public void addLast(T e) {
		if(e != null){
			if (size == 0){
				last = new Node<T>(e,null);
				first = last;
			}
			else{
				Node<T> aux = new Node<T>(e,null);
				Node<T> bak = new Node<T>();
				last.next = aux;
				bak = last;
				last = aux;
				last.previous = bak;
			}
			size++;
		}
	}

	/**
	 * Devuelve el primer elemento. O(1)
	 *
	 * Si el Deque está vacío, lanza NoSuchElementException.
	 */
	@Override
	public T getFirst() {
		if (isEmpty())
			throw new NoSuchElementException(); 
		return first.element;
		
	}

	/**
	 * Devuelve el último elemento. O(1)
	 *
	 * Si el Deque está vacío, lanza NoSuchElementException.
	 */
	@Override
	public T getLast() {
		if(isEmpty())
			throw new NoSuchElementException(); 
		return last.element;
	}

	/**
	 * Elimina el primer elemento, y lo devuelve. O(1)
	 *
	 * Si el Deque está vacío, lanza NoSuchElementException.
	 */
	@Override
	public T removeFirst() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		return deleteNode(first);
		
	}

	/**
	 * Elimina el último elemento, y lo devuelve. O(1)
	 *
	 * Si el Deque está vacío, lanza NoSuchElementException.
	 */
	@Override
	public T removeLast() {
		if(isEmpty())
			throw new NoSuchElementException();
		return deleteNode(last);
	}

	// Sugerencia: Para las dos primitivas borrado anteriores y las dos
	// siguientes, usar un único método auxiliar privado:
	//
	//      private T borrarNodo(Nodo<T> nodo);
	//

	private T deleteNode(Node<T> node){
		if (node == null)return null;
		Node<T> aux = new Node<T>();
		Node<T> bak = new Node<T>();
		aux = node;
		if(size == 1){
			bak = node;
			node = null;
			first = null;
			last = null;
			size--;
			return bak.element;
		}
		else if (node.previous == null) {
			bak = first;
			aux = first.next;
			aux.previous = null;
			first = aux;
			size--;
			return bak.element;
			
		}
		else if (node.next == null){
			bak = last;
			aux = last.previous;
			aux.next = null;
			last = aux;
			size--;
			return bak.element;
		}
		else{ //node in the middle
			bak = node;
			aux = node.previous;
			aux.next = aux.next.next;
			aux.next.previous = aux;
			size--;
			return bak.element;
			
		}
	}

	/**
	 * Elimina un elemento, si existe. O(n)
	 *
	 * Elimina por la cabeza, esto es: de estar presente más de una vez,
	 * se elimina la primera instancia.
	 *
	 * Si no existe el elemento, devuelve false.
	 *
	 * La comparación se realiza con equals().
	 */
	@Override
	public boolean removeFirstOccurrence(Object o) {
		if(o == null)return false;
		boolean found = false;
		Node<T> aux = new Node<T>();
		aux = first;
		while(!found && aux != null){
			if (aux.element.equals(o)) {
				deleteNode(aux);
				found = true;
				size--;
			}
			aux = aux.next;
		}
		return found;
	}

	/**
	 * Elimina un elemento, si existe. O(n)
	 *
	 * Elimina por la cola, esto es: de estar presente más de una vez,
	 * se elimina la última instancia.
	 *
	 * Si no existe el elemento, devuelve false.
	 *
	 * La comparación se realiza con equals().
	 */
	@Override
	public boolean removeLastOccurrence(Object o) {
		if(o == null)return false;
		Node<T> aux = new Node<T>();
		aux = last;
		boolean found = false;
		while(!found && aux != null){
			if (aux.element.equals(o)) {
				found = true;
				deleteNode(aux);
			}
			aux = aux.previous;
		}
		return found;
	}

	/**
	 * Devuelve el número de elementos en O(1).
	 */
	@Override
	public int size() {
		return size;
	}

	//
	// Métodos de Collection.
	//

	/**
	 * Devuelve true si el deque está vacío. O(1)
	 */
	@Override
	public boolean isEmpty() {
		return !(size>0);
	}

	/**
	 * Vacía el deque. O(1)
	 */
	@Override
	public void clear() {
		first = null;
		last = null;
		size = 0;
	}

	/**
	 * Añade, al final del deque, todos los elementos de una colección. O(L)
	 * donde L es el tamaño de la colección.
	 */
	@Override
	public boolean addAll(Collection<? extends T> collection) {
		if(collection.size() == 0){
			return false;
		}
		for(T element: collection){
			addLast(element);
		}
		return true;
	}

	/**
	 * Devuelve true si existe un elemento en el deque. O(n)
	 */
	@Override
	public boolean contains(Object o) {
		boolean found = false;
		Node<T> aux = new Node<T>();
		aux = first;
		while(!found && aux!= null){
			if (aux.element.equals(o)) {
				found = true;
			}
			aux = aux.next;
		}
		// Ayuda: usar java.util.Objects.equals()
		// para evitar problemas con null.
		return found;
	}

	/**
	 * Devuelve true si todos los elementos de la colección existen en el Deque.
	 *
	 * Incluir en un comentario la complejidad de su solución.
	 */
	@Override
	//La complejidad es O(n*L), siendo o(n) la complejidad del contains y o(L) la complejidad
//	del for
	public boolean containsAll(Collection<?> c) {
		if(c == null)return false;
		boolean included = true;
		for(Object element: c){
			if (!contains(element)) {
				included = false;
			}
		}
		return included;
	}
	public String toString(){
		if(isEmpty())return "Deque{}";
		StringBuilder sb = new StringBuilder();
		sb.append("Deque{");
		Node<T> aux = new Node<T>();
		aux = first;
		while(aux.next != null){
			sb.append(aux.element);
			sb.append("->");
			aux = aux.next;
		}
		sb.append(aux.element);
		sb.append("}");
		return sb.toString();
	}
	
	public boolean equals(Object obj){
		if(obj == null){return false;}
		else if(obj == this){return true;}
		else{
			boolean same = false;
			if(obj instanceof DequeEnlazada){
				same = true;
				@SuppressWarnings("unchecked")
				DequeEnlazada<T> mirror = (DequeEnlazada<T>) obj;
				if(mirror.size() != this.size())return false;
				Node<T> aux1 = new Node<T>();
				Node<T> aux2 = new Node<T>();
				aux1 = first;
				aux2 = mirror.getFirstNode();
				while(aux1 != null && same){
					if(aux1.element.equals(aux2.element)){
						aux1 = aux1.next;
						aux2 = aux2.next;
					}
					else{
						same = false;
					}
				}
			}
			return same;
		}
	}
	
	public Node<T> getFirstNode(){
		return first;
	}
//	public String toString(){
//		if (isEmpty()) 
//			return "[]";
//		
//		String text = "[";
//		Node<T> aux = new Node<T>();
//		aux = first;
//		while (aux.next != null) {
//			text+=aux.element+"->";
//			aux = aux.next;
//		}
//		return text+aux.element+"]";
//	}
}
