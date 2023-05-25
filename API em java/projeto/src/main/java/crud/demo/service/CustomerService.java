package crud.demo.service;


import crud.demo.model.dto.CustomerDto;
import crud.demo.model.entity.CustomerEntity;
import org.springframework.http.ResponseEntity;

public interface CustomerService {
    public ResponseEntity findAllCustomers();

    public ResponseEntity<? extends CustomerEntity> saveCustomer(CustomerDto customer);

    public ResponseEntity<Object> deleteCustomer(Long id);

    public ResponseEntity findCustomerById(Long id);

    public CustomerEntity updateCustomer(Long id, CustomerDto customerDto);
}
