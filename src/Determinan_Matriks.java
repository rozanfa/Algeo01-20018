public class Determinan_Matriks extends Matriks {
    
    /**
     * Returns a float consisting the determinant of a given matrice using the cofactor method
     */
    static Float kofaktor (Matriks mat){
        if (mat.iEff == 1) return mat.matrix[0][0];
        else if (mat.iEff == 2) return ((mat.matrix[0][0] * mat.matrix[1][1]) - (mat.matrix[0][1] * mat.matrix[1][0]));
        else {
            Float tempDet = 0.0f;
            int colTraversal, i, j, colWriteHead;

            Matriks subMatrix = new Matriks();
            subMatrix.iEff = mat.iEff -1; subMatrix.jEff = mat.jEff -1; subMatrix.iInitial = mat.iInitial -1;

            for (colTraversal = 0; colTraversal < mat.iEff; colTraversal++){
                colWriteHead = 0;
                for(j = 0; j < mat.jEff; j++){
                    if(colTraversal != j){
                        for (i = 1; i < mat.iEff; i++){
                            subMatrix.matrix[i-1][colWriteHead] = mat.matrix[i][j];
                        }
                        colWriteHead++;
                    }
                } 
                if (colTraversal % 2 == 0) tempDet += mat.matrix[0][colTraversal] * kofaktor(subMatrix);
                else tempDet += -1 * mat.matrix[0][colTraversal] * kofaktor(subMatrix);
            }
            return tempDet;
        }
    }
}
