package GkMirip;
import java.util.Scanner;

public class Matriks {
    
    float Mat[][] = new float[10][10];

    int iEff = 0;
    int jEff = 0;

    Matriks(){
        int i,j;

        for (i = 0; i < 10; i++){
            for (j = 0; j < 10; j++){
                this.Mat[i][j]= 0;
            }
        }

    }

    void isiMatriks(){
        Scanner scanner = new Scanner(System.in);
        int n, m;
        System.out.print("Masukkan baris matriks: ");
        n = scanner.nextInt();
        System.out.print("Masukkan kolom matriks: ");
        m = scanner.nextInt();
        
        int i,j;

        for (i = 0; i < n; i++){
            for (j = 0; j < m; j++){
                this.Mat[i][j] = scanner.nextInt();
            }
        }

        this.iEff = n;
        this.jEff = m;
    }

    void cetakMatriks(){
        int i,j;
        for (i = 0; i < this.iEff; i++){
            for (j = 0; j < this.jEff; j++){
                System.out.print(this.Mat[i][j] + " ");
            }
            System.out.println();
        }
    }

    void substractRow(int r, int s, float k){
        // Baris r dikurangi k kali baris s
        for (int j = 0; j < this.jEff; j++){
            this.Mat[r][j] -= k *this.Mat[s][j];
        }
    }

    void makeLeftOne(int r, int c){
        // Menjadikan elemen baris paling kiri bernilai satu 
        // dengan membagi baris dengan konstanta yang sesuai
        float k = this.Mat[r][c];
        for (int j = c; j < this.jEff; j++){
            this.Mat[r][j] /= k;
        }
    }

    void spl_gauss(){
        if (this.Mat[0][0] != 1){
            
        }
    }
}