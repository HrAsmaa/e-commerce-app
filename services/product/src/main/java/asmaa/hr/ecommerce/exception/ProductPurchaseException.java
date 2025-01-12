package asmaa.hr.ecommerce.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ProductPurchaseException extends RuntimeException {
    String message;
}
