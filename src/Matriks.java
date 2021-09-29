import java.io.File;
import java.util.Locale;
import java.util.Scanner;

public class Matriks {
    
    String name;
    Double Mat[][] = new Double[15][15];
    Double matrix[][]; 

    int iEff = 0;
    int jEff = 0;
    int zeroRows = 0;
    int iInitial = 0;
    boolean justDeletedAllZeroRow = false;
    boolean isHaveAnswer = true; //pengen banget gw fix ke hasAnswer aeugh // sorry bas rip inggris aeugh
    boolean justSwappedRow = false;

    public String result = new String();

    /**
     * Constructor for the type Matriks <br></br>
     * 
     * Contains: <ul> 
     * <li> Double matrix[10][10]</li>
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
        justSwappedRow = false;
        isHaveAnswer = true;
    }

    void isiMatriks(){
        Scanner scanner = new Scanner(System.in);
        int n, m;
        System.out.print("Masukkan baris matriks: ");
        n = scanner.nextInt();
        System.out.print("Masukkan kolom matriks: ");
        m = scanner.nextInt();
        
        this.iEff = n;
        this.jEff = m;
        this.iInitial = n;

        System.out.println("\nPilih jenis input");
        System.out.println("1. Input dari keyboard");
        System.out.println("2. Input dari file");
        System.out.print("Pilihan : ");
        int option = scanner.nextInt();
        System.out.println("");
        switch (option) {
            case 1: {
                isiMatriks_dariKeyboard();
                break;
            }
            case 2: {
                isiMatriks_dariFile();
                break;
            }
            default : {
                System.out.println("Input tidak valid");
            }
        }
    }

    void isiMatriks_dariKeyboard(){
        Scanner scanner = new Scanner(System.in);
        System.out.println(String.format("Masukkan matriks %d x %d :", this.iEff, this.jEff));
        for (int i = 0; i < this.iInitial; i++){
            for (int j = 0; j < this.jEff; j++){
                this.Mat[i][j] = scanner.nextDouble();
            }
        }
    }

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
                    for (int i = 0; i < this.iInitial; i++){
                        for (int j = 0; j < this.jEff; j++){
                            this.Mat[i][j] = fileScanner.nextDouble();
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

    Matriks copy(){
        Matriks newMat = new Matriks();
        newMat.iEff = this.iEff;
        newMat.jEff = this.jEff;
        newMat.iInitial = this.iInitial;
        newMat.zeroRows = this.zeroRows;
        newMat.justDeletedAllZeroRow = this.justDeletedAllZeroRow;
        newMat.isHaveAnswer = this.isHaveAnswer;
        newMat.name = this.name;
        for (int i = 0; i < this.iEff; i++){
            for (int j = 0; j < this.jEff; j++){
                newMat.Mat[i][j] = this.Mat[i][j];
            }
        }
        return newMat;
    }

    public void cetakMatriks(){
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

    public String getMatriksString(){
        String matriksString = new String();
        int i,j;
        for (i = 0; i < this.iEff; i++){
            for (j = 0; j < this.jEff; j++){
                matriksString += String.format("%2.2f ", this.Mat[i][j]);
            }
            matriksString += "\n";
        }
        for (i = 0; i < this.zeroRows; i++){
            for (j = 0; j < this.jEff; j++){
                matriksString += String.format("%2.2f ", 0.00);
            }
            matriksString += "\n";
        }
        return matriksString;
    }

    public void substractRow(int r, int s, Double k){
        // Baris r dikurangi k kali baris s
        if (r != s && k != 0){
            System.out.println();
            System.out.println(String.format("Baris %d dikurangi oleh %.2f kali baris %d" , r, k, s));
            for (int j = 0; j < this.jEff; j++){
                this.Mat[r][j] -= k *this.Mat[s][j];
            }
            deleteRowIfRowAllZero(r);
            
            cetakMatriks();
        }

    }

    /**
     * Checks if the elmt in (r, s) is 0, if it is: <br></br>
     * Swaps the row with the bottom most row by shuffling every row downwards by one, the bottom most row loops back to the top. <br> </br>
     * Ex: {RowA, RowB, RowC} becomes {RowC, RowA, RowB}. 
     * @param r --> Which row is the element in
     * @param s --> Which column is the element in
     */
    public void swapIfFirstRowIsZero(int r, int s){ 
        if (this.Mat[r][s] == 0){
            for (int i = r; i < this.iEff; i++) {
                if (this.Mat[i][s] != 0){
                    for (int j = 0; j < this.jEff; j++){
                        Double temp = this.Mat[r][j];
                        this.Mat[r][j] = this.Mat[i][j];
                        this.Mat[i][j] = temp;
                    }
                }
                //break;
            }
        }
    }

    public void swapRow(int row1, int row2){
        //System.out.println(String.format("%d %d", row1, row2));
        //System.out.println("Before swapRow");
        //cetakMatriks();
        Double temp;
        for (int j = 0; j < this.jEff; j++){
            //System.out.println(String.format("%f, %f", this.Mat[row1][j], this.Mat[row2][j]));
            temp = this.Mat[row2][j];
            this.Mat[row2][j] = this.Mat[row1][j];
            this.Mat[row1][j] = temp;
            //System.out.println("");
            //System.out.println(temp);
            //cetakMatriks();
            //System.out.println("");
        }
        //System.out.println("");
        //System.out.println("After swapRow");
        //cetakMatriks();
        System.out.println(String.format("\nTukar baris %d dengan %d", row1, row2));
        cetakMatriks();
        this.justSwappedRow = true;
    }

