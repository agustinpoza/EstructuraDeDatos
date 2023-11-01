package tp4;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import TDALista.PositionList;  
import TDALista.ListaDE;

/*
Estos tests asumen que los ejercicios 3 y 4 fueron resueltos mediante una clase EjerciciosTP4
con un método público para cada ejercicio.
 
public class EjerciciosTP4<E> {
	
	ej3Pertenece
	ej4Duplicados
	
}

Los ejercicios 1 y 2 se asumen fueron resueltos mediantes una clase DoubleLinkedList.
*/

public class EjerciciosTP4Test {
	
	private EjerciciosTP4<Integer> ejerciciosInt;
	    
    @Before
    public void setUp() {
        ejerciciosInt = new EjerciciosTP4<>();
    }

	@Test
	public void testEj2agregar() {
		ListaDE<Integer> l = new ListaDE<>();
		l.agregar(1,2);
		
		int[] expected = {2, 1};
		
		int i = 0;
		for(int e : l) {
			assertEquals(expected[i], e);
			i++;
		}
		
		assertEquals(2, l.size());
		
		l.agregar(3,4);
		int[] expected2 = {2, 3, 4, 1};
		
		i = 0;
		for(int e : l) {
			assertEquals(expected2[i], e);
			i++;
		}
		
		l.agregar(5,6);
		int[] expected3 = {2, 5, 3, 4, 6, 1};
		
		i = 0;
		for(int e : l) {
			assertEquals(expected3[i], e);
			i++;
		}
	}
		
	@Test
	public void testEj3Pertenece() {
		PositionList<Integer> l = new ListaDE<Integer>();
		l.addLast(1);
		l.addLast(3);
		l.addLast(5);
		l.addLast(7);
		assertTrue(ejerciciosInt.ej3Pertenece(l, 3));
		assertTrue(ejerciciosInt.ej3Pertenece(l, 7));
		assertFalse(ejerciciosInt.ej3Pertenece(l, 4));;
	}
	
	@Test
	public void testEj3PerteneceVacio() {
		PositionList<Integer> l = new ListaDE<Integer>();
		assertFalse(ejerciciosInt.ej3Pertenece(l, 3));
	}
	
	@Test
	public void testEj3PerteneceEquivalencia() {
		EjerciciosTP4<Persona> ej = new EjerciciosTP4<Persona>();
		
		PositionList<Persona> l = new ListaDE<Persona>();
    	l.addLast(new Persona("Luke"));
    	l.addLast(new Persona("Anakin"));
    	
    	assertTrue("La comparación de elementos debe ser por equivalencia", ej.ej3Pertenece(l, new Persona("Anakin")));
	}
	
	@Test
	public void testEj4Duplicados() {
		PositionList<Integer> l = new ListaDE<Integer>();
		l.addLast(1);
		l.addLast(3);
		l.addLast(5);
		l.addLast(7);
		
		int[] expected = {1, 1, 3, 3, 5, 5, 7, 7};
		
		int i = 0;
		for(int e : ejerciciosInt.ej4Duplicados(l)) {
			assertEquals(expected[i], e);
			i++;
		}
	}
	
	@Test
	public void testEj4DuplicadosVacia() {
		PositionList<Integer> vacia = new ListaDE<Integer>();
		assertEquals(0, ejerciciosInt.ej4Duplicados(vacia).size());
	}
}

class Persona {
	private String name;
	
	public Persona(String n) {
		name = n;
	}
	
	public String getName() {
		return name;
	}
	
	public boolean equals(Object o) {
		if (!(o instanceof Persona)) {
            return false;
        }
		Persona p = (Persona) o;
         
		return name.equals(p.getName());		
	}
}
