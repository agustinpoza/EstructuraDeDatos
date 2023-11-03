package TDAArbol;

import TDALista.Position;

public class TNodoHDHEI<E> implements Position<E>{

	E elem;
	TNodoHDHEI<E> HEI;
	TNodoHDHEI<E> HD;
	
	public TNodoHDHEI(E elem, TNodoHDHEI<E> hei, TNodoHDHEI<E> hd) {
		this.elem = elem;
		this.HD = hd;
		this.HEI = hei;
	}
	public TNodoHDHEI(E elem ) {
		this(elem,null,null);
	}
	
	public TNodoHDHEI<E> getHEI(){
		return this.HEI;
	}
	
	public TNodoHDHEI<E> getHD(){
		return this.HD;
	}
	
	public E element() {
		return elem;
	}
	
	public void setHEI(TNodoHDHEI<E> n){
		this.HEI = n;
	}
	
	public void setHD(TNodoHDHEI<E> n){
		this.HD = n;
	}
	
	public void setElement(E e){
		this.elem = e;
	}
	
}
