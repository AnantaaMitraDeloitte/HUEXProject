import java.util.Map;

enum ExpenseType {
    EXACT,
    EQUAL,
    PERCENT
}

public class Expense {
    private String expenseName;
    private String userCreated;
    private String userPaid;

    private int totalUsers;
    private ExpenseType expenseType;

    private Double amountPaid;

    // userId to share map for users taking part in the event.
    // Note: For Equal share, the share is not required and is set as 0.0
    private Map<String, Double> userShareMap;

    public Expense(String expenseName, String userCreated, String userPaid,
                   int totalUsers, ExpenseType expenseType, Double amountPaid, Map<String, Double> userShareMap) {
        this.expenseName = expenseName;
        this.userCreated = userCreated;
        this.userPaid = userPaid;
        this.totalUsers = totalUsers;
        this.expenseType = expenseType;
        this.amountPaid = amountPaid;
        this.userShareMap = userShareMap;
    }

    public String getExpenseName() {
        return expenseName;
    }

    public Double getAmountPaid() {
        return amountPaid;
    }

    public String getUserCreated() {
        return userCreated;
    }

    public void setUserCreated(String userCreated) {
        this.userCreated = userCreated;
    }

    public String getUserPaid() {
        return userPaid;
    }

    public void setUserPaid(String userPaid) {
        this.userPaid = userPaid;
    }

    public int getTotalUsers() {
        return totalUsers;
    }

    public void setTotalUsers(int totalUsers) {
        this.totalUsers = totalUsers;
    }

    public ExpenseType getExpenseType() {
        return expenseType;
    }

    public void setExpenseType(ExpenseType expenseType) {
        this.expenseType = expenseType;
    }

    public Map<String, Double> getUserShareMap() {
        return userShareMap;
    }

    public String toString() {
        return "Expense Name: " + expenseName + " Paid By: " + getUserPaid() + " Total Amount: " + amountPaid;
    }
}
