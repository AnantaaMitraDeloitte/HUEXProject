import java.util.HashMap;
import java.util.Map;

class Constants {
    static final String ADD_USER_COMMAND = "Add_User";
    static final String ADD_EXPANCE_COMMAND = "Add_Expense";
    static final String SHOW_USER_DATA = "Show_User_Data";
    static final String SHOW_USER_BALANCES = "Show_All_User_Balance";
    static final String SHOW_USER_EXPENSE = "Show_User_Expense";
    static final double EPS = 0.0001;
}

class ParserUtil {
    public static User parseUser(String userId, String userDetails) {
        String[] userDetailTokenized = userDetails.split(" ");
        return new User(userId, userDetailTokenized[0], userDetailTokenized[1], userDetailTokenized[2]);
    }

    public static Expense parseExpense(String expenseDetails) throws IllegalArgumentException {
        String[] expenseDetailsTokens = expenseDetails.split(" ");

        String expenseName = expenseDetailsTokens[0];
        Double totalAmount = Double.parseDouble(expenseDetailsTokens[1]);
        String userPaidId = expenseDetailsTokens[2];
        String createById = expenseDetailsTokens[3];
        int totalUsers = Integer.parseInt(expenseDetailsTokens[4]);

        String expenseType = expenseDetailsTokens[5].toUpperCase();

        Map<String, Double> userShareMap = new HashMap<>();

        int curUser = 0;
        switch (ExpenseType.valueOf(expenseType)) {
            case EQUAL:
                while (curUser < totalUsers) {
                    userShareMap.put(expenseDetailsTokens[curUser + 6], 0.0);
                    curUser++;
                }

                break;
            case EXACT:
                Double curUsersAmountSum = 0.0;
                while (curUser < totalUsers) {
                    userShareMap.put(expenseDetailsTokens[curUser + 6],
                            Double.parseDouble(expenseDetailsTokens[curUser + totalUsers + 6]));
                    curUsersAmountSum += Double.parseDouble(expenseDetailsTokens[curUser + totalUsers + 6]);
                    curUser++;

                }

                // Validation check:
                if (totalAmount - curUsersAmountSum > Constants.EPS)
                    throw new IllegalArgumentException("Validation Check Failed");

                break;
            case PERCENT:
                Double curPercentageSum = 0.0;
                while (curUser < totalUsers) {
                    userShareMap.put(expenseDetailsTokens[curUser + 6],
                            Double.parseDouble(expenseDetailsTokens[curUser + totalUsers + 6]));
                    curPercentageSum += Double.parseDouble(expenseDetailsTokens[curUser + totalUsers + 6]);
                    curUser++;
                }

                // Validation check:
                if (100.00 - curPercentageSum > Constants.EPS)
                    throw new IllegalArgumentException("Validation Check Failed");

                break;
        }

        return new Expense(expenseName, createById, userPaidId,
                totalUsers, ExpenseType.valueOf(expenseType), totalAmount, userShareMap);
    }
}