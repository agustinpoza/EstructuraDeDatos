package TP3;

import TDAPila.EmptyStackException;
import TDAPila.Stack;
import java.util.ArrayList;

public class PilaConLista<E> implements Stack<E>{

	ArrayList<E> l = new ArrayList<E>();
	
	@Override
	public int size() {
		return l.size();
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return l.isEmpty();
	}

	@Override
	public E top() throws EmptyStackException {
		if(l.isEmpty()) throw new EmptyStackException();
		return l.get(l.size()-1);
	}

	@Override
	public void push(E element) {
		l.add(element);
		
	}

	@Override
	public E pop() throws EmptyStackException {
		if(l.isEmpty()) throw new EmptyStackException();
		return l.remove(l.size()-1);
	}

}
