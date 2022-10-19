import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Lab3 {

    public static void main(String[] args) {

    }//end main


    public static int questionOne() {

        try {

            FileReader reader = new FileReader("data/lab3Data.txt");
            Scanner fileParser = new Scanner(reader);
            while (fileParser.hasNextLine()) {

                String line = fileParser.nextLine();
                Scanner lineParser = new Scanner(line);
                lineParser.useDelimiter("|");
                String provider = lineParser.next();
                String customer = lineParser.next();


            }//end while


        }
        catch (FileNotFoundException fnf) {

            System.out.println(fnf.getMessage());

        }

        return 1;

    }//end questionOne

}//end class
