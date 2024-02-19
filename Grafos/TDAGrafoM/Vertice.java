package TDAGrafoM;

import TDALista.*;
import TDALista.PositionList;

public class Vertice<V,E>  implements Vertex<V>{
	
	private V rotulo;
	private Position<Vertex<V>> posicionEnNodos; 
	private int indice; 
	
	public V element() {
		return rotulo;
	}
	
	public Vertice(V rotulo, int i) {
		this.rotulo = rotulo;
		this.indice = i;
	}
	
	//stters y getters 
	public void setRotulo(V nuevoRotulo) {
		this.rotulo = nuevoRotulo;
	}
	public int getIndice(){
		return this.indice;
	}
	public void setPosicionEnNodos(Position<Vertex<V>> p) {
		this.posicionEnNodos = p;
	}
	public Position<Vertex<V>> getPositionEnNodos(){
		return this.posicionEnNodos;
	}
	
}
