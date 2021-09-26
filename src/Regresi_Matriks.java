public class Regresi_Matriks extends Matriks {
	   static void Regression (Matriks mat) {
		   Matriks mat1 = new Matriks();
		   mat1.iEff=mat.jEff;
		   mat1.jEff=mat.jEff+1;
		   mat1.iInitial=mat.jEff;
		   for (int i=0;i<mat1.iEff;i++) {
			   for (int j=0;j<mat1.jEff;j++) {
				   if (i==0 && j==0) {
					   mat1.Mat[i][j]=(float) mat1.iEff;
				   }
				   else {
					   mat1.Mat[i][j]=(float) 0;
				   }
			   }
		   }
		   int r=0;
		   while (r<mat.jEff) {
			   if (r<mat.jEff-1)
				   for (int k=0;k<mat.iEff;k++) {
					   mat1.Mat[0][r+1]+=mat.Mat[k][r];
					   mat1.Mat[r+1][0]+=mat.Mat[k][r];
			   }
			   else {
				   for (int k=0;k<mat.iEff;k++) {
					   mat1.Mat[0][r+1]+=mat.Mat[k][r];
				   }
			   }
			   r++;
		   }
		   for (int i=1;i<mat1.iEff;i++) {
			   for (int j=1;j<mat1.jEff;j++) {
				   for (int k=0;k<mat.iEff;k++) {
					   mat1.Mat[i][j]+=mat.Mat[k][i-1]*mat.Mat[k][j-1];
					   }
			   } 
		   }
		   mat1.cetakMatriks();
		   Extended.spl_gauss(mat1);
	   }
	       
	       
}


