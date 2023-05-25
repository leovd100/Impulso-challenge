package crud.demo.singleton;

import crud.demo.model.entity.CustomerEntity;
import crud.demo.repository.CustomerRepositoryInterface;

import java.util.List;
import java.util.Optional;

public class CustomerRepositorySingleton {

    private static CustomerRepositorySingleton instance;

    private final CustomerRepositoryInterface repository;

    private CustomerRepositorySingleton(CustomerRepositoryInterface repository){
        this.repository = repository;
    }

    public static CustomerRepositorySingleton getInstance(CustomerRepositoryInterface repository){

        if(instance == null){
            synchronized (CustomerRepositorySingleton.class){
                instance = new CustomerRepositorySingleton(repository);
            }
        }
        return instance;

    }


    public CustomerEntity saveCustomer(CustomerEntity customer){
        return repository.save(customer);
    }

    public Optional<CustomerEntity> findCustomerById(Long id){
        return repository.findById(id);
    }


    public List<CustomerEntity> findAllCustomers(){
        return repository.findAll();
    }


    public void deleteCustomer(CustomerEntity customerEntity){
        repository.delete(customerEntity);
    }



    public void deleteCustomerById(Long id){
        repository.deleteById(id);
    }



}
