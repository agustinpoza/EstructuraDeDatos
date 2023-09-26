package Lista;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class IteradorLista<E> implements Iterator<E> {
	
	protected PositionList<E> lista;
	protected Position<E> cursor;
	
	public IteradorLista(PositionList<E> l) {
		try {
		lista = l;
		if(lista.isEmpty()) cursor = null;
		else cursor = lista.first();
		} catch(EmptyListException e) {}
	}
	
	
	@Override
	public boolean hasNext() {
		return cursor != null;
	}

	@Override
	public E next() {
		if(cursor == null) throw new NoSuchElementException("error: no hay siguiente");
		E toReturn = cursor.element();
		try {
			if(cursor == lista.last()){
				cursor = null;
			}
			else cursor = lista.next(cursor);
		}catch(EmptyListException | BoundaryViolationException | InvalidPositionException e) {}
		return null;
	}
	
}
