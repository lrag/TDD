package tutorial.junit;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

//En las pruebas en las que se utilizan parámetros
//solo puede haber UN metodo marcada con @Test
@RunWith(Parameterized.class)
public class ParameterizerMiClaseTest {

    @Parameters
    public static Collection<Object[]> conjuntoDeDatosDePrueba() {
        return Arrays.asList(new Object[][] {
        /* a * b = result */
        { 10, 5, 50 }, { 1, 0, 0 },
        { 2, -3, -6 }, { -1, -5, 5 }});
    }
    
    private int a;
    private int b;
    private int result;
    
    public ParameterizerMiClaseTest(int a, int b, int result) {
        super();
        this.a = a;
        this.b = b;
        this.result = result;
    }

    @Test
    public void testMultiplicar() {
        Calculadora tester = new Calculadora();
        assertEquals("Result", result, tester.multiplicar(a, b));
    }

}





