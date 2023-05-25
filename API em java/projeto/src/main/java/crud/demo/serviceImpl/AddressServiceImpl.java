package crud.demo.serviceImpl;

import crud.demo.model.entity.AddressEntity;
import crud.demo.repository.AddressRepositoryInterface;
import crud.demo.singleton.AddressRepositorySingleton;
import crud.demo.service.AddressService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService {

    private AddressRepositorySingleton addressRepository;

    public AddressServiceImpl(AddressRepositoryInterface addressRepositoryInterface){
        this.addressRepository = AddressRepositorySingleton.getInstance(addressRepositoryInterface);
    }

    @Override
    public List<AddressEntity> findAllAddress() {
        return this.addressRepository.getAllAddress();
    }

    @Override
    public ResponseEntity<? extends  AddressEntity> findAddresById(Long id) {
        Optional<AddressEntity> address = this.addressRepository.getById(id);
        return address.<ResponseEntity<? extends AddressEntity>>map(ResponseEntity::ok).orElseGet(() -> new ResponseEntity("Endereço não encontrado", HttpStatus.NOT_FOUND));

    }

    public List<AddressEntity> listTest(Long zipCode){
        return this.addressRepository.getAddrezByZipCode(zipCode);
    }

    @Override
    public AddressEntity saveAddress(AddressEntity entity) {
        return this.addressRepository.saveAddress(entity);
    }
}
