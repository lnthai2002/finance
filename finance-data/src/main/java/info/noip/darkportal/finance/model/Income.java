package info.noip.darkportal.finance.model;

public class Income extends Payment {
    private IncomeCategory incomeCategory;

    public IncomeCategory getIncomeCategory() {
        return incomeCategory;
    }

    public void setIncomeCategory(IncomeCategory incomeCategory) {
        this.incomeCategory = incomeCategory;
    }
}
