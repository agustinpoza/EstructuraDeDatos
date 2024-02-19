package TDAGrafoVD;

import java.util.Iterator;


import TDALista.*;

public class GrafoDecorado<V,E> implements Graph<V,E> {
	
	protected PositionList<Vertice<V,E>> vertices;
	protected PositionList<Arco<V,E>> arcos;
	
	public GrafoDecorado() {
		vertices = new ListaDE<Vertice<V,E>>();
		arcos = new ListaDE<Arco<V,E>>();
	}
	
	public Iterable<Vertex<V>> vertices() {
		PositionList<Vertex<V>> lista = new ListaDE<Vertex<V>>();
		for(Vertex<V> v : vertices) {
			lista.addLast(v);
		}
		return lista;
	}

	@Override
	public Iterable<Edge<E>> edges() {
		PositionList<Edge<E>> lista = new ListaDE<Edge<E>>();
		for(Edge<E> e : arcos) {
			lista.addLast(e);
		}
		return lista;
	}

	@Override
	public Iterable<Edge<E>> incidentEdges(Vertex<V> v) throws InvalidVertexException {
		PositionList<Edge<E>> lista = new ListaDE<Edge<E>>();
		Vertice<V,E> vert = checkVertice(v);;
		for(Edge<E> e : vert.getAdyacentes()) {
			lista.addLast(e);
		}
		return lista;
	}

	@Override
	public Vertex<V> opposite(Vertex<V> v, Edge<E> e) throws InvalidVertexException, InvalidEdgeException {
		Vertice<V,E> vert = checkVertice(v);
		Arco<V,E> arc = checkArco(e);
		Vertice<V,E> toReturn = null;
		if(arc.getV1().equals(vert))
			toReturn = arc.getV2();
		if(arc.getV2().equals(vert))
			toReturn = arc.getV1();
		if(toReturn == null)
			throw new InvalidEdgeException("Arco no incidente a el vertice");
		return toReturn;
	}

	@Override
	public Vertex<V>[] endvertices(Edge<E> e) throws InvalidEdgeException {
		Vertex<V> [] array = (Vertex<V>[]) new Vertex[2];
		Arco<V,E> arco = checkArco(e);
		array[0] = arco.getV1();
		array[1] = arco.getV2();
		return array;
	}

	@Override
	public boolean areAdjacent(Vertex<V> v, Vertex<V> w) throws InvalidVertexException {
		boolean cumple = false;
		Vertice<V,E> v1 = checkVertice(v);
		Vertice<V,E> v2 = checkVertice(w);//comprobamos si w es nulo o no es un vertice valido
		Iterator<Arco<V,E>> it = v1.getAdyacentes().iterator();
		Arco<V,E> a;
		while(it.hasNext() && !cumple) {
			a = it.next();
			try {
				if(this.opposite(v, a) == v2)
					cumple = true;
			} catch (InvalidVertexException | InvalidEdgeException e) {}
		}
		return cumple;
	}

	@Override
	public V replace(Vertex<V> v, V x) throws InvalidVertexException {
		Vertice<V,E> vertice = checkVertice(v); 
		V toReturn = vertice.element();
		vertice.setRotulo(x);;
		return toReturn;
	}

	@Override
	public Vertex<V> insertVertex(V x) {
		Vertice<V,E> v = new Vertice<V,E>(x);
		vertices.addLast(v);
		try {
			v.setPosicionLista(vertices.last());
		} catch (EmptyListException e) {e.printStackTrace();}
		return v;
	}

	@Override
	public Edge<E> insertEdge(Vertex<V> v, Vertex<V> w, E x) throws InvalidVertexException {
		//Obtengo los vertices v y w
		Vertice<V,E> vv = checkVertice(v);; Vertice<V,E> ww = checkVertice(w);;
		Arco<V,E> arco = new Arco<V,E>(x , vv, ww); // construyo un arco
		//Agrego el arco al final de la lista de adyacentes de v y anoto su posicion:
		vv.getAdyacentes().addLast(arco);
		try {
			arco.setPosicionLista1(vv.getAdyacentes().last());
			//Agrego el arco al final de la lista de adyacentes de w y anoto su posicion:
			ww.getAdyacentes().addLast(arco);
			arco.setPosicionLista2(ww.getAdyacentes().last());
			//Agrego el arco al final de la lista de arcos y le seteo su posicion
			arcos.addLast(arco);
			arco.setPosicionEnArcos(arcos.last());
		} catch (EmptyListException e) {e.printStackTrace();}
		return arco;
	}

	@Override
	public V removeVertex(Vertex<V> v) throws InvalidVertexException {
		Vertice<V,E> removeV = checkVertice(v);
		V toReturn = removeV.element();
		try {
			for(Arco<V,E> e : removeV.getAdyacentes()) {
				removeEdge(e);
			}
			this.vertices.remove(removeV.getPosicionLista());
		}catch(InvalidEdgeException | InvalidPositionException e) {}
		
		return toReturn;
	}

	@Override
	public E removeEdge(Edge<E> e) throws InvalidEdgeException {
		E res = null;
		Arco<V,E> ee = checkArco(e); //recupero extremos del arco:
		Vertice<V,E> v1 = ee.getV1(); Vertice<V,E> v2 = ee.getV2();
		try {
		//Elimino a e de la lista de adyacentes de v1:
		v1.getAdyacentes().remove(ee.getPosicionLista1());
		//Elimino a e de la lista de adyacentes de v2:
		v2.getAdyacentes().remove(ee.getPosicionLista2());
		//Elimino a e de la lista de arcos y retorno el rotulo del arco:
		Position<Arco<V,E>> pee = ee.getPosicionEnArcos();
		res = arcos.remove(pee).element();
		}
		catch(Exception e1) {}
		
		return res;
	}

	
	
	
	private Vertice<V,E> checkVertice(Vertex<V> v) throws InvalidVertexException{
		try {
		Vertice<V,E> toReturn = (Vertice<V,E>) v;
		if(toReturn == null) {
			throw new InvalidVertexException("Vertice Incorrecto");
		}
		return toReturn;
		}catch(ClassCastException e) {throw new InvalidVertexException("Vertice Incorrecto");}
	}
	private Arco<V,E> checkArco(Edge<E> v) throws InvalidEdgeException{
		try {
		Arco<V,E> toReturn = (Arco<V,E>) v;
		if(toReturn == null) {
			throw new InvalidEdgeException("Arco Incorrecto");
		}
		return toReturn;
		}catch(ClassCastException e) {throw new InvalidEdgeException("Arco Incorrecto");}
	}
}

