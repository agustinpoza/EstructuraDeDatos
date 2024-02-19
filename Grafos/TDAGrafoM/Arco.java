package TDAGrafoM;

import TDALista.Position;

public class Arco<V,E> implements Edge<E> {

	private E rotulo;
	private Vertice<V,E> v1, v2;
	private Position<Edge<E>> posicionEnArcos;
	
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
	public Position<Edge<E>> getPosicionEnArcos(){
		return this.posicionEnArcos;
	}
	
	public void setv1(Vertice<V,E> p) {
		this.v1 = p;
	}
	public void setv2(Vertice<V,E> p) {
		this.v2 = p;
	}
	
	public void setPosEnArcos(Position<Edge<E>> p) {
		this.posicionEnArcos = p;
	}
	public void setRotulo(E e) {
		this.rotulo = e;
	}
}
