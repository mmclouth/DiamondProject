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


        String[] taskNames = {
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



        //TODO: rearrange matrix for precedence
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

        setMatrix(tempMatrix2);


    }























    public void performAlgorithm(){

        HashMap<String, HashMap<String, Integer>> tempMatrix = (HashMap<String, HashMap<String, Integer>>) matrix.clone();

        int numOfTasks = tempMatrix.keySet().size();
        int rowsFound = 0;
        int colsFound = 0;


        String[] orderedTasks = new String[numOfTasks];

        ArrayList<String> emptyRows = determineEmptyRows(tempMatrix);
        ArrayList<String> emptyCols = determineEmptyColumns(tempMatrix);

        System.out.println(emptyRows);
        System.out.println(emptyCols);

        for(String task : emptyRows){
            orderedTasks[rowsFound] =  task;
            rowsFound++;
        }

        for(String task : emptyCols){
            orderedTasks[numOfTasks - colsFound - 1] =  task;
            colsFound++;
        }

        System.out.println(Arrays.asList(orderedTasks));

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

        //get first key of linkedhashmap
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
