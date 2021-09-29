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

        System.out.print("Masukkan titik yang ingin ditaksir: ");
        Double x = scanner.nextDouble();
        result += "titik yang ingin ditaksir merupakan: " + x + "\n\n";

        polinomFormat.fillToMatrix();
        polinomFormat.matrix.obe_gauss_jordan(); System.out.println("");
        polinomFormat.copyEquation();

        System.out.println("Persamaan yang didapat merupakan: \n" + polinomFormat.getEquation());
        result += "Persamaan yang didapat merupakan: \n" + polinomFormat.getEquation();

        System.out.print("Maka ditaksir y(" + x + ") = " + String.format("%.4f", polinomFormat.getYFromEq(x)));
        result += "\nMaka ditaksir y(" + x + ") = " + String.format("%.4f", polinomFormat.getYFromEq(x));

        return result;
    }
}
