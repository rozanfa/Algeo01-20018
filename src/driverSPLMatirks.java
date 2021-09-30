public class driverSPLMatirks {
    static String metode_invers(){
        String result = "Sistem Persamaan Linier\n";
        Matriks main = new Matriks();
        System.out.println("Masukkan SPL dalam bentuk matriks augmented");
        main.isiMatriks();
        result += main.getMatriksString() + "\n";
        result += "Metode Matriks Balikan\n\n";
        if (main.iEff == (main.jEff - 1)){
            // Buat menjadi Ax = B
            Matriks b = new Matriks();
            b.iEff = main.iEff;
            b.jEff = 1;
            for (int i = 0; i < b.iEff; i++) {
                b.Mat[i][0] = main.Mat[i][main.jEff - 1];
            }

            Matriks a = new Matriks();
            a.iEff = main.iEff;
            a.jEff = main.jEff - 1;
            for (int i = 0; i < a.iEff; i++){
                for (int j = 0; j < a.jEff; j++){
                    a.Mat[i][j] = main.Mat[i][j];
                }
            }
            System.out.println("");
            System.out.println("Matriks x :");
            b.cetakMatriks();
            System.out.println("");
            System.out.println("Matriks A :");
            a.cetakMatriks();

            Double detA = Determinan_Matriks.kofaktor(a);
            if (detA == 0){
                result += "Determinan matriks = 0\nMatriks tidak mempunyai balikan\nTidak bisa diselesaikan menggunakan metode matriks balikan\n";
                System.out.println("Determinan matriks = 0\nMatriks tidak mempunyai balikan\nTidak bisa diselesaikan menggunakan metode matriks balikan");
            }
            else {
                Matriks inversA = Balikan_Matriks.adjoin(a);


                System.out.println("");
                System.out.println("Invers Matriks A :");
                inversA.cetakMatriks();
    
                Matriks res = new Matriks();
                res = Matriks.multiplyMatrix(inversA, b);
                System.out.println("");
                System.out.println("Matriks hasil perkalian invers A dengan x :");
                res.cetakMatriks();
                System.out.println("");
                result += "Solusi :\n";
                System.out.println("Solusi :");
                for (int i = 0; i < res.iEff; i++){
                    result += String.format("x[%d] = %f\n", i+1, res.Mat[i][0]);
                    System.out.println(String.format("x[%d] = %f", i+1, res.Mat[i][0]));
                }
            }
        }
        else {
            result += "Solusi banyak\nTidak bisa diselesaikan menggunakan metode matriks balikan\n";
            System.out.println("Solusi banyak");
            System.out.println("Tidak bisa diselesaikan menggunakan metode matriks balikan");
        }

        return result;
    }


    static String metode_kaidah_crammer(){
        String result = "Sistem Persamaan Linier\n";
        Matriks main = new Matriks();
        System.out.println("Masukkan SPL dalam bentuk matriks augmented");
        main.isiMatriks();
        result += main.getMatriksString() + "\n";
        result += "Kaidah Crammer\n\n";
        Double res[] = new Double[15];
        if (main.iEff == (main.jEff - 1)){
            Matriks cons = new Matriks();
            cons.iEff = main.iEff;
            cons.jEff = 1;
            for (int i = 0; i < cons.iEff; i++) {
                cons.Mat[i][0] = main.Mat[i][main.jEff - 1];
            }

            Matriks var = new Matriks();
            var.iEff = main.iEff;
            var.jEff = main.jEff - 1;
            for (int i = 0; i < var.iEff; i++){
                for (int j = 0; j < var.jEff; j++){
                    var.Mat[i][j] = main.Mat[i][j];
                }
            }
            System.out.println("");
            Double detVar = Determinan_Matriks.kofaktor(var.copy());
            System.out.println(String.format("Determinan matriks A : %f" , detVar));
            System.out.println("");

            if (detVar != 0){
                for (int j = 0; j < var.jEff; j++){
                    Matriks varCopy = var.copy();
                    for (int ii = 0; ii <= var.jEff; ii++){
                        varCopy.Mat[ii][j] = cons.Mat[ii][0];
                    }
                    //System.out.println("mat var");
                    //var.cetakMatriks();
                    //System.out.println("");
                    Double detVarCopy = Determinan_Matriks.kofaktor(varCopy);
                    System.out.println(String.format("Determinan matriks A[%d] : %f" ,j + 1 , detVarCopy));
                    res[j] =  detVarCopy / detVar;
                    System.out.println(String.format("x[%d] = %f / %f = %f", j + 1, detVarCopy,detVar, res[j]));
                    System.out.println();
                }
                result += "Solusi :\n";
                System.out.println("Solusi :");
                for (int j = 0; j < var.jEff; j++){
                    result += String.format("x[%d] = %f\n", j + 1 , res[j]);
                    System.out.println(String.format("x[%d] = %f", j + 1 , res[j]));
                }
            }
            else {
                result += "Determinan = 0\nSolusi tidak ada\n";
                System.out.println("Determinan = 0\nSolusi tidak ada");
            }

        }
        else {
            result += "Solusi banyak\nTidak bisa diselesaikan menggunakan metode matriks balikan\n";
            System.out.println("Solusi banyak");
            System.out.println("Tidak bisa diselesaikan menggunakan kaidah crammer");
        }

        return result;
    }

    static String metode_gauss_jordan(){
        SPL_Matriks M = new SPL_Matriks();
        System.out.println("Masukkan SPL dalam bentuk matriks augmented");
        M.isiMatriks();
        String result = "Sistem Persamaan Linear\n";
        result += M.getMatriksString() + "\n";
        M.spl_gauss_jordan();
        result += "Metode Gauss-Jordan\n\n" + "Matriks akhir :\n" + M.getMatriksString() + "\n";
        result += M.result;
        return result;
    }

    static String metode_gauss(){
        SPL_Matriks M = new SPL_Matriks();
        System.out.println("Masukkan SPL dalam bentuk matriks augmented");
        M.isiMatriks();
        String result = "Sistem Persamaan Linear\n";
        result += M.getMatriksString() + "\n";
        M.spl_gauss();
        result += "Metode Gauss\n\n" + "Matriks akhir :\n" + M.getMatriksString() + "\n";
        result += M.result;
        return result;
    }
}
