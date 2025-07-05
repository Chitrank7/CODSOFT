import java.util.Scanner;

// Class to represent the user's bank account
class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println(" Amount deposited: ₹" + amount);
        } else {
            System.out.println(" Invalid deposit amount.");
        }
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println(" Invalid withdrawal amount.");
        } else if (amount > balance) {
            System.out.println(" Insufficient balance.");
        } else {
            balance -= amount;
            System.out.println(" Amount withdrawn: ₹" + amount);
        }
    }

    public void checkBalance() {
        System.out.println(" Current Balance: ₹" + balance);
    }
}

// Class to represent the ATM
class ATM {
    private BankAccount account;
    private Scanner sc;

    public ATM(BankAccount account) {
        this.account = account;
        this.sc = new Scanner(System.in);
    }

    public void start() {
        System.out.println(" Welcome to the ATM!");

        while (true) {
            System.out.println("\nSelect an option:");
            System.out.println("1. Withdraw");
            System.out.println("2. Deposit");
            System.out.println("3. Check Balance");
            System.out.println("4. Exit");
            System.out.print("Enter your choice (1-4): ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    handleWithdraw();
                    break;
                case 2:
                    handleDeposit();
                    break;
                case 3:
                    account.checkBalance();
                    break;
                case 4:
                    System.out.println(" Thank you for using the ATM. Goodbye!");
                    return;
                default:
                    System.out.println(" Invalid choice. Please try again.");
            }
        }
    }

    private void handleWithdraw() {
        System.out.print("Enter amount to withdraw: ₹");
        double amount = sc.nextDouble();
        account.withdraw(amount);
    }

    private void handleDeposit() {
        System.out.print("Enter amount to deposit: ₹");
        double amount = sc.nextDouble();
        account.deposit(amount);
    }
}

// Main class to run the ATM interface
public class Task3_ATMInterface {
    public static void main(String[] args) {
        BankAccount userAccount = new BankAccount(10000.0); // initial balance ₹10,000
        ATM atm = new ATM(userAccount);
        atm.start();
    }
}
