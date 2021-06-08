package org.uma.mbd.mdGenetico.genetico;

public class Poblacion {
	private Individuo[] individuos;

	/**
	 * 
	 * @param tamaño
	 *            Número de individuos de la población.
	 * @param longitud
	 *            Tamaño de los cromosomas de los individuos de la población.
	 * @param problema
	 *            Problema a resolver.
	 * @throws RuntimeException
	 *             si el tamaño de la población o la longitud de los cromosomas
	 *             indicados no son válidos.
	 */
	public Poblacion(int tamaño, int longitud, Problema problema) {
		// COMPLETAR

		if(tamaño < 0 || longitud < 0){
			throw new RuntimeException("Tamaño de poblacion o longitud del cromosoma no valido");
		}

		individuos = new Individuo[tamaño];
		setIndividuo(tamaño, longitud, problema);

	}

	private void setIndividuo(int tamaño, int longitud, Problema problema){
		for(int i = 0; i < tamaño; i++){
			Individuo individuo = new Individuo(longitud, problema);
			individuos[i] = individuo;
		}
	}

	/**
	 * Devuelve el número de individuos.
	 * 
	 * @return Número de individuos en la población.
	 */
	public int numIndividuos() {
		return individuos.length;
	}

	/**
	 * Devuelve el individuo con fitness mayor de la población.
	 * 
	 * @return Individuo con mejor fitness.
	 */
	public Individuo mejorIndividuo() {
		// COMPLETAR

		double fitness = 0;
		Individuo mejorInviduo = individuos[0];
		int j = 0;

		for(int i = 1; i < individuos.length; i++){
			if(fitness < individuos[i].fitness()){
				fitness = individuos[i].fitness();
				mejorInviduo = individuos[i];
			}
		}
		return mejorInviduo;
	}

	/**
	 * Devuelve el i-ésimo individuo de la población.
	 * 
	 * @param i
	 *            Posición del individuo a devolver.
	 * @return Individuo en la posición i.
	 * @throws RuntimeException
	 *             si el índice está fuera del rango de valores válidos.
	 */
	public Individuo individuo(int i) {
		return individuos[i];
	}

	/**
	 * Si el individuo que se pasa como argumento tienen mejor fitness que el
	 * peor de los individuos de la población, entonces el peor es sustituido
	 * por el que se pasa.
	 * 
	 * @param ind
	 *            Individuo con el que se sustituye el peor de los individuos si
	 *            este es mejor.
	 */
	public void reemplaza(Individuo ind) {
		// COMPLETAR

		if(individuos[peorIndividuo()].fitness() < ind.fitness()){
			individuos[peorIndividuo()] = ind;
		}
	}

	private int peorIndividuo(){
		int pos = 0;
		double peorFit = individuos[0].fitness();

		for(int i = 1; i < numIndividuos(); i++){
			if(peorFit > individuos[i].fitness()){
				peorFit = individuos[i].fitness();
				pos = i;
			}
		}

		return pos;
	}

}
