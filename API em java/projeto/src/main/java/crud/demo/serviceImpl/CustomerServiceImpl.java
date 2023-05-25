package crud.demo.serviceImpl;

import crud.demo.exception.HttpException;
import crud.demo.model.dto.CustomerDto;
import crud.demo.model.entity.AddressEntity;
import crud.demo.model.entity.CustomerEntity;
import crud.demo.repository.CustomerRepositoryInterface;
import crud.demo.service.AddressService;
import crud.demo.service.CustomerCheckService;
import crud.demo.service.CustomerService;
import crud.demo.singleton.CustomerRepositorySingleton;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {


    private final AddressService addressService;
    private final CustomerCheckService checkService;
    private CustomerRepositorySingleton customerRepository;
    public CustomerServiceImpl(CustomerRepositoryInterface customerRepositoryInterface, CustomerCheckService customerCheckService, AddressService addressService) {
        this.addressService = addressService;
        this.checkService = customerCheckService;
        this.customerRepository = CustomerRepositorySingleton.getInstance(customerRepositoryInterface);
    }


    @Override
    @Transactional
    public ResponseEntity<? extends CustomerEntity> saveCustomer(CustomerDto customer) {
        CustomerEntity customerDtoToEntity = new CustomerEntity(customer);
        try {
            AddressEntity saveAddres = addressService.saveAddress(customer.getAddres());
            this.checkService.checkReturn(saveAddres);
            customerDtoToEntity.setAddres(saveAddres);
        }catch (HttpException ex){
            throw new HttpException(ex.getStatusCode(),ex.getMessage());
        }catch (Exception ex){
            ex.getMessage();
        }


        return ResponseEntity.ok(customerRepository.saveCustomer(customerDtoToEntity));
    }



    @Override
    public ResponseEntity findCustomerById(Long id) {

        if(id == null){
            throw new HttpException(HttpStatus.BAD_REQUEST, "Parâmetro id não passado");
        }

        Optional<CustomerEntity> customerEntity = customerRepository.findCustomerById(id);

        this.checkService.checkReturn(customerEntity);

        return ResponseEntity.ok(customerEntity);
    }

    @Override
    @Transactional
    public CustomerEntity updateCustomer(Long id, CustomerDto customerDto) {
        Optional<CustomerEntity> customerEntity = customerRepository.findCustomerById(id);
        customerEntity.get().updateEntity(customerDto, id);
        try{
            return customerRepository.saveCustomer(customerEntity.get());
        }catch (HttpException ex){
            throw new HttpException(ex.getStatusCode(), "Erro ao salvar usuário");
        }
    }

    @Override
    public ResponseEntity findAllCustomers() {
        List<CustomerEntity> listCustomer = customerRepository.findAllCustomers();

        this.checkService.checkReturn(listCustomer);

        return ResponseEntity.status(HttpStatus.OK).body(listCustomer.stream().map(this::customerToDTO).collect(Collectors.toList()));
    }



    @Override
    @Transactional
    public ResponseEntity<Object> deleteCustomer(Long id) {
        Optional<CustomerEntity> customerEntity = customerRepository.findCustomerById(id);
        try{
            customerRepository.deleteCustomer(customerEntity.get());
            return new ResponseEntity<>("Usuário Deletado com sucesso!", HttpStatus.NO_CONTENT);
        }catch (HttpException ex){
            throw new HttpException(ex.getStatusCode(), ex.getMessage());
        }catch (IllegalArgumentException ex) {
            ex.getStackTrace();
        }
        return null;
    }

    private CustomerDto customerToDTO(CustomerEntity entity){
        return new CustomerDto(entity);
    }
}
