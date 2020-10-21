package info.noip.darkportal.finance.data.repository;

import info.noip.darkportal.finance.data.model.Category;

public interface CategoryRepository extends BaseRepository<Category, Long> {
    @Override
    Iterable<Category> findAll();

    Iterable<Category> findAllByEffect(Integer effect);
}
