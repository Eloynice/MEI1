import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class NodeArray {
    Node[] array = new Node[1374];
    static class Node {
        int ug;
        int[] adj;
        float area;
        int[] presc;
        int[][] periods = new int[75][9];
    }

    public int[] returnInsertable(Scanner myReader){
        String data = myReader.nextLine();
        String[] str_split = data.split(",", 0);
        int size = str_split.length;
        int[] toInsert = new int [size];
        for(int i=0; i<size; i++) {
            toInsert[i] = Integer.parseInt(str_split[i]);
        }
        return toInsert;
    }

    public int[] returnPeriods(String data){
        String[] str_split = data.split(",", 0);
        int size = str_split.length;
        int[] toInsert = new int [size];
        for(int i=0; i<size; i++) {
            toInsert[i] = Integer.parseInt(str_split[i]);
        }
        return toInsert;
    }

    public NodeArray() {
        int index = 1;

        try {
            File myObj = new File("graph_init");
            Scanner myReaderAdj = new Scanner(myObj);

            File myObj2 = new File("ugs_init");
            Scanner myReaderPresc = new Scanner(myObj2);

            File myObj3 = new File("area_init");
            Scanner myReaderArea = new Scanner(myObj3);

            File myObj4 = new File("period_init");
            Scanner myReaderPeriod = new Scanner(myObj4);

            while (myReaderAdj.hasNextLine()) {
                Node toInsert = new Node();
                toInsert.adj = returnInsertable(myReaderAdj);
                toInsert.presc = returnInsertable(myReaderPresc);
                toInsert.ug = index;
                toInsert.area = Float.parseFloat(myReaderArea.nextLine());

                String data = myReaderPeriod.nextLine();
                String[] str_split = data.split("/", 0);
                int size = str_split.length;
                for(int i = 0; i < size; i++) {
                    int[] pArray = returnPeriods(str_split[i]);
                    toInsert.periods[i] = pArray;
                }



                this.array[index] = toInsert;
                index++;
            }
            myReaderAdj.close();
            myReaderPresc.close();
            myReaderArea.close();

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}