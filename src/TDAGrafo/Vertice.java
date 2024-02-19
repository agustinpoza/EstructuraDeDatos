package TDAGrafo;

import TDALista.*;
import TDALista.PositionList;

public class Vertice<V,E>  implements Vertex<V>{
	
	private V rotulo;
	private Position<Vertice<V,E>> posicionEnNodos; 
	private PositionList<Arco<V,E>> adyacentes; 
	
	public V element() {
		return rotulo;
	}
	
	public Vertice(V rotulo) {
		this.rotulo = rotulo;
		adyacentes = new ListaDE<Arco<V,E>>();
	}
	
	//stters y getters 
	public void setRotulo(V nuevoRotulo) {
		this.rotulo = nuevoRotulo;
	}
	public PositionList<Arco<V,E>> getAdyacentes(){
		return this.adyacentes;
	}
	public void setPosicionEnNodos(Position<Vertice<V,E>> p) {
		this.posicionEnNodos = p;
	}
	public Position<Vertice<V,E>> getPositionEnNodos(){
		return this.posicionEnNodos;
	}
	
}
