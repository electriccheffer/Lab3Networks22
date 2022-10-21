import java.awt.desktop.FilesEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicReferenceArray;

public class Lab3 {

    public static void main(String[] args) {

        int solutionOne = questionOne();
        if (solutionOne < 0)
            System.exit(1);
        System.out.println("There are " + solutionOne + " total number of Autonomous Systems.");

        int solutionTwo = questionTwo();
        System.out.println("The number of unique links are: " + solutionTwo);

        ArrayList<Integer> solutionThree = questionThree();
        System.out.println("The number of p2p relationsihps are: " + solutionThree.get(0));
        System.out.println("The number of c2p relationships are : " + solutionThree.get(1));

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

                String provider = lineParser.next();
                String customer = lineParser.next();

                String linkOne = provider + "-" + customer;
                String linkTwo = customer + "-" + provider;

                if (linkMap.get(linkOne) == null && linkMap.get(linkTwo) == null) {
                    linkMap.put(linkOne,true);
                }


            }//end while
            reader.close();
            return linkMap.size();
        }
        catch (FileNotFoundException fnf) {
            System.out.println(fnf.getMessage());
        }
        catch (IOException ioe) {

            System.out.println(ioe.getMessage());

        }
        return -1;

    }//end question two


    private static ArrayList<Integer> questionThree() {
        try {
            FileReader reader = new FileReader("./data/lab3Data.txt");
            Scanner fileParser = new Scanner(reader);
            fileParser.nextLine();
            ArrayList<Integer> countList = new ArrayList<>();
            HashMap<String,Boolean> p2p = new HashMap<>();
            HashMap<String,Boolean> c2p = new HashMap<>();
            countList.add(0);
            countList.add(0);

            while (fileParser.hasNextLine()) {

                String line = fileParser.nextLine();
                Scanner lineParser = new Scanner(line);
                lineParser.useDelimiter("\\|");
                String provider = lineParser.next();
                String customer = lineParser.next();
                String mainLink = provider + "-" + customer;
                String otherLink = customer + "-" + provider;
                String relationship = lineParser.next();

                switch (relationship) {

                    case "-1":
                        if (p2p.get(mainLink) == null && p2p.get(otherLink) == null) {
                            p2p.put(mainLink,true);
                            p2p.put(otherLink,true);
                            countList.set(0,countList.get(0) + 1);
                        }//end if
                        break;
                    case "0":
                        if (c2p.get(mainLink) == null && c2p.get(otherLink) == null) {
                            c2p.put(mainLink,true);
                            c2p.put(otherLink,true);
                            countList.set(1,countList.get(1) + 1);
                        }//end if
                        break;
                }//end switch

            }//end while
            reader.close();

            return countList;
        }//end try
        catch (FileNotFoundException fnf) {
            System.out.println(fnf.getMessage());
        }//end catch
        catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }//end catch
        return new ArrayList<>();
    }//end questionThree
}//end class
