import java.util.Scanner;

public class driverPolinom {
    
    public static String polinom(){
        Scanner scanner = new Scanner(System.in);
        Polinom_Matriks polinomFormat = new Polinom_Matriks();
        String result = "Interpolasi polinom:\n";
        result += "\nTitik yang diberikan:";

        System.out.println("Pilih jenis input");
        System.out.println("1. Input dari keyboard");
        System.out.println("2. Input dari file");
        System.out.print("Pilihan : ");
        int option = scanner.nextInt();
        System.out.println("");
        switch (option) {
            case 1: {
                System.out.print("Berapa banyaknya titik yang ingin dimasukkan: ");
                int n = scanner.nextInt();
                polinomFormat.readPoints_keyboard(n);
                result += polinomFormat.getPoints();
                break;
            }
            case 2: {
                System.out.print("Berapa banyaknya titik yang ingin dimasukkan: ");
                int n = scanner.nextInt();
                result += polinomFormat.getPoints();
                polinomFormat.readPoints_file(n);
                break;
            }
            default : {
                System.out.println("Input tidak valid");
            }
        }

        result += polinomFormat.getPoints();
        System.out.print("Masukkan jumlah titik yang ingin ditaksir: ");
        int xAmount = scanner.nextInt();

        if (xAmount > 0) System.out.println("Masukkan titik yang ingin ditaksir:");
        Double x[] = new Double[xAmount];
        for (int i = 0; i < xAmount; i++){
            x[i] = scanner.nextDouble();
        }
        if (xAmount > 0) result += "\nTitik yang ingin ditaksir adalah:\n";
        for (int i = 0; i < xAmount; i++){
            result +=  x[i];
            if (i != xAmount -1) result += ", ";
        }   
        if(xAmount > 0) result += "\n"; 


        polinomFormat.fillToMatrix();
        polinomFormat.matrix.obe_gauss_jordan(); System.out.println("");
        polinomFormat.copyEquation();

        System.out.println("Persamaan yang didapat merupakan: \n" + polinomFormat.getEquation());
        result += "\nPersamaan yang didapat merupakan:\n" + polinomFormat.getEquation();

        if (xAmount > 0){
            result += "\nMaka ditaksir:";
            System.out.println("Maka ditaksir:");
            for (int i = 0; i < xAmount; i++){
                System.out.print("p(" + x[i] + ") = " + String.format("%.4f", polinomFormat.getYFromEq(x[i])) + "\n");
                result += "\np(" + x[i] + ") = " + String.format("%.4f", polinomFormat.getYFromEq(x[i]));
            }
        }
        
        return result;
    }
}
