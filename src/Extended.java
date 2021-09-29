import java.util.Scanner;

public class Extended extends Matriks{

    // gw buat ini karena.. Lemon-san memutuskan hanya butuh 1 matrix, 
    // barangkali di fungsi-fungsi berikutnya butuh dua matrix bisa make ini
    // Nox out.



    /**
     * Fills in a specified matrix. <br></br>
     * User inputs the row and columns first, then the matrix's elements
     */
    static void readMatrix(Matriks mat){
        Scanner scanner = new Scanner(System.in);
        int baris, kolom;
        System.out.print("Masukkan baris matriks: ");
        baris = scanner.nextInt();
        System.out.print("Masukkan kolom matriks: ");
        kolom = scanner.nextInt();
        
        int i,j;
        mat.iEff = baris;
        mat.jEff = kolom;
        mat.iInitial = baris;
        for (i = 0; i < baris; i++){
            for (j = 0; j < kolom; j++){
                mat.matrix[i][j] = scanner.nextDouble();
            }
            deleteRowIfRowAllZero(mat, i);
        }
    }

    /**
     * Takes in a matrix and prints it to the screen.
     */
    static void printMatrix(Matriks mat){
        int i,j;
        for (i = 0; i < mat.iEff; i++){
            for (j = 0; j < mat.jEff; j++){
                System.out.print(String.format("%2.2f ", mat.matrix[i][j]));
            }
            System.out.println();
        }
        for (i = 0; i < mat.zeroRows; i++){
            for (j = 0; j < mat.jEff; j++){
                System.out.print(String.format("%2.2f ", 0.00));
            }
            System.out.println();
        }
        
    }

    /**
     * Substracts a row from a specificed matrix. <br> </br>
     * <b>row(r) = row(r) - (k * row(s))</b>
     */
    static void substractRow(Matriks mat, int r, int s, Double k){
        // Baris r dikurangi k kali baris s
        for (int j = 0; j < mat.jEff; j++){
            mat.matrix[r][j] -= k * mat.matrix[s][j];
        }
        deleteRowIfRowAllZero(mat, r);
    }    

    /**
     * Checks if the elmt in (r, s) is 0, if it is: <br></br>
     * Swaps the row with the bottom most row by shuffling every row downwards by one, the bottom most row loops back to the top. <br> </br>
     * <b>Ex: {RowA, RowB, RowC} becomes {RowC, RowA, RowB}. </b>
     */
    static void swapIfFirstRowIsZero(Matriks mat, int r, int s){ 
        if (mat.matrix[r][s] == 0){
            for (int i = r; i < mat.iEff; i++) {
                if (mat.matrix[i][s] != 0){
                    for (int j = 0; j < mat.jEff; j++){
                        Double temp = mat.matrix[r][j];
                        mat.matrix[r][j] = mat.matrix[i][j];
                        mat.matrix[i][j] = temp;
                    }
                }
            }
        }
    }

    /**
     * makes the left most non-zero Double in the row a 0 <br></br>
     * <b> Ex: {0, 3, 6} becomes {0, 1, 2} </b>
     */
    static void makeLeftOne(Matriks mat, int r, int c){
        Double k = findFirstNonZeroInRow(mat, r, c);
        if (k != 0){
            for (int j = c; j < mat.jEff; j++){
                mat.matrix[r][j] /= k;
            }
        }
    }
   
    /**
     * Finds the first element in row  starting from ELMT(r,c) that is not Zero (ELMT != 0) <br></br>
     * @return the first non-zero element
     */
    static Double findFirstNonZeroInRow(Matriks mat, int r, int c){
        Double k = (Double) 0.0;
        if (mat.matrix[r][c] != 0){
            k = mat.matrix[r][c];
        }
        else {
            for (int j = 0; j < mat.jEff; j++){
                
                if (mat.matrix[r][j] != 0){
                    k = mat.matrix[r][j];
                    break;
                }
            }
        }
        return k;
    }

    /**
     * Deletes a specified row (ROW(r)) if the whole row contains the element 0 <br></br>
     * Increments the zeroRows count
     */
    static void deleteRowIfRowAllZero(Matriks mat, int r){
        // Menghapus baris jika semua elemen di dalam baris bernilai nol.
        // Namun, tetap akan dicetak ke dalam layar karena terdapat
        // variabel yang menyimpan jumlah baris yang dihapus
        boolean allZero = true;
        for (int j=0; j< mat.jEff; j++){
            if (mat.matrix[r][j] != 0) 
                allZero = false;
        }
        if (allZero) {
            for (int i=r+1; i<mat.iEff; i++){
                for (int j=0; j<mat.jEff; j++){
                    mat.matrix[i-1][j] = mat.matrix[i][j];
                }
            }
            mat.iEff -= 1;
            mat.zeroRows += 1;
            mat.justDeletedAllZeroRow = true;
        }
    }

    /**
     * Performs OBE to a specified matrix until it becomes a gaussian matrice
     * @param mat --> The specified matrix
     */
    static void obe_gauss(Matriks mat){
        for (int i = 0; i < mat.iEff; i++){
            swapIfFirstRowIsZero(mat, i,i);
            makeLeftOne(mat, i, i);
            for (int ii = i + 1; ii < mat.iEff; ii++){
                substractRow(mat, ii, i, mat.matrix[ii][i]);
            }
        }
    }


