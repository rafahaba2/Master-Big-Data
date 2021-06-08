package org.uma.mbd.mdGenetico.genetico;
import java.util.Arrays;
import java.util.Random;

public class Cromosoma {
	/**
	 * Datos de un cromosoma, cada posición del array representa un gen del
	 * cromosoma.
	 */
	protected int[] datos;

	/**
	 * Variable de clase de tipo Random.
	 */
	protected static Random gna = new Random(100);

	/**
	 * Valor por defecto 0 para los genes.
	 */
	protected static int GEN_POR_DEFECTO = 0;

	/**
	 * Constructor de la clase Cromosoma
	 * 
	 * @param longitud
	 *            Número de genes que tiene el cromosoma.
	 * @param aleatorio
	 *            Valor booleano que indica si debe asignar de forma aleatoria
	 *            un valor 0 o 1 a cada gen, o inicializarlos con el valor por
	 *            defecto. Si true se asigna aleatoriamente.
	 * @throws RuntimeException
	 *             si longitud no positiva.
	 */
	public Cromosoma(int longitud, boolean aleatorio) {
		// COMPLETAR

		if(longitud < 0){
			throw new RuntimeException("Longitud no es positiva");
		}
		datos = new int[longitud];
		setGen(aleatorio);
	}

	/**
	 * Metodo privado que me va a servir para dar valores aleatorios a los genes en caso de que aleatorio sea true
	 * en caso de que aleatorio sea false, se rellenara con 0s
	 */

	private void setGen(boolean aleatorio){

		int n_alea;

		if(aleatorio){
			for(int i = 0; i < datos.length; i++){
				n_alea = gna.nextInt(2);
				datos[i] = n_alea;
			}
		}else{
			for(int i = 0; i < datos.length; i++){
				datos[i] = GEN_POR_DEFECTO;
			}
		}

	}

	/**
	 * Consulta el gen en la posición indicada.
	 * 
	 * @param i
	 *            índice del gen que se consulta.
	 * @return Valor del gen en la posición indicada.
	 * @throws RuntimeException
	 *             si el índice está fuera del rango de valores válidos.
	 */
	public int gen(int i) {

		if(i >= datos.length){
			throw new RuntimeException("Indice fuera del rango de valores validos");
		}

		return datos[i];
	}

	/**
	 * Modifica el i-ésimo gen del cromosoma.
	 * 
	 * @param i
	 *            índice del gen a modificar.
	 * @param val
	 *            Nuevo valor para el gen.
	 * @throws RuntimeException
	 *             si i está fuera del rango de valores válidos o si val no es
	 *             un valor válido.
	 */
	public void gen(int i, int val) {
		//COMPLETAR
		if(i >= datos.length){
			throw new RuntimeException("Indice fuera del rango de valores validos");
		}

		datos[i] = val;
	}

	/**
	 * Invierte los valores de los genes aleatoriamente.
	 * 
	 * @param probMutacion
	 *            Probabilidad de mutacion de cada gen.
	 * @throws RuntimeException
	 *             si la probabilidad indicada no es un valor válido.
	 */
	public void mutar(double probMutacion) {
		// COMPLETAR
		if(probMutacion < 0 || probMutacion > 1){
			throw new RuntimeException("Probabilidad de mutacion indicada no tiene un valor valido");
		}

		mutarGen(probMutacion);
	}

	/**
	 * Metodo privado para mutar el gen segun la probMutacion que le pasamos como parámetro
	 * @param probMutacion
	 */
	private void mutarGen(double probMutacion){
		double n_alea;

		for(int i = 0; i < datos.length; i++){
			//Generamos un numero aleatorio entre 0 y 1
			n_alea = gna.nextDouble();
			if(n_alea <= probMutacion && datos[i] == 1){
				datos[i] = 0;
			}else if(n_alea <= probMutacion && datos[i] == 0){
				datos[i] = 1;
			}
		}
	}

	/**
	 * Longitud del cromosoma.
	 * 
	 * @return int Longitud del cromosoma.
	 */
	public int longitud() {
		return datos.length;
	}

	/**
	 * Realiza una copia en profundidad del cromosoma.
	 * 
	 * @return Un objeto Cromosoma copia del objeto que recibe el mensaje.
	 */
	public Cromosoma copia() {
		// Creamos un cromosoma igual.
		// los arrays de datos deben ser iguales pero no el mismo!
		// COMLETAR
		Cromosoma copia = new Cromosoma(datos.length, false);
		for(int i = 0; i < datos.length; i++){
			copia.gen(i, datos[i]);
		}
		return copia;
	}

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		for (int i = 0; i < datos.length - 1; i++) {
			s.append(datos[i]).append(", ");
		}
		return "Cromosoma(" + s + datos[datos.length - 1] + ")";
	}
}
