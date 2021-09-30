import java.io.File;
import java.util.Locale;
import java.util.Scanner;

public class Regresi_Matriks extends Matriks {
	public static Matriks Regression (Matriks mat, double[] est) {
		   Matriks mat1 = new Matriks();
		   mat1.iEff=mat.jEff;
		   mat1.jEff=mat.jEff+1;
		   mat1.iInitial=mat.jEff;
		   for (int i=0;i<mat1.iEff;i++) {
			   for (int j=0;j<mat1.jEff;j++) {
				   if (i==0 && j==0) {
					   mat1.Mat[i][j]=(double) mat.iEff;
				   }
				   else {
					   mat1.Mat[i][j]=0.0;
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
           System.out.println("Persamaan Normal Estimation Equation for Multiple Linear Regressionnya dalam bentuk matriks adalah :");
		   mat1.cetakMatriks();
		   mat1.obe_gauss_jordan();
		   System.out.println();
		   System.out.println("Persamaan Regresi linearnya adalah :");
		   System.out.print("y = ");
		   for (int l=0;l<mat1.iEff;l++) {
			   if (l==0) {
				   System.out.print(mat1.Mat[l][mat1.jEff-1]);
			   }
		       else {
		    	   if (mat1.Mat[l][mat1.jEff-1]>=0) {
		    		   System.out.print(" + "+ mat1.Mat[l][mat1.jEff-1]+"x"+"["+String.valueOf(l)+"]");
		    	   }
		    	   else {
		    		   System.out.print(" - "+-mat1.Mat[l][mat1.jEff-1]+"x"+"["+String.valueOf(l)+"]");
		    	   } 
			   }
		    }   
		   System.out.println();
		   System.out.println();
		   System.out.println("Hasil dari taksirannya : ");
		   System.out.print("y = ");
		   int k=0;
		   for (int i=0;i<mat1.iEff;i++) {
			   if (i==0) {
				   System.out.print(mat1.Mat[i][mat1.jEff-1]);
			   }
		       else {
		    	   if (mat1.Mat[i][mat1.jEff-1]>=0) {
		    		   System.out.print(" + "+ mat1.Mat[i][mat1.jEff-1]+"*"+est[k]);
		    	   }
		    	   else {
		    		   System.out.print(" - "+-mat1.Mat[i][mat1.jEff-1]+"*"+est[k]);
		    	   }
		    	   k+=1;
			   }
		   }
		   System.out.println();
		   double sum=0;
		   k=0;
		   for (int i=0;i<mat1.iEff;i++) {
			   if (i==0) {
				   sum+=mat1.Mat[i][mat1.jEff-1];
			   }
		       else {
		    	   sum+=mat1.Mat[i][mat1.jEff-1]*est[k];
		    	   k+=1;
			   }
		   }
		   System.out.println("y = "+sum);
		   
		   return mat1;

	  }
	   
}


