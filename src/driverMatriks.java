public class driverMatriks {
    public static void main(String[] args) {
        Matriks m1 = new Matriks();
        m1.isiMatriks();
        Regresi_Matriks.Regression(m1);
        

        //Matriks a = new Matriks();
        //a.isiMatriks();
        //Determinan_Matriks.DetwithRowReduction(a);
        //SPL_Matriks M = new SPL_Matriks();
        //M.isiMatriks();
        //M.spl_gauss();
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
}
