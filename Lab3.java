import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
class Node implements Comparable<Node> {

    String name;
    int links;
    public Node(String name, int links) {
        this.name = name;
        this.links = links;
    }

    public int compareTo(Node otherNode) {

        if(this.links < otherNode.links) {
            return 1;
        }
        else if (this.links > otherNode.links) {
            return -1;
        }
        else
            return 0;
    }//end if

}

class NodeComparator implements Comparator<Node> {


    @Override
    public int compare(Node node, Node t1) {
            return node.compareTo(t1);
    }
}
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

        ArrayList<String> solutionFour = questionFour();
        System.out.println("Top ten systms are: ");
        for (int i = 0; i < 10; i++) {
            System.out.println(solutionFour.get(i));
        }//end for

        ArrayList<String> solutionFive = questionFive();
        System.out.println("Top ten Provider systems are: ");
        for (String nodeName : solutionFive) {
            System.out.println(nodeName);
        }//end for

        ArrayList<String> solutionSix = questionSix();
        System.out.println("Top ten Peer-To-Peer Systems are:");
        for (String nodName: solutionSix) {
            System.out.println(nodName);
        }//end for

        ArrayList<String> solutionSeven = questionSeven();
        System.out.println("The number of tier one providers are: ");
        for (String nodeName : solutionSeven) {
            System.out.println(nodeName);
        }//end for

        ArrayList<String> solutionEight = questionEight();
        System.out.println("The number of stub ASes are: ");
        for (String stub: solutionEight) {
            System.out.println(stub);
        }
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


    private static ArrayList<String> questionFour() {

        ArrayList<String> topTenNodes = new ArrayList<>();
        HashMap<String,Integer> countMap = new HashMap<>();

        try {

                FileReader reader = new FileReader("./data/lab3Data.txt");
                Scanner fileParser = new Scanner(reader);
                fileParser.nextLine();
                while (fileParser.hasNextLine()) {

                    String line = fileParser.nextLine();
                    Scanner lineParser = new Scanner(line);
                    lineParser.useDelimiter("\\|");
                    String asOne = lineParser.next();
                    String asTwo = lineParser.next();
                    if (countMap.get(asOne) == null) {
                        countMap.put(asOne,1);
                    }//end if
                    else {
                        countMap.put(asOne,countMap.get(asOne) + 1);

                    }
                    if (countMap.get(asTwo) == null) {
                        countMap.put(asTwo,1);
                    } else if (countMap.get(asTwo) != null) {
                        countMap.put(asTwo,countMap.get(asTwo) + 1);
                    }


                }//end while

                Set<String> keys = countMap.keySet();
                PriorityQueue<Node> sortQueue = new PriorityQueue<>(new NodeComparator());

                for (String key: keys) {
                    Node localNode = new Node(key,countMap.get(key));
                    sortQueue.add(localNode);
                }//end for

                for (int i = 0; i < 10 ; i++) {
                    Node localNode = sortQueue.remove();
                    topTenNodes.add(localNode.name);
                }
                reader.close();
                return topTenNodes;
            }//end try
            catch (FileNotFoundException fnf) {

                System.out.println(fnf.getMessage());

            }//end catch
            catch (IOException ioe) {

                System.out.println(ioe.getMessage());

            }
            catch (NullPointerException npe) {

                System.out.println(npe.getMessage());

            }//end catch
            return topTenNodes;
    }//end questionFour


    public static ArrayList<String> questionFive() {

            ArrayList<String> topTen = new ArrayList<>();
            try {
                FileReader reader = new FileReader("./data/lab3Data.txt");
                Scanner fileParser = new Scanner(reader);
                fileParser.nextLine();
                HashMap<String,Integer> countMap = new HashMap<>();

                while(fileParser.hasNextLine()) {

                    String line = fileParser.nextLine();

                    Scanner lineParser = new Scanner(line);
                    lineParser.useDelimiter("\\|");

                    String provider = lineParser.next();
                    lineParser.next();
                    String systemType = lineParser.next();
                    if (systemType.equals("-1")) {

                        if (countMap.get(provider) == null)
                            countMap.put(provider,1);
                        else
                            countMap.put(provider,countMap.get(provider) + 1);

                    }//end if
                    else
                        continue;

                }//end while

                Set<String> keySet = countMap.keySet();
                PriorityQueue<Node> topTenQueue = new PriorityQueue<>(new NodeComparator());
                for (String key: keySet) {
                    Node tmpNode = new Node(key,countMap.get(key));
                    topTenQueue.add(tmpNode);
                }//end for

                for (int i = 0; i < 10; i++) {
                    topTen.add(topTenQueue.remove().name);
                }//end for

                reader.close();
                return topTen;
            } //end try
            catch (FileNotFoundException fnf) {
                System.out.println(fnf.getMessage());
            }
            catch (IOException ioe) {
                System.out.println(ioe.getMessage());
            }

            return topTen;

    } //end questionFive

    public static ArrayList<String> questionSix() {

        ArrayList<String> topTen = new ArrayList<>();
        HashMap<String,Integer> countMap = new HashMap<>();
        PriorityQueue<Node> topTenQueue = new PriorityQueue<>(new NodeComparator());

        try {

            FileReader reader = new FileReader("./data/lab3Data.txt");
            Scanner fileParser = new Scanner(reader);
            fileParser.nextLine();

            while (fileParser.hasNextLine()) {

                String line = fileParser.nextLine();
                Scanner lineParser = new Scanner(line);
                lineParser.useDelimiter("\\|");

                String peerOne = lineParser.next();
                String peerTwo = lineParser.next();
                String relationship = lineParser.next();

                if (relationship.equals("0")) {

                    if (countMap.get(peerOne) == null)
                        countMap.put(peerOne,1);
                    else
                        countMap.put(peerOne,countMap.get(peerOne) + 1);
                    if (countMap.get(peerTwo) == null)
                        countMap.put(peerTwo,1);
                    else
                        countMap.put(peerTwo,countMap.get(peerTwo) + 1);

                }//end if

            }//end while

            Set<String> keySet = countMap.keySet();
            for (String key : keySet) {
                topTenQueue.add(new Node(key,countMap.get(key)));
            }//end for

            for (int i = 0; i < 10; i++) {
                topTen.add(topTenQueue.remove().name);
            }//end for

            reader.close();
            return topTen;

        }//end try
        catch (FileNotFoundException fnf) {

            System.out.println(fnf.getMessage());

        }//end catch
        catch (IOException ioe) {

            System.out.println(ioe.getMessage());

        }//end catch

        return topTen;
    }//end questionSix


    public static ArrayList<String> questionSeven() {

        HashMap<String,Boolean> nodeMap = new HashMap<>();

        try {

            FileReader reader = new FileReader("./data/lab3Data.txt");
            Scanner fileParser = new Scanner(reader);
            fileParser.nextLine();
            while (fileParser.hasNextLine()) {

                String line = fileParser.nextLine();
                Scanner lineParser = new Scanner(line);
                lineParser.useDelimiter("\\|");

                String asOne = lineParser.next();
                String asTwo = lineParser.next();
                String relationship = lineParser.next();

                if (nodeMap.get(asOne) == null)
                    nodeMap.put(asOne,true);
                if (nodeMap.get(asTwo) == null)
                    nodeMap.put(asTwo,true);

            }//end while
            reader.close();

            FileReader secondRead = new FileReader("./data/lab3Data.txt");
            Scanner fileParserTwo = new Scanner(secondRead);
            fileParserTwo.nextLine();
            while (fileParserTwo.hasNextLine()) {

                String line = fileParserTwo.nextLine();
                Scanner lineParser = new Scanner(line);
                lineParser.useDelimiter("\\|");

                String asOne = lineParser.next();
                String asTwo = lineParser.next();
                String relationship = lineParser.next();

                if(relationship.equals("-1") && nodeMap.get(asTwo) != null)
                    nodeMap.remove(asTwo);

            }//end while

            secondRead.close();

            ArrayList<String> tierOneSystems = new ArrayList<>(nodeMap.keySet());

            return tierOneSystems;

        }//end try
        catch (FileNotFoundException fnf) {

            System.out.println(fnf.getMessage());

        }
        catch (IOException ioe) {

            System.out.println(ioe.getMessage());

        }

        return new ArrayList<String>();

    }//end questionSeven


    public static ArrayList<String> questionEight() {

        HashMap<String,Boolean> nodeMap = new HashMap<>();

        try {

            FileReader reader = new FileReader("./data/lab3Data.txt");
            Scanner fileParser = new Scanner(reader);
            fileParser.nextLine();

            while(fileParser.hasNextLine()) {

                String line = fileParser.nextLine();
                Scanner lineParser = new Scanner(line);
                lineParser.useDelimiter("\\|");

                String asOne = lineParser.next();
                String asTwo = lineParser.next();
                if (nodeMap.get(asOne) == null)
                    nodeMap.put(asOne,true);
                if (nodeMap.get(asTwo) == null)
                    nodeMap.put(asTwo,true);

            }//end while

            reader.close();

            reader = new FileReader("./data/lab3Data.txt");
            fileParser = new Scanner(reader);
            fileParser.nextLine();
            while (fileParser.hasNextLine()) {

                String line = fileParser.nextLine();
                Scanner lineParser = new Scanner(line);
                lineParser.useDelimiter("\\|");
                String asOne = lineParser.next();
                lineParser.next();
                String relationship = lineParser.next();

                if (nodeMap.get(asOne) != null && relationship.equals("-1"))
                    nodeMap.remove(asOne);

            }//end while

            ArrayList<String> stubs = new ArrayList<>(nodeMap.keySet());
            return stubs;

        }//end try
        catch (FileNotFoundException fnf) {
            System.out.println(fnf.getMessage());
        }//end catch
        catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }

        return new ArrayList<String>();

    }//end questionEight

}//end class
