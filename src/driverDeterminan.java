
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

    // lanjutin ya ja :))
}
