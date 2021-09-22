public class SPL_Matriks extends Matriks{
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
        for (int i = 0; i < this.iEff; i++){
            if (i != this.iEff - 1){
                System.out.print(String.format("x[%d] = %.2f, ", i+1, this.Mat[i][this.jEff-1]));
            }
            else{
                System.out.println(String.format("x[%d] = %.2f", i+1, this.Mat[i][this.jEff-1]));
            }
        }
    }



    
    void printManySolution(){
        char var[] = {'r','s','t','u','v','w','x','y','z','a'};
        int maxVar = this.jEff - this.iEff + 1;
        Float res[] = new Float[10];
        boolean isResConstant[] = new boolean[10];
        String stringRes[] = new String[10];
        int p = this.jEff - 2;
        int j = 0;
        
        for (int i=0; i<this.iEff; i++){
            res[i] = (float) 0;
            while (this.Mat[i][j] != 1 && j < jEff - 2){
                j++;
                maxVar++;
            }
        }
        int countVar = maxVar;
        for (int i=this.iEff; i>=0; i--){
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
        }
    }
    


    void printUniqueSolution_gauss(){
        Float res[] = new Float[10];
        int p = this.iEff - 1;
        for (int i=this.iEff-1; i>=0; i--){
            res[i] = this.Mat[i][this.jEff-1]; 
            for (int j=this.jEff-2; j>p; j--){
                res[p] -= res[j] * this.Mat[i][j];
            }
            p--;
        }
        for (int i=0; i < this.iEff; i++){
            if (i != this.iEff - 1){
                System.out.print(String.format("x[%d] = %.2f, ", i+1, res[i]));
            }
            else{
                System.out.println(String.format("x[%d] = %.2f", i+1, res[i]));
            }
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
                System.out.println("Solusi banyak");
            }
            else {
                printUniqueSolution_gauss();
            }
        }
        else {
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
            System.out.println("Solusi tidak ada");
        }
    }
}
