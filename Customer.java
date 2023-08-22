public class Customer {

    private String name;
    private int numAccounts;
    private int CustomerID;
    public Customer(String nname, int nnumAccounts, int CCustomerID)
    {
        name = nname;
        numAccounts = nnumAccounts;
        CustomerID = CCustomerID;
    }

    public String getName()
    {
        return name;
    }

    public int getNumAccounts()
    {
        return numAccounts;
    }

    public int getCustomerID()
    {
        return CustomerID;
    }
}
