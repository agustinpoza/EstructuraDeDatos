package tp4;
import TDALista.PositionList;
import TDACola.*;
import TDALista.ListaDE;


public class TestPractica<E> {
	
	
	
	public static void main(String []s) {
		try {
			Practica<Integer> ejercicios = new Practica<Integer>();
			
			PositionList<Integer> l1 = new ListaDE<Integer>();
			PositionList<Integer> l2 = new ListaDE<Integer>();	
			PositionList<Queue<Character>> l3 = new ListaDE<Queue<Character>>();
			Queue<Character> q1 = new ColaEnlazada();
			Queue<Character> q2 = new ColaEnlazada();
			Queue<Character> q3 = new ColaEnlazada();
			q1.enqueue('c');
			q1.enqueue('b');
			q1.enqueue('a');
			q2.enqueue('e');
			q2.enqueue('d');
			q3.enqueue('i');
			q3.enqueue('h');
			q3.enqueue('g');
			q3.enqueue('f');
			l3.addLast(q1);
			l3.addLast(q2);
			l3.addLast(q3);
			l1.addLast(1);
			l1.addLast(2);
			l1.addLast(3);
			l1.addLast(4);
			l1.addLast(5);
			l1.addLast(6);
			l2.addLast(3);
			l2.addLast(4);
			l2.addLast(5);
			System.out.println(ejercicios.contiene(l1, l2));
			ejercicios.ejc(l2);
			//System.out.print("aaaaa");
			for(Integer e : l2) {
				System.out.print(e);
			}
			System.out.println();
			PositionList<Character> l4 = ejercicios.ejd(l3);
			for(Character e : l4) {
				System.out.print(e);
			}
			
			
			
			
			
			
			
			
			
			
			
			
			
		} catch (Exception e) {System.out.println("algo va mal");}
		
		
		
		
	}
}
