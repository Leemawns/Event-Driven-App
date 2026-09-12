package NileDotCom;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class appGUI extends JFrame {
    InventoryItem[] cartItems = new InventoryItem[MAX_ITEMS];
    InventoryItem currItem = null;
    int[] cartQuantities = new int[MAX_ITEMS];
    double[] cartEntryPrices = new double[MAX_ITEMS];
    int[] cartDiscounts = new int[MAX_ITEMS];
    static double entryPrice = 0;
    static double subtotal = 0;
    static int userItemQty = 0;

    private static final int WIDTH = 700;
    private static final int HEIGHT = 100;
    private static final int MAX_ITEMS = 5;


    private JLabel blankLabel, controlsLabel, idLabel, qtyLabel, itemLabel, cartLabel, totalLabel;
    private JButton blankButton, processB, confirmB, deleteB, finishB, newB, exitB;
    private JTextField blankTextField, blankTextFieldS, idTextField, qtyTextField, itemTextField, totalTextField;
    private JTextField[] cartLineArray = new JTextField[MAX_ITEMS];

    // declaring reference variables for each the event handlers associated with each
    // button the user may interact with.
    private ProcessButtonHandler procbHandler;
    private ConfirmButtonHandler confbHandler;
    private DeleteButtonHandler deletebHandler;
    private FinishButtonHandler finbHandler;
    private NewButtonHandler newbHandler;
    private ExitButtonHandler exitbHandler;

    static int itemCount = 0;

    static int discount = 0;


    public appGUI()
    {
        setTitle("Nile.com - FALL 2026");
        setSize(WIDTH, HEIGHT);


        // These blank elements are just for creating whitespace in the grid layouts
        blankButton = new JButton(" ");
        blankLabel = new JLabel("", SwingConstants.RIGHT);

        // Here are the JLabel object instantiations
        idLabel = new JLabel("Enter Item ID for Item #" + (itemCount+1) + ":", SwingConstants.RIGHT);
        qtyLabel = new JLabel("Enter Quanity for Item #" + (itemCount+1) + ":", SwingConstants.RIGHT);
        itemLabel = new JLabel("Details for Item #" + (itemCount+1) + ":", SwingConstants.RIGHT);
        totalLabel = new JLabel("Current Subtotal for " + itemCount + " item(s):", SwingConstants.RIGHT);
        controlsLabel = new JLabel(" USER CONTROLS ", SwingConstants.RIGHT);
        cartLabel = new JLabel("Your Shopping Cart is Currently Empty", SwingConstants.CENTER);

        // Here are the JTextField object instantiations
        blankTextField = new JTextField();
        blankTextFieldS = new JTextField();
        idTextField = new JTextField();
        qtyTextField = new JTextField();
        itemTextField = new JTextField();
        totalTextField = new JTextField();

        for(int i = 0; i < MAX_ITEMS; i++)
        {
            cartLineArray[i] = new JTextField();
        }



        //-------------------------------------------------------------
        // Instantiating buttons to and registering their handlers.
        processB = new JButton("Search For Item #" + (itemCount+1) + "To Cart");
        procbHandler = new ProcessButtonHandler();
        processB.addActionListener(procbHandler);

        confirmB = new JButton("Add Item #" + (itemCount+1) + "To Cart");
        confbHandler = new ConfirmButtonHandler();
        confirmB.addActionListener(confbHandler);

        deleteB = new JButton("Delete Last Item From Cart");
        deletebHandler = new DeleteButtonHandler();
        deleteB.addActionListener(deletebHandler);

        finishB = new JButton("Check Out");
        finbHandler = new FinishButtonHandler();
        finishB.addActionListener(finbHandler);

        newB = new JButton("Empty Cart - Start A New Order");
        newbHandler = new NewButtonHandler();
        newB.addActionListener(newbHandler);

        exitB = new JButton("Exit (Close App)");
        exitbHandler = new ExitButtonHandler();
        exitB.addActionListener(exitbHandler);

        // Setting the initial properties for buttons and fields
        confirmB.setEnabled(true); // disable until calculation complete
        deleteB.setEnabled(true);
        finishB.setEnabled(true); // disable until confirm is complete

        itemTextField.setEditable(false);
        totalTextField.setEditable(false);
        blankTextField.setEditable(false);
        blankTextField.setBackground(Color.DARK_GRAY);
        blankTextField.setVisible(false);

        blankButton.setBackground(Color.DARK_GRAY);
        blankButton.setVisible(false);

        blankTextFieldS.setEditable(false);
        blankTextFieldS.setBackground(Color.DARK_GRAY);
        blankTextFieldS.setVisible(true);


        // Cotainer to hold parts of the GUI
        Container pane = getContentPane();


        // Grid layout with #rows, #columns, horizontal space, and vertical space respectively
        GridLayout grid6by2 = new GridLayout(6, 2, 8, 4);
        GridLayout grid7by2 = new GridLayout(7,2,8,4);


        // Just making the panels to put into the north, center, and south parts of the pane container
        JPanel northPanel = new JPanel();
        JPanel centerPanel = new JPanel();
        JPanel southPanel = new JPanel();

        // Setting the layout of each panel to the grid layouts made previously
        northPanel.setLayout(grid6by2);
        centerPanel.setLayout(grid7by2);
        southPanel.setLayout(grid6by2);


        // Actually adding the panes to the container (GUI) here
        pane.add(northPanel, BorderLayout.NORTH);
        pane.add(centerPanel, BorderLayout.CENTER);
        pane.add(southPanel, BorderLayout.SOUTH);

        //differentiating the sections by color
        pane.setBackground(Color.DARK_GRAY);
        northPanel.setBackground(Color.BLUE);
        centerPanel.setBackground(Color.LIGHT_GRAY);
        southPanel.setBackground(Color.GREEN);

        // just so the frame is created within the center of the screen
        centerFrame(WIDTH, HEIGHT);

        // adding labels to each grid in each panel of the gui;
        northPanel.add(blankLabel);
        northPanel.add(blankTextField);
        northPanel.add(idLabel);
        northPanel.add(idTextField);
        northPanel.add(qtyLabel);
        northPanel.add(qtyTextField);
        northPanel.add(itemLabel);
        northPanel.add(itemTextField);
        northPanel.add(totalLabel);
        northPanel.add(totalTextField);

        totalLabel.setFont(new Font("Calibri", Font.BOLD,14));
        totalLabel.setForeground(new Color(173, 216, 230));

        northPanel.add(blankLabel);
        northPanel.add(blankTextField);

        centerPanel.add(cartLabel);
        centerPanel.add(cartLineArray[0]);
        centerPanel.add(cartLineArray[1]);
        centerPanel.add(cartLineArray[2]);
        centerPanel.add(cartLineArray[3]);
        centerPanel.add(cartLineArray[4]);

        // Adding buttons to the south panel
        southPanel.add(controlsLabel);
        controlsLabel.setHorizontalAlignment(JLabel.CENTER);
        southPanel.add(blankButton);
        southPanel.add(processB);       southPanel.add(confirmB);
        southPanel.add(deleteB);        southPanel.add(finishB);
        southPanel.add(newB);           southPanel.add(exitB);

        deleteB.setEnabled(false);
        confirmB.setEnabled(false);
        finishB.setEnabled(false);
    }

    public void centerFrame(int frameWidth, int frameHeight)
    {
        Toolkit aTk = Toolkit.getDefaultToolkit();
        Dimension screen =  aTk.getScreenSize();


        int xPositionOfFrame = (screen.width - frameWidth) / 2;
        int yPositionOfFrame = (screen.height - frameHeight) / 2;

        setBounds(xPositionOfFrame, yPositionOfFrame, frameWidth, frameHeight);
    }

    private class ProcessButtonHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {

            System.out.println("The Find Item Button Was Clicked...");
            try
            {
                String idS = idTextField.getText().trim();
                String qtyS = qtyTextField.getText().trim();
                userItemQty = Integer.parseInt(qtyS);

                currItem = FindItems.SearchForItemInInventoryFile(idS);
                if(currItem != null){
                    if(currItem.getInStock())
                    {
                        if(currItem.getQuantity() < userItemQty)
                        {
                            JOptionPane.showMessageDialog(null, "Insufficient stock. Only " + currItem.getQuantity() + " on hand. Please reduce the quantity.", "ERROR", JOptionPane.ERROR_MESSAGE);
                        }
                        else
                        {
                            //then good to proceed with displaying the details:
                            if(userItemQty < 5)
                            {
                                discount = 0;
                            }
                            else if(userItemQty < 10)
                            {
                                discount = 10;
                            }
                            else if(userItemQty < 15)
                            {
                                discount = 15;
                            }
                            else if(userItemQty >= 15)
                            {
                                discount = 20;
                            }
                            entryPrice = (currItem.getPrice() * (double)userItemQty * (1.0 - ((double)discount/100)));
                            itemTextField.setText(currItem.getID() + " " + currItem.getDesc() + " $" + String.format("%.2f", currItem.getPrice()) + " " + userItemQty + " " + discount + "% " + String.format("$%.2f",entryPrice));
                            itemLabel.setText("Details for Item #" + (itemCount+1) + ":");

                            processB.setEnabled(false);
                            confirmB.setEnabled(true);
                        }
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "Sorry... that item is out of stock, please try another item", "ERROR", JOptionPane.ERROR_MESSAGE);
                        idTextField.setText("");
                        qtyTextField.setText("");
                    }

                }
            }
            catch(NumberFormatException numEx)
            {
                JOptionPane.showMessageDialog(null, "You've entered an invalid number format", "ERROR", JOptionPane.ERROR_MESSAGE);
            }


        }
    }

    private class ConfirmButtonHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            System.out.println("The Add Item To Cart Button Was Clicked...");

            cartItems[itemCount] = currItem;
            cartQuantities[itemCount] = userItemQty;
            cartEntryPrices[itemCount] = entryPrice;
            cartDiscounts[itemCount] = discount;
            subtotal+=cartEntryPrices[itemCount];
            itemCount++;

            cartLabel.setText("Your Shopping Cart Currently Contains " + itemCount + " Item(s)");

            totalTextField.setText(String.format("$%.2f",subtotal));
            totalLabel.setText("Current Subtotal for " + itemCount + " item(s):");
            processB.setText("Search For Item #" + (itemCount+1));
            confirmB.setText("Add Item #" + (itemCount+1) + " To Cart");

            confirmB.setEnabled(false);
            processB.setEnabled(true);

            deleteB.setEnabled(true);
            finishB.setEnabled(true);


            cartLineArray[itemCount-1].setText("Item " + itemCount + " - SKU: " + cartItems[itemCount-1].getID() + ", Desc: " + cartItems[itemCount-1].getDesc() + ", Price Ea. $" + cartItems[itemCount-1].getPrice() + ", Qty: " + cartQuantities[itemCount-1] + ", Total: " + String.format("$%.2f",cartEntryPrices[itemCount-1]));
            idTextField.setText("");
            qtyTextField.setText("");

            idLabel.setText("Enter item ID for Item #" + (itemCount+1) + ":");
            qtyLabel.setText("Enter quantity for Item #" + (itemCount+1) + ":");
        }
    }

    private class DeleteButtonHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            System.out.println("The Delete Last Item Button Was Clicked...");

            itemCount--;

            subtotal -= cartEntryPrices[itemCount];

            idLabel.setText("Enter item ID for Item #" + (itemCount+1) + ":");
            qtyLabel.setText("Enter quantity for Item #" + (itemCount+1) + ":");
            totalLabel.setText("Current Subtotal for " + (itemCount) + " item(s):");
            cartLineArray[itemCount].setText("");

            processB.setText("Search for Item #" + (itemCount+1));
            confirmB.setText("Add Item #" + (itemCount+1) + " To Cart");

            itemTextField.setText("");

            totalTextField.setText(String.format("$%.2f",subtotal));

        }
    }

    private class FinishButtonHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            System.out.println("The Check Out Button Was Clicked...");

            ZonedDateTime now = ZonedDateTime.now();

            String transactionID = now.format(DateTimeFormatter.ofPattern("ddMMyyyyHHmmss"));

            String invoiceDateTime = now.format(DateTimeFormatter.ofPattern("MMMM dd, yyyy, h:mm:ss a z"));

            StringBuilder invoice = new StringBuilder();

            invoice.append("Date: " + invoiceDateTime + "\n");

            invoice.append("\nNumber of line items: " + itemCount + "\n");

            invoice.append("\nItem$# / ID / Title / Price / Qty / Disc % / Subtotal:\n\n");

            try
            {

                FileWriter transactionFile = new FileWriter("transactions.csv", true);

                for(int itemNum = 0; itemNum < itemCount; itemNum++)
                {
                    InventoryItem cur = cartItems[itemNum];
                    transactionFile.write(transactionID + ", " + cur.getID() + ", " + cur.getDesc() + ", " + cur.getPrice() + ", " + cartQuantities[itemNum] + ", " + ((double)cartDiscounts[itemNum] / 100) + ", " + String.format("$%.2f",cartEntryPrices[itemNum]) + ", " + invoiceDateTime + "\n");
                    invoice.append((itemNum+1) + ". " + cur.getID() + " " + String.format("$%.2f",cur.getPrice()) + " " + cartQuantities[itemNum] + " " + ((double)cartDiscounts[itemNum] / 100) + "% " + cartEntryPrices[itemNum] + "\n");
                }

                invoice.append("\n\n" + String.format("$%.2f", subtotal) + "\n");

                invoice.append("\nTax rate:\t" + "6%\n");

                double taxAmount = subtotal*0.06;
                double total = subtotal*1.06;

                invoice.append("\nTax amount:\t" + String.format("$%.2f", taxAmount) + "\n");

                invoice.append("\nORDER TOTAL:\t" + String.format("$%.2f",total) + "\n");

                invoice.append("\nThanks for shopping at Nile Dot Com!");

                JOptionPane.showMessageDialog(null, invoice.toString(), "Nile Dot Com - FINAL INVOICE", JOptionPane.INFORMATION_MESSAGE);

                transactionFile.close();
            }
            catch(IOException ioE)
            {
                JOptionPane.showMessageDialog(null, "Reached an IOException when attempting to open transactions.csv", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private class NewButtonHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            System.out.println("The Empty Cart Button Was Clicked...");

            resetApp();
        }
    }

    private class ExitButtonHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            System.out.println("The Exit Button Was Clicked...");

            System.exit(0);
        }
    }

    private void resetApp()
    {
        currItem = null;
        entryPrice = 0;
        subtotal = 0;
        userItemQty = 0;
        discount = 0;
        itemCount = 0;


        idLabel.setText("Enter Item ID for Item #" + (itemCount+1) + ":");
        qtyLabel.setText("Enter Quanity for Item #" + (itemCount+1) + ":");
        itemLabel.setText("Details for Item #" + (itemCount+1) + ":");
        totalLabel.setText("Current Subtotal for " + itemCount + " item(s):");
        cartLabel.setText("Your Shopping Cart is Currently Empty");

        idTextField.setText("");
        qtyTextField.setText("");
        itemTextField.setText("");
        totalTextField.setText("");

        for(int i = 0; i < MAX_ITEMS; i++)
        {
            cartLineArray[i].setText("");
        }

        processB.setText("Search for Item #" + (itemCount+1));
        confirmB.setText("Add Item #" + (itemCount+1) + " To Cart");

        processB.setEnabled(true);
        deleteB.setEnabled(false);
        confirmB.setEnabled(false);
        finishB.setEnabled(false);

    }
}
