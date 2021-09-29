import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class driverMatriks {
    public static String output = new String();

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
            int option;
            try {
                option = scanner.nextInt();
            }
            catch (Exception e){
                option = -1;
            }
            
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
            if (option >= 1 && option <= 5) {
                System.out.println("\nAPAKAH ANDA INGIN MENYIMPAN HASIL KE DALAM FILE?");
                System.out.println("1. Ya");
                System.out.println("2. Tidak");
                System.out.print("Pilihan : ");
                option = scanner.nextInt();
                System.out.println("");
                switch (option) {
                    case 1: {
                        saveResult();
                        break;
                    }
                    case 2: {
                        break;
                    }
                    default: {
                        System.out.println("Input tidak valid");
                    }
                }
            }
        }
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
                output = driverSPLMatirks.metode_gauss();
                break;
            }
            case 2: {
                output = driverSPLMatirks.metode_gauss_jordan();
                break;
            }
            case 3: {
                output = driverSPLMatirks.metode_invers();
                break;
            }
            case 4: {
                output = driverSPLMatirks.metode_kaidah_crammer();
                break;
            }
            default : {
                System.out.println("Input tidak valid");
            }
        }
    }



    static public void saveResult(){
        System.out.print("Masukkan nama file yang ingin dibuat: ");
        Scanner scanner = new Scanner(System.in);
        String fileName = scanner.nextLine();
        File file = new File("output\\" + fileName + ".txt");
        boolean succeed;

        try {
            succeed = file.createNewFile();

            if (succeed){
                System.out.println("File berhasil dibuat: " + file.getAbsolutePath());
            }

            else {
                System.out.println("File sudah ada di " + file.getAbsolutePath());
            }
        }

        catch (IOException e) {
            System.out.println("Terjadi error saat membuat file");
            e.printStackTrace();
        }

        try {
            FileWriter fileWriter = new FileWriter("output\\" + fileName + ".txt");
            fileWriter.write(output);
            fileWriter.close();
            System.out.println("Berhasil menulis ke file " + file.getAbsolutePath());
        }
        catch (IOException e) {
            System.out.println("Terjadi error saat menulis file");
            e.printStackTrace();
        }
    }
}
