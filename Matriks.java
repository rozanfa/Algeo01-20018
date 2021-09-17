package GkMirip;
import java.util.Scanner;

public class Matriks {
    
    int Mat[][] = new int[10][10];

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
        n = scanner.nextInt();
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
}