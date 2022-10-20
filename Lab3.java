import java.awt.desktop.FilesEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class Lab3 {

    public static void main(String[] args) {

        int solutionOne = questionOne();
        if (solutionOne < 0)
            System.exit(1);
        System.out.println("There are " + solutionOne + " total number of Autonomous Systems.");

        int solutionTwo = questionTwo();
        System.out.println("The number of unique links are: " + solutionTwo);
    }//end main


    private static int questionOne() {

        try {

            FileReader reader = new FileReader("data/lab3Data.txt");
            Scanner fileParser = new Scanner(reader);
            HashMap<String,Boolean> countAS = new HashMap<>();
            fileParser.nextLine();

            while (fileParser.hasNextLine()) {

                String line = fileParser.nextLine();
                Scanner lineParser = new Scanner(line);
                lineParser.useDelimiter("\\|");
                String provider = lineParser.next();
                String customer = lineParser.next();

                if (countAS.get(provider) == null) {
                    countAS.put(provider,true);
                }//end if
                if(countAS.get(customer) == null) {
                    countAS.put(customer,true);
                }

            }//end while
            reader.close();
            return countAS.size();

        }//end try block
        catch (FileNotFoundException fnf) {
            System.out.println(fnf.getMessage());
        }
        catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }

        return -1;

    }//end questionOne

    private static int questionTwo() {
        try {
            FileReader reader = new FileReader("./data/lab3Data.txt");
            Scanner fileParser = new Scanner(reader);
            HashMap<String,Boolean> linkMap = new HashMap<>();
            fileParser.nextLine();
            while (fileParser.hasNextLine()) {
                String line = fileParser.nextLine();
                Scanner lineParser = new Scanner(line);
                lineParser.useDelimiter("\\|");
                while(lineParser.hasNext()) {

                    String provider = lineParser.next();
                    String customer = lineParser.next();

                    String link = provider + "-" + customer;
                    if (linkMap.get(link) == null)
                        linkMap.put(link,true);

                }//end while

            }//end while
            reader.close();
            return linkMap.size()/2;
        }
        catch (FileNotFoundException fnf) {
            System.out.println(fnf.getMessage());
        }
        catch (IOException ioe) {

            System.out.println(ioe.getMessage());

        }
        return -1;

    }//end question two

}//end class
