import java.util.ArrayList;

/**
 * Created by kenziemclouth on 11/14/17.
 */
public class Triangularization {

    int[][] dependencyMatrix;

    public String[] getNameMatrix() {
        return nameMatrix;
    }

    public void setNameMatrix(String[] nameMatrix) {
        this.nameMatrix = nameMatrix;
    }

    String[] nameMatrix;

    public Triangularization (int[][] dependencyMatrix){
        this.dependencyMatrix = dependencyMatrix;
    }

    public Triangularization (int[][] dependencyMatrix, String[] nameMatrix){
        this.dependencyMatrix = dependencyMatrix;
        this.nameMatrix = nameMatrix;
    }

    /**
     * Default constructor for the Arabic class
     */
    public Triangularization (){
        initializeMatrices();
    };

    public int[][] getDependencyMatrix() {
        return dependencyMatrix;
    }

    public void setDependencyMatrix(int[][] dependencyMatrix) {
        this.dependencyMatrix = dependencyMatrix;
    }




    public void performAlgorithm(){

        int[][] tempMatrix = this.dependencyMatrix;

        boolean complete = false;
        ArrayList<Integer> emptyRows = new ArrayList<Integer>();
        ArrayList<Integer> emptyCols = new ArrayList<Integer>();
        int counterrrr = 0;

        int rowDiff = 0;
        int colDiff = 0;
        ArrayList<Integer> orderOfTasks = new ArrayList<Integer>();
        ArrayList<String> remainingTasks = new ArrayList<String>();
        ArrayList<Integer> addedBefore = new ArrayList<Integer>();
        ArrayList<Integer> addingNow = new ArrayList<Integer>();

        for(int i=0 ; i<tempMatrix.length ; i++){
            orderOfTasks.add(0);
            remainingTasks.add(Integer.toString(i+1));
        }

        int colsFound = 0;
        int rowsFound = 0;
        int newSize = tempMatrix.length;

        //Keep iterating through algorithm until all tasks are sorted
        while(!complete){

            //Reinitialize the list of empty rows found
            emptyRows = new ArrayList<Integer>();
            addingNow = new ArrayList<>();

            //iterate through each row
            for(int row=0 ; row<tempMatrix.length ; row++){
                boolean emptyRow = true;

                //Iterate through each column to see if the row is "empty"
                for (int col=0 ; col < tempMatrix[0].length ; col++){
                    if(tempMatrix[row][col] == 1 && row != col){
                        emptyRow = false;
                    }
                }

                if(emptyRow){
                    emptyRows.add(row);

                    //check to see if the empty row has been sorted yet, if not add task to orderOfTasks
                    if(!orderOfTasks.contains(remainingTasks.get(row))){
                        orderOfTasks.set(rowsFound, Integer.parseInt(remainingTasks.get(row)));
                        if(Integer.parseInt(remainingTasks.get(row)) == 13){
                            System.out.println("TAsk 13");
                        }
                        rowsFound++;
                    }


                }

            }

            if(emptyRows.size() == 0){

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

                        if( !orderOfTasks.contains(remainingTasks.get(col)) && !emptyRows.contains(col)){
                            orderOfTasks.set(dependencyMatrix.length-colsFound-1, Integer.parseInt(remainingTasks.get(col)));
                            colsFound++;
                        }

                    }
                }
            }

