package crud.demo.service;

import crud.demo.model.entity.AddressEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AddressService {


    List<AddressEntity> findAllAddress();

    ResponseEntity<? extends  AddressEntity> findAddresById(Long id);

    List<AddressEntity> listTest(Long zipCode);

    AddressEntity saveAddress(AddressEntity entity);


}
