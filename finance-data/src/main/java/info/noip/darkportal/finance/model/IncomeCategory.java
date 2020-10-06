package info.noip.darkportal.finance.model;

import java.util.Set;

public class IncomeCategory extends Category {
    private Set<Income> incomes;

    public Set<Income> getIncomes() {
        return incomes;
    }

    public void setIncomes(Set<Income> incomes) {
        this.incomes = incomes;
    }

    public IncomeCategory(String name) {
        super(name);
    }
}
