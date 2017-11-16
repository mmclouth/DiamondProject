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
        ArrayList<String> remainingTasks = new ArrayList<String>();

        for(int i=0 ; i<tempMatrix.length ; i++){
            orderOfTasks.add(0);
            remainingTasks.add(Integer.toString(i+1));
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

                    if(remainingTasks.contains(Integer.toString(row+1))){
                        orderOfTasks.set(rowsFound, Integer.parseInt(remainingTasks.get(row)));
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

                        if(remainingTasks.contains(Integer.toString(col+1)) && !emptyRows.contains(col)){
                            orderOfTasks.set(matrix.length-colsFound-1, Integer.parseInt(remainingTasks.get(col)));
                            colsFound++;
                        }

                    }
                }
            //}

            for(int task : orderOfTasks){
                if(remainingTasks.contains(Integer.toString(task))){
                    remainingTasks.remove(Integer.toString(task));
                }
            }



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

            if(newSize == 1){
                orderOfTasks.set(rowsFound, Integer.parseInt(remainingTasks.get(0)));
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

                //PRINTING MATRIX
                System.out.println();
                System.out.print("  ");
                //System.out.print("Remaining Tasks: ");
                for(int i=0 ; i<newSize; i++){
                    System.out.print(remainingTasks.get(i));
                    System.out.print(" ");
                }
                System.out.println();
                for (int row = 0; row < newSize; row++) {
                    System.out.print(remainingTasks.get(row));
                    System.out.print(" ");
                    for (int col = 0; col < newSize; col++) {
                        System.out.print(tempMatrix[row][col]);
                        System.out.print(" ");
                    }
                    System.out.println();
                }


            }
        }

        System.out.println();
        System.out.println();
        System.out.println();
        System.out.print("FINAL Order of Tasks:   ");
        for(int task : orderOfTasks){
            if(task == 0){
                System.out.print('-');
            } else {
                System.out.print(task);
            }
            System.out.print(" ");
        }

        return tempMatrix;

    }
}
