package asmaa.hr.ecommerce.customer;

import asmaa.hr.ecommerce.exception.CustomerNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public String createCustomer(CustomerRequest customerRequest) {
        Customer customer = customerRepository.save(this.customerMapper.toEntity(customerRequest));
        return customer.getId();
    }

    public void updateCustomer(CustomerRequest customerRequest) {
        Customer customer = customerRepository.findById(customerRequest.id()).orElseThrow(() ->
                new CustomerNotFoundException(
                        String.format("Cannot upate customer: Customer with ID %s does not exist", customerRequest.id())
                ));
        mergeCustomer(customer, customerRequest);
        customerRepository.save(this.customerMapper.toEntity(customerRequest));
    }

    private void mergeCustomer(Customer customer, CustomerRequest customerRequest) {
        if(StringUtils.isNotBlank(customerRequest.firstname())){
            customer.setFirstname(customerRequest.firstname());
        }
        if(StringUtils.isNotBlank(customerRequest.lastname())){
            customer.setLastname(customerRequest.lastname());
        }
        if(StringUtils.isNotBlank(customerRequest.email())){
            customer.setEmail(customerRequest.email());
        }
        if(customerRequest.address() != null){
            customer.setAddress(customerRequest.address());
        }
    }

    public List<CustomerResponse> getAllCustomers() {
        return this.customerRepository.findAll().stream().map(customerMapper::toResponse).toList();
    }

    public CustomerResponse getCustomerById(String customerId) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(() ->
                new CustomerNotFoundException(
                        String.format("Customer with ID %s does not exist", customerId)
                ));
        return this.customerMapper.toResponse(customer);
    }

    public void deleteCustomer(String customerId) {
        this.customerRepository.deleteById(customerId);
    }

    public Boolean customerExist(String customerId) {
        return this.customerRepository.findById(customerId).isPresent();
    }
}
