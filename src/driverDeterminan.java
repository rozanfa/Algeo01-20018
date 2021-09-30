public class driverDeterminan {
	public static String metode_kofaktor(){
        String result = "Matriks yang akan dicari determinannya melalui metode kofaktor:\n";
        Matriks matriks = new Matriks();
        Extended.readMatrix(matriks);
        System.out.println("\nMatriks yang dimasukkan:");
        Extended.printMatrix(matriks);
        result += Extended.getMatriksString(matriks) + "\n";
        result += "Determinan matriksnya adalah: ";
        System.out.println("Determinan matriksnya adalah: " + String.format("%.2f", Determinan_Matriks.kofaktor(matriks)));
        result += String.format("%.2f", Determinan_Matriks.kofaktor(matriks));
        return result;
    }
	public static String metode_sarrus() {
		String result = "Matriks yang akan dicari determinannya melalui metode sarrus:\n";
        Matriks matriks = new Matriks();
        Extended.readMatrix(matriks);
        System.out.println("\nMatriks yang dimasukkan:");
        Extended.printMatrix(matriks);
        Determinan_Matriks.DetwithSarrus(matriks);
        result += Extended.getMatriksString(matriks) + "\n";
        result+="Determinan matriksnya adalah: "+ String.format("%.2f", Determinan_Matriks.kofaktor(matriks));
        return result;
	}
	public static String metode_rowreduction() {
		String result = "Matriks yang akan dicari determinannya melalui metode rowreduction:\n";
		Matriks matriks = new Matriks();
        Extended.readMatrix(matriks);
        System.out.println("\nMatriks yang dimasukkan:");
        Extended.printMatrix(matriks);
        Determinan_Matriks.DetwithRowReduction(matriks);
        result += Extended.getMatriksString(matriks) + "\n";
        result+="Determinan matriksnya adalah: "+ String.format("%.2f", Determinan_Matriks.kofaktor(matriks));
        return result;
	}

}
