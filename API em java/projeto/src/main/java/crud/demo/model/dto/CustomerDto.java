package crud.demo.model.dto;

import crud.demo.model.entity.AddressEntity;
import crud.demo.model.entity.CustomerEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {
    private String name;
    private Integer age;
    private String email;
    private AddressEntity addres;

    public CustomerDto(CustomerEntity entity) {
        this.name = entity.getName();
        this.age = entity.getAge();
        this.email = entity.getEmail();
        this.addres = entity.getAddres();
    }
}
