import java.io.File;
import java.util.Locale;
import java.util.Scanner;

public class SPL_Matriks extends Matriks{

    @Override
    void isiMatriks_dariKeyboard(){

        Scanner scanner = new Scanner(System.in);
        System.out.println(String.format("Masukkan matriks %d x %d :", this.iEff, this.jEff));
        for (int i = 0; i < this.iEff; i++){
            for (int j = 0; j < this.jEff; j++){
                this.Mat[i][j] = scanner.nextDouble();
            }
            deleteRowIfRowAllZero(i);
            if (this.justDeletedAllZeroRow) {
                i--;
                this.justDeletedAllZeroRow = false;
            }
        }
    }

    @Override
    void isiMatriks_dariFile(){
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
                    for (int i = 0; i < this.iEff; i++){
                        for (int j = 0; j < this.jEff; j++){
                            this.Mat[i][j] = fileScanner.nextDouble();
                        }
                        deleteRowIfRowAllZero(i);
                        if (this.justDeletedAllZeroRow) {
                            i--;
                            this.justDeletedAllZeroRow = false;
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


    boolean isSPLHaveSolution(){
        boolean yes = false;
        if (this.Mat[this.iEff-1][this.jEff-1] != 0) {
            for (int j = 0; j < this.jEff - 1; j++){
                if (this.Mat[this.iEff-1][j] != 0) {
                    yes = true;
                }
            }
        }
        else {
            yes = true;
        } 
        return yes;
    }

    boolean isSPLHaveManySolution(){
        return this.iEff < this.jEff - 1;
    }

    void printUniqueSolution_gauss_jordan(){
        result += "Solusi unik\n";
        System.out.println("Solusi unik");
        for (int i = 0; i < this.iEff; i++){
            result += String.format("x[%d] = %f\n", i+1, this.Mat[i][this.jEff-1]);
            System.out.println(String.format("x[%d] = %f", i+1, this.Mat[i][this.jEff-1]));
        }
    }



    
    void printManySolution(){
        result += "Solusi banyak\n";
        System.out.println("Solusi banyak");
        char varName[] = {'o','p','q', 'r','s','t','u','v','w','x','y','z','a','b','c','d','e','f','g','h','i','j','k','l','m','n'};
        for (int i=0; i<this.iEff; i++){
            //int varNameCounter = 0;
            //for (int k=0; k<i ;k++) varName ++;
            int f = findFirstNonZeroIndexInRow(i, 0);
            if (this.Mat[i][f] != 1) {
                makeLeftOne(i, f);
            }
            String res = String.format("x[%d] = %f ", f+1, this.Mat[i][this.jEff-1]) ;
            for (int jj = f + 1; jj<this.jEff-1; jj++){ 
                if (this.Mat[i][jj] != 0){
                    Double kof = -1 * this.Mat[i][jj];
                    if (kof < 0){
                        res += String.format("- %f%c ", -1*kof, varName[jj]);
                    }
                    else {
                        res += String.format("+ %f%c ", kof, varName[jj]);
                    }
                }
                //varName ++;
            }
            result += res + "\n";
            System.out.println(res);
        }
       // char varName = 'p';
        int c = this.iEff;
        for (int j = this.iEff; j < this.jEff - 1; j++){
            //for (int k=0; k<this.iEff-1 ;k++) varName ++;
            result += String.format("x[%d] = %c\n", j+1, varName[c]);
            System.out.println(String.format("x[%d] = %c", j+1, varName[c]));
            c++;
            //varName ++;
        }
        /*
        char var[] = {'r','s','t','u','v','w','x','y','z','a'};
        int maxVar = this.jEff - this.iEff + 1;
        Double res[] = new Double[10];
        boolean isResConstant[] = new boolean[10];
        String stringRes[] = new String[10];
        int p = this.jEff - 2;
        int j = 0;
        
        for (int i=0; i<this.iEff; i++){
            res[i] = (Double) 0;
            while (this.Mat[i][j] != 1 && j < jEff - 2){
                j++;
                maxVar++;
            }
        }
        int countVar = maxVar;
        for (int i=this.iEff-1; i>=0; i--){
            if (this.Mat[i][p] == 1){
                res[p] = this.Mat[i][this.jEff-1];
                if (maxVar == 0){
                    isResConstant[p] = true;
                }
                else {
                    isResConstant[p] = true;
                    for (j = this.jEff - 2; j > p; j--){
                        stringRes[j] = "";
                        if (isResConstant[j]) {
                            res[p] -= res[j];
                        }
                        else {
                            isResConstant[p] = false;
                            System.out.println(countVar);
                            stringRes[j] += String.format("%.2f", this.Mat[i][j] * - 1) + var[countVar];
                            countVar--;
                        }
                        stringRes[j] = res[p].toString() + "+(" + stringRes[j] + ")";
                    }
                }
                p--;
                
            }
            else {
                while (p > 0 && this.Mat[i][p] != 1){
                    isResConstant[p] = false;
                    stringRes[p] = "" + var[countVar];
                    countVar--;
                    p--;
                }
            }
        }
        for (j=0; j < this.jEff - 1; j++){
            if (j != this.jEff - 2){
                if (isResConstant[j]) {
                    System.out.print(String.format("x[%d] = %.2f, ", j+1, res[j]));
                }
                else {
                    System.out.print(String.format("x[%d] = " + stringRes[j] + " , ", j + 1));
                }
            }
            else{
                if (isResConstant[j]) {
                    System.out.print(String.format("x[%d] = %.2f", j+1, res[j]));
                }
                else {
                    System.out.print(String.format("x[%d] = " + stringRes[j], j + 1));
                }
            }
        }*/
    }
    


    void printUniqueSolution_gauss(){
        result += "Solusi unik\n";
        System.out.println("Solusi unik");
        Double res[] = new Double[15];
        int p = this.iEff - 1;
        for (int i=this.iEff-1; i>=0; i--){
            res[i] = this.Mat[i][this.jEff-1]; 
            for (int j=this.jEff-2; j>p; j--){
                res[p] -= res[j] * this.Mat[i][j];
            }
            p--;
        }
        for (int i=0; i < this.iEff; i++){
            result += String.format("x[%d] = %f\n", i+1, res[i]);
            System.out.println(String.format("x[%d] = %f", i+1, res[i]));
        }
    }


    void spl_gauss(){
        obe_gauss();
        System.out.println("");
        System.out.println("Hasil Matriks :");
        cetakMatriks();
        System.out.println("");
        if (isSPLHaveSolution()){
            if (isSPLHaveManySolution()){
                //System.out.println("Solusi banyak");
                printManySolution();
            }
            else {
                printUniqueSolution_gauss();
            }
        }
        else {
            result += "Solusi tidak ada\n";
            System.out.println("Solusi tidak ada");
        }
    }


    void spl_gauss_jordan(){
        obe_gauss_jordan();
        System.out.println("");
        System.out.println("Hasil Matriks :");
        cetakMatriks();
        System.out.println("");
        if (isSPLHaveSolution()){
            if (isSPLHaveManySolution()){
                //System.out.println("Solusi banyak");
                printManySolution();
            }
            else {
                printUniqueSolution_gauss_jordan();
            }
        }
        else {
            result += "Solusi tidak ada\n";
            System.out.println("Solusi tidak ada");
        }
    }


}
