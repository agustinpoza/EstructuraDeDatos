package ABB;
import java.util.Comparator;

import TDAMap.Entry;
import TDAMap.InvalidKeyException;
import TDAMap.Map;
public class MapABB<K extends Comparable<K>, V> implements Map<K,V>{
	
	protected NodoABB<K,V> root;
	protected Comparator<K> comp;
	protected int size;
	
	public MapABB(Comparator<K> comp) {
		//root = new NodoABB<K,V>(null,null,null);
		this.comp = comp;
		size = 0;
	}
	
	
	public void createRoot(K key, V value) {
		if(size == 0 && root == null) {
			NodoABB<K,V> e = new NodoABB<K,V>(key,value,null);
			root = e;
			size++;
		}
	}
	
	public NodoABB<K,V> buscar(K x){
		return buscarAux(x,root); //busqueda recursiva desde la raiz
	}
	
	private NodoABB<K,V> buscarAux(K x , NodoABB<K,V> p){
		if(p.getKey() == null) return  p; //llegue a un dummy
		else {
			int c = comp.compare(x, p.getKey());
			if(c == 0) return p; // lo encontre
			else if(c < 0)
				return buscarAux(x,p.getLeft()); //busco a izquierda porque x < p.element
			else
				return buscarAux(x,p.getRight()); // busco a derecha porque x > p.element
		}
	}
	
	public void eliminar(NodoABB<K,V> p) {
		if(isExternal(p)) {
			p.setKey(null); p.setValue(null);; p.setLeft(null); p.setRight(null); // caso 1: p es hoja; convertimos el nodo en un dummy y soltammos sus dummys
		}
		else {
			if(p == root) {
				//completar
			}
			
			else if(soloTieneHijoIzquierdo(p)) {
				// caso 2: enganchar al padre de p con el hijo izquierdo de p
				if(p.getParent().getLeft() == p) // p es el hijo izquierdo de su padre
					p.getParent().setLeft(p.getLeft());
				else // p es el hijo derecho de su padre
					p.getParent().setRight(p.getLeft()); // el hijo derecho del padre de p es el hijo de p 
				p.getLeft().setParent(p.getParent()); // ahora el padre dl hijo izq de p es su abuelo
			}
			else if(soloTieneHijoDerecho(p)) {
				// caso 3: enganchar al padre de p con el hijo derecho de p
				if(p.getParent().getLeft() == p)// p es el hijo izquierdo de su padre
					p.getParent().setLeft(p.getRight()); // el hijo izq del padre de p es el hijo de p
			}
			else {// caso 4: p tiene dos hijos; seteo como rotulo de p al rotulo del sucesor inorder de p
				p.setValue(p.getRight().getValue());
				p.setKey(eliminarMinimo(p.getRight()));
			}
		}
	}
	
	private boolean isExternal(NodoABB<K,V> p) {
		return p.getRight().getKey() == null && p.getLeft().getKey() == null;
	}
	private boolean soloTieneHijoIzquierdo(NodoABB<K,V> p) {
		return p.getLeft().getKey()!=null && p.getRight().getKey() == null;
	}
	private boolean soloTieneHijoDerecho(NodoABB<K,V> p) {
		return p.getLeft().getKey()==null && p.getRight().getKey() != null;
	}
	
	private K eliminarMinimo(NodoABB<K,V> p) {
		if(p.getLeft().getKey() == null) { // el hijo izquierdo de p es un dummy
			K toReturn = p.getKey(); // salvo el rotulo a devolver
			if(p.getLeft().getKey()==null) { // p es hoja(pues sus hijos son dummy)
				p.setKey(null);
				p.setValue(null);
				p.setLeft(null);
				p.setRight(null);
			}else { //p solo tiene hijo derecho (porque no tiene izquierdo)
				p.getParent().setRight(p.getRight()); // engancho al padre de p con el hijo derecho de p
				p.getRight().setParent(p.getParent()); // seguro tiene que ser el hijo derecho de su padre
			}
			return toReturn;
		}else {//si p tiene hijo izquierdo, entonces p.element() no es el minimo. el minimo tiene que estar en el subarbol izquierdo
			return eliminarMinimo(p.getRight());
		}
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
	public V get(K key) throws InvalidKeyException {
		// TODO Auto-generated method stub
		return null;
	}




	@Override
	public V put(K key, V value) throws InvalidKeyException {
		V toReturn = null;
		if(size == 0 && root == null) {
			this.createRoot(key, value);
			toReturn = value;
		}
		else {
			NodoABB<K,V> n = this.buscar(key);
			if(n != null) {
				toReturn = n.getValue();
				n.setValue(value);
			}
			else {
				
			}
				
		}
		return toReturn;
	}




	@Override
	public V remove(K key) throws InvalidKeyException {
		// TODO Auto-generated method stub
		return null;
	}




	@Override
	public Iterable<K> keys() {
		// TODO Auto-generated method stub
		return null;
	}




	@Override
	public Iterable<V> values() {
		// TODO Auto-generated method stub
		return null;
	}




	@Override
	public Iterable<Entry<K, V>> entries() {
		// TODO Auto-generated method stub
		return null;
	}
	
	private void checkKey(K key) throws InvalidKeyException{
		if(key==null) throw new InvalidKeyException("key invalida");
	}
	
}
