package info.noip.darkportal.finance.model;

public class Expense extends Payment {
    private ExpenseCategory expenseCategory;

    public ExpenseCategory getExpenseCategory() {
        return expenseCategory;
    }

    public void setExpenseCategory(ExpenseCategory expenseCategory) {
        this.expenseCategory = expenseCategory;
    }
}
