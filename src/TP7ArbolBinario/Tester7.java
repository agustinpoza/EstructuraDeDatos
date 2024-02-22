package TP7ArbolBinario;
import TDAArbol.EmptyTreeException;
import TDAArbol.InvalidOperationException;
import TDAArbolBinario.ArbolBinario;
import TDAArbolBinario.BinaryTree;
import TDADiccionario.Dictionary;
import TDADiccionario.Entry;
import TDALista.InvalidPositionException;
import TDALista.Position;
import TDALista.PositionList;
import TDADiccionario.DiccionarioHashAbierto;

public class Tester7 {
	public static void main(String s[]) {
		Ejercicios<Character> e = new Ejercicios<>();
		ArbolBinario<Integer> arbolInt = new ArbolBinario<>();
		ArbolBinario<Character> arbol2 = new ArbolBinario<>();
		try {
			arbol2.createRoot('/');
			Position<Character> p1 = arbol2.addLeft(arbol2.root(), '+');
			Position<Character> p2 = arbol2.addRight(arbol2.root(), '-');
			Position<Character> p3 = arbol2.addLeft(p1, '*');
			Position<Character> p4 = arbol2.addRight(p1, '-');
			Position<Character> p5 = arbol2.addLeft(p2, '3');
			Position<Character> p6 = arbol2.addRight(p2, '7');
			Position<Character> p7 = arbol2.addLeft(p3, '0');
			Position<Character> p8 = arbol2.addRight(p3, '5');
			Position<Character> p9 = arbol2.addRight(p4, '4');
			Position<Character> p10 = arbol2.addLeft(p4, '2');
			
			for(Character c : arbol2) {
				System.out.print(c);
			}
			System.out.println();
			System.out.println();
			Dictionary<Character,Character> d = arbol2.preOrdenEj2(); 
			for(Entry<Character,Character> entrada : d.entries()) {
				System.out.print("<"+entrada.getKey()+","+entrada.getValue()+">");
			}
			System.out.println();
			System.out.println();
			Iterable<Character> l = e.ej3(arbol2);
			for(Character c: l) {
				System.out.print(c);
			}
			System.out.println();
			System.out.println();
			arbolInt.createRoot(999999);
			Position<Integer> i1 = arbolInt.addLeft(arbolInt.root(), 0);
			Position<Integer> i2 = arbolInt.addRight(arbolInt.root(), 1);
			Position<Integer> i3 = arbolInt.addLeft(i1, 2);
			Position<Integer> i4 = arbolInt.addRight(i1, 3);
			Position<Integer> i5 = arbolInt.addLeft(i2, 4);
			Position<Integer> i6 = arbolInt.addRight(i2,5);
			Position<Integer> i7 = arbolInt.addLeft(i3, 6);
			Position<Integer> i8 = arbolInt.addRight(i3, 7);
			Position<Integer> i9 = arbolInt.addRight(i4, 8);
			Position<Integer> i10 = arbolInt.addLeft(i4, 9);
			
			Dictionary<Integer,Integer> dick = e.ejd(arbolInt);
			
			for(Entry<Integer,Integer> entr : dick.entries()) {
				System.out.print("// "+entr.getKey()+" - "+entr.getValue());
			}
			
		} catch (InvalidOperationException | InvalidPositionException | EmptyTreeException e1) {e1.printStackTrace();}
	}
}
