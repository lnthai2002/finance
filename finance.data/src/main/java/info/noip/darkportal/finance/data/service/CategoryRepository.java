package info.noip.darkportal.finance.data.service;

import info.noip.darkportal.finance.data.model.Category;

import java.util.Set;

public interface CategoryRepository extends CrudRepository<Category> {
    Set<Category> findAllExpenseCategories();
    Set<Category> findAllIncomeCategories();
}
