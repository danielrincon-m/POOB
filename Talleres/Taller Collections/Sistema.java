class Sistema {
	HashMap<String, Estacion> estaciones;
	ArrayList<Ruta> rutas;
	
	//Punto # 1 : consulta frecuente
	/**
	*obtiene el  tiempo de espera de una estacion 
	*@param nombreEstacion, nombre de la estacion
	*@return nombreRutas
	*/
	public int tiempoDeEspera(String nombreEstacion) {
		if(!existeEstacion(nombreEstacion)){
			throw new SistemaException(SistemaException.ESTACION_NO_EXISTE);
		}
		return estaciones.get(nombreEstacion).getTiempoEspera();
	}
	
	//Punto # 4: consulta frecuente
	/**
	*obtiene las rutas para ir de una estaciona a otra
	*@param estacion1,estacion1, nombre de las dos estaciones para desplazarce
	*@return nombreRutas
	*/
	public ArrayList<String> getRutas (String estacion1, String estacion2) {
		if (!existeEstacion(estacion1) || !existeEstacion(estacion2)) {
			throw new SistemaException(SistemaException.ESTACION_NO_EXISTE);
		}
		
		ArrayList<RutaDisponible> rutasDisponibles = new ArrayList<RutaDisponible>();
		for (Ruta r : rutas) {
			if (r.visitaEstacion(estacion1) && r.visitaEstacion(estacion2)) {
				int paradas = r.getParadas(estacion1, estacion2);
				rutasDisponibles.add(new RutaDisponible(r.getNombre, paradas));
			}
		}
		Collections.sort(rutasDisponibles, new ComparaRutas());
		
		ArrayList<String> nombreRutas = new ArrayList<String>();
		for (RutaDisponible r : rutasDisponibles) {
			nombreRutas.add(r.nombre);
		}
		return nombreRutas;
	}
	//Punto #6 :consulta frecuente
	/**
	*obtiene el tiempo de recorrido de un plan de tuta
	*@param lista anidadas de un plan de rutas
	*@return tiempo de recorrido
	*/
	public int tiempoDeRecorrido(<ArrayList<ArrayList<String>>> plan){
		public int tiempoRecorrido = 0;
		for(int i=0; i<plan.length()-1 ; i++){
			nombreRuta= plan[i+1][0];
			if(!existeRuta(nombreRuta)){
				throw new SistemaException(SistemaException.RUTA_NO_EXISTE);
			}
			if(!existeEstacion(plan[i][0])){
				throw new SistemaException(SistemaException.ESTACION_NO_EXISTE);
			}
			tiempoRecorrido+= tiempoDeEspera(plan[i][0]);
		}
		return tiempoRecorrido;
		
	}
	//Punto # 1: De persistencia
	/**
	*importar nueva ruta desde un archivo texto
	*@param nombreDelArchivo, nombre del archivo texto
	*/
	public void importarRuta(String nombreDelArchivo) {
		File file = new File(nombreDelArchivo);
		Scanner scanner = new Scanner(file);
		
		String nombre = scanner.nextLine();
		String[] estaciones = scanner.nextLine().split(",");
		
		scanner.close();
		
		rutas.add(new Ruta(nombre, estaciones));
	}
	
	class RutaDisponible {
		public String nombre;
		public int numParadas;
		
		public RutaDisponible (nombre, numParadas) {
			this.nombre = nombre;
			this.numParadas = numParadas;
		}
	}
}
