package crud.demo.controller;

import crud.demo.model.entity.AddressEntity;
import crud.demo.serviceImpl.AddressServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {

    private final AddressServiceImpl service;

    public AddressController(AddressServiceImpl service){
        this.service = service;
    }

    @GetMapping("")
    public ResponseEntity<List<AddressEntity>> findAllAddress(){
        return ResponseEntity.ok(service.findAllAddress());
    }

    @GetMapping("/")
    public ResponseEntity<? extends AddressEntity> findAllAddress(@RequestParam("id") Long id){
        return service.findAddresById(id);
    }

    @GetMapping("/teste")
    public List<AddressEntity> teste(@RequestParam("zip") Long zip){
        return service.listTest(zip);
    }

}