            for(int task : orderOfTasks){
                if(remainingTasks.contains(Integer.toString(task))){
                    remainingTasks.remove(Integer.toString(task));
                    addingNow.add(task);
                }
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


            int oldSize = newSize;
            newSize = this.dependencyMatrix.length - (rowsFound + colsFound);

            if(newSize == 1){
                orderOfTasks.set(rowsFound, Integer.parseInt(remainingTasks.get(0)));
                complete = true;
            } else {

                int[][] oldMatrix = tempMatrix;
                tempMatrix = new int[newSize][newSize];

                rowDiff = 0;

                for (int row = 0; row < newSize; row++) {

                    if (orderOfTasks.contains(row + 1) && !addedBefore.contains(row+1)) {
                        rowDiff++;
                    }

                    colDiff = 0;

                    for (int col = 0; col < newSize; col++) {

                        if (orderOfTasks.contains(col + 1) && !addedBefore.contains(col+1)) {
                            colDiff++;
                        }


                        if((row + rowDiff) <= newSize  && (col + colDiff) <= newSize) {
                            tempMatrix[row][col] = oldMatrix[row + rowDiff][col + colDiff];
                            if((row +rowDiff) == 17){
                                System.out.println("Stop");
                                counterrrr = 1;
                            }
                            if((row + rowDiff) == 16 && counterrrr ==1){
                                System.out.println("Stop");
                            }

                        } else if(rowDiff+row > newSize){
                            System.out.println("Index out of bounds on rowdiff");
                        } else {
                            System.out.println("Index out of bounds on coldiff");
                        }

                    }
                }

                for(int number : addingNow){
                    if(!addedBefore.contains(number)){
                        addedBefore.add(number);
                    }
                }

                //PRINTING MATRIX
                System.out.println(newSize);
                System.out.println(oldSize);
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


        //Print order of tasks (number labels)
        System.out.println();
        System.out.print("FINAL Order of Tasks:  ");
        for(int task : orderOfTasks){
            System.out.print(task);
            System.out.print(" ");
        }

        //Get size of final matrix and make new tempMatrix
        newSize = this.dependencyMatrix.length;
        tempMatrix = new int[newSize][newSize];
        int[][] finalMatrix = new int[newSize][newSize];
        String[] tempNameMatrix = new String[newSize];

        //Rearrange matrix rows to match the final order of tasks
        for(int i=0 ; i<newSize ; i++){
            tempMatrix[i]=this.dependencyMatrix[orderOfTasks.get(i)-1];
            tempNameMatrix[i] = this.nameMatrix[orderOfTasks.get(i)-1];
        }

        //Rearrange matrix columns to match the final order of tasks
        for(int i=0 ; i<newSize ; i++){
            for(int j=0 ; j<newSize ; j++) {
                finalMatrix[i][j] = tempMatrix[i][orderOfTasks.get(j) - 1];
            }
        }


        //Print out final matrix with column and row headers
        System.out.println();
        System.out.println();
        System.out.println("Final Ordered Matrix: ");
        System.out.print("  ");
        for(int i=0 ; i<newSize; i++){
            System.out.print(orderOfTasks.get(i));
            System.out.print(" ");
        }
        System.out.println();
        for (int row = 0; row < newSize; row++) {
            System.out.print(orderOfTasks.get(row));
            System.out.print(" ");
            for (int col = 0; col < newSize; col++) {
                if(finalMatrix[row][col] == 0){
                    System.out.print('-');
                } else {
                    System.out.print(finalMatrix[row][col]);
                }
                System.out.print(" ");
            }
            System.out.println();
        }

        //Print final ordered list of tasks;
        for(int i=0 ; i<newSize; i++){
            System.out.print(nameMatrix[orderOfTasks.get(i)-1]);
            System.out.println();
            tempNameMatrix[i] = nameMatrix[orderOfTasks.get(i)-1];
        }


        setDependencyMatrix(finalMatrix);
        setNameMatrix(tempNameMatrix);

    }

    private void initializeMatrices(){

        String[] taskNames = {"maintain airway",
                                "give oxygen",
                                "get ECG",
                                "establish IV access",
                                "get cardiac consult",
                                "give fluid bolus",
                                "Intubation",
                                "assess breath sounds",
                                "assess jugular venous distention",
                                "direct manual pressure",
                                "assess tissue perfusion",
                                "give blood",
                                "assess for hidden source of bleeding",
                                "CPR",
                                "maintain hemodynamic stability",
                                "assess signs of increased ICP",
                                "elevate HOB",
                                "get neuro consult"};

        int[][] holdMatrix =
                {       {1,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0},
                        {1,1,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0},
                        {1,0,1,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0},
                        {1,0,1,1,0,0,0,0,0,0,0,0,0,1,0,0,0,0},
                        {1,0,1,0,1,0,0,0,0,0,0,0,0,1,0,0,0,0},
                        {1,0,0,1,0,1,0,0,0,0,0,0,0,1,0,0,0,0},
                        {1,0,0,0,0,0,1,0,0,0,0,0,0,1,0,0,0,0},
                        {1,0,0,0,0,0,0,1,0,0,0,0,0,1,0,0,0,0},
                        {1,0,0,0,0,0,0,0,1,0,0,0,1,1,0,0,0,0},
                        {1,0,0,0,0,0,0,0,0,1,0,0,0,1,0,0,0,0},
                        {1,0,0,0,0,0,0,0,0,1,1,0,0,1,0,0,0,0},
                        {1,0,0,1,0,0,0,0,0,0,0,1,0,1,0,0,0,0},
                        {1,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0},
                        {0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0},
                        {1,0,1,1,0,0,0,0,0,0,0,1,0,1,1,0,0,0},
                        {1,0,1,0,0,0,0,0,0,0,0,0,0,1,0,1,0,0},
                        {1,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,1,0},
                        {1,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,0,1}   };


        setDependencyMatrix(holdMatrix);
        setNameMatrix(taskNames);

    }
}
