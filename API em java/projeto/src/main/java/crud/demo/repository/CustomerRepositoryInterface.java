package crud.demo.repository;

import crud.demo.model.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepositoryInterface extends JpaRepository<CustomerEntity, Long> {






}
