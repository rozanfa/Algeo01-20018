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



    /*
    void printManySolution(){
        Char var = 'r';
        List res = new List();
        Int p = this.iEff - 1;
        for (int i=0; i<this.iEff; i++){
            res[i] = 0;
            if (this.Mat[i][i] != 1){
                var++;
            }
        }
        for (int i=this.iEff; i>=0; i--){
            if (this.Mat[i][p] == 1){
                res[p] = this.Mat[i][this.jEff-1];
                p--;
            }
            else {
                while (p > 0 && this.Mat[i][p] != 1){
                    res[p] = var;
                    var--;
                    p--;
                }
            }
        }

    }
    */


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
                System.out.println("Solusi banyak");
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
