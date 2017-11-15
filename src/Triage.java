import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by kenziemclouth on 11/15/17.
 */
public class Triage {

    public static void main(String[] args) {


        Scanner input = new Scanner(System.in);


        int heartRate;

        System.out.print("Enter heart rate: ");
        heartRate = Integer.parseInt(input.next());

        System.out.println("Heart rate is "+ heartRate);

        if(heartRate > 400){
            System.out.println("You're probably on cocaine.");
        } else {
            System.out.println("healthy");

        }

        System.out.print("Enter blood pressure: ");
        String bloodPressure = input.next();

        System.out.println("Heart rate is "+ bloodPressure);


        String[] bloodPressureSplit = bloodPressure.split("/");

        System.out.println(bloodPressureSplit[0]);
        System.out.println(bloodPressureSplit[1]);

        System.out.print("Is patient bleeding?");
        String response = input.next();
        boolean bleeding = false;

        if(response.equals("yes")){
            bleeding = true;
        }


    }




}
