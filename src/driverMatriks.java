import java.util.Scanner;

public class driverMatriks {
    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            System.out.println("\nMENU");
            System.out.println("1. Sistem Persamaan Linier");
            System.out.println("2. Determinan");
            System.out.println("3. Matriks balikan");
            System.out.println("4. Interpolasi Polinom");
            System.out.println("5. Regresi linier berganda");
            System.out.println("6. Keluar");
            System.out.print("Pilihan : ");
            Scanner scanner = new Scanner(System.in);
            int option = scanner.nextInt();
            System.out.println("");
            switch (option) {
                case 1: {
                    sistem_persamaan_linear();
                    break;
                }
                case 2: {

                    break;
                }
                case 3: {

                    break;
                }
                case 4: {

                    break;
                }
                case 5: {

                    break;
                }
                case 6: {
                    running = false;
                    break;
                }
                default: {
                    System.out.println("Input tidak valid");
                }
            }
        }

        /*
        Matriks mat1 = new Matriks();
        Extended.readMatrix(mat1);
        System.out.println("Matriks yang dimasukkan adalah:");
        Extended.printMatrix(mat1);
        System.out.println("Akan dilakukan operasi invers memakai metode -adjoin");
        // Extended.adjoint(mat1);
        Extended.printMatrix(Balikan_Matriks.adjoin(mat1));
        

        SPL_Matriks mat2 = new SPL_Matriks();
        mat2.spl_gauss_jordan();
        */
    }

    public static void sistem_persamaan_linear(){
        System.out.println("PILIH METODE");
        System.out.println("1. Metode eliminasi Gauss");
        System.out.println("2. Metode eliminasi Gauss-Jordan");
        System.out.println("3. Metode matriks balikan");
        System.out.println("4. Kaidah Crammer");
        System.out.print("Pilihan : ");
        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();
        System.out.println("");
        switch (option) {
            case 1: {
                driverSPLMatirks.metode_gauss();
                break;
            }
            case 2: {
                driverSPLMatirks.metode_gauss_jordan();
                break;
            }
            case 3: {
                driverSPLMatirks.metode_invers();
                break;
            }
            case 4: {
                driverSPLMatirks.metode_kaidah_crammer();
                break;
            }
            default : {
                System.out.println("Input tidak valid");
            }
        }
    }
}
