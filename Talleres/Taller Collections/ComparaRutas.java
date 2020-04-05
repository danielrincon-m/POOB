class ComparaRutas implements Comparator<RutaDisponible> {	
	public int compare(RutaDisponible r1, RutaDisponible r2) {
		int comparaParadas = Integer.compare(r1.numParadas, r2.numParadas);
		int comparaNombre = r1.nombre.compareTo(r2.nombre);
		
		if (comparaParadas == 0) {
			return comparaNombre;
		}
		return comparaParadas;
	}
}
