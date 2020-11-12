package info.noip.darkportal.finance.data.service.jpa;

import info.noip.darkportal.finance.data.Messages;
import info.noip.darkportal.finance.data.model.Category;
import info.noip.darkportal.finance.data.repository.CategoryRepository;
import info.noip.darkportal.finance.data.repository.EntityNotFoundException;
import info.noip.darkportal.finance.data.service.CategoryService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Profile({"default", "jpa"})
public class JpaCategoryService implements CategoryService {
    private CategoryRepository categoryRepository;
    private Messages messages;

    public JpaCategoryService(CategoryRepository categoryRepository, Messages messages) {
        this.categoryRepository = categoryRepository;
        this.messages = messages;
    }

    @Override
    public Set<Category> findAllExpenseCategories() {
        return StreamSupport.stream(categoryRepository.findAllByEffect(-1).spliterator(), false)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Category> findAllIncomeCategories() {
        return StreamSupport.stream(categoryRepository.findAllByEffect(1).spliterator(), false)
                .collect(Collectors.toSet());
    }

    @Override
    public Category save(Category object) {
        return categoryRepository.save(object);
    }

    @Override
    public Collection<Category> findAll() {
        return StreamSupport.stream(categoryRepository.findAll().spliterator(), false)
        .collect(Collectors.toSet());
    }

    @Override
    public Category findById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(messages.get("jpa.entityNotFound")));
    }

    @Override
    public void delete(Category object) {
        categoryRepository.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public void update(Category object) {
        if (categoryRepository.existsById(object.id())) {
            categoryRepository.save(object);
        }
        else
        {
            throw new EntityNotFoundException("Category not exists");
        }
    }
}
