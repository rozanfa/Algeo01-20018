import java.io.File;
import java.util.Locale;
import java.util.Scanner;

public class driver_Regresi {
	public static String regression() {
		Scanner scanner = new Scanner(System.in);
		Matriks mat = new Matriks();
		isiRegresi(mat);
		String result = "Persamaan yang dimasukkan dalam bentuk matriks :\n";
        Extended.printMatrix(mat);
        result += Extended.getMatriksString(mat) + "\n";
	    double est[]= new double[mat.jEff-1];
	    result +="nilai peubah x : ";
	    System.out.println("nilai peubah x : ");
	    for (int i = 0; i < mat.jEff-1; i++){
	    	est[i] = scanner.nextDouble();
	    	if (i!=mat.jEff-1) {
		    	result +=String.valueOf(est[i])+",";
	    	}
	    	else {
		    	result +=String.valueOf(est[i])+".\n";
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
	static void isiRegresi_dariKeyboard(Matriks mat){
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < mat.iEff; i++){
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
