import java.util.Scanner;

public class driverPolinom {
    
    public static String polinom(){
        Scanner scanner = new Scanner(System.in);
        Polinom_Matriks polinomFormat = new Polinom_Matriks();
        String result = "Mencari Interpolasi polinom:\n";

        System.out.print("Berapa banyaknya titik yang ingin dimasukkan: ");
        int n = scanner.nextInt();
        polinomFormat.readPoints(n);
        result += polinomFormat.getPoints();

        System.out.print("Masukkan jumlah titik yang ingin ditaksir: ");
        int xAmount = scanner.nextInt();
        System.out.println("Masukkan titik yang ingin ditaksir:");
        Double x[] = new Double[xAmount];
        for (int i = 0; i < xAmount; i++){
            x[i] = scanner.nextDouble();
        }
        result += "\nTitik yang ingin ditaksir adalah: ";
        for (int i = 0; i < xAmount; i++){
            result +=  x[i];
            if (i != xAmount -1) result += ", ";
        }   result += "\n";


        polinomFormat.fillToMatrix();
        polinomFormat.matrix.obe_gauss_jordan(); System.out.println("");
        polinomFormat.copyEquation();

        System.out.println("Persamaan yang didapat merupakan: \n" + polinomFormat.getEquation());
        result += "Persamaan yang didapat merupakan: \n" + polinomFormat.getEquation();

        result += "\nMaka ditaksir:";
        System.out.println("Maka ditaksir:");
        for (int i = 0; i < xAmount; i++){
            System.out.print("p(" + x[i] + ") = " + String.format("%.4f", polinomFormat.getYFromEq(x[i])) + "\n");
            result += "\np(" + x[i] + ") = " + String.format("%.4f", polinomFormat.getYFromEq(x[i]));
        }
        return result;
    }
}
