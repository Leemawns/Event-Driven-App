package NileDotCom;

import javax.swing.*;
import java.awt.FlowLayout;

public class NiledotcomGUI extends JFrame{

    private JLabel cur_itemID_label;
    private JLabel cur_item_quant_label;
    private JLabel cur_item_details_label;
    private JLabel cur_subtotal;
    private int cur_item_num;
    private int total_items;

    public NiledotcomGUI()
    {
        super("Nile.com");
        setLayout(new FlowLayout());
        cur_item_num = 1;
        cur_itemID_label = new JLabel("Enter item ID for Item #" + cur_item_num);
        cur_item_quant_label = new JLabel("Enter quantity for Item #" + cur_item_num);
        cur_item_details_label = new JLabel("Details for Item #");
        cur_subtotal = new JLabel("Current Subtotal for " + total_items + " item(s):");

        add(cur_itemID_label);
    }


}
