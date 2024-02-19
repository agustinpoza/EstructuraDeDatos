package TDAGrafoVD;

import TDALista.*;
import TDAMap.MapeoHashAbierto;

public class Vertice<V, E> extends MapeoHashAbierto<Object,Object> implements Vertex<V>  {

	protected V rotulo;
	protected Position<Vertice<V,E>> PosicionLista;
	protected PositionList<Arco<V,E>> adyacentes;
	
	
	public Vertice(V rotulo) {
		this.rotulo = rotulo;
		this.PosicionLista = null;
		this.adyacentes = new ListaDE<>();
	}
	
	@Override
	public V element() {
		return this.rotulo;
	}
	public void setRotulo(V x) {
		this.rotulo = x;
	}

	public Position<Vertice<V, E>> getPosicionLista() {
		return PosicionLista;
	}

	public void setPosicionLista(Position<Vertice<V, E>> posicionLista) {
		PosicionLista = posicionLista;
	}

	public PositionList<Arco<V, E>> getAdyacentes() {
		return adyacentes;
	}

	public void setAdyacentes(PositionList<Arco<V, E>> adyacentes) {
		this.adyacentes = adyacentes;
	}
	
	
}
 