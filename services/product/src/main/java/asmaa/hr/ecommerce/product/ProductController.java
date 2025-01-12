package asmaa.hr.ecommerce.product;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<Integer> createProduct(@RequestBody @Valid ProductRequest productRequest) {
        return ResponseEntity.ok(this.productService.createProduct(productRequest));
    }

    @PostMapping("/purchase")
    public ResponseEntity<List<PurchaseProductResponse>> purchaseProducts(@RequestBody @Valid List<PurchaseProductRequest> purchaseProductRequest) {
        return ResponseEntity.ok(this.productService.purchaseProduct(purchaseProductRequest));
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> findAllProducts() {
        return ResponseEntity.ok(this.productService.findAllProducts());
    }

    @GetMapping("/{product-id}")
    public ResponseEntity<ProductResponse> findProductsById(@RequestParam("product-id") Integer productId) {
        return ResponseEntity.ok(this.productService.findProductsById(productId));
    }

    @DeleteMapping("/{product-id}")
    public ResponseEntity<Void> deleteProduct(@RequestParam("product-id") Integer productId) {
        this.productService.deleteProduct(productId);
        return ResponseEntity.noContent().build();
    }


}
