import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class User {
    private String userId;
    private String name;
    private String email;
    private String phone;

    // Total expenses made till date.
    private double totalExpense;

    // List of expenses till date.
    private List<Expense> userExpenses;

    // UserId to Amount Due Map. The users this guy has to pay.
    private Map<String, Double> usersToPay;

    public User(String userId, String name, String email, String phone) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.totalExpense = 0.0;
        this.userExpenses = new ArrayList<>();
        this.usersToPay = new HashMap<>();
    }

    public List<Expense> getUserExpenses() {
        return userExpenses;
    }

    public void addUserExpense(Expense expense) {
        userExpenses.add(expense);
    }

    public Map<String, Double> getUsersToPay() {
        return usersToPay;
        
    }

    public void addUserToPay(String user, Double amount) {
    	
        usersToPay.put(user, Math.round(amount*100.00)/100.00);
    }

    public void removeUserToPay(String userId) {
        usersToPay.remove(userId);
    }

    public String getUserId() {
        return userId;
    }

    public double getTotalExpense() {
        return totalExpense;
    }

    public void setTotalExpense(Double totalExpense) {
        this.totalExpense = Math.round(totalExpense*100.00)/100.00;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String toString() {
        return "Name: " + name + " Email: " + email + " Phone: " + phone + " Balance Due: " + totalExpense;
    }
}