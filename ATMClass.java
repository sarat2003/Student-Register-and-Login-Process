package com.shivam.BankApplicationUsingExaception_22_Jan_25;

import java.util.Scanner;

class InsufficientFundsException extends Exception
{
    public InsufficientFundsException(String message)
    {
        super(message);
    }
}
class InvalidAmountException extends RuntimeException
{
    public InvalidAmountException(String message)
    {

        super(message);
    }
}
class AccountNotFoundException extends Exception
{
    public AccountNotFoundException(String message)
    {

        super(message);
    }
}
class LoanNotAllowedException extends RuntimeException
{
    public LoanNotAllowedException(String message)
    {

        super(message);
    }
}

interface Bank{
    void deposit(double amount) throws InvalidAmountException;
    void withdraw(double amount) throws InvalidAmountException, InsufficientFundsException;
    void transfer(BankAccount toAccount,double amount) throws InvalidAmountException, InsufficientFundsException, AccountNotFoundException;
    void applyForLoan(double amount) throws LoanNotAllowedException,InvalidAmountException;

    public double getBalance();
}
class BankAccount implements Bank{
    private long accountNumber;
    private double balance;

    public BankAccount(long accountNumber, double balance)
    {
        super();
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    @Override
    public void deposit(double amount) throws InvalidAmountException
    {
        try
        {
            if(amount<=0)
            {
                throw new InvalidAmountException("Please enter valid amount to deposit");
            }
            else
            {
                balance+=amount;
            }
        }
        catch (RuntimeException e)
        {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void withdraw(double amount) throws InvalidAmountException,InsufficientFundsException
    {
        try
        {
            if(amount>balance)
            {
                throw new InvalidAmountException("Invalid Amount");
            }
            else if(amount<=0)
            {
                throw new InvalidAmountException("Please enter valid amount");
            }
            else
            {
                balance-=amount;
            }
        }
        catch(RuntimeException e)
        {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void transfer(BankAccount acc,double amount) throws InsufficientFundsException,AccountNotFoundException,InvalidAmountException
    {
        try
        {
           if(amount > balance)
           {
               throw new InsufficientFundsException("Insufficient funds........");
           }
           else if(acc==null)
           {
               throw new AccountNotFoundException("Insufficient balance in you account to transfer");
           }
           else if(amount<=0)
           {
               throw new InvalidAmountException("Please enter valid amount...");
           }
           else
           {
               acc.balance+=amount;
               this.balance-=amount;
           }
        }
        catch(RuntimeException e)
        {
            System.out.println(e.getMessage());
        }
    }
    @Override
    public void applyForLoan(double amount) throws LoanNotAllowedException, InvalidAmountException
    {
        try
        {
            if(amount<=0)
            {
                throw new InvalidAmountException("Please enter valid Amount");
            }
            else if(amount>50000)
            {
                throw new LoanNotAllowedException("Loan amount exceeds the allowed limit");
            }
            balance+=amount;
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
    @Override
    public double getBalance(){
        return balance;
    }

}
class Customer
{
    private String name;
    private BankAccount account;

    public Customer(String name, BankAccount account)
    {
        this.name = name;
        this.account = account;
    }
    public BankAccount getAccount()
    {
        return account;
    }
}
public class ATMClass
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
//        System.out.println("Enter the customer name:");
//        String name = sc.next();
//
//        System.out.println("Account Number:");
//        Long accnum = sc.nextLong();

        BankAccount ba = new BankAccount(122334455,5000);
        BankAccount ba1 = new BankAccount(122334456,2000);
        Customer cust = new Customer("Shivam",ba);

        System.out.println("Select an option :");
        System.out.println("1. Deposit");
        System.out.println("2. Withdraw");
        System.out.println("3. Transfer");
        System.out.println("4. Loan Application");
        System.out.println("5. Check Balance");
        System.out.println("6. Exit");
        System.out.println("Enter your Option :");
        int opt = sc.nextInt();
        try
        {
            switch (opt)
            {
                case 1:
                    System.out.println("Enter deposit amount:");
                    double damt=sc.nextDouble();
                    cust.getAccount().deposit(damt);
                    System.out.println("The amount after deposit is:"+ ba.getBalance());
                    break;
                case 2:
                    System.out.println("Enter the withdraw amount:");
                    double wamt = sc.nextDouble();
                    ba.withdraw(wamt);
                    System.out.println("Balance after Withdraw:"+ba.getBalance());

                case 3:
                    System.out.println("Enter the transferAmount");
                    double tamt = sc.nextDouble();
                    ba.transfer(ba,tamt);
                    ba1.transfer(ba,tamt);
                    System.out.println(ba.getBalance());
                    System.out.println(ba1.getBalance());
                    break;

                case 4:
                    System.out.println("Enter the loan Application");
                    double la = sc.nextDouble();
                    ba.applyForLoan(la);
                    System.out.println("Loan should be approved, balance should be increase by:"+ba.getBalance());
                    break;
                case 5:
                    System.out.println("Enter the Check Balance:"+cust.getAccount().getBalance());
                    return;
            }

        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}
