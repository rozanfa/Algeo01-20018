import java.util.Scanner;

public class Matriks {
    
    String name;
    Float Mat[][] = new Float[10][10];
    Float matrix[][]; 

    int iEff = 0;
    int jEff = 0;
    int zeroRows = 0;
    int iInitial = 0;
    boolean justDeletedAllZeroRow = false;
    boolean isHaveAnswer = true; //pengen banget gw fix ke hasAnswer aeugh 

    /**
     * Constructor for the type Matriks <br></br>
     * 
     * Contains: <ul> 
     * <li> float matrix[10][10]</li>
     * <li> int iEff </li>
     * <li> int jEff </li>
     * <li> int zeroRows </li>
     * <li> int iInitial </li>
     * <li> boolean justDeletedAllZeroRow </li>
     * <li> boolean isHaveAnswer</li>
     */
    public Matriks(){
        matrix = Mat; 
        iEff = 0;
        jEff = 0;
        zeroRows = 0;
        iInitial = 0;
        justDeletedAllZeroRow = false;
        isHaveAnswer = true;
    }

    void isiMatriks(){
        Scanner scanner = new Scanner(System.in);
        int n, m;
        System.out.print("Masukkan baris matriks: ");
        n = scanner.nextInt();
        System.out.print("Masukkan kolom matriks: ");
        m = scanner.nextInt();
        
        int i,j;
        this.iEff = n;
        this.jEff = m;
        this.iInitial = n;
        for (i = 0; i < n; i++){
            for (j = 0; j < m; j++){
                this.Mat[i][j] = scanner.nextFloat();
            }
            deleteRowIfRowAllZero(i);
        }
    }

    void cetakMatriks(){
        int i,j;
        for (i = 0; i < this.iEff; i++){
            for (j = 0; j < this.jEff; j++){
                System.out.print(String.format("%2.2f ", this.Mat[i][j]));
            }
            System.out.println();
        }
        for (i = 0; i < this.zeroRows; i++){
            for (j = 0; j < this.jEff; j++){
                System.out.print(String.format("%2.2f ", 0.00));
            }
            System.out.println();
        }
        
    }

    void substractRow(int r, int s, Float k){
        // Baris r dikurangi k kali baris s
        for (int j = 0; j < this.jEff; j++){
            this.Mat[r][j] -= k *this.Mat[s][j];
        }
        deleteRowIfRowAllZero(r);
        System.out.println();
        System.out.println(String.format("%d %d %.1f", r, s, k));
        cetakMatriks();
    }

    /**
     * Checks if the elmt in (r, s) is 0, if it is: <br></br>
     * Swaps the row with the bottom most row by shuffling every row downwards by one, the bottom most row loops back to the top. <br> </br>
     * Ex: {RowA, RowB, RowC} becomes {RowC, RowA, RowB}. 
     * @param r --> Which row is the element in
     * @param s --> Which column is the element in
     */
    void swapIfFirstRowIsZero(int r, int s){ 
        if (this.Mat[r][s] == 0){
            for (int i = r; i < this.iEff; i++) {
                if (this.Mat[i][s] != 0){
                    for (int j = 0; j < this.jEff; j++){
                        Float temp = this.Mat[r][j];
                        this.Mat[r][j] = this.Mat[i][j];
                        this.Mat[i][j] = temp;
                    }
                }
            }
        }
    }

    void makeLeftOne(int r, int c){
        // Menjadikan elemen baris paling kiri bernilai satu 
        // dengan membagi baris dengan konstanta yang sesuai
        float k = findFirstNonZeroInRow(r, c);
        if (k != 0){
            for (int j = c; j < this.jEff; j++){
                this.Mat[r][j] /= k;
            }
        }
    }

    float findFirstNonZeroInRow(int r, int c){
        float k = (float) 0;
        if (this.Mat[r][c] != 0){
            k = this.Mat[r][c];
        }
        else {
            for (int j = 0; j < this.jEff; j++){
                
                if (this.Mat[r][j] != 0){
                    k = this.Mat[r][j];
                    break;
                }
            }
        }
        return k;
    }

    void obe_gauss(){
        // Melakukan OBE pada matriks hingga menjadi Matriks Gaussian
        for (int i = 0; i < this.iEff; i++){
            swapIfFirstRowIsZero(i,i);
            makeLeftOne(i, i);
            for (int ii = i + 1; ii < this.iEff; ii++){
                substractRow(ii, i, this.Mat[ii][i]);
            }
        }
    }

    void obe_gauss_jordan(){
        // Melakukan OBE pada matriks hingga menjadi Matriks Gauss-Jordan
        obe_gauss();
        for (int i = 1; i < this.iEff; i++){
            for (int ii = i - 1; ii >= 0; ii--){
                substractRow(ii, i, findFirstNonZeroInRow(ii, i));
                while (this.justDeletedAllZeroRow) {
                    substractRow(ii, i, findFirstNonZeroInRow(ii, i));
                    this.justDeletedAllZeroRow = false;
                }
            }
            makeLeftOne(i, i);
        }
    }

    void deleteRowIfRowAllZero(int r){
        // Menghapus baris jika semua elemen di dalam baris bernilai nol.
        // Namun, tetap akan dicetak ke dalam layar karena terdapat
        // variabel yang menyimpan jumlah baris yang dihapus
        boolean allZero = true;
        for (int j=0; j< this.jEff; j++){
            if (this.Mat[r][j] != 0) 
                allZero = false;
        }
        if (allZero) {
            for (int i=r+1; i<this.iEff; i++){
                for (int j=0; j<this.jEff; j++){
                    this.Mat[i-1][j] = this.Mat[i][j];
                }
            }
            this.iEff -= 1;
            this.zeroRows += 1;
            this.justDeletedAllZeroRow = true;
        }
    }

}