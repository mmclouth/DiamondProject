import java.util.*;

/**
 * Created by kenziemclouth on 11/28/17.
 */
public class Decomposition {


    private HashMap<String, HashMap<String, Integer>> matrix;

    public Decomposition(){
        initializeMatrix();
    }


    public HashMap<String, HashMap<String, Integer>> getMatrix() {
        return matrix;
    }

    public void setMatrix(HashMap<String, HashMap<String, Integer>> matrix) {
        this.matrix = matrix;
    }


    public static void main(String[] args) {

        Decomposition decomp = new Decomposition();


        ArrayList<String> taskNames = new ArrayList<>();

        taskNames.add("airway");
        taskNames.add("oxygen");
        taskNames.add("ECG");
        taskNames.add("IV");
        taskNames.add("CPR");
        taskNames.add("fluid");
        taskNames.add("cardiac");

        decomp.printMatrix(decomp.getMatrix());
        decomp.selectTasks(taskNames);
        decomp.printMatrix(decomp.getMatrix());
        decomp.performAlgorithm();
    }



    private void initializeMatrix(){

        /*
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
                "get neuro consult"};*/


        /*String[] taskNames = {
                "airway",
                "oxygen",
                "ECG",
                "IV",
                "cardiac",
                "fluid",
                "Intu",
                "breath",
                "jugular",
                "press",
                "tissue",
                "blood",
                "bleed",
                "CPR",
                "hemo",
                "ICP",
                "HOB",
                "neuro"};
                */

        String[] taskNames = {
                "airway",
                "CPR",
                "oxygen",
                "ECG",
                "intubate",
                "IV",
                "blood",
                "breath",
                "jugular",
                "pressure",
                "bleeding",
                "fluidbolis",
                "hemo",
                "ICP",
                "HOB",
                "tissue",
                "cardiac",
                "neuro"};



        //TODO: rearrange matrix for precedence
        /*
        int[][] holdMatrix =
                {       {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
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
                        {1,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0},
                        {1,0,1,1,0,0,0,0,0,0,0,1,0,1,1,0,0,0},
                        {1,0,1,0,0,0,0,0,0,0,0,0,0,1,0,1,0,0},
                        {1,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,1,0},
                        {1,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,0,1}   };
                    */

        int[][] holdMatrix =
                {
                        {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                        {1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                        {1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                        {1,1,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                        {1,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0},
                        {1,1,0,1,0,1,0,0,0,0,0,0,0,0,0,0,0,0},
                        {1,1,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0},
                        {1,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0},
                        {1,1,0,0,0,0,0,0,1,0,0,1,0,0,0,0,0,0},
                        {1,1,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0},
                        {1,1,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0},
                        {1,1,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0},
                        {1,1,0,0,0,1,0,0,0,0,0,0,1,0,0,0,0,0},
                        {1,1,0,0,1,1,1,0,0,0,0,0,0,1,0,0,0,0},
                        {1,1,0,0,1,0,0,0,0,0,0,0,0,0,1,0,0,0},
                        {1,1,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0},
                        {1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0},
                        {1,1,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,1},
                };




        HashMap<String, HashMap<String, Integer>> tempMatrix = new HashMap<>();

        for(int row=0 ; row<holdMatrix.length ; row++){

            HashMap<String,Integer> task = new HashMap<>();

            for(int col=0 ; col<holdMatrix.length ; col++){
                task.put(taskNames[col], holdMatrix[row][col]);
            }

            tempMatrix.put(taskNames[row], task);

        }

        setMatrix(tempMatrix);

    }


    public void printMatrix(HashMap<String,HashMap<String, Integer>> tempMatrix){

        System.out.println();
        System.out.println();

        System.out.printf("%10s",  "    ");
        for(String task: tempMatrix.keySet()){
            System.out.printf("%10s", task);
        }

        System.out.println();

        for(String task: tempMatrix.keySet()){
            System.out.printf("%10s", task);
            for(String task2 : tempMatrix.get(task).keySet()){
                System.out.format("%10d", tempMatrix.get(task).get(task2));
            }
            System.out.println();
        }
    }


    public void selectTasksWithNumbers(ArrayList<Integer> taskNums){

        String[] names = {
                "airway",
                "oxygen",
                "ECG",
                "IV",
                "cardiac",
                "fluidbolis",
                "intubate",
                "breath",
                "jugular",
                "pressure",
                "tissue",
                "blood",
                "bleeding",
                "CPR",
                "hemo",
                "ICP",
                "HOB",
                "neuro"};

        ArrayList<String> taskNames = new ArrayList<>();

        for(int i=0 ; i<taskNums.size() ; i++){
            if(taskNums.get(i)==1){
                taskNames.add(names[i]);
            }
        }

        System.out.print("Task names in num: " + taskNames);

        selectTasks(taskNames);

    }


    public void selectTasks(ArrayList<String> taskNames){

        HashMap<String, HashMap<String, Integer>> tempMatrix = (HashMap<String, HashMap<String, Integer>>) matrix.clone();

        HashMap<String, HashMap<String, Integer>> tempMatrix2 = new HashMap<>();
        HashMap<String, Integer> tempRow;


        //Remove rows of tasks
        for(String key : tempMatrix.keySet()){

            if(taskNames.contains(key)){

                tempRow = new HashMap<>();

                //Remove columns of tasks
                for(String key2 : tempMatrix.get(key).keySet()){

                    if(taskNames.contains(key2)){
                        tempRow.put(key2, tempMatrix.get(key).get(key2));
                    }

                }
                tempMatrix2.put(key, tempRow);
            }
        }

        printMatrix(tempMatrix2);
        setMatrix(tempMatrix2);

    }

    public void selectTasks(HashMap<String, Integer> tasks){
        HashMap<String, HashMap<String, Integer>> tempMatrix = (HashMap<String, HashMap<String, Integer>>) matrix.clone();

        HashMap<String, HashMap<String, Integer>> tempMatrix2 = new HashMap<>();
        HashMap<String, Integer> tempRow;


        //Remove rows of tasks
        for(String key : tempMatrix.keySet()){

            if(tasks.get(key) == 1){

                tempRow = new HashMap<>();

                //Remove columns of tasks
                for(String key2 : tempMatrix.get(key).keySet()){

                    if(tasks.get(key2) == 1){
                        tempRow.put(key2, tempMatrix.get(key).get(key2));
                    }

                }
                tempMatrix2.put(key, tempRow);
            }
        }

        System.out.println("MAtrix after selectTasks");
        printMatrix(tempMatrix2);
        setMatrix(tempMatrix2);
    }

    public HashMap<String, HashMap<String, Integer>> removeTasks(ArrayList<String> taskNames){

        HashMap<String, HashMap<String, Integer>> tempMatrix = (HashMap<String, HashMap<String, Integer>>) matrix.clone();

        HashMap<String, HashMap<String, Integer>> tempMatrix2 = new HashMap<>();
        HashMap<String, Integer> tempRow;


        //Remove rows of tasks
        for(String key : tempMatrix.keySet()){

            if(!taskNames.contains(key)){

                tempRow = new HashMap<>();

                //Remove columns of tasks
                for(String key2 : tempMatrix.get(key).keySet()){

                    if(!taskNames.contains(key2)){
                        tempRow.put(key2, tempMatrix.get(key).get(key2));
                    }

                }
                tempMatrix2.put(key, tempRow);
            }
        }

        return tempMatrix2;

    }


    public ArrayList<String> performAlgorithm(){

        HashMap<String, HashMap<String, Integer>> tempMatrix = (HashMap<String, HashMap<String, Integer>>) matrix.clone();

        System.out.println("initial matrix");
        printMatrix(tempMatrix);

        int numOfTasks = tempMatrix.keySet().size();
        int rowsFound = 0;
        int colsFound = 0;


        String[] orderedTasks = new String[numOfTasks];
        boolean complete = false;

        while(!complete) {


            ArrayList<String> emptyRows = determineEmptyRows(tempMatrix);


            for (String task : emptyRows) {
                orderedTasks[rowsFound] = task;
                rowsFound++;
            }

            if(emptyRows.isEmpty()) {
                ArrayList<String> emptyCols = determineEmptyColumns(tempMatrix);
                for (String task : emptyCols) {
                    orderedTasks[numOfTasks - colsFound - 1] = task;
                    colsFound++;
                }
            }

            ArrayList<String> tasksAsList = new ArrayList(Arrays.asList(orderedTasks));
            System.out.println("Current task list: " + tasksAsList);

            tempMatrix = removeTasks(tasksAsList);
            printMatrix(tempMatrix);

            //if there is one or no tasks left to be sorted, sort the last task and then end the algorithm
            if(tempMatrix.size() < 2){

                //if one more task, get task name and place it in remaining ordered slot
                if(tempMatrix.size() == 1){
                    Map.Entry<String, HashMap<String, Integer>> mapEntry = tempMatrix.entrySet().iterator().next();
                    String lastKey = mapEntry.getKey();
                    orderedTasks[rowsFound] = lastKey;
                }

                complete = true;
            }


        }


        System.out.println("PRINTING FINAL MATRIX");
        printMatrix(tempMatrix);
        ArrayList<String> tasksAsList = new ArrayList(Arrays.asList(orderedTasks));
        System.out.println(tasksAsList);

        return tasksAsList;
    }

    private ArrayList<String> determineEmptyRows(HashMap<String, HashMap<String, Integer>> tempMatrix){

        ArrayList<String> emptyRows = new ArrayList<>();

        //Determine empty rows
        for(String key : tempMatrix.keySet()){

            boolean emptyRow = true;
            for(String key2 : tempMatrix.get(key).keySet()){

                if(tempMatrix.get(key).get(key2) == 1 && !key.equals(key2)){
                    emptyRow=false;
                }
            }

            if(emptyRow){
                emptyRows.add(key);
            }

        }

        return emptyRows;
    }

    private ArrayList<String> determineEmptyColumns(HashMap<String, HashMap<String, Integer>> tempMatrix) {

        ArrayList<String> emptyCols = new ArrayList<>();

        //get first key of hashmap
        Map.Entry<String, HashMap<String, Integer>> mapEntry = tempMatrix.entrySet().iterator().next();

        for (String key2 : tempMatrix.get(mapEntry.getKey()).keySet()) {

            boolean emptyCol = true;
            for (String key : tempMatrix.keySet()) {

                if (tempMatrix.get(key).get(key2) == 1 && !key.equals(key2)) {
                    emptyCol = false;
                }
            }

            if (emptyCol) {
                emptyCols.add(key2);
            }

        }

        return emptyCols;
    }

}
