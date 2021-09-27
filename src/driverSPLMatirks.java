public class driverSPLMatirks {
    static void spl_metode_invers(){
        Matriks main = new Matriks();
        System.out.println("Masukkan SPL dalam bentuk matriks augmented");
        main.isiMatriks();
        if (main.iEff == (main.jEff - 1)){
            Matriks x = new Matriks();
            x.iEff = main.iEff;
            x.jEff = 1;
            for (int i = 0; i < x.iEff; i++) {
                x.Mat[i][0] = main.Mat[i][main.jEff - 1];
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
            x.cetakMatriks();
            System.out.println("");
            System.out.println("Matriks A :");
            a.cetakMatriks();

            Matriks inversA = Balikan_Matriks.gauss_jordan(a);


            System.out.println("");
            System.out.println("Invers Matriks A :");
            inversA.cetakMatriks();

            Matriks res = new Matriks();
            res = Matriks.multiplyMatrix(inversA, x);
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
}
