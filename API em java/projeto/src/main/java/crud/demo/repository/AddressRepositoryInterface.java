package crud.demo.repository;

import crud.demo.model.entity.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AddressRepositoryInterface extends JpaRepository<AddressEntity, Long> {

    @Query(value = "SELECT * FROM ADDRESS  AD WHERE AD.ZIP_CODE = :zipCode", nativeQuery = true)
    public List<AddressEntity> findByZipCode(Long zipCode);


}
