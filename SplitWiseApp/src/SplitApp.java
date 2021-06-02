import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class AppExecutor {
    private static AppExecutor appExecutor; // singleton!
    // UserId to User Map (sort of the local cache for all users)
    Map<String, User> userMap;

    private AppExecutor() {
        this.userMap = new HashMap<String, User>();
    }

    public static AppExecutor getExecutor() {
        if (appExecutor != null) return appExecutor;

        appExecutor = new AppExecutor();
        return appExecutor;
    }

    void addUser(String userDetails) {
        User newUser = ParserUtil.parseUser(Integer.toString(userMap.size() + 1), userDetails);
        userMap.put(Integer.toString(userMap.size() + 1), newUser);
        System.out.println("Added user id:"+Integer.toString(userMap.size()));
    }

    void addExpense(String expenseDetails) throws IllegalArgumentException {
        // Parse and Validate the Expense.
        Expense expense = ParserUtil.parseExpense(expenseDetails);

        // Process the expense for updating user balances.
        ExpenseProcessor.processExpense(expense, userMap);
        System.out.println("Added Expence Name : "+expense.getExpenseName() +",Expence Amount : "+expense.getAmountPaid());
    }

    void showExpense(String userId) {
        User currentUser = userMap.get(userId);
        List<Expense> userExpenses = currentUser.getUserExpenses();
        Map<String, Double> usersToPayMap = currentUser.getUsersToPay();

        System.out.println(currentUser); // User Details

        System.out.println("User Expense Details:");
        if(userExpenses.size() == 0) System.out.println("No Expenses");

        for (Expense userExpense : userExpenses) {
            System.out.println(userExpense);
        }

        printUserBalances(userId, usersToPayMap);
    }

    void showBalance() {
        for (String userId : userMap.keySet()) {
            User currentUser = userMap.get(userId);

            System.out.println(currentUser); // User Details
            Map<String, Double> usersToPayMap = currentUser.getUsersToPay();

            printUserBalances(userId, usersToPayMap);
        }
    }

    void showData(String userId) {
        User user = userMap.get(userId);
        System.out.println(user); // user details
    }

    private void printUserBalances(String userId, Map<String, Double> usersToPayMap) {
        //System.out.println("User Balance Details:");
        boolean isDebtFree = true;
        for (String userToPay : usersToPayMap.keySet()) {
            if(usersToPayMap.get(userToPay) <= 0.0) continue; // Ignore zero or negative balance.

            isDebtFree = false;

            // Print only debt (this person ows the other person in the list):
            System.out.println("User Balance Details : "+userId + " owes " + userToPay + " " + usersToPayMap.get(userToPay));
        }

        if(isDebtFree || usersToPayMap.size() == 0) System.out.println("User Balance Details: No Balances (owes nothing)");
    }
}

public class SplitApp {
    public static void main(String[] args) {
        AppExecutor appExecutor = AppExecutor.getExecutor();

        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();

        // Note: No format checks are applied.
        while (command.compareTo("EXIT") != 0) {
            String[] tokens = command.split(" ");
            try {
                if (tokens[0].compareTo(Constants.ADD_USER_COMMAND) == 0)
                    appExecutor.addUser(command.substring(Constants.ADD_USER_COMMAND.length() + 1));
                else if (tokens[0].compareTo(Constants.ADD_EXPANCE_COMMAND) == 0)
                    appExecutor.addExpense(command.substring(Constants.ADD_EXPANCE_COMMAND.length() + 1));
                else if (tokens[0].compareTo(Constants.SHOW_USER_DATA) == 0)
                    appExecutor.showData(command.substring(Constants.SHOW_USER_DATA.length() + 1));
                else if (tokens[0].compareTo(Constants.SHOW_USER_BALANCES) == 0)
                    appExecutor.showBalance();
                else if (tokens[0].compareTo(Constants.SHOW_USER_EXPENSE) == 0)
                    appExecutor.showExpense(command.substring(Constants.SHOW_USER_EXPENSE.length() + 1));
                else
                    System.out.println("Unknown Command");
            } catch (IllegalArgumentException e) {
                System.out.println(e);
            }

            command = sc.nextLine();
        }
    }
}

