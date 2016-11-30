package tp2.parte1;

public class Prueba {
	public static void main(String[] args) {
		DequeEnlazada<String> doblecola = new DequeEnlazada<String>();
		doblecola.add("A");
		doblecola.add("B");
		doblecola.add("C");
		doblecola.add("D");
		doblecola.add("E");
		System.out.println(doblecola);
		doblecola.removeLast();
		doblecola.removeFirst();
		System.out.println(doblecola);
		System.out.println(doblecola.contains("Z"));
	}

}
