import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by kenziemclouth on 11/28/17.
 */
public class Patient {

    public static void main(String[] args) {


        Scanner input = new Scanner(System.in);

        int bleeding = 0;
        int airway = 0;
        int maintainAirway = 0;
        int giveOxygen = 0;
        int ECG = 0;
        int IVaccess = 0;
        int cardiacConsult = 0;
        int fluidBolus = 0;
        int intubation = 0;
        int breathSounds = 0;
        int assessJugular = 0;
        int manualPressure = 0;
        int assessTissue = 0;
        int giveBlood = 0;
        int assesHiddenBleeding = 0;
        int CPR = 0;
        int hemodynamicStability = 0;
        int assesICP = 0;
        int elevateHOB = 0;
        int neuroConsult = 0;



        int heartRate;

        System.out.print("Enter heart rate: ");
        heartRate = Integer.parseInt(input.next());

        System.out.println("Heart rate is "+ heartRate);

        if(heartRate == 0){
            //System.out.println("Perform CPR");
            CPR = 1;
        }

        else {

            if (heartRate > 100 || heartRate < 60) {
                System.out.println("Perform necessary tasks");
                maintainAirway = 1;
                giveOxygen = 1;
                ECG = 1;
                IVaccess = 1;
                cardiacConsult = 1;

            } else {
                System.out.println("healthy");
            }
        }

        int bloodPressure;

        System.out.print("Enter systolic blood pressure: ");
        bloodPressure = Integer.parseInt(input.next());

        System.out.println("Blood Pressure is "+ bloodPressure);

        if(bloodPressure < 90){
            System.out.println("Perform Necessary Tasks");
            maintainAirway = 1;
            IVaccess = 1;
            fluidBolus = 1;

        } else {
            System.out.println("healthy");

        }

        System.out.print("Is patient's airway blocked?");
        String response = input.next();

        if(response.equals("yes")){
            System.out.println("Perform Intubation");
            airway = 1;
            intubation = 1;
        }

        int respRate;

        System.out.print("Enter respiratory rate: ");
        respRate = Integer.parseInt(input.next());

        System.out.println("Respiratory rate is "+ respRate);

        if(respRate < 10 || respRate > 29){
            System.out.println("Perform Necessary Tasks");
            maintainAirway = 1;
            giveOxygen = 1;
            breathSounds = 1;
            assessJugular = 1;
        } else {
            System.out.println("healthy");
        }

        System.out.print("Is patient excessively bleeding (20-40% blood loss)?");
        String response2 = input.next();

        if(response2.equals("yes")){
            //System.out.println("Perform Necessary Tasks");
            bleeding = 1;
            maintainAirway = 1;
            manualPressure = 1;
            assessTissue = 1;
            IVaccess = 1;
            fluidBolus = 1;
            giveBlood = 1;
            assesHiddenBleeding = 1;
        }

        int glascow;

        System.out.print("Enter glascow coma: ");
        glascow = Integer.parseInt(input.next());

        System.out.println("Glascow coma is "+ glascow);

        if(glascow < 9){
            System.out.println("Perform Necessary Tasks");
            maintainAirway = 1;
            hemodynamicStability = 1;
            assesICP = 1;
            elevateHOB = 1;
            neuroConsult = 1;

        } else {
            System.out.println("healthy");
        }

        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();


        ArrayList<Integer> tasksNeeded = new ArrayList<>();

        tasksNeeded.add(0,maintainAirway);
        tasksNeeded.add(1,giveOxygen);
        tasksNeeded.add(2,ECG);
        tasksNeeded.add(3,IVaccess);
        tasksNeeded.add(4,cardiacConsult);
        tasksNeeded.add(5,fluidBolus);
        tasksNeeded.add(6,intubation);
        tasksNeeded.add(7,breathSounds);
        tasksNeeded.add(8,assessJugular);
        tasksNeeded.add(9,manualPressure);
        tasksNeeded.add(10,assessTissue);
        tasksNeeded.add(11,giveBlood);
        tasksNeeded.add(12,assesHiddenBleeding);
        tasksNeeded.add(13,CPR);
        tasksNeeded.add(14,hemodynamicStability);
        tasksNeeded.add(15,assesICP);
        tasksNeeded.add(16,elevateHOB);
        tasksNeeded.add(17,neuroConsult);

        Decomposition decomp = new Decomposition();
        /*decomp.selectTasksWithNumbers(tasksNeeded);
        decomp.performAlgorithm();
*/
        HashMap<String, Integer> tasksNeededMap = new HashMap<>();

        tasksNeededMap.put("airway",   maintainAirway);
        tasksNeededMap.put("oxygen",   giveOxygen);
        tasksNeededMap.put("ECG",      ECG);
        tasksNeededMap.put("IV",       IVaccess);
        tasksNeededMap.put("cardiac",  cardiacConsult);
        tasksNeededMap.put("fluidbolis",    fluidBolus);
        tasksNeededMap.put("intubate", intubation);
        tasksNeededMap.put("breath",   breathSounds);
        tasksNeededMap.put("jugular",  assessJugular);
        tasksNeededMap.put("pressure", manualPressure);
        tasksNeededMap.put("tissue",   assessTissue);
        tasksNeededMap.put("blood",    giveBlood);
        tasksNeededMap.put("bleeding", assesHiddenBleeding);
        tasksNeededMap.put("CPR",      CPR);
        tasksNeededMap.put("hemo",     hemodynamicStability);
        tasksNeededMap.put("ICP",      assesICP);
        tasksNeededMap.put("HOB",      elevateHOB);
        tasksNeededMap.put("neuro",    neuroConsult);

        decomp = new Decomposition();
        decomp.selectTasks(tasksNeededMap);
        decomp.performAlgorithm();

    }

