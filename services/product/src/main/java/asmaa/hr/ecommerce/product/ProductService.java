package asmaa.hr.ecommerce.product;

import asmaa.hr.ecommerce.exception.ProductPurchaseException;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public Integer createProduct(ProductRequest productRequest){
        Product product = this.productMapper.toProduct(productRequest);
        product = productRepository.save(product);
        return product.getId();
    }

    public List<PurchaseProductResponse> purchaseProduct(List<PurchaseProductRequest> productRequest){
        List<Integer> productsIds = productRequest.stream().map(PurchaseProductRequest::productId).toList();
        List<Product> products = this.productRepository.findByIdInOrderById(productsIds);
        if(productsIds.size() > products.size()) {
            throw new ProductPurchaseException("Some products does not exist in the database");
        }
        List<PurchaseProductResponse> purchaseProductResponseList = new ArrayList<>();
        var sortedProductsRequest = productRequest.stream().sorted(Comparator.comparing(PurchaseProductRequest::productId)).toList();
        for (int i=0 ; i<products.size() ; i++){
            var p = products.get(i);
            var requestProduct = sortedProductsRequest.get(i);
            if(p.getAvailableQuantity()< requestProduct.quantity())
                throw new EntityNotFoundException(" Not enough quantity");
            else
                p.setAvailableQuantity(p.getAvailableQuantity() - requestProduct.quantity());
            this.productRepository.save(p);
            PurchaseProductResponse purchaseProductResponse = productMapper.toPurchaseProductResponse(requestProduct,p);
            purchaseProductResponseList.add(purchaseProductResponse);
        }
        return purchaseProductResponseList;

    }

    public List<ProductResponse> findAllProducts(){
        return this.productRepository
                .findAll()
                .stream()
                .map(p -> productMapper.toProductResponse(p))
                .toList();
    }

    public ProductResponse findProductsById(Integer productId){
        Product product = this.productRepository.findById(productId).orElseThrow(
                () -> new EntityNotFoundException(" Product with id " + productId + " not found"));
        return this.productMapper.toProductResponse(product);
    }

    public void deleteProduct(Integer productId) {
        this.productRepository.deleteById(productId);
    }
}
