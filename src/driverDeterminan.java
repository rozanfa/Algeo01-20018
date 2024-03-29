public class driverDeterminan extends Matriks{
	public static String metode_kofaktor(){
        String result = "Matriks yang akan dicari determinannya melalui metode kofaktor:\n";
        Matriks matriks = new Matriks();
        matriks.isiMatriks();
        System.out.println("\nMatriks yang dimasukkan:");
        matriks.cetakMatriks();
        result += matriks.getMatriksString() + "\n";
        result += "Determinan matriksnya adalah: ";
        System.out.println("Determinan matriksnya adalah: " + Determinan_Matriks.kofaktor(matriks));
        result += String.format("%.2f", Determinan_Matriks.kofaktor(matriks));
        return result;
    }
	public static String metode_sarrus() {
	        Matriks matriks = new Matriks();
	        matriks.isiMatriks();
	        System.out.println("\nMatriks yang dimasukkan:");
	        matriks.cetakMatriks();
	        if (matriks.jEff==3 & matriks.iEff==3) {
	        	String result = "Matriks yang akan dicari determinannya melalui metode sarrus:\n";
	        	result += matriks.getMatriksString() + "\n";
	            result+="Determinan matriksnya adalah: "+ Determinan_Matriks.DetwithSarrus(matriks);
	            return result;
	        }
	        else {
	        	System.out.println("Tidak bisa mencari determinan menggunakan metode Sarrus selain di matriks 3 x 3");
	        	String result ="Tidak bisa mencari determinan menggunakan metode Sarrus selain di matriks 3 x 3";
	        	return result;
	        }
	}
	public static String metode_obe() {
		String result = "Matriks yang akan dicari determinannya melalui metode obe:\n";
		Matriks matriks = new Matriks();
		matriks.isiMatriks();
        System.out.println("\nMatriks yang dimasukkan:");
        matriks.cetakMatriks();
        result += matriks.getMatriksString()+"\n";
        result+="Determinan matriksnya adalah: "+ Determinan_Matriks.DetwithOBE(matriks);
        return result;
	}

}
