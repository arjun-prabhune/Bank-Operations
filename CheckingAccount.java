public class CheckingAccount
{
    private static int checkingAccountNum = 5000001;
    String name;
    double balance;
    private int accountNumber;

    public CheckingAccount(String nname, double bbalance)
    {
        accountNumber = checkingAccountNum;
        checkingAccountNum++;
        name = nname;
        balance = bbalance;
    }


    public void deposit(double depositAmount)
    {
        balance+=depositAmount;
    }

    public void withdraw(double withdrawAmount)
    {
        balance-=withdrawAmount;
    }
    
    public int getAccountNumber()
    {
        return accountNumber;
    }

    public double getBalance()
    {
        return balance;
    }
}