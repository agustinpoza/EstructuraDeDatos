package TDAGrafo;

import TDALista.Position;

public class Arco<V,E> implements Edge<E> {

	private E rotulo;
	private Vertice<V,E> v1, v2;
	private Position<Arco<V,E>> posicionEnArcos;
	private Position<Arco<V,E>> posicionEnlv1, posicionEnlv2;
	
	public Arco(E rotulo, Vertice<V,E> predecesor, Vertice<V,E> sucesor) {
		this.rotulo = rotulo;
		this.v1 = predecesor;
		this.v2 = sucesor;
	}
	
	public E element() {
		return rotulo;
	}

	public Vertice<V,E> getv1(){
		return this.v1;
	}
	public Vertice<V,E> getv2(){
		return this.v2;
	}
	public Position<Arco<V,E>> getPosicionEnArcos(){
		return this.posicionEnArcos;
	}
	public Position<Arco<V,E>> getPosicionEnLv1(){
		return this.posicionEnlv1;
	}
	public Position<Arco<V,E>> getPosicionEnLv2(){
		return this.posicionEnlv2;
	}
	
	public void setv1(Vertice<V,E> p) {
		this.v1 = p;
	}
	public void setv2(Vertice<V,E> p) {
		this.v2 = p;
	}
	public void setPosicionEnlv1(Position<Arco<V,E>> p) {
		this.posicionEnlv1 = p;
	}
	public void setPosicionEnlv2(Position<Arco<V,E>> p) {
		this.posicionEnlv2 = p;
	}
	public void setPosEnArcos(Position<Arco<V,E>> p) {
		this.posicionEnArcos = p;
	}
	public void setRotulo(E e) {
		this.rotulo = e;
	}
}
