package NileDotCom;/*  Name: Eric Lemons
    Course: CNT 4714 - Fall 2026
    Assignment title: Project 1 - An Event-driven Enterprise Simulation
    Date: Sunday September 13, 2026
 */

import javax.swing.*;

public class Main
{
    public static void main( String args[] )
    {
//        NiledotcomGUI gui = new NiledotcomGUI();
//        gui.setVisible(true);

        JFrame aNewStore = new appGUI();
        aNewStore.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        aNewStore.setVisible(true);

        FindItems.SearchForItemInInventoryFile();
    }
}
