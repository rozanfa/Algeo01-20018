import java.util.Scanner;

public class driverTesting {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Masukkan n: ");
        int n = scanner.nextInt();
        
        Polinom_Matriks shit = new Polinom_Matriks();
        shit.readPoints(n);

        shit.printPoints();
        shit.fillToMatrix();
        shit.matrix.obe_gauss_jordan(); 
        shit.copyEquation();
        System.out.println(shit.getYFromEq(9.2));
        scanner.close();
    }

}
