package NileDotCom;

import javax.swing.*;
import java.io.*;
import java.util.Scanner;

public class FindItems {
    public static void SearchForItemInInventoryFile()
    {
        File inputFile = new File("inventory.csv");
        FileReader inputFileReader = null;
        BufferedReader inputBufferedReader = null;
        Scanner sc = null;
        String inventoryLine; // this will be the incoming entry of the inventory
        String itemIDFromFile;
        boolean found = false;

//        String searchItem = "22345532";
//        String searchItem = "14";
//        String searchItem = "ryxx34";
        String searchItem = "RTX3452";


        try {
            inputFileReader = new FileReader(inputFile);
            inputBufferedReader = new BufferedReader(inputFileReader);

            System.out.println("Search Item Is: " + searchItem);

            inventoryLine = inputBufferedReader.readLine();

            while(inventoryLine != null){
                sc = new Scanner(inventoryLine).useDelimiter("\\s*,\\s*");
                itemIDFromFile = sc.next();
                if(itemIDFromFile.equals(searchItem)){
                    System.out.println("Found It!");
                    found = true;
                    break;
                }
                else
                {
                    inventoryLine = inputBufferedReader.readLine();
                }
            }
            if(!found){
                System.out.println("Search Item Not In File!");
            }
        }

        catch(FileNotFoundException fileNotFound){
            JOptionPane.showMessageDialog(null, "Error: File not found", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        catch(IOException ioException){
            JOptionPane.showMessageDialog(null, "Error: Problem reading from file", "ERROR", JOptionPane.ERROR_MESSAGE);
        }

    }
}
