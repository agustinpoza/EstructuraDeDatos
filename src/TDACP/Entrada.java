package TDACP;

public class Entrada<K, V> implements Entry<K,V>{
	
	protected V value;
	protected K key;
	
	public Entrada(K clave, V valor) {
		key = clave;
		value = valor;
	}
	
	public V getValue() {
		return value;
	}
	public void setValue(V value) {
		this.value = value;
	}
	public K getKey() {
		return key;
	}
	public void setKey(K key) {
		this.key = key;
	}
	
}
