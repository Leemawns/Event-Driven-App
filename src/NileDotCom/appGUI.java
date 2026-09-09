package NileDotCom;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class appGUI extends JFrame {
    private static final int WIDTH = 700;
    private static final int HEIGHT = 100;

    private JLabel blankLabel, controlsLabel;
    private JButton blankButton, processB, confirmB, deleteB, finishB, newB, exitB;

    private ProcessButtonHandler procBHandler;
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

        blankButton = new JButton(" ");
        blankLabel = new JLabel("", SwingConstants.RIGHT);

        controlsLabel = new JLabel(" USER CONTROLS ", SwingConstants.RIGHT);

        processB = new JButton("Search For Item #" + (itemCount+1) + "To Cart");
        procBHandler = new ProcessButtonHandler();
        processB.addActionListener(procBHandler);

        Container pane = getContentPane();

        GridLayout grid6by2 = new GridLayout(6, 2, 8, 4);
        GridLayout grid7by2 = new GridLayout(7,2,8,4);

        JPanel northPanel = new JPanel();
        JPanel centerPanel = new JPanel();
        JPanel southPanel = new JPanel();

        northPanel.setLayout(grid6by2);
        centerPanel.setLayout(grid7by2);
        southPanel.setLayout(grid6by2);

        pane.add(northPanel, BorderLayout.NORTH);
        pane.add(centerPanel, BorderLayout.CENTER);
        pane.add(southPanel, BorderLayout.SOUTH);

        pane.setBackground(Color.DARK_GRAY);
        northPanel.setBackground(Color.BLUE);
        centerPanel.setBackground(Color.LIGHT_GRAY);
        southPanel.setBackground(Color.GREEN);

        centerFrame(WIDTH, HEIGHT);
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
