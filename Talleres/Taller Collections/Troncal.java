class Troncal {
	public void exportarTroncal() {
		File file = new File(nombre + ".txt");
		file.createNewFile();
		FileWriter fw = new FileWriter(file);
		fw.append(nombre + "\n");
		fw.append(recorridoPromedio + "\n");
		fw.append(treeMapToString(tramos) + "\n");
		fw.append(arrayListToString(estaciones));
		fw.close();
	}
}