package TP4Iteradores;

import TDACola.EmptyQueueException;
import TDACola.Queue;
import TDALista.BoundaryViolationException;
import TDALista.EmptyListException;
import TDALista.InvalidPositionException;
import TDALista.*;
import TDALista.PositionList;

public class Practica<E> {
	
	public boolean contiene(PositionList<E> l1, PositionList<E> l2) throws EmptyListException, InvalidPositionException, BoundaryViolationException {
		boolean contiene = false;
		Position<E> p = l2.first();
		for(E e : l1) {
			if(e.equals(p.element())) {
				contiene = true;
			}
			if(contiene) {
				if(!e.equals(p.element())) return false;
			}
			if(p==l2.last() && contiene) {
				if(e.equals(p.element())) {
					contiene = true;
				}
				if(contiene) {
					if(!e.equals(p.element())) return false;
				}
			}
			if(p!=l2.last() && contiene) p = l2.next(p);
		}
		return contiene;
	}
	
	
	public void ejc(PositionList<E> l) {
		try {
			Position<E> ulti = l.last();
			while(ulti != l.first()) {
				l.addLast(ulti.element());
				ulti = l.prev(ulti);
			}
			l.addLast(ulti.element());
		}catch(Exception e) {System.out.print("hubo un error");}
	}
	
	
	public PositionList<Character> ejd(PositionList<Queue<Character>> l) throws EmptyListException, EmptyQueueException, InvalidPositionException, BoundaryViolationException{
		PositionList<Character> laux = new ListaDE<Character>();
		Position<Queue<Character>> p = l.last();
		Queue<Character> q;
		while(p!=l.first()) {
			q = p.element();
			while(!q.isEmpty()) {
				laux.addFirst(q.dequeue());
			}
			p = l.prev(p);
		}
		q = p.element();
		while(!q.isEmpty()) {
			laux.addFirst(q.dequeue());
		}
		return laux;
	}
}
