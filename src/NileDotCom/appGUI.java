package NileDotCom;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class appGUI extends JFrame {
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


        // Adding buttons to the south panel
        southPanel.add(controlsLabel);
        controlsLabel.setHorizontalAlignment(JLabel.CENTER);
        southPanel.add(blankButton);
        southPanel.add(processB);       southPanel.add(confirmB);
        southPanel.add(deleteB);        southPanel.add(finishB);
        southPanel.add(newB);           southPanel.add(exitB);
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
//            try
//            {
//
//            }

        }
    }

    private class ConfirmButtonHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            System.out.println("The Add Item To Cart Button Was Clicked...");
        }
    }

    private class DeleteButtonHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            System.out.println("The Delete Last Item Button Was Clicked...");
        }
    }

    private class FinishButtonHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            System.out.println("The Check Out Button Was Clicked...");
        }
    }

    private class NewButtonHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            System.out.println("The Empty Cart Button Was Clicked...");
        }
    }

    private class ExitButtonHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            System.out.println("The Exit Button Was Clicked...");
        }
    }
}
