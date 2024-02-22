package TP3Listas;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.*;
import static org.junit.Assert.*;    


/*
Estos tests asumen que los ejercicios fueron resueltos mediante una clase Ejercicios
con un método público para cada ejercicio.
 
public class EjerciciosTP3<E> {
	
	ej1aPertenece
	ej1bAlMenosNVeces
	ej2aIntercalar
	ej2bIntercalarOrdenado
	ej3Invertir
	ej4RespetaFormato
	ej5Eliminar

}
*/


public class EjerciciosTP3Test {
	
    private EjerciciosTP3<Integer> ejerciciosInt;
    
    @Before
    public void setUp() {
        ejerciciosInt = new EjerciciosTP3<>();
    }

    @Test
    public void testEj1aPertenece() {
    	ArrayList<Integer> l = new ArrayList<>(Arrays.asList(1, 2, 3, 4));

    	assertTrue(ejerciciosInt.ej1aPertenece(l, 1));
    	assertTrue(ejerciciosInt.ej1aPertenece(l, 4));
        assertFalse(ejerciciosInt.ej1aPertenece(l, 16));
    }
    
    @Test
    public void testEj1aPerteneceListaVacia() {
    	ArrayList<Integer> l = new ArrayList<>();

    	assertFalse(ejerciciosInt.ej1aPertenece(l, 1));
    	assertFalse(ejerciciosInt.ej1aPertenece(l, 4));
    }

    @Test
    public void testEj1aPerteneceEquivalencia() {
    	EjerciciosTP3<Persona> ej = new EjerciciosTP3<>();
    	ArrayList<Persona> l = new ArrayList<>();
    	l.add(new Persona("Luke"));
    	l.add(new Persona("Anakin"));
    	
    	assertTrue("La comparación de elementos debe ser por equivalencia", ej.ej1aPertenece(l, new Persona("Anakin")));
    }
    
