import java.io.File;
import java.util.Locale;
import java.util.Scanner;

public class driver_Regresi extends Matriks{
	public static String regression() {
		Scanner scanner = new Scanner(System.in);
		Matriks mat = new Matriks();
		isiRegresi(mat);
		System.out.println("Persamaan yang dimasukkan dalam bentuk matriks :");
		String result = "Persamaan yang dimasukkan dalam bentuk matriks :\n";
        Extended.printMatrix(mat);
        result += Extended.getMatriksString(mat) + "\n";
	    double est[]= new double[mat.jEff-1];
	    // array untuk menyimpan nilai-nilai variabel x
	    result +="nilai peubah x : ";
	    System.out.println("nilai peubah x : ");
	    for (int i = 0; i < mat.jEff-1; i++){
	    	est[i] = scanner.nextDouble();
	    	if (i!=mat.jEff-2) {
		    	result +=String.valueOf(est[i])+",";
	    	}
	    	else {
		    	result +=String.valueOf(est[i])+"\n";
	    	}
	    }
	    Matriks mat1;
	    mat1=Regresi_Matriks.Regression(mat, est);
	    result +="Persamaan Regresi linearnya adalah :\n";
	    result +="y = ";
	    for (int l=0;l<mat1.iEff;l++) {
			   if (l==0) {
				   result+=String.valueOf(mat1.Mat[l][mat1.jEff-1]);
			   }
		       else {
		    	   if (mat1.Mat[l][mat1.jEff-1]>=0) {
		    		   result+=" + "+ String.valueOf(mat1.Mat[l][mat1.jEff-1])+"x"+"["+String.valueOf(l)+"]";
		    	   }
		    	   else {
		    		   result+=" - "+String.valueOf(-mat1.Mat[l][mat1.jEff-1])+"x"+"["+String.valueOf(l)+"]";
		    	   } 
			   }
		    }
	    result+="\n";
	    result+="Hasil dari taksiran : ";
	    int k=0;
	    double sum=0;
	    for (int i=0;i<mat1.iEff;i++) {
		    if (i==0) {
			    sum+=mat1.Mat[i][mat1.jEff-1];
		    }
	        else {
	    	    sum+=mat1.Mat[i][mat1.jEff-1]*est[k];
	    	    k+=1;
		    }
	    }
	    result+=String.valueOf(sum)+"\n";
	    return result;
	}

   static void isiRegresi(Matriks mat){
	    Scanner scanner = new Scanner(System.in);
        System.out.println("\nPilih jenis input");
        System.out.println("1. Input dari keyboard");
        System.out.println("2. Input dari file");
        System.out.print("Pilihan : ");
        int option = scanner.nextInt();
        System.out.println("");
        switch (option) {
            case 1: {
            	int n, m;
                System.out.print("Masukkan banyaknya variabel: ");
                n = scanner.nextInt();
                System.out.print("Masukkan banyaknya sampel: ");
                m = scanner.nextInt();
                mat.iEff = m;
                mat.jEff = n+1;
                mat.iInitial = m;
                mat.isiMatriks_dariKeyboard();
                break;
            }
            case 2: {
            	int n, m;
                System.out.print("Masukkan baris matriks: ");
                m = scanner.nextInt();
                System.out.print("Masukkan kolom matriks: ");
                n = scanner.nextInt();
                mat.iEff = m;
                mat.jEff = n;
                mat.iInitial = m;
                mat.isiMatriks_dariFile();
                break;
            }
            default : {
                System.out.println("Input tidak valid");
            }
        }
    }
   
}
