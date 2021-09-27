public class driverSPLMatirks {
    static void metode_invers(){
        Matriks main = new Matriks();
        System.out.println("Masukkan SPL dalam bentuk matriks augmented");
        main.isiMatriks();
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

            Matriks inversA = Balikan_Matriks.gauss_jordan(a);


            System.out.println("");
            System.out.println("Invers Matriks A :");
            inversA.cetakMatriks();

            Matriks res = new Matriks();
            res = Matriks.multiplyMatrix(inversA, b);
            System.out.println("");
            System.out.println("Matriks hasil perkalian invers A dengan x :");
            res.cetakMatriks();
            System.out.println("");
            System.out.println("Solusi :");
            for (int i = 0; i < res.iEff; i++){
                System.out.println(String.format("x[%d] = %.2f", i+1, res.Mat[i][0]));
            }
        }
        else {
            System.out.println("Solusi banyak");
            System.out.println("Tidak bisa diselesaikan menggunakan metode matriks balikan");
        }
    }


    static void metode_kaidah_crammer(){
        Matriks main = new Matriks();
        System.out.println("Masukkan SPL dalam bentuk matriks augmented");
        main.isiMatriks();
        Float res[] = new Float[10];
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
            Float detVar = Determinan_Matriks.kofaktor(var.copy());
            System.out.println(String.format("Determinan matriks A : %f" , detVar));
            System.out.println("");

            for (int j = 0; j < var.jEff; j++){
                Matriks varCopy = var.copy();
                for (int ii = 0; ii <= var.jEff; ii++){
                    varCopy.Mat[ii][j] = cons.Mat[ii][0];
                }
                //System.out.println("mat var");
                //var.cetakMatriks();
                //System.out.println("");
                Float detVarCopy = Determinan_Matriks.kofaktor(varCopy);
                System.out.println(String.format("Determinan matriks A[%d] : %f" ,j + 1 , detVarCopy));
                res[j] =  detVarCopy / detVar;
                System.out.println(String.format("x[%d] = %f / %f = %f", j + 1, detVarCopy,detVar, res[j]));
                System.out.println();
            }
            System.out.println("Solusi :");
            for (int j = 0; j < var.jEff; j++){
                System.out.println(String.format("x[%d] = %f", j + 1 , res[j]));
            }

        }
        else {
            System.out.println("Solusi banyak");
            System.out.println("Tidak bisa diselesaikan menggunakan kaidah crammer");
        }
    }

    static void metode_gauss_jordan(){
        SPL_Matriks M = new SPL_Matriks();
        M.isiMatriks();
        M.spl_gauss_jordan();
    }

    static void metode_gauss(){
        SPL_Matriks M = new SPL_Matriks();
        M.isiMatriks();
        M.spl_gauss();
    }
}
