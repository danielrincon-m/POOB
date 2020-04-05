class Sistema {
	HashMap<String, Estacion> estaciones;
	ArrayList<Ruta> rutas;
	
	public int tiempoDeEspera(String nombreEstacion) {
		return estaciones.get(nombreEstacion).getTiempoEspera();
	}
	
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
