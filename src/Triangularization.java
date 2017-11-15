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


        ArrayList<Integer> orderOfTasks = new ArrayList<Integer>();
        ArrayList<Integer> remainingTasks = new ArrayList<Integer>();

        for(int i=0 ; i<tempMatrix.length ; i++){
            orderOfTasks.add(0);
            remainingTasks.add(i+1);
        }

        int colsFound = 0;
        int rowsFound = 0;

        while(!complete){

            emptyRows = new ArrayList<Integer>();

            for(int row=0 ; row<tempMatrix.length ; row++){
                boolean emptyRow = true;
                for (int col=0 ; col < tempMatrix[0].length ; col++){
                    if(tempMatrix[row][col] == 1 && row != col){
                        emptyRow = false;
                    }
                }

                if(emptyRow){
                    emptyRows.add(row);

                    if(!orderOfTasks.contains(row)){
                        orderOfTasks.set(rowsFound, remainingTasks.get(row));
                        rowsFound++;
                    }

                }

            }

            //if(emptyRows.size() == 0){

                emptyCols = new ArrayList<Integer>();

                for(int col=0 ; col < tempMatrix.length ; col++) {

                    boolean emptyCol = true;

                    for (int row = 0; row < tempMatrix[0].length; row++) {
                        if (tempMatrix[row][col] == 1 && row != col) {
                            emptyCol = false;
                        }
                    }

                    if (emptyCol) {
                        emptyCols.add(col);

                        if(!orderOfTasks.contains(col)){
                            orderOfTasks.set(matrix.length-colsFound-1,remainingTasks.get(col));
                            colsFound++;
                        }

                    }
                }
            //}
/*
            for(int task : orderOfTasks){
                if(remainingTasks.contains(task)){
                    remainingTasks.remove(task);
                }
            }*/

            System.out.print("Empty Rows:   ");
            for(int row : emptyRows){
                System.out.print(row+1);
                System.out.print(" ");
            }
            System.out.println();
            System.out.print("Empty Columns:   ");
            for(int col : emptyCols){
                System.out.print(col+1);
                System.out.print(" ");
            }

            System.out.println();
            System.out.print("Order of Tasks:   ");
            for(int task : orderOfTasks){
                if(task == 0){
                    System.out.print('-');
                } else {
                    System.out.print(task);
                }
                System.out.print(" ");
            }

            int newSize = this.matrix.length - (rowsFound + colsFound);

            if(newSize == 0){
                complete = true;
            } else {

                tempMatrix = new int[newSize][newSize];

                int rowDiff = 0;

                for (int row = 0; row < newSize; row++) {

                    if (orderOfTasks.contains(row + 1)) {
                        rowDiff++;
                    }

                    int colDiff = 0;

                    for (int col = 0; col < newSize; col++) {

                        if (orderOfTasks.contains(col + 1)) {
                            colDiff++;
                        }

                        tempMatrix[row][col] = this.matrix[row + rowDiff][col + colDiff];

                    }
                }


                System.out.println();
                for (int row = 0; row < newSize; row++) {
                    for (int col = 0; col < newSize; col++) {
                        System.out.print(tempMatrix[row][col]);
                        System.out.print(" ");
                    }
                    System.out.println();
                }


            }
        }

        return tempMatrix;

    }
}
