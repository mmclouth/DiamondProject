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

        if(heartRate > 100 || heartRate < 60){
            System.out.println("Perform necessary tasks");
            maintainAirway = true;
            giveOxygen = true;
            ECG = true;
            IVaccess = true;
            cardiacConsult = true;

        } else {
            System.out.println("healthy");

        }

        System.out.print("Enter blood pressure: ");
        String bloodPressure = input.next();

        System.out.println("Blood Pressure is "+ bloodPressure);


        String[] bloodPressureSplit = bloodPressure.split("/");

        System.out.println(bloodPressureSplit[0]);
        System.out.println(bloodPressureSplit[1]);



        System.out.print("Is patient excessively bleeding (20-40% blood loss)?");
        String response = input.next();


        if(response.equals("yes")){
            bleeding = true;
        }

        System.out.print("Is patient's airway blocked?");
        response = input.next();

        if(response.equals("yes")){
            airway = true;
        }

        int respRate;

        System.out.print("Enter respiratory rate: ");
        respRate = Integer.parseInt(input.next());

        System.out.println("Respiratory rate is "+ respRate);

        if(respRate < 10 || respRate > 29){
            System.out.println("Maintain Airway");
        } else {
            System.out.println("healthy");

        }




    }




}
