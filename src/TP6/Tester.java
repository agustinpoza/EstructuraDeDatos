package TP6;

import TDAMap.*;
import TDALista.*;
import TDAArbol.*;
import TDACola.EmptyQueueException;



public class Tester<E> {
	
	public static void main(String s[]) {
		
		Ejercicios<Character> e = new Ejercicios<>();
		Ejercicios<String> es = new Ejercicios<>();
		
		try {
		//ej 2 y 6
		ArbolGeneral<Integer> t2 = new ArbolGeneral<Integer>(); 
		t2.createRoot(0);
		
		Position<Integer> p1 = t2.addFirstChild(t2.root(), 1);
		Position<Integer> p2 = t2.addFirstChild(t2.root(), 2);
		Position<Integer> p3 = t2.addFirstChild(t2.root(), 3);
		
		Position<Integer> p4 = t2.addFirstChild(p1, 4);
		Position<Integer> p5 = t2.addFirstChild(p1, 5);
		
		Position<Integer> p6 = t2.addFirstChild(p2, 6);
		Position<Integer> p7 = t2.addFirstChild(p2, 7);
		Position<Integer> p8 = t2.addFirstChild(p2, 8);
		
		Position<Integer> p9 = t2.addFirstChild(p3, 9);
		
		Position<Integer> p10 = t2.addFirstChild(p9, 10);
		Position<Integer> p11 = t2.addFirstChild(p9, 11);
		
		
		t2.preOrdenGraficar(t2.root());
		t2.eliminarUltimoHijo(p9);
		System.out.println();
		t2.preOrdenGraficar(t2.root());
		System.out.println(e.ej6(t2, 4));
		System.out.print("/////////////// ej2");
		System.out.println();
		
		
		
		//ej 3
		
		ArbolGeneral<Character> t3 = new ArbolGeneral<>();
		//0
		t3.createRoot('A');
		//1
		Position<Character> n1 = t3.addFirstChild(t3.root(), 'D');
		Position<Character> n2 = t3.addFirstChild(t3.root(), 'C');
		Position<Character> n3 = t3.addFirstChild(t3.root(), 'B');
		//2
		Position<Character> n4 = t3.addFirstChild(n1, 'F');
		Position<Character> n5 = t3.addFirstChild(n1, 'E');
		//2
		Position<Character> n6 = t3.addFirstChild(n2, 'I');
		Position<Character> n7 = t3.addFirstChild(n2, 'E');
		Position<Character> n8 = t3.addFirstChild(n2, 'G');
		//2
		Position<Character> n9 = t3.addFirstChild(n3, 'L');
		//3
		Position<Character> n10 = t3.addFirstChild(n9, 'L');
		Position<Character> n11 = t3.addFirstChild(n9, 'J');
		//3
		Position<Character> n12 = t3.addFirstChild(n5, 'I');
		Position<Character> n13 = t3.addFirstChild(n5, 'E');
		Position<Character> n14 = t3.addFirstChild(n5, 'G');
		//3
		Position<Character> n15 = t3.addFirstChild(n8, 'I');
		Position<Character> n16 = t3.addFirstChild(n8, 'I');
		Position<Character> n17 = t3.addFirstChild(n8, 'I');
		
		t3.preOrdenGraficar(t3.root());
		System.out.println();
		
		Map<Character,Integer> m = e.cantidadRepeticiones(t3);
		
		Iterable<Entry<Character,Integer>> i = m.entries();
		
		for(Entry<Character,Integer> entrada : i) {
			System.out.print(entrada.getKey()+"-"+entrada.getValue()+",");
		}
		System.out.println();
		System.out.print("/////////////// ej3");
		System.out.println();
		
		ArbolGeneral<String> t4 = new ArbolGeneral<>();
		t4.createRoot("Agustin");
		
		Position<String> c1 = t4.addFirstChild(t4.root(), "Ariel");
		Position<String> c2 = t4.addFirstChild(t4.root(), "Martin");
		Position<String> c3 = t4.addFirstChild(t4.root(), "Gustavo");
		
		Position<String> c4 = t4.addFirstChild(c1, "Calabaza");
		Position<String> c5 = t4.addFirstChild(c1, "Zapallo");
		
		Position<String> c6 = t4.addFirstChild(c2, "Kiwi");
		Position<String> c7 = t4.addFirstChild(c2, "Gustavo");
		Position<String> c8 = t4.addFirstChild(c2, "Pera");
		
		Position<String> c9 = t4.addFirstChild(c3, "Fideos");
		
		Position<String> c10 = t4.addFirstChild(c9, "Hacha");
		Position<String> c11 = t4.addFirstChild(c9, "Gustavo");

		Position<String> c12 = t4.addFirstChild(c5, "Auto");
		Position<String> c13 = t4.addFirstChild(c5, "Gustavo");
		Position<String> c14 = t4.addFirstChild(c5, "Nafta");
		
		Position<String> c15 = t4.addFirstChild(c8, "Bizarro");
		Position<String> c16 = t4.addFirstChild(c8, "Monstruo");
		Position<String> c17 = t4.addFirstChild(c8, "Magneto");
		
		
		t4.preOrdenGraficar(t4.root());
		System.out.println();
		
		Iterable<Position<String>> i4 = e.ej4(t4, "Gustavo");
		
		
		for(Position<String> posicion : i4) {
			System.out.print("-" + posicion.element());
		}
		System.out.println();
		System.out.print("/////////////// ej4");
		System.out.println();
		
		int cant = es.ej5_estupido(t4, "Gustavo");
		System.out.println(cant);
		t4.preOrdenGraficar(t4.root());
		System.out.println();
		
		System.out.println();
		System.out.println();
		Map<Character,Integer> m2 = e.profundidadNodos(t3);
		for(Entry<Character,Integer> entrada : m2.entries()) {
			System.out.print(entrada.getKey()+"-"+entrada.getValue()+",");
		}
		System.out.println();
		System.out.println();
		t3.preOrdenGraficar(t3.root());
		e.removerNodosR(t3, 'E');
		System.out.println();
		t3.preOrdenGraficar(t3.root());
		System.out.println();
		t3.graficarNiveles();
		t3.agregarNivel('I', 1 );
		System.out.println();
		t3.graficarNiveles();
		System.out.println();
		t3.preOrdenGraficar(t3.root());
		}catch(EmptyTreeException | InvalidOperationException | InvalidPositionException | InvalidKeyException | BoundaryViolationException | EmptyQueueException e2) {
			e2.printStackTrace();
		}
		
	}
	
}
