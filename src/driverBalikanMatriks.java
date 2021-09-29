
public class driverBalikanMatriks {
    
    public static String metode_gaussJordan(){
        String result = "Balikan matriks melalui metode Gauss Jordan:\n";
        System.out.println("Masukkan matriks:");
        Matriks matriks = new Matriks();
        Extended.readMatrix(matriks);
        System.out.println("\nMatriks yang dimasukkan:");
        Extended.printMatrix(matriks);
        result += Extended.getMatriksString(matriks) + "\n";
        
        if (Determinan_Matriks.kofaktor(matriks) == 0){
            System.out.println("Matriks tidak dapat ditentukan balikan karena determinan matriks merupakan 0.0");
            result += "Matriks tidak dapat ditentukan balikan karena determinan matriks merupakan 0.0";
        }
        else {
            Matriks inversedMat = new Matriks();
            inversedMat = Balikan_Matriks.gauss_jordan(matriks);

            System.out.println("\nMatriks balikannya merupakan:");
            Extended.printMatrix(inversedMat);

            result += "Matriks balikannya merupakan:\n";
            result += Extended.getMatriksString(inversedMat);
        }
        return result;
    }

    public static String metode_adjoint(){
        String result = "Balikan matriks melalui metode adjoin:\n";
        System.out.println("Masukkan matriks:");
        Matriks matriks = new Matriks();
        Extended.readMatrix(matriks);
        System.out.println("\nMatriks yang dimasukkan:");
        Extended.printMatrix(matriks);
        result += Extended.getMatriksString(matriks) + "\n";
        
        if (Determinan_Matriks.kofaktor(matriks) == 0){
            System.out.println("Matriks tidak dapat ditentukan balikan karena determinan matriks merupakan 0.0");
            result += "Matriks tidak dapat ditentukan balikan karena determinan matriks merupakan 0.0";
        }
        else {
            Matriks inversedMat = new Matriks();
            inversedMat = Balikan_Matriks.adjoin(matriks);

            System.out.println("\nMatriks balikannya merupakan:");
            Extended.printMatrix(inversedMat);

            result += "Matriks balikannya merupakan:\n";
            result += Extended.getMatriksString(inversedMat);
        }
        return result;
    }
}
