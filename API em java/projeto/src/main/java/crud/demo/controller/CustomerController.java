package crud.demo.controller;

import crud.demo.model.dto.CustomerDto;
import crud.demo.model.entity.CustomerEntity;
import crud.demo.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
@Validated
public class CustomerController {

    private final CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @GetMapping("/test")
    public ResponseEntity<String> testApi(){
        return new ResponseEntity<>("Api on", HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<Object> getAll(){
        return service.findAllCustomers();
    }


    @GetMapping("/search")
    public ResponseEntity getCustomerById (@RequestParam("id")  Long id){
        return service.findCustomerById(id);
    }

    @PostMapping("/")
    public ResponseEntity<? extends CustomerEntity> saveCustomer(@RequestBody CustomerDto customer){
        return service.saveCustomer(customer);
    }

    @DeleteMapping("/del")
    public ResponseEntity<Object> deleteCustomer(@RequestParam("id") Long id){
        return service.deleteCustomer(id);
    }


    @PutMapping("/upd")
    public ResponseEntity<? extends  CustomerEntity> updateCustomer(
            @RequestBody CustomerDto customerDto,
            @RequestParam("id") Long id
    ){
       return ResponseEntity.ok(service.updateCustomer(id, customerDto));
    }
}
