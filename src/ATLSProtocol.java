import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by kenziemclouth on 11/14/17.
 */
public class ATLSProtocol {


    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.println("Enter name of file:");

        String filePath = input.next();

        //create new file using user given file path
        File inputFile = new File(filePath);

        //check to see if file exists
        if (inputFile.exists()) {
            try {


                //Create new BufferedReader object to read from input file
                BufferedReader in = new BufferedReader(new FileReader(inputFile));

                String line;

                //Create arraylist to hold contents of the input file
                ArrayList<ArrayList<String>> listOfLines = new ArrayList<>();

                //Read from input file until end of file
                while ((line = in.readLine()) != null) {

                    //convert current line in input file to an ArrayList of Strings split by commas
                    ArrayList<String> singleLine = new ArrayList<>(Arrays.asList(line.split(",")));
                    listOfLines.add(singleLine);

                    for (String chunk : singleLine) {
                        System.out.print(chunk);
                        System.out.print(" ");
                    }

                    System.out.println();

                }

                //close BufferedReader
                in.close();

                //catch exception
            } catch (IOException e) {
                System.err.println(e.toString());
            }

        }

        int[][] matrix =    {{1,0,0,0,0,0,1,0},
                            {0,1,1,0,1,0,0,0},
                            {0,0,1,1,0,0,0,0},
                            {0,0,0,1,0,0,0,0},
                            {0,0,0,1,1,0,1,0},
                            {0,1,1,1,0,1,0,1},
                            {0,0,0,0,0,0,1,0},
                            {1,0,1,0,1,0,0,1}};

        String[] nameMatrix =
                {"Task 1",
                "Task 2",
                "Task 3",
                "Task 4",
                "Task 5",
                "Task 6",
                "Task 7",
                "Task 8"};

        Triangularization triangle = new Triangularization(matrix, nameMatrix);

        //triangle.performAlgorithm();

        triangle = new Triangularization();
        triangle.performAlgorithm();


    }

}