    public void sort(){
        // Mengurutkan matriks berdasarkan angka 0 di bagian depan
        for (int i = 0; i < this.iEff; i++){
            for (int ii = i + 1; ii < this.iEff; ii++){
                int i1 = findFirstNonZeroIndexInRow(i, 0);
                int i2 = findFirstNonZeroIndexInRow(ii, 0);
                if (i1 > i2) {
                    swapRow(i, ii);
                }
            }
        }
    }

    public void makeLeftOne(int r, int c){
        // Menjadikan elemen baris paling kiri bernilai satu 
        // dengan membagi baris dengan konstanta yang sesuai
        Double k = findFirstNonZeroInRow(r, 0);
        if (k != 0 && k!= 1){
            for (int j = 0; j < this.jEff; j++){
                this.Mat[r][j] /= k;
            }
            System.out.println(String.format("\nBagi baris %d dengan %.2f", r, k));
            cetakMatriks();
        }
    }

    public Double findFirstNonZeroInRow(int r, int c){
        Double k = (Double) 0.0;
            for (int j = 0; j < this.jEff; j++){
                
                if (this.Mat[r][j] != 0){
                    k = this.Mat[r][j];
                    break;
                }
            }
        return k;
    }

    public int findFirstNonZeroIndexInRow(int r, int c){
        int idx = 0;
            for (int j = 0; j < this.jEff; j++){
                
                if (this.Mat[r][j] != 0){
                    idx = j;
                    break;
                }
        }
        return idx;
    }

    public void obe_gauss(){
        // Melakukan OBE pada matriks hingga menjadi Matriks Gaussian
        sort();
        for (int i = 0; i < this.iEff; i++){
            makeLeftOne(i, i);
            for (int ii = i + 1; ii < this.iEff; ii++){
                swapIfFirstRowIsZero(i,i);
                if (this.justSwappedRow){
                    this.justSwappedRow = false;
                    ii = i + 1;
                }
                substractRow(ii, i, this.Mat[ii][i]);
                while (this.justDeletedAllZeroRow) {
                    //System.out.println(String.format("first nonzero %d", findFirstNonZeroIndexInRow(i, 0)));
                    while (ii >= this.iEff){
                        ii--;
                    }
                    while (i >= this.iEff){
                        i--;
                    }
                    substractRow(ii, i, this.Mat[ii][findFirstNonZeroIndexInRow(i, 0)]);
                    this.justDeletedAllZeroRow = false;
                }
            }
        }
    }

    public void obe_gauss_jordan(){
        // Melakukan OBE pada matriks hingga menjadi Matriks Gauss-Jordan
        obe_gauss();
        for (int i = 1; i < this.iEff; i++){
            for (int ii = i - 1; ii >= 0; ii--){
                substractRow(ii, i, this.Mat[ii][findFirstNonZeroIndexInRow(i, 0)]);
                while (this.justDeletedAllZeroRow) {
                    //System.out.println(String.format("first nonzero %d", findFirstNonZeroIndexInRow(i, 0)));
                    while (ii >= this.iEff){
                        ii--;
                    }
                    while (i >= this.iEff){
                        i--;
                    }
                    substractRow(ii, i, this.Mat[ii][findFirstNonZeroIndexInRow(i, 0)]);
                    this.justDeletedAllZeroRow = false;
                }
            }
            makeLeftOne(i, i);
        }
        sort();
        for (int i = 1; i < this.iEff; i++){
            for (int ii = i - 1; ii >= 0; ii--){
                substractRow(ii, i, this.Mat[ii][findFirstNonZeroIndexInRow(i, 0)]);
                while (this.justDeletedAllZeroRow) {
                    //System.out.println(String.format("first nonzero %d", findFirstNonZeroIndexInRow(i, 0)));
                    while (ii >= this.iEff){
                        ii--;
                    }
                    while (i >= this.iEff){
                        i--;
                    }
                    substractRow(ii, i, this.Mat[ii][findFirstNonZeroIndexInRow(i, 0)]);
                    this.justDeletedAllZeroRow = false;
                }
            }
            makeLeftOne(i, i);
        }
        sort();
    }


    public void deleteRowIfRowAllZero(int r){
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

    static public Matriks multiplyMatrix(Matriks m1, Matriks m2){
        Matriks m3 = new Matriks();
        m3.iEff = m1.iEff;
        m3.jEff = m2.jEff;
        int i,j,k;

        //System.out.println("m1");
        //m1.cetakMatriks();

        //System.out.println("m2");
        //m2.cetakMatriks();
        
        for (i=0; i<m1.iEff; i++){
            for (j=0; j<m2.jEff; j++){
                m3.Mat[i][j] = (Double) 0.0;
                for (k=0; k<m1.jEff; k++){
                    //System.out.println(String.format("i %d j %d k %d",i,j,k));
                    m3.Mat[i][j] += m1.Mat[i][k] * m2.Mat[k][j];
                }
            }
        }
        return m3;
    }
}