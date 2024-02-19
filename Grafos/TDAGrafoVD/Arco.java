package TDAGrafoVD;

import TDALista.*;
import TDAMap.MapeoHashAbierto;

public class Arco<V,E> extends MapeoHashAbierto<Object, Object> implements Edge<E>{

	protected E rotulo;
	private Position<Arco<V,E>> posicionEnArcos;
	protected Position<Arco<V,E>> posicionLista1, posicionLista2;
	protected Vertice<V,E> v1;
	protected Vertice<V,E> v2;
	
	
	public Arco(E r, Vertice<V,E> vv1, Vertice<V,E> vv2) {
		this.rotulo = r;
		this.v1 = vv1;
		this.v2 = vv2;
	}
	
	public E element() {
		return rotulo;
	}
	public void setRotulo(E x) {
		this.rotulo = x;
	}

	public Position<Arco<V, E>> getPosicionEnArcos() {
		return posicionEnArcos;
	}

	public void setPosicionEnArcos(Position<Arco<V, E>> posicionEnArcos) {
		this.posicionEnArcos = posicionEnArcos;
	}

	public Position<Arco<V, E>> getPosicionLista1() {
		return posicionLista1;
	}

	public void setPosicionLista1(Position<Arco<V, E>> posicionLista1) {
		this.posicionLista1 = posicionLista1;
	}

	public Position<Arco<V, E>> getPosicionLista2() {
		return posicionLista2;
	}

	public void setPosicionLista2(Position<Arco<V, E>> posicionLista2) {
		this.posicionLista2 = posicionLista2;
	}

	public Vertice<V, E> getV1() {
		return v1;
	}


	public void setV1(Vertice<V, E> v1) {
		this.v1 = v1;
	}


	public Vertice<V, E> getV2() {
		return v2;
	}


	public void setV2(Vertice<V, E> v2) {
		this.v2 = v2;
	}

	
	
	
}
