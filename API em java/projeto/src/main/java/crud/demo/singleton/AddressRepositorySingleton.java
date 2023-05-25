package crud.demo.singleton;


import crud.demo.model.entity.AddressEntity;
import crud.demo.repository.AddressRepositoryInterface;

import java.util.List;
import java.util.Optional;

public class AddressRepositorySingleton {
    private static volatile AddressRepositorySingleton instance;
    private final AddressRepositoryInterface repository;

    private AddressRepositorySingleton(AddressRepositoryInterface repository){
        this.repository = repository;
    }

    public static AddressRepositorySingleton getInstance(AddressRepositoryInterface repository){
        if(instance == null){
            synchronized (AddressRepositorySingleton.class) {
                    instance = new AddressRepositorySingleton(repository);
            }
        }
        return instance;
    }

    public List<AddressEntity> getAddrezByZipCode(Long id){
        return this.repository.findByZipCode(id);
    }

    public List<AddressEntity> getAllAddress(){
        return this.repository.findAll();
    }

    public Optional<AddressEntity> getById(Long id){
        return this.repository.findById(id);
    }

    public AddressEntity saveAddress(AddressEntity address){
        return this.repository.save(address);
    }


}