    private HashMap<String, String> taskCodes;


    public Patient(){

        taskCodes = new HashMap<String,String>();

        taskCodes.put("airway",   "Maintain airway");
        taskCodes.put("oxygen",   "Give Oxygen");
        taskCodes.put("ECG",      "Get ECG");
        taskCodes.put("IV",       "Establish IV Access");
        taskCodes.put("cardiac",  "Get a cardiac consult");
        taskCodes.put("fluidbolis","Give fluid bolis");
        taskCodes.put("intubate", "Lift the chin, provide suction, and intubate");
        taskCodes.put("breath",   "Assess breathing");
        taskCodes.put("jugular",  "Assess jugular venous distention");
        taskCodes.put("pressure", "Apply manual pressure");
        taskCodes.put("tissue",   "Assess tissue perfusion");
        taskCodes.put("blood",    "Give blood");
        taskCodes.put("bleeding", "Assess for hidden source of bleeding");
        taskCodes.put("CPR",      "Perform CPR");
        taskCodes.put("hemo",     "Maintain hemodynamic stability");
        taskCodes.put("ICP",      "Assess signs of increased ICP");
        taskCodes.put("HOB",      "Elevate head of bed");
        taskCodes.put("neuro",    "Get a neuro consult");


    };

    public ArrayList<String> triage(int heartRate, int bloodPressure, int respRate, int glascow,  String response, String response2){


        int bleeding = 0;
        int airway = 0;
        int maintainAirway = 0;
        int giveOxygen = 0;
        int ECG = 0;
        int IVaccess = 0;
        int cardiacConsult = 0;
        int fluidBolus = 0;
        int intubation = 0;
        int breathSounds = 0;
        int assessJugular = 0;
        int manualPressure = 0;
        int assessTissue = 0;
        int giveBlood = 0;
        int assesHiddenBleeding = 0;
        int CPR = 0;
        int hemodynamicStability = 0;
        int assesICP = 0;
        int elevateHOB = 0;
        int neuroConsult = 0;


        if(heartRate == 0){
            //System.out.println("Perform CPR");
            CPR = 1;
        }

        else {

            if (heartRate > 100 || heartRate < 60) {
                maintainAirway = 1;
                giveOxygen = 1;
                ECG = 1;
                IVaccess = 1;
                cardiacConsult = 1;
            }
        }


        if(bloodPressure < 90){
            maintainAirway = 1;
            IVaccess = 1;
            fluidBolus = 1;
        }

        if(response.equals("yes")){
            airway = 1;
            intubation = 1;
        }

        if(respRate < 10 || respRate > 29){
            maintainAirway = 1;
            giveOxygen = 1;
            breathSounds = 1;
            assessJugular = 1;
        }

        if(response2.equals("yes")){
            bleeding = 1;
            maintainAirway = 1;
            manualPressure = 1;
            assessTissue = 1;
            IVaccess = 1;
            fluidBolus = 1;
            giveBlood = 1;
            assesHiddenBleeding = 1;
        }

        if(glascow < 9){
            maintainAirway = 1;
            hemodynamicStability = 1;
            assesICP = 1;
            elevateHOB = 1;
            neuroConsult = 1;
        }


        ArrayList<Integer> tasksNeeded = new ArrayList<>();

        tasksNeeded.add(0,maintainAirway);
        tasksNeeded.add(1,giveOxygen);
        tasksNeeded.add(2,ECG);
        tasksNeeded.add(3,IVaccess);
        tasksNeeded.add(4,cardiacConsult);
        tasksNeeded.add(5,fluidBolus);
        tasksNeeded.add(6,intubation);
        tasksNeeded.add(7,breathSounds);
        tasksNeeded.add(8,assessJugular);
        tasksNeeded.add(9,manualPressure);
        tasksNeeded.add(10,assessTissue);
        tasksNeeded.add(11,giveBlood);
        tasksNeeded.add(12,assesHiddenBleeding);
        tasksNeeded.add(13,CPR);
        tasksNeeded.add(14,hemodynamicStability);
        tasksNeeded.add(15,assesICP);
        tasksNeeded.add(16,elevateHOB);
        tasksNeeded.add(17,neuroConsult);

        HashMap<String, Integer> tasksNeededMap = new HashMap<>();

        tasksNeededMap.put("airway",   maintainAirway);
        tasksNeededMap.put("oxygen",   giveOxygen);
        tasksNeededMap.put("ECG",      ECG);
        tasksNeededMap.put("IV",       IVaccess);
        tasksNeededMap.put("cardiac",  cardiacConsult);
        tasksNeededMap.put("fluidbolis",    fluidBolus);
        tasksNeededMap.put("intubate", intubation);
        tasksNeededMap.put("breath",   breathSounds);
        tasksNeededMap.put("jugular",  assessJugular);
        tasksNeededMap.put("pressure", manualPressure);
        tasksNeededMap.put("tissue",   assessTissue);
        tasksNeededMap.put("blood",    giveBlood);
        tasksNeededMap.put("bleeding", assesHiddenBleeding);
        tasksNeededMap.put("CPR",      CPR);
        tasksNeededMap.put("hemo",     hemodynamicStability);
        tasksNeededMap.put("ICP",      assesICP);
        tasksNeededMap.put("HOB",      elevateHOB);
        tasksNeededMap.put("neuro",    neuroConsult);

        Decomposition decomp = new Decomposition();
        decomp.selectTasks(tasksNeededMap);
        ArrayList<String> orderedTasks = decomp.performAlgorithm();

        for(int i=0 ; i<orderedTasks.size() ; i++){
            orderedTasks.set(i, this.taskCodes.get(orderedTasks.get(i)));
        }

        return orderedTasks;
    }


}

