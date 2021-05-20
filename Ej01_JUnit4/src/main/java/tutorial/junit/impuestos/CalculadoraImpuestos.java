package tutorial.junit.impuestos;


public interface CalculadoraImpuestos {
	double calcularImpuestosSobreIngreso(double ingreso) throws InvalidIngresoException;
}
