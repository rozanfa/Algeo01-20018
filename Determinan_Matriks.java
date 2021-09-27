public class Determinan_Matriks extends Matriks {
    

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
    
    static int NumberofSwap(Matriks mat,int r, int s){ 
        int x=0;
    	if (mat.Mat[r][s] == 0){
            for (int i = r; i < mat.iEff; i++) {
                if (mat.Mat[i][s] != 0){
                	x+=1;
                }
            }

        }
    	return x;
    }
    
    static void substractRow(Matriks mat, int r, int s, Float k, Float h){
        for (int j = 0; j < mat.jEff; j++){
            mat.Mat[r][j] -= k/h *mat.Mat[s][j];
        }
        mat.deleteRowIfRowAllZero(r);
        System.out.println();
        System.out.println(String.format("%d %d %.1f", r, s, k));
        mat.cetakMatriks();
    }
    
    static void RowReduction (Matriks mat) {
    	int i,j,swap=0;
    	for (i = 0; i < mat.iEff; i++){
            mat.swapIfFirstRowIsZero(i,i);
            swap+=NumberofSwap(mat,i,i);
            for (j = i + 1; j < mat.iEff; j++){
                substractRow(mat, j, i, mat.Mat[j][i], mat.Mat[i][i]);
            }
        }
    	if  (swap%2!=0) {
    		mat.Mat[0][0]*=-1;
    	}
    }
    
    static void DetwithRowReduction (Matriks mat) {
    	int i,j;
    	double det;
    	RowReduction(mat);
    	if (mat.justDeletedAllZeroRow) {
    		det=0;
    	}
    	else {
    		det=1.0;
    		for (i=0; i <mat.iEff; i++){
        		for(j = 0; j <mat.jEff; j++){
        			if (i==j) {
                    	det*=mat.Mat[i][j];
                    }
                }
            }
    	}
    	System.out.println(det);
    }
    
    static void DetwithSarrus (Matriks mat){
    	if (mat.jEff == 3 && mat.iEff ==3) {
    		Matriks matbaru = new Matriks();
    		String[][] matbaru2 = new String[3][5];
    		matbaru.iEff=3;
    		matbaru.jEff=5;
    		matbaru.iInitial = 3;

    		int i,j;
    		for (i=0; i <matbaru.iEff; i++){
                for(j = 0; j <matbaru.iEff; j++){
                	if (j<2 && i<3) {
                		matbaru.Mat[i][j+3]=mat.Mat[i][j];
                	}
                	matbaru.Mat[i][j]=mat.Mat[i][j];
                }
    		}
    		for (i=0; i <3; i++){
                for(j = 0; j<5; j++){
                	matbaru2[i][j]="a"+String.valueOf(i+1)+String.valueOf(j+1);
                	
                }
    		}
    		
    		for (i = 0; i <3; i++){
                for (j = 0; j <5; j++){
                    System.out.print(matbaru2[i][j]+" ");
                }
                System.out.println();
    		}
    		System.out.println("");
    		System.out.println(matbaru2[0][0]+" * "+matbaru2[1][1]+" * "+matbaru2[2][2]+" + "
	                           +matbaru2[0][1]+" * "+matbaru2[1][2]+" * "+matbaru2[2][3]+" + "
			                   +matbaru2[0][2]+" * "+matbaru2[1][3]+" * "+matbaru2[2][4]+" - "
	                           +matbaru2[2][0]+" * "+matbaru2[1][1]+" * "+matbaru2[0][2]+" - "
			                   +matbaru2[2][1]+" * "+matbaru2[1][2]+" * "+matbaru2[0][3]+" - "
	                           +matbaru2[2][2]+" * "+matbaru2[1][3]+" * "+matbaru2[0][4]);
    		
    		System.out.println("");
    		
    		System.out.print(matbaru.Mat[0][0]+" * "+matbaru.Mat[1][1]+" * "+matbaru.Mat[2][2]+" + "
    		                 +matbaru.Mat[0][1]+" * "+matbaru.Mat[1][2]+" * "+matbaru.Mat[2][3]+" + "
    				         +matbaru.Mat[0][2]+" * "+matbaru.Mat[1][3]+" * "+matbaru.Mat[2][4]+" - "
    		                 +matbaru.Mat[2][0]+" * "+matbaru.Mat[1][1]+" * "+matbaru.Mat[0][2]+" - "
    				         +matbaru.Mat[2][1]+" * "+matbaru.Mat[1][2]+" * "+matbaru.Mat[0][3]+" - "
    		                 +matbaru.Mat[2][2]+" * "+matbaru.Mat[1][3]+" * "+matbaru.Mat[0][4]);
    		
    		System.out.println(" = "+ (matbaru.Mat[0][0]*matbaru.Mat[1][1]*matbaru.Mat[2][2]+
    				                  matbaru.Mat[0][1]*matbaru.Mat[1][2]*matbaru.Mat[2][3]+
    				                  matbaru.Mat[0][2]*matbaru.Mat[1][3]*matbaru.Mat[2][4]-
    				                  matbaru.Mat[2][0]*matbaru.Mat[1][1]*matbaru.Mat[0][2]-
    				                  matbaru.Mat[2][1]*matbaru.Mat[1][2]*matbaru.Mat[0][3]-
    				                  matbaru.Mat[2][2]*matbaru.Mat[1][3]*matbaru.Mat[0][4]));
    	}
    		else {
        	System.out.println("Tidak bisa mencari determinan menggunakan metode Sarrus selain di matriks 3 x 3");
        }
    }
}

