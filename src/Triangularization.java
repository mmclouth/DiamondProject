import java.util.ArrayList;

/**
 * Created by kenziemclouth on 11/14/17.
 */
public class Triangularization {

    int[][] matrix;

    public Triangularization (int[][] matrix){
        this.matrix = matrix;
    }

    /**
     * Default constructor for the Arabic class
     */
    public Triangularization (){};

    public int[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(int[][] matrix) {
        this.matrix = matrix;
    }

    public int[][] performAlgorithm(){

        int[][] tempMatrix = this.matrix;

        boolean complete = false;
        ArrayList<Integer> emptyRows = new ArrayList<Integer>();
        ArrayList<Integer> emptyCols = new ArrayList<Integer>();

        while(!complete){

            emptyRows = new ArrayList<Integer>();

            for(int row=0 ; row<tempMatrix.length ; row++){
                boolean emptyRow = true;
                for (int col=0 ; col < tempMatrix[0].length ; col++){
                    if(matrix[row][col] == 1 && row != col){
                        emptyRow = false;
                    }
                }

                if(!emptyRow){
                    emptyRows.add(row);
                }

            }

            if(emptyRows.size() == 0){

                emptyCols = new ArrayList<Integer>();

                for(int col=0 ; col < tempMatrix.length ; col++) {

                    boolean emptyCol = true;

                    for (int row = 0; row < tempMatrix[0].length; row++) {
                        if (matrix[row][col] == 1 && row != col) {
                            emptyCol = false;
                        }
                    }

                    if (!emptyCol) {
                        emptyCols.add(col);
                    }
                }
            }

        }

        return tempMatrix;

    }
}
