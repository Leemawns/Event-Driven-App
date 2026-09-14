package NileDotCom;

public class InventoryItem {
    private String id;
    private String desc;
    private boolean inStock;
    private int quantity;
    private double price;

    public InventoryItem(String incID, String incDesc, boolean incInStock, int incQuantity, double incPrice)
    {
        id = incID;
        desc = incDesc;
        inStock = incInStock;
        quantity = incQuantity;
        price = incPrice;
    }

    public String getID()
    {
        return id;
    }

    public String getDesc()
    {
        return desc;
    }

    public boolean getInStock()
    {
        return inStock;
    }

    public int getQuantity()
    {
        return quantity;
    }

    public double getPrice()
    {
        return price;
    }
}
