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

    }//end main


    public static int questionOne() {

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

}//end class
