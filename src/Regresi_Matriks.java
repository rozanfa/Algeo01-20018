import java.io.File;
import java.util.Locale;
import java.util.Scanner;

public class Regresi_Matriks extends Matriks {
	   static void Regression () {
		   Scanner scanner = new Scanner(System.in);
		   Matriks mat = new Matriks();
		   isiRegresi(mat);
		   int x;
		   System.out.print("Masukkan banyaknya taksiran: ");
	       x = scanner.nextInt();
	       double est[]= new double[x];
	       System.out.print("Masukkan taksiran: ");
	       for (int i = 0; i < x; i++){
	                est[i] = scanner.nextDouble();
	       }
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
           System.out.println("Persamaan Normal Estimation Equation for Multiple Linear Regressionnya adalah :");
		   mat1.cetakMatriks();
		   
		   mat1.obe_gauss_jordan();
		   System.out.println("Persamaan Regresi linearnya adalah :");
		   System.out.print("y = ");
		   for (int l=0;l<mat1.iEff;l++) {
			   if (l==0) {
				   System.out.print(mat1.Mat[l][mat1.jEff-1]);
			   }
		       else {
		    	   if (mat1.Mat[l][mat1.jEff-1]>=0) {
		    		   System.out.print(" + "+ mat1.Mat[l][mat1.jEff-1]+"x"+String.valueOf(l));
		    	   }
		    	   else {
		    		   System.out.print(" - "+-mat1.Mat[l][mat1.jEff-1]+"x"+String.valueOf(l));
		    	   } 
			   }
		    }   
		   System.out.println();
		   for (int k=0;k<x;k++) {
			   
			   System.out.println("Hasil dari taksiran ke "+String.valueOf(k+1) +" : ");
			   System.out.print("y = ");
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
				   }
			   }
			   System.out.println();
			   int sum=0;
			   for (int i=0;i<mat1.iEff;i++) {
				   if (i==0) {
					   sum+=mat1.Mat[i][mat1.jEff-1];
				   }
			       else {
			    	   sum+=mat1.Mat[i][mat1.jEff-1]*est[k];
				   }
			   }
			   System.out.println();
			   System.out.println("y = "+sum);
		   }

	  }
	   static void isiRegresi_dariKeyboard(Matriks mat){
	        Scanner scanner = new Scanner(System.in);
	        for (int i = 0; i < mat.iInitial; i++){
	        	System.out.println("Masukkan sampel ke "+String.valueOf(i+1)+" :");
	            for (int j = 0; j < mat.jEff; j++){
	            	mat.Mat[i][j] = scanner.nextDouble();
	            }
	        }
	    }
	   static void isiRegresi(Matriks mat){
		    Scanner scanner = new Scanner(System.in);
		    int n, m;
	        System.out.print("Masukkan banyaknya peubah x: ");
	        n = scanner.nextInt();
	        System.out.print("Masukkan banyaknya sampel: ");
	        m = scanner.nextInt();
	        
	        mat.iEff = m;
	        mat.jEff = n+1;
	        mat.iInitial = m;

	        System.out.println("\nPilih jenis input");
	        System.out.println("1. Input dari keyboard");
	        System.out.println("2. Input dari file");
	        System.out.print("Pilihan : ");
	        int option = scanner.nextInt();
	        System.out.println("");
	        switch (option) {
	            case 1: {
	            	isiRegresi_dariKeyboard(mat);
	                break;
	            }
	            case 2: {
	                isiRegresi_dariFile(mat);
	                break;
	            }
	            default : {
	                System.out.println("Input tidak valid");
	            }
	        }
	    }
	   
	   static void isiRegresi_dariFile(Matriks mat){
	        Scanner scanner = new Scanner(System.in);
	        Scanner fileScanner = null;
	        boolean isFileAvailable = false;
	        
	        while (!isFileAvailable) {
	            try {
	                System.out.print("Masukkan path file: ");
	                String fileName = scanner.nextLine();
	                File file = new File(fileName);
	                fileScanner = new Scanner(file);
	                fileScanner.useLocale(Locale.US);
	                if (fileScanner.hasNext()){
	                    for (int i = 0; i < mat.iInitial; i++){
	                        for (int j = 0; j < mat.jEff; j++){
	                        	mat.Mat[i][j] = fileScanner.nextDouble();
	                        }
	                        fileScanner.nextLine();
	                    }
	                }
	                isFileAvailable = true;
	            } catch (Exception ex) {
	                System.out.println("Error : Tidak ditemukan file. " + ex.getMessage());
	                isFileAvailable = false;
	            }
	        }
	    }
}