	@Test
    public void testEj1bAlMenosNVeces() {
    	ArrayList<Integer> l = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 2, 2, 3, 4, 5));

    	assertTrue(ejerciciosInt.ej1bAlMenosNVeces(l, 1, 1));
    	assertTrue(ejerciciosInt.ej1bAlMenosNVeces(l, 2, 2));
    	assertTrue(ejerciciosInt.ej1bAlMenosNVeces(l, 2, 3));
    	assertTrue(ejerciciosInt.ej1bAlMenosNVeces(l, 4, 1));
    	assertTrue(ejerciciosInt.ej1bAlMenosNVeces(l, 4, 2));
    	assertFalse(ejerciciosInt.ej1bAlMenosNVeces(l, 2, 4));
    	assertFalse(ejerciciosInt.ej1bAlMenosNVeces(l, 5, 2));
    }
	
	@Test
    public void testEj1bAlMenosNVecesListaVacia() {
    	ArrayList<Integer> l = new ArrayList<>();

    	assertFalse(ejerciciosInt.ej1bAlMenosNVeces(l, 1, 1));
    	assertFalse(ejerciciosInt.ej1bAlMenosNVeces(l, 4, 1));
    }
	
	@Test
    public void testEj1bAlMenosNVecesIdentidad() {
    	EjerciciosTP3<Persona> ej = new EjerciciosTP3<>();
    	ArrayList<Persona> l = new ArrayList<>();
    	Persona luke = new Persona("Luke");
    	Persona anakin = new Persona("Anakin");
    	l.add(luke);
    	l.add(anakin);
    	
    	assertTrue("La comparación de elementos debe ser por identidad", ej.ej1bAlMenosNVeces(l, anakin, 1));
    	assertFalse("La comparación de elementos debe ser por identidad", ej.ej1bAlMenosNVeces(l, new Persona("Anakin"), 1));
    }
	
	@Test
	public void testEj2aIntercalar() {
		ArrayList<Integer> l1 = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 2, 2, 3, 4));
		ArrayList<Integer> l2 = new ArrayList<>(Arrays.asList(7, 4, 2, 8));

		assertEquals(Arrays.asList(1, 7, 2, 4, 3, 2, 4, 8, 2, 2, 3, 4), ejerciciosInt.ej2aIntercalar(l1, l2));
		assertEquals(Arrays.asList(7, 1, 4, 2, 2, 3, 8, 4, 2, 2, 3, 4), ejerciciosInt.ej2aIntercalar(l2, l1));
	}
	
	@Test
	public void testEj2aIntercalarListasVacias() {
		ArrayList<Integer> l1 = new ArrayList<>(Arrays.asList(1, 2, 3));
		ArrayList<Integer> vacia = new ArrayList<>();

		assertEquals(l1, ejerciciosInt.ej2aIntercalar(l1, vacia));
		assertEquals(l1, ejerciciosInt.ej2aIntercalar(vacia, l1));
		assertEquals(vacia, ejerciciosInt.ej2aIntercalar(vacia, vacia));
	}
	
    @Test
    public void testEj2bIntercalarOrdenado() {
    	ArrayList<Integer> l1 = new ArrayList<>(Arrays.asList(1, 2, 2, 3, 3, 4));
		ArrayList<Integer> l2 = new ArrayList<>(Arrays.asList(2, 4, 4, 8));
    	
		assertEquals(Arrays.asList(1, 2, 3, 4, 8), ejerciciosInt.ej2bIntercalarOrdenado(l1, l2));
		assertEquals(Arrays.asList(1, 2, 3, 4, 8), ejerciciosInt.ej2bIntercalarOrdenado(l2, l1));
    }
    
    @Test
    public void testEj3Invertir() {
    	ArrayList<Integer> l = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 2, 2, 3, 4));
    	ArrayList<Integer> expected = new ArrayList<>(Arrays.asList(4, 3, 2, 2, 4, 3, 2, 1));
    	
    	ejerciciosInt.ej3Invertir(l);
    	assertEquals(expected, l);
    }
    
    @Test
    public void testEj3InvertirVacio() {
    	ArrayList<Integer> l = new ArrayList<>();
    	
    	ejerciciosInt.ej3Invertir(l);
    	assertEquals(new ArrayList<Integer>(), l);
    }

    @Test
    public void testEj4RespetaFormato() {
    	ArrayList<Integer> l1 = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 4, 3, 2, 1));
    	ArrayList<Integer> l2 = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
    		
        assertTrue(ejerciciosInt.ej4RespetaFormato(l1, l2));
        
        ArrayList<Integer> l3 = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 3, 2, 1));
        ArrayList<Integer> l4 = new ArrayList<>(Arrays.asList(2, 2, 3, 4, 4, 3, 2, 1));
        
        assertFalse(ejerciciosInt.ej4RespetaFormato(l1, l3));
        assertFalse(ejerciciosInt.ej4RespetaFormato(l1, l4));
    }
    
    @Test
    public void testEj4RespetaFormatoTamanioDiferente() {
    	ArrayList<Integer> l1 = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 4, 3, 2, 1));
    	ArrayList<Integer> l2 = new ArrayList<>(Arrays.asList(1, 2, 3));
    	ArrayList<Integer> vacia = new ArrayList<>();
    		
        assertFalse(ejerciciosInt.ej4RespetaFormato(l1, l2));
        assertFalse(ejerciciosInt.ej4RespetaFormato(l1, vacia));
        assertFalse(ejerciciosInt.ej4RespetaFormato(vacia, l1));
        
        ArrayList<Integer> l3 = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 3, 2, 1));
        ArrayList<Integer> l4 = new ArrayList<>(Arrays.asList(2, 2, 3, 4, 4, 3, 2, 1));
        
        assertFalse(ejerciciosInt.ej4RespetaFormato(l1, l3));
        assertFalse(ejerciciosInt.ej4RespetaFormato(l1, l4));
    }
    
    @Test
    public void testEj4RespetaFormatoListaVacia() {
    	ArrayList<Integer> vacia = new ArrayList<>();
    		
        assertTrue(ejerciciosInt.ej4RespetaFormato(vacia, vacia));
    }
    
    @Test
    public void testEj4RespetaFormatoEquivalencia() {
    	EjerciciosTP3<Persona> ej = new EjerciciosTP3<>();
    	ArrayList<Persona> l1 = new ArrayList<>();
    	l1.add(new Persona("Luke"));
    	l1.add(new Persona("Anakin"));
    	l1.add(new Persona("Anakin"));
    	l1.add(new Persona("Luke"));
    	
    	ArrayList<Persona> l2 = new ArrayList<>();
    	l2.add(new Persona("Luke"));
    	l2.add(new Persona("Anakin"));
        
        assertTrue(ej.ej4RespetaFormato(l1, l2));
    }
    
	@Test
    public void testEj5Eliminar() {
    	ArrayList<Integer> l1 = new ArrayList<>(Arrays.asList(1, 5, 2, 3, 4, 3, 2, 1));
    	ArrayList<Integer> l2 = new ArrayList<>(Arrays.asList(1, 2, 3));
        
    	ejerciciosInt.ej5Eliminar(l1, l2);
        assertEquals(Arrays.asList(5, 4, 3, 2, 1), l1);
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