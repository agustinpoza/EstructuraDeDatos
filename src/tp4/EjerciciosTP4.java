package tp4;
import TDAPila.*;
import java.util.Iterator;

import TDALista.ListaDE;
import TDALista.PositionList;

public class EjerciciosTP4<E> {
	public boolean ej3Pertenece(PositionList<E> l, E e1){
		boolean cumple = false;
		for(E e : l) {
			if(e.equals(e1))
				cumple = true;
		}
		return cumple;
	}
	public PositionList<E> ej4Duplicados(PositionList<E> l){
		PositionList<E> laux = new ListaDE<E>();
		for(E e : l) {
			laux.addLast(e);
			laux.addLast(e);
		}
		return laux;
	}
}
