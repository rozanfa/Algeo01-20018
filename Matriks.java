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
        int m, n;
        m = scanner.nextInt();
        n = scanner.nextInt();
        
        int i,j;

        for (i = 0; i < m; i++){
            for (j = 0; j < n; j++){
                this.Mat[i][j] = scanner.nextInt();
            }
        }

        this.iEff = m;
        this.jEff = n;
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