    /**
     * Performs OBE to a specified matrix until it becomes a Gaussian-Jordan matrice
     * @param mat
     */
    static void obe_gauss_jordan(Matriks mat){
        obe_gauss(mat);
        for (int i = 1; i < mat.iEff; i++){
            for (int ii = i - 1; ii >= 0; ii--){
                substractRow(mat, ii, i, findFirstNonZeroInRow(mat, ii, i));
                while (mat.justDeletedAllZeroRow) {
                    substractRow(mat, ii, i, findFirstNonZeroInRow(mat, ii, i));
                    mat.justDeletedAllZeroRow = false;
                }
            }
            makeLeftOne(mat, i, i);
        }
    }
    
    /**
     * Transposes a given matrix and returns it
     */
    static Matriks getTranspose(Matriks mat){
        Matriks temp = new Matriks();
        temp.iEff = mat.jEff; temp.jEff = mat.iEff;

        for (int i = 0; i < mat.iEff; i++){
            for (int j = 0; j < mat.jEff; j++){
                temp.matrix[j][i] = mat.matrix[i][j];
            }
        }
        return temp;
    }

    /**
     * Produces a cofactor matrice of the given matrix and returns it
     */
    static Matriks getCofactorMatrix(Matriks mat){
        Matriks temp = new Matriks();
        temp.iEff = mat.iEff; temp.jEff = mat.jEff;

        Matriks subMatrix = new Matriks();
        subMatrix.iEff = mat.iEff - 1; subMatrix.jEff = mat.jEff - 1;
        for (int i = 0; i < mat.iEff; i++){
            for (int j = 0; j < mat.jEff; j++){
                int iWriteHead, jWriteHead; 
                iWriteHead = 0;
                for (int iReadHead = 0; iReadHead < mat.iEff; iReadHead++){
                    jWriteHead = 0;
                    if (iReadHead != i){
                        for (int jReadHead = 0; jReadHead < mat.jEff; jReadHead++){
                            if (jReadHead != j){
                                // System.out.println(iReadHead + " " + jReadHead + " " + iWriteHead + " " + jWriteHead);
                                subMatrix.matrix[iWriteHead][jWriteHead] = mat.matrix[iReadHead][jReadHead];
                                jWriteHead = jWriteHead + 1;
                            }
                        }
                        iWriteHead = iWriteHead + 1;
                    }
                }
                // printMatrix(subMatrix);
                if ((i + j) % 2 == 0) temp.matrix[i][j] = Determinan_Matriks.kofaktor(subMatrix);
                else temp.matrix[i][j] = -1 * Determinan_Matriks.kofaktor(subMatrix);
            }
        }

        return temp;
    }


    /**
     * produces the adjoint matrice of the given matrix
     */
    static Matriks getAdjoint(Matriks mat){
        Matriks temp = new Matriks();
        temp = getTranspose(getCofactorMatrix(mat));
        return temp;
    }
    static boolean isSPLHaveSolution(Matriks mat){
        boolean yes = false;
        if (mat.Mat[mat.iEff-1][mat.jEff-1] != 0) {
            for (int j = 0; j < mat.jEff - 1; j++){
                if (mat.Mat[mat.iEff-1][j] != 0) {
                    yes = true;
                }
            }
        }
        else {
            yes = true;
        } 
        return yes;
    }

    static boolean isSPLHaveManySolution(Matriks mat){
        return mat.iEff < mat.jEff - 1;
    }

    static void printUniqueSolution_gauss_jordan(Matriks mat){
        for (int i = 0; i < mat.iEff; i++){
            if (i != mat.iEff - 1){
                System.out.print(String.format("x[%d] = %.2f, ", i+1, mat.Mat[i][mat.jEff-1]));
            }
            else{
                System.out.println(String.format("x[%d] = %.2f", i+1, mat.Mat[i][mat.jEff-1]));
            }
        }
    }
    


    static void printUniqueSolution_gauss(Matriks mat){
        Double res[] = new Double[10];
        int p = mat.iEff - 1;
        for (int i=mat.iEff-1; i>=0; i--){
            res[i] = mat.Mat[i][mat.jEff-1]; 
            for (int j=mat.jEff-2; j>p; j--){
                res[p] -= res[j] * mat.Mat[i][j];
            }
            p--;
        }
        for (int i=0; i < mat.iEff; i++){
            if (i != mat.iEff - 1){
                System.out.print(String.format("x[%d] = %.2f, ", i+1, res[i]));
            }
            else{
                System.out.println(String.format("x[%d] = %.2f", i+1, res[i]));
            }
        }
    }


    static void spl_gauss(Matriks mat){
        mat.obe_gauss();
        System.out.println("");
        System.out.println("Hasil Matriks :");
        mat.cetakMatriks();
        System.out.println("");
        if (isSPLHaveSolution(mat)){
            if (isSPLHaveManySolution(mat)){
                System.out.println("Solusi banyak");
            }
            else {
                printUniqueSolution_gauss(mat);
            }
        }
        else {
            System.out.println("Solusi tidak ada");
        }
    }
}
