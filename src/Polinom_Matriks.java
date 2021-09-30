import java.util.Scanner;

public class Polinom_Matriks extends Matriks{

    // Inisialisasi basic matrice buat nyimpen titik (x,y) & basic stuffs
    Double xyMatrice[][] = new Double[15][2];
    Double equation[] = new Double[15];
    int eqLength = 0;
    int iEffxy = 0;
    int jEffxy = 2;
    Scanner scanner = new Scanner(System.in);
    public Matriks matrix = new Matriks();


    /** 
     * Reads the points in the form of(x,y) without brackets, each line is one point. <br> </br>
     * Specify how many points to be read
     */
    public void readPoints(int points){
        for (int i = 0; i < points; i++){
            this.xyMatrice[i][0] = scanner.nextDouble();
            this.xyMatrice[i][1] = scanner.nextDouble();
        }
        this.iEffxy = points;
    };

    /**
     * prints the points given to a (x,y) format
     */
    public void printPoints(){
        for (int i = 0; i < iEffxy; i++){
            System.out.print("(" + this.xyMatrice[i][0]);
            System.out.print("," + this.xyMatrice[i][1]);
            System.out.println(")");
        }
    }

    /**
     * returns the points as a string using (x,y) format
     */
    public String getPoints(){
        String temp = "";
        for (int i = 0; i < iEffxy; i++){
            temp += "(" + this.xyMatrice[i][0] + "," + this.xyMatrice[i][1] + ")\n";
        }
        return temp;
    }


    /**
     * fills the points given to the matrice
     */
    public void fillToMatrix(){
        for (int i = 0; i < this.iEffxy; i++){
            for (int j = 0; j< this.iEffxy + 1; j++){
                if (j == 0) this.matrix.Mat[i][j] = (Double) 1.0;                                                       // kalo di col:1 set 1 aja
                else if (j != this.iEffxy) this.matrix.Mat[i][j] = (Double) exponent(this.xyMatrice[i][0], j);   // kalo gk di col:1 set x^i
                else this.matrix.Mat[i][j] = this.xyMatrice[i][1];                                      // kalo di col paling ujung set ke y
            }
        }
        this.matrix.iEff = this.iEffxy;
        this.matrix.jEff = this.iEffxy + 1;
    }
    
    /**
     * Precondition: matrice has been Gauss-Jordan-ed. copies the equation for easier use
     */
    public void copyEquation(){
        for(int i = 0; i < this.matrix.iEff; i++){
            this.equation[i] = (double) this.matrix.Mat[i][this.matrix.jEff - 1];
        }
        this.eqLength = this.iEffxy;
    }

    public double getYFromEq(double x){
        double temp = 0;
        for (int i = 0; i < this.eqLength; i++){
            if (i != 0) temp += this.equation[i] * exponent(x, i);
            else temp += this.equation[i];
            // System.out.println(this.equation[i] + " " + i);
            // if (i != 0) System.out.println(exponent(this.equation[i], i) * x);
            // else System.out.println(this.equation[i]);
        }
        return temp;
    }

    // /**
    //  * Temporary function to turn a double to a Double
    //  */
    // public Double toDouble(double value){
    //     Double temp = (Double) value;
    //     return temp;
    // }

    /**
     * Exponents a number a^b and returns it
     */
    public double exponent(double a, int b){
        double temp = 1;
        for (int i = 0; i < b; i++){
            temp *= a;
        }
        return temp;
    }


    /**
     * returns a string filled with the equation in the form  of:
     * <ul>
     *  <li> x[0] = ...
     *  <li> x[1] = ...
     * <li> ...
     * <li> x[n] = ...
     * </ul>
     */
    public String getEquation(){
        String temp = "p(x) = ";
        for (int i = 0; i < eqLength; i++){
            if (i != 0){
                if (equation[i] >= 0) temp += " + ";
                else temp += " - ";
            }

            if (equation[i] >= 0) temp += String.format("%.4f", equation[i]);
            else temp+= String.format("%.4f", -1 * equation[i]);

            if (i > 0) temp += "x";
            if (i > 1) temp += "^" +i;
            }
        temp += "\n";
        return temp;
    }
}
