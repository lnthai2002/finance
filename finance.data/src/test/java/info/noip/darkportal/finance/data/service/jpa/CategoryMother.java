package info.noip.darkportal.finance.data.service.jpa;

import info.noip.darkportal.finance.data.model.Category;

public class CategoryMother {
    public static Category complete() {
        return new Category()
                .name("Salary")
                .effect(1);
    }
}
