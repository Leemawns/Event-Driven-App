package NileDotCom;

import javax.swing.*;
import java.io.*;
import java.util.Scanner;

public class FindItems {
    public static InventoryItem SearchForItemInInventoryFile(String itemID)
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
//        String searchItem = "RTX3452";

        try {
            inputFileReader = new FileReader(inputFile);
            inputBufferedReader = new BufferedReader(inputFileReader);

            System.out.println("Search Item Is: " + itemID);

            inventoryLine = inputBufferedReader.readLine();

            while(inventoryLine != null){
                sc = new Scanner(inventoryLine).useDelimiter("\\s*,\\s*");
                itemIDFromFile = sc.next();
                if(itemIDFromFile.equals(itemID)){
                    System.out.println("Found It!");
                    String id = itemIDFromFile;
                    String desc = sc.next();
                    boolean inStock = Boolean.parseBoolean(sc.next());
                    int quantity = Integer.parseInt(sc.next());
                    double price = Double.parseDouble(sc.next());

                    InventoryItem retItem = new InventoryItem(id, desc, inStock,quantity, price);
                    return retItem;
                }
                else
                {
                    inventoryLine = inputBufferedReader.readLine();
                }
            }
            System.out.println("Search Item Not In File!");
            JOptionPane.showMessageDialog(null, "item ID " + itemID + " not in file", "ERROR", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        catch(FileNotFoundException fileNotFound){
            JOptionPane.showMessageDialog(null, "Error: File not found", "ERROR", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        catch(IOException ioException){
            JOptionPane.showMessageDialog(null, "Error: Problem reading from file", "ERROR", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
}
