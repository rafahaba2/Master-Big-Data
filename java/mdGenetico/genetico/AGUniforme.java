package org.uma.mbd.mdGenetico.genetico;

public class AGUniforme extends AlgoritmoGenetico {

	/**
	 * 
	 * @param tPoblacion
	 *            Tamaño de la población que va a utilizar.
	 * @param longCromosoma
	 *            Longitud de los cromosomas de los individuos de dicha
	 *            población.
	 * @param pasos
	 *            Número de pasos del algoritmo (generaciones).
	 * @param probMutacion
	 *            Probabilidad de mutar un gen en el cromosoma.
	 * @param problema
	 *            Problema que se debe resolver.
	 */
	public AGUniforme(int tPoblacion, int longCromosoma, int pasos,
			double probMutacion, Problema problema) {
		super(tPoblacion, longCromosoma, pasos, probMutacion, problema);
	}

	/**
	 * @see AlgoritmoGenetico#recombinar(Cromosoma, Cromosoma)
	 */
	@Override
	protected Cromosoma recombinar(Cromosoma cromosoma1, Cromosoma cromosoma2) {
		// COMPLETAR
		Cromosoma cromosoma = new Cromosoma(cromosoma1.longitud(), false);

		for(int i = 0; i < cromosoma1.longitud(); i++){
			int alea = Cromosoma.gna.nextInt(2);

			if(alea == 0){
				cromosoma.gen(i, cromosoma1.gen(i));
 			}else{
				cromosoma.gen(i, cromosoma2.gen(i));
			}
		}

		return cromosoma;
	}

}
