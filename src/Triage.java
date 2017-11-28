import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by kenziemclouth on 11/15/17.
 */
public class Triage {

    public static void main(String[] args) {


        Scanner input = new Scanner(System.in);

        boolean bleeding = false;
        boolean airway = false;
        boolean maintainAirway = false;
        boolean giveOxygen = false;
        boolean ECG = false;
        boolean IVaccess = false;
        boolean cardiacConsult = false;
        boolean fluidBolus = false;
        boolean intubation = false;
        boolean breathSounds = false;
        boolean assessJugular = false;
        boolean manualPressure = false;
        boolean assessTissue = false;
        boolean giveBlood = false;
        boolean assesHiddenBleeding = false;
        boolean CPR = false;
        boolean hemodynamicStability = false;
        boolean assesICP = false;
        boolean elevateHOB = false;
        boolean neuroConsult = false;



        int heartRate;

        System.out.print("Enter heart rate: ");
        heartRate = Integer.parseInt(input.next());

        System.out.println("Heart rate is "+ heartRate);

        if(heartRate == 0){

            System.out.println("Perform CPR");
            CPR = true;
        }

        else {

            if (heartRate > 100 || heartRate < 60) {
                System.out.println("Perform necessary tasks");
                maintainAirway = true;
                giveOxygen = true;
                ECG = true;
                IVaccess = true;
                cardiacConsult = true;

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
            maintainAirway = true;
            IVaccess = true;
            fluidBolus = true;

        } else {
            System.out.println("healthy");

        }

        System.out.print("Is patient's airway blocked?");
        String response = input.next();

        if(response.equals("yes")){
            airway = true;
            intubation = true;
        }

        int respRate;

        System.out.print("Enter respiratory rate: ");
        respRate = Integer.parseInt(input.next());

        System.out.println("Respiratory rate is "+ respRate);

        if(respRate < 10 || respRate > 29){
            System.out.println("Perform Necesary Tasks");
            maintainAirway = true;
            giveOxygen = true;
            breathSounds = true;
            assessJugular = true;
        } else {
            System.out.println("healthy");
        }

        System.out.print("Is patient excessively bleeding (20-40% blood loss)?");
        String response2 = input.next();

        if(response2.equals("yes")){
            bleeding = true;
            maintainAirway = true;
            manualPressure = true;
            assessTissue = true;
            IVaccess = true;
            fluidBolus = true;
            giveBlood = true;
            assesHiddenBleeding = true;
        }

        int glascow;

        System.out.print("Enter glascow coma: ");
        glascow = Integer.parseInt(input.next());

        System.out.println("Glascow coma is "+ glascow);

        if(glascow < 9){
            System.out.println("Perform Necesary Tasks");
            maintainAirway = true;
            hemodynamicStability = true;
            assesICP = true;
            elevateHOB = true;
            neuroConsult = true;

        } else {
            System.out.println("healthy");
        }


    }




}
