package TDAArbolBinario;

import TDALista.Position;

public class BTNodo<E> implements Position<E>{
	
	protected E element;
	protected BTNodo<E> padre;
	protected BTNodo<E> HI;
	protected BTNodo<E> HD;
	
	public BTNodo(E elem, BTNodo<E> padre, BTNodo<E> HI, BTNodo<E> HD) {
		this.element = elem;
		this.HD = HD;
		this.HI = HI;
		this.padre = padre;
	}
	public BTNodo(E elem, BTNodo<E> padre) {
		this.element = elem;
		this.HD = null;
		this.HI = null;
		this.padre = padre;
	}
	public BTNodo(E elem) {
		this.element = elem;
		this.HD = null;
		this.HI = null;
		this.padre = null;
	}
	
	public E element() {
		return element;
	}
	public void setElement(E elem) {
		this.element = elem;
	}
	
	public BTNodo<E> getPadre() {
		return padre;
	}
	public void setPadre(BTNodo<E> padre) {
		this.padre = padre;
	}
	public BTNodo<E> getHI() {
		return HI;
	}
	public void setHI(BTNodo<E> hI) {
		HI = hI;
	}
	public BTNodo<E> getHD() {
		return HD;
	}
	public void setHD(BTNodo<E> hD) {
		HD = hD;
	}
	
}
