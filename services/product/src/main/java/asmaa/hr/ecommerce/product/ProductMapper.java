package asmaa.hr.ecommerce.product;

import asmaa.hr.ecommerce.category.Category;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {

    public Product toProduct(ProductRequest productRequest) {
        return Product.builder().id(productRequest.id())
                .name(productRequest.name())
                .price(productRequest.price())
                .availableQuantity(productRequest.availableQuantity())
                .description(productRequest.description())
                .category(
                        Category.builder().id(productRequest.categoryId()
                        ).build()
                )
                .build();
    }

    public ProductResponse toProductResponse(Product p) {
        return new ProductResponse(p.getId(), p.getName(), p.getDescription(), p.getAvailableQuantity(), p.getPrice(), p.getCategory().getId());
    }

    public PurchaseProductResponse toPurchaseProductResponse(PurchaseProductRequest purchaseProductRequest, Product product) {
        return new PurchaseProductResponse(product.getId(), product.getName(), product.getDescription(), product.getPrice(), purchaseProductRequest.quantity());
    }
}
