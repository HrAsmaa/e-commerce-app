package asmaa.hr.ecommerce.category;

import asmaa.hr.ecommerce.product.ProductResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryService {

    private final CategoryMapper categoryMapper;
    private final CategoryRepository categoryRepository;

    public Integer createCategory(CategoryRequest categoryRequest){
        Category category = categoryMapper.toCategory(categoryRequest);
        category = categoryRepository.save(category);
        return category.getId();
    }

    public List<CategoryResponse> findAllCategories() {
        return this.categoryRepository.findAll().stream()
                .map(c -> categoryMapper.toCategoryResponse(c))
                .toList();
    }

    public CategoryResponse findCategoryById(Integer categoryId) {
        Category category = this.categoryRepository.findById(categoryId).orElseThrow(
                () -> new EntityNotFoundException("category with id " + categoryId + " not found")
        );
        return this.categoryMapper.toCategoryResponse(category);
    }

    public void deleteCategory(Integer categoryId) {
        this.categoryRepository.deleteById(categoryId);
    }
}
