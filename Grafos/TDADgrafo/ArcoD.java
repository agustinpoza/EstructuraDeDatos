package TDADgrafo;

import TDALista.Position;

public class ArcoD<V,E> implements Edge<E> {
	protected E rotulo;
	protected Position<Edge<E>> posicionListaArcos;
	protected VerticeD<V,E> verticeO;
	protected VerticeD<V,E> verticeD;
	
	public ArcoD(E rotulo, VerticeD<V,E> origen, VerticeD<V,E> destino) {
		this.rotulo = rotulo;
		this.verticeO = origen;
		this.verticeD = destino;
		this.posicionListaArcos = null;
	}
	
	public E getRotulo() {
		return rotulo;
	}

	public void setRotulo(E rotulo) {
		this.rotulo = rotulo;
	}

	public Position<Edge<E>> getPosicionListaArcos() {
		return posicionListaArcos;
	}

	public void setPosicionListaArcos(Position<Edge<E>> posicionListaArcos) {
		this.posicionListaArcos = posicionListaArcos;
	}

	public VerticeD<V, E> getVerticeO() {
		return verticeO;
	}

	public void setVerticeO(VerticeD<V, E> verticeO) {
		this.verticeO = verticeO;
	}

	public VerticeD<V, E> getVerticeD() {
		return verticeD;
	}

	public void setVerticeD(VerticeD<V, E> verticeD) {
		this.verticeD = verticeD;
	}

	@Override
	public E element() {
		return this.rotulo;
	}
	
}
