package info.noip.darkportal.finance.repository;

import info.noip.darkportal.finance.model.Category;

import java.util.Set;

public interface CategoryRepository extends CrudRepository<Category> {
    Set<Category> findAllExpenseCategories();
    Set<Category> findAllIncomeCategories();
}
