public class Balikan_Matriks extends Matriks {

    /**
     * Mencari balikan suatu matrix menggunakan metode Gauss-Jordan <br></br>
     * Prerequisite: Must be square (COLS == ROWS), Must not have 0 as determinant
     * @param mat
     * @return inversed
     */
    static Matriks gauss_jordan(Matriks mat){
        Matriks inversed = new Matriks();

        /*  Akan menggunakan prinsip operasi yang sama jika dilakukan kepada matriks determinan maka 
            akan menghasilkan inverse matriks tersebut. */
        
        // Buat matriks identitas berukuran sama dengan matriks input
        inversed.iEff = mat.iEff;
        inversed.jEff = mat.jEff;
        inversed.iInitial = mat.iInitial; // jujur masih gatau iIinitial buat apaan mon apaan sih ini
        for (int i = 0; i < mat.iEff; i++){
            for (int j = 0; j < mat.jEff; j++){
                if (i == j) inversed.matrix[i][j] = (float)1.0;
                else inversed.matrix[i][j] = (float)0.0;
            }
        }

        // Lakukan operasi OBE pada matrix input, lakukan operasi yg sama kepada matrix identitas
        obe_gauss_jordan(mat, inversed);
        // Selesai, return matriks yg telah diinverse
        return inversed;                                
    }

    static Matriks adjoin(Matriks mat){
        Matriks temp = new Matriks();
        temp = Extended.getAdjoint(mat);
        float det = Determinan_Matriks.kofaktor(mat);
        
        for (int i = 0; i < temp.iEff; i++){
            for(int j = 0; j < temp.jEff; j++){
                temp.matrix[i][j] /= det;
            }
        }
        return temp;
    }


    

    // *************************************************************
    // 
    //
    //      BELOW IS CUSTOM OPERATIONS FOR INVERSING A MATRICE
    //
    //
    // *************************************************************

    /**
     * Substracts a row from a specificed matrix. <br> </br>
     * <b>row(r) = row(r) - (k * row(s))</b><br></br>
     * Custom for Balikan_Matriks: checks for mat1, performs the same operation for mat2
     */
    static void substractRow(Matriks mat1, Matriks mat2, int r, int s, Float k){
        // Baris r dikurangi k kali baris s
        for (int j = 0; j < mat1.jEff; j++){
            mat1.matrix[r][j] -= k * mat1.matrix[s][j];
            mat2.matrix[r][j] -= k * mat2.matrix[s][j];
        }

        // System.out.println("\nsubstractRow Called");
        // Extended.printMatrix(mat2);
        // Bagian deleteAllZeroRow diapus karena klo ada row yg 0 semua -> gada det -> gada balikan
    }    


    /**
     * Checks if the elmt in (r, s) is 0, if it is: <br></br>
     * Swaps the row with the bottom most row by shuffling every row downwards by one, the bottom most row loops back to the top. <br> </br>
     * <b>Ex: {RowA, RowB, RowC} becomes {RowC, RowA, RowB}. </b> <br></br>
     * Custom for Balikan_Matriks: checks for mat1, performs the same operation for mat2
     */
    static void swapIfFirstRowIsZero(Matriks mat1, Matriks mat2, int r, int s){ 
        if (mat1.matrix[r][s] == 0){
            for (int i = r; i < mat1.iEff; i++) {
                if (mat1.matrix[i][s] != 0){
                    for (int j = 0; j < mat1.jEff; j++){
                        Float temp = mat1.matrix[r][j];
                        mat1.matrix[r][j] = mat1.matrix[i][j];
                        mat1.matrix[i][j] = temp;

                        Float tempMat2 = mat2.matrix[r][j];
                        mat2.matrix[r][j] = mat2.matrix[i][j];
                        mat2.matrix[i][j] = tempMat2;
                    }
                }
            }
            // System.out.println("\nswapIfFirstRowIsZero Called");
            // Extended.printMatrix(mat2);
        }
    }

    /**
     * makes the left most non-zero float in the row a 0 <br></br>
     * <b> Ex: {0, 3, 6} becomes {0, 1, 2} </b> <br></br>
     * Custom for Balikan_Matriks: checks for mat1, performs the same op for mat2
     */
    static void makeLeftOne(Matriks mat1, Matriks mat2, int r, int c){
        float k = Extended.findFirstNonZeroInRow(mat1, r, c);
        if (k != 0){
            for (int j = 0; j < mat1.jEff; j++){
                mat1.matrix[r][j] /= k;
                mat2.matrix[r][j] = mat2.matrix[r][j] / k;
            }
            // System.out.println("\nmakeLeftOne Called");
            // Extended.printMatrix(mat2);
        }
    }


    /**
     * Performs OBE towards mat1, and does the same operation towards mat2 <br></br>
     * Prerequisite: size of mat1 == size of mat2 <br></br>
     * Custom for Balikan_Matriks
     * 
     */
    static void obe_gauss(Matriks mat1, Matriks mat2){
        for (int i = 0; i < mat1.iEff; i++){
            swapIfFirstRowIsZero(mat1, mat2, i,i); 
            makeLeftOne(mat1, mat2, i, i);
            for (int ii = i + 1; ii < mat1.iEff; ii++){
                substractRow(mat1, mat2, ii, i, mat1.matrix[ii][i]);
            }
        }
    }

    /**
     * Performs OBE towards mat1, and does the same operation towards mat2 <br></br>
     * Prerequisite: size of mat1 == size of mat2 <br></br>
     * Custom for Balikan_Matriks
     */
    static void obe_gauss_jordan(Matriks mat1, Matriks mat2){
        obe_gauss(mat1, mat2);
        for (int i = 1; i < mat1.iEff; i++){
            for (int ii = i - 1; ii >= 0; ii--){
                substractRow(mat1, mat2, ii, i, Extended.findFirstNonZeroInRow(mat1, ii, i));
                while (mat1.justDeletedAllZeroRow) {
                    substractRow(mat1, mat2, ii, i, Extended.findFirstNonZeroInRow(mat1, ii, i));
                    mat1.justDeletedAllZeroRow = false;
                }
            }
            makeLeftOne(mat1, mat2, i, i);
        }
    }

}
