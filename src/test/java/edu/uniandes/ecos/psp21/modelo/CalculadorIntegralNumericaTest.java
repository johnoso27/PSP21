package edu.uniandes.ecos.psp21.modelo;

import junit.framework.TestCase;

/**
 * Clase que contiene las pruebas de los métodos la clase
 * CalculadorIntegralNumerica
 *
 * @version 1.0.1.0
 * @author JohnDany
 */
public class CalculadorIntegralNumericaTest extends TestCase {

    /**
     * Test del metodo
     * @param testName 
     */
    public CalculadorIntegralNumericaTest(String testName) {
        super(testName);
    }

    /**
     * Test del método calcularIntegralNumerica de la clase
     * CalculadorIntegralNumerica.
     * @throws java.lang.Exception
     */
    public void testCalcularIntegralNumerica() throws Exception {
        CalculadorIntegralNumerica calculador = new CalculadorIntegralNumerica();
        assertNotNull(null, calculador);

        double actual = calculador.calcularIntegralNumerica(10, 1.1, 9.0);
        System.out.println("Prueba de datos sin margen de error: x=1.1 dof=9 valor esperado=0.3500589 valor actual=" + actual);
        assertEquals(0.3500589, actual, 0.5);
    }

    /**
     * Test para el método calcularIntegralNumericaEnMargenError, de la clase
     * CalculadorIntegralNumerica.
     *
     * @throws java.lang.Exception
     */
    public void testCalcularIntegralNumericaEnMargenError() throws Exception {
        CalculadorIntegralNumerica calculador = new CalculadorIntegralNumerica();
        assertNotNull(null, calculador);

        double actual = calculador.calcularIntegralNumericaEnMargenError(10, 1.1, 0.000001, 9.0);
        System.out.println("Prueba de datos 1 : x=1.1 dof=9 Margen de error:0.000001 valor esperado=0.3500589 valor actual=" + actual);
        assertEquals(0.3500589, actual, 0.000001);

        actual = calculador.calcularIntegralNumericaEnMargenError(10, 1.1812, 0.000001, 10.0);
        System.out.println("Prueba de datos 2 : x=1.1812 dof=10 Margen de error:0.000001 valor esperado=0.36757 valor actual=" + actual);
        assertEquals(0.36757, actual, 0.000004);

        actual = calculador.calcularIntegralNumericaEnMargenError(10, 2.750, 0.000001, 30.0);
        System.out.println("Prueba de datos 3 : x=2.750 dof=30 Margen de error:0.000001 valor esperado=0.49500 valor actual=" + actual);
        assertEquals(0.49500, actual, 0.000003);
    }

    /**
     * Test para el método calcularLimiteIntegral, de la clase
     * CalculadorIntegralNumerica.
     *
     * @throws java.lang.Exception
     */
    public void testCalcularLimiteIntegral() throws Exception {
        CalculadorIntegralNumerica calculador = new CalculadorIntegralNumerica();
        assertNotNull(null, calculador);

        double actual = calculador.calcularLimiteIntegral(10, 0.20, 0.000001, 6.0);
        assertEquals(0.55338, actual, 0.000001);
        System.out.println("Prueba 1: p=0.20 Margen de error=0.000001 dof=6 valor esperado=0.55338 valor actual=" + actual);
        actual = calculador.calcularLimiteIntegral(10, 0.45, 0.000001, 15.0);
        assertEquals(1.75305, actual, 0.000001);
        System.out.println("Prueba 2: p=0.45 Margen de error=0.000001 dof=15 valor esperado=1.75305 valor actual=" + actual);
        actual = calculador.calcularLimiteIntegral(10, 0.495, 0.000001, 4.0);
        assertEquals(4.60409, actual, 0.00003);
        System.out.println("Prueba 3: p=0.495 Margen de error=0.000001 dof=4 valor esperado=4.60409 valor actual=" + actual);
    }
}
