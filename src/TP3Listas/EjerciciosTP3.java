package TP3Listas;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;
public class EjerciciosTP3<E> {
	
	public boolean ej1aPertenece(ArrayList<E> l, E x) {
		boolean in = false;
		for(int i=0; i<l.size() && in==false;i++) {
			if(l.get(i).equals(x)) in = true;
		}
		return in;
	}
	public boolean ej1bAlMenosNVeces(ArrayList<E> l, E x, int n) {
		boolean in = false;
		int cont = 0;
		for(int i=0; i<l.size() && cont != n;i++) {
			if(l.get(i)==x) {
				in = true;
				cont++;
			}
		}
		return cont==n;
	}
	
	public ArrayList<E> ej2aIntercalar(ArrayList<E> a, ArrayList<E> b){
		ArrayList<E> auxL = new ArrayList<E>();
		for(int i =0; i<a.size() || i<b.size();i++) {
			if(i<a.size() && i<b.size()) {
				auxL.add(a.get(i));
				auxL.add(b.get(i));
			}
			if(!(i<a.size()) && i<b.size()) {
				auxL.add(b.get(i));
			}
			if(!(i<b.size()) && i<a.size()) {
				auxL.add(a.get(i));
			}
		}
		return auxL;
	}
	public ArrayList<Integer> ej2bIntercalarOrdenado(ArrayList<Integer> l1, ArrayList<Integer> l2){
		ArrayList<Integer> auxL = new ArrayList<Integer>();
		int index1 = 0;
		int index2 = 0;
		while(index1 <l1.size() && index2<l2.size()) {
			if(l1.get(index1) < l2.get(index2)) {
				if(auxL.size()>0 && auxL.get(auxL.size()-1)!=l1.get(index1)||auxL.size()==0)
				auxL.add(l1.get(index1));
				index1++;
			}
			else if(l1.get(index1) > l2.get(index2)) {
				if(auxL.size()>0 && auxL.get(auxL.size()-1)!=l2.get(index2)||auxL.size()==0)
				auxL.add(l2.get(index2));
				index2++;
			}
			else if(l1.get(index1) == l2.get(index2)) {
				if(auxL.size()>0 && auxL.get(auxL.size()-1)!=l1.get(index1)||auxL.size()==0)
				auxL.add(l1.get(index1));
				index1++;
				index2++;
			}
		}
		while(index1 <l1.size() || index2<l2.size()) {
			if(index1<l1.size() && !(index2<l2.size())) {
				if(auxL.size()>0 && auxL.get(auxL.size()-1)!=l1.get(index1)||auxL.size()==0)
				auxL.add(l1.get(index1));
				index1++;
			}
			if(index2<l2.size() && !(index1<l1.size())) {
				if(auxL.size()>0 && auxL.get(auxL.size()-1)!=l2.get(index2)||auxL.size()==0)
				auxL.add(l2.get(index2));
				index2++;
			}
		}
		return auxL;
	}
	
	public void ej3Invertir(ArrayList<E> l) {
		Stack<E> stackAux = new Stack<E>();
		while(!l.isEmpty()) {
			stackAux.add(l.remove(0));
		}
		while(!stackAux.isEmpty()) {
			l.add(stackAux.pop());
		}
	}
	
	public boolean ej4RespetaFormato(ArrayList<E> l1, ArrayList<E> l2) {
		boolean cumple = true;
		if(l1.size() == 0 && l2.size() == 0) return cumple;
		if(l1.size() == l2.size()*2) {
			for(int i=0;i<l2.size() && cumple;i++) {
				if(!l1.get(i).equals(l2.get(i))) cumple = false;
			}
			for(int i=l2.size()-1;i==0 && cumple;i--) {
				if(!l1.get(l1.size()-1-i).equals(l2.get(i))) cumple = false;
			}
		}
		else cumple = false;
		return cumple;
	}
	
	public void ej5Eliminar(ArrayList<E> l1, ArrayList<E> l2) {
		for(int i=0; i<l1.size();i++) {
			l1.removeAll(l2);
		}
	}
	
}
