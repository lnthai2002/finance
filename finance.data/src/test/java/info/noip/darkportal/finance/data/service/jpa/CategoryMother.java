package info.noip.darkportal.finance.data.service.jpa;

import info.noip.darkportal.finance.data.model.Category;

public class CategoryMother {
    public static Category complete() {
        return Category.Builder.aCategory()
                .withName("Salary")
                .withEffect(1)
                .build();
    }
}
