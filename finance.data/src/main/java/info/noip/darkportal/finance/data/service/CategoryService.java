package info.noip.darkportal.finance.data.service;

import info.noip.darkportal.finance.data.model.Category;

import java.util.Set;

public interface CategoryService extends CrudService<Category> {
    Set<Category> findAllExpenseCategories();
    Set<Category> findAllIncomeCategories();
}
