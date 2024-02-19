package TDACP;

import java.util.Comparator;

public class ColaHeap<K,V> implements PriorityQueue<K,V> {
	
	//Atributos
	protected Entry<K,V> [] elems;
	protected Comparator<K> comp;
	protected int size, maxElem;
	
	public ColaHeap(int maxElementos, Comparator<K> comparador) {
		this.maxElem = maxElementos;
		this.comp = comparador;
		elems = (Entry<K,V> []) new Entrada[maxElementos];
		size = 0;
	}
	@Override
	public int size() {
		return size;
	}
	@Override
	public boolean isEmpty() {
		return size == 0;
	}
	@Override
	public Entry<K, V> min() throws EmptyPriorityQueueException {
		if(size == 0)
			throw new EmptyPriorityQueueException("cola vacia");
		//al estar ordenado siempre retornamos el primer elemento del arreglo
		return elems[1];
	}
	@Override
	public Entry<K, V> insert(K key, V value) throws InvalidKeyException {
		if(key == null)
			throw new InvalidKeyException("key invalida");
		Entrada<K,V> entrada = new Entrada<>(key,value);
		size++; //sumamos una posicion
		elems[size] = entrada; //colocamos la entrada en la ultima posicion
		int i = size;
		boolean seguir = true;
		while(i>1 && seguir) {
			Entry<K,V> elemActual = elems[i]; //nos paramos en el ultimo elemento (primera pasada)
			Entry<K,V> elemPadre = elems[i/2]; //obtenemos el padre del elemento
			if(comp.compare(elemActual.getKey(), elemPadre.getKey()) < 0) { //nos fijamos si el elemento actual es menor que el padre
				Entry<K,V> aux = elems[i];//creamos un auxiliar con el elemento actual
				elems[i] = elems[i/2];//en donde estaba el elemento actual ponemos al padre
				elems[i/2] = aux;//donde estaba el padre ponemos el auxiliar
				i = i/2;//cambiamos el puntero de el elemento actual al padre
			}
			else
				seguir = false; //si el elemento actual es mayor que el padre paramos ya que esta todo ordenado
		}
		return entrada;
	}
	@Override
	public Entry<K, V> removeMin() throws EmptyPriorityQueueException {
		if(size == 0)
			throw new EmptyPriorityQueueException("cola vacia");
		Entry<K,V> entrada = elems[1]; //devolvemos el primer elemento
		if(size == 1) {
			elems[1] = null;
			size = 0;
		}
		else {
			elems[1] = elems[size]; //ponemos al ultimo elemento como el primero
			elems[size] = null; //eliminamos el ultimo elemento
			size--;
			int i = 1; //ponemos el puntero en 1
			boolean seguir = true;
			while(seguir) {
				int hi = i*2; //seteamos como hi al 2 elem (primera pasada)
				int hd = i*2+1; //seteamos como hd al 3 elem (primera pasada)
				boolean tieneHijoIzquierdo = hi <= size(); //nos fijamos si tiene hijo izquierdo y/o derecho
				boolean tieneHijoDerecho = hd <= size();
				int m;
				if(!tieneHijoIzquierdo) //si no tiene hijo izquierdo quiere decir que no tiene hijos
					seguir = false;
				else {
					if(tieneHijoDerecho) {
						if(comp.compare(elems[hi].getKey(), elems[hd].getKey()) < 0) //comparamos si el hijo izquierdo es menor que el derecho
							m = hi; //si el hijo izquierdo es menor que el derecho ponemos el puntero m en el
						else 
							m = hd; //si no lo ponemos en el derecho
					}
					else
						m = hi; //si no tiene hijo derecho ponemos el puntero en el izquierdo
					if(comp.compare(elems[i].getKey(), elems[m].getKey()) > 0) { //comparamos si el puntero i[1] es mayor que el puntero m
						Entry<K,V> aux = elems[i]; //si lo es, creamos un auxiliar con el elemento en i[1]
						elems[i] = elems[m]; //luego en i[1] ponemos lo que esta en el puntero m
						elems[m] = aux; // luego en el puntero m ponemos lo que teniamos en el auxiliar creado
						i = m; //luego cambiamos el puntero i por m
					}
					else
						seguir = false; // en caso de que el puntero i sea menor que m, significa que ya terminamos de ordenar 
				}
			}
		}
		return entrada;
	}
}
