package TDAArbol;

import TDALista.Position;
import TDALista.PositionList;
import TDALista.ListaDE;

public class TNodo<E> implements Position<E>{

	protected E elem;
	protected TNodo<E> padre;
	protected PositionList<TNodo<E>> hijos;
	
	public TNodo(E e, TNodo<E> p, PositionList<TNodo<E>> h) {
		this.elem = e;
		this.padre = p;
		this.hijos = h;
	}
	public TNodo(E e, TNodo<E> p) {
		this.elem = e;
		this.padre = p;
		this.hijos = new ListaDE<TNodo<E>>();
	}
	public TNodo(E e) {
		this.elem = e;
		this.padre = null;
		this.hijos = new ListaDE<TNodo<E>>();
	}
	
	public E element() {
		return this.elem;
	}
	
	public TNodo<E> getPadre(){
		return this.padre;
	}
	
	public PositionList<TNodo<E>> getHijos(){
		return this.hijos;
	}
	public void setElem(E e) {
		this.elem = e;
	}
	
	public void setPadre(TNodo<E> p){
		this.padre = p;
	}
	
	public void setHijos(PositionList<TNodo<E>> h){
		this.hijos = h;
	}
}
