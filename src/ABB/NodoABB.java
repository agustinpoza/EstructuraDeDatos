package ABB;
import TDAMap.Entry;

public class NodoABB<K,V> implements Entry<K,V>{
	
	private K key;
	private V value;
	private NodoABB<K,V> left, right, parent;
	
	public NodoABB(K key, V val, NodoABB<K,V> p) {
		this.key = key;
		this.value = val;
		this.parent = p;
		this.left = new NodoABB<>(null,null,this);
		this.right = new NodoABB<>(null,null,this);
	}
	public NodoABB(K key, V val, NodoABB<K,V> p,NodoABB<K,V> l, NodoABB<K,V> r) {
		this.key = key;
		this.value = val;
		this.parent = p;
		this.left = l;
		this.right = r;
	}
	
	
	public NodoABB<K,V> getLeft() {
		return left;
	}

	public void setLeft(NodoABB<K,V> left) {
		this.left = left;
	}

	public NodoABB<K,V> getRight() {
		return right;
	}

	public void setRight(NodoABB<K,V> right) {
		this.right = right;
	}
	
	public void setKey(K key) {
		this.key = key;
	}
	public void setValue(V val) {
		this.value = val;
	}

	public NodoABB<K,V> getParent() {
		return parent;
	}

	public void setParent(NodoABB<K,V> parent) {
		this.parent = parent;
	}

	@Override
	public K getKey() {
		return this.key;
	}
	
	public V getValue() {
		return this.value;
	}
	
	
}
