package asmaa.hr.ecommerce.customer;

import org.springframework.stereotype.Service;

@Service
public class CustomerMapper {

    public Customer toEntity(CustomerRequest customerRequest) {
        if(customerRequest == null) return null;
        return Customer.builder()
                    .id(customerRequest.id())
                    .firstname(customerRequest.firstname())
                    .lastname(customerRequest.lastname())
                    .email(customerRequest.email())
                    .address(customerRequest.address())
                    .build();
    }

    public CustomerResponse toResponse(Customer c) {
        return new CustomerResponse(c.getId(),c.getFirstname(), c.getLastname(), c.getEmail(), c.getAddress());
    }
}
