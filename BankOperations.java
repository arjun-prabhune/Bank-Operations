import java.util.Scanner;
public class BankOperations
{
    public static void main(String[] args)
    {
        int customerID = 1000000;
        CheckingAccount[] checkingAccounts = new CheckingAccount[25];
        Customer[] customers = new Customer[10];
        Scanner scanner = new Scanner(System.in);
        String[] inputFirstWord;
        String[] amountsArr;
        boolean flag = true;

        int customersCount = 0;
        int checkingAccountsCount = 0;
        while(flag) {

            inputFirstWord = scanner.nextLine().split(" ");


            if(inputFirstWord[0].equals("new")) //for function new
            {
                int accounts = Integer.parseInt(inputFirstWord[1]);//get the account amounts
                if(accounts > 5)
                {
                    System.out.println("MAX 5 accounts per customer!");
                    break;
                }
                String name = inputFirstWord[2] + " " + inputFirstWord[3]; // get the name of the customer
                Customer customer = new Customer(name, accounts, customerID); // creating a customer object
                customers[customersCount] = customer; //adding customer object to the array of customers
                customerID++; //incrementing the customer ID
                customersCount++; //incrementing the customer count
                System.out.println("Customer ID: " + customerID); //printing out the customer ID
                amountsArr = scanner.nextLine().split(" ");


                for(int i = 0; i < accounts; i++) // inputting the totals for each account
                {

                    double accAmount = Double.parseDouble(amountsArr[i + (amountsArr.length - accounts)]); //getting the amount per account
                    CheckingAccount checkingAccount = new CheckingAccount(name, accAmount);// creating a new account with total that is inputted
                    checkingAccounts[checkingAccountsCount] = checkingAccount;
                    checkingAccountsCount++;
                }

                for(int a = checkingAccountsCount - accounts; a < checkingAccountsCount; a++) // gets all of the account IDS
                {
                    System.out.println("Account ID: " + checkingAccounts[a].getAccountNumber());
                }

            }

            if(inputFirstWord[0].equals("deposit"))
            {

                int accountNum = Integer.parseInt(inputFirstWord[1]); //get accountNum to add to
                double depositAmount = Double.parseDouble(inputFirstWord[2]); //get the amount to be deposited
                for(int i = 0; i < checkingAccountsCount; i++) //looks through the checking Accounts array
                {
                    if(checkingAccounts[i].getAccountNumber() == accountNum) // looks to see if ID is matched
                    {
                        checkingAccounts[i].deposit(depositAmount); // deposits money to that ID
                        System.out.printf("New balance: %.2f\n", checkingAccounts[i].getBalance()); // prints the balance
                    }
                }

            }

            if(inputFirstWord[0].equals("withdraw"))
            {
                int accountNum = Integer.parseInt(inputFirstWord[1]); // get accountNum to add to
                double withdrawAmount = Double.parseDouble(inputFirstWord[2]); // get the amount to be withdrawn
                for(int i = 0; i < checkingAccountsCount; i++) // loops through checking account array
                {
                    if(checkingAccounts[i].getAccountNumber() == accountNum) //if account is a match
                    {
                        if(checkingAccounts[i].getBalance() >= withdrawAmount) // if there is more balance than requested
                        {
                            checkingAccounts[i].withdraw(withdrawAmount); //process withdrawal
                            System.out.printf("New balance: %.2f\n", checkingAccounts[i].getBalance()); //print withdrawal
                        }
                        else
                            System.out.println("Withdrawal rejected to avoid negative balance."); //otherwise print error
                    }
                }
            }

            if(inputFirstWord[0].equals("add")) {
                int accountsToAdd = Integer.parseInt(inputFirstWord[1]); //get the number of accounts to add
                if (inputFirstWord[2].charAt(0) == '1')  // if an int is detected
                {
                    int custID = Integer.parseInt(inputFirstWord[2]); //get the customer id
                    for (int i = 0; i < customersCount; i++)  //loop through customer array
                    {

                        if (customers[i].getCustomerID() == custID)  //if ID is found
                        {
                            double balance = scanner.nextDouble(); //input the balance that user wants
                            if(customers[i].getNumAccounts() + accountsToAdd > 5) //check to see if his accounts remain less than 5
                                System.out.println("Error: Customer already has " + customers[i].getNumAccounts() + " accounts."); //if more than 5, print accounts
                            for (int j = 0; j < accountsToAdd; j++) //loop
                            {
                                CheckingAccount newCheckingAccount = new CheckingAccount(customers[i].getName(), balance); //create a new checking account
                                checkingAccounts[checkingAccountsCount+1] = newCheckingAccount; // adding checking account to the array
                                System.out.println("Account ID: " + newCheckingAccount.getAccountNumber());
                            }
                        }
                    }

                }
                else
                {
                    String name = inputFirstWord[2] + " " + inputFirstWord[3]; // if they decide to add by name instead
                    for (int i = 0; i < customersCount; i++) // loop to find the customer
                    {
                        if (customers[i].getName().equals(name))  //once you find the customer
                        {
                            if (customers[i].getNumAccounts() + accountsToAdd <= 5) //check to see if accounts is less than 5
                            {
                                double balance = scanner.nextDouble(); // get the balance
                                for (int j = 0; j < accountsToAdd; j++) //loop to create the desired accounts
                                {
                                    CheckingAccount newCheckingAccount = new CheckingAccount(name, balance);
                                    checkingAccounts[checkingAccountsCount+1] = newCheckingAccount;
                                    System.out.println("Account ID: " + newCheckingAccount.getAccountNumber());
                                }
                            }
                            else
                                System.out.println("Error: Customer already has " + customers[i].getNumAccounts() + " accounts.");
                        }
                    }
                }
            }

            if(inputFirstWord[0].equals("close"))
            {
                flag = false;
                //break;
            }

        }


    }
}