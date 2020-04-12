class ComparaRutas implements Comparator<RutaDisponible> {
	//Punto # 4: consulta frecuente
	/**
	*ordena el nombre de las rutas alfabeticamente ordenadas de menor a mayor por el numero de paradas
	*@param RutaDisponible r1, RutaDisponible r2, nombre de las rutas a comparar
	*/	
	public int compare(RutaDisponible r1, RutaDisponible r2) {
		int comparaParadas = Integer.compare(r1.numParadas, r2.numParadas);
		int comparaNombre = r1.nombre.compareTo(r2.nombre);
		
		if (comparaParadas == 0) {
			return comparaNombre;
		}
		return comparaParadas;
	}
}
