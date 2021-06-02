import java.util.Map;

public class ExpenseProcessor {
    public static void processExpense(Expense expense, Map<String, User> userMap) {
        Map<String, Double> userShareMap = expense.getUserShareMap();
        Boolean firstUser = true;

        // Iterate over each person in the list:
        for (String userdId : userShareMap.keySet()) {
            Double userAmount = 0.0;

            // Note: expenses are already validated while creation.
            switch (expense.getExpenseType()) {
                case EQUAL:
                    userAmount = expense.getAmountPaid() / expense.getTotalUsers();
                    userAmount = Math.round(userAmount * 100.0) / 100.0;

                    // Handling a corner case (last condition in the functional requirements section)
                    // For example assigning 33.34 to the first person and 33.33 to the rest for equal share of 100
                    if (firstUser) {
                        Double delta = expense.getAmountPaid() - (userAmount * expense.getTotalUsers());
                        if (delta >= Constants.EPS) {
                            userAmount = (Math.round(userAmount * 100.0) + Math.round(delta * 100.0))/ 100.0;

                        }
                        firstUser = false;
                    }

                    break;
                case EXACT:
                    userAmount = userShareMap.get(userdId);
                    break;
                case PERCENT:
                    userAmount = (userShareMap.get(userdId) / 100.0) * expense.getAmountPaid();
                    userAmount = Math.round(userAmount * 100.0) / 100.0;
                    break;
            }

            User currentUser = userMap.get(userdId);
            User payer = userMap.get(expense.getUserPaid());

            // If this person is not the actualy payer of the event
            if (userdId.compareTo(expense.getUserPaid()) != 0) {
                // payer owes current userId -userAmount value
                payer.addUserToPay(userdId,
                        payer.getUsersToPay().containsKey(userdId) ?
                                payer.getUsersToPay().get(userdId) - userAmount :
                                -userAmount);

                // userId owes payer +userAmount value
                currentUser.addUserToPay(expense.getUserPaid(),
                        currentUser.getUsersToPay().containsKey(expense.getUserPaid()) ?
                                currentUser.getUsersToPay().get(expense.getUserPaid()) + userAmount :
                                userAmount);

                currentUser.setTotalExpense(currentUser.getTotalExpense() + userAmount);
            } else {
                // Same person who paid!
                currentUser.setTotalExpense(currentUser.getTotalExpense() - (expense.getAmountPaid() - userAmount));
            }

            // Add expense to the user's list of expenses for record.
            currentUser.addUserExpense(expense);
        }
    }
}