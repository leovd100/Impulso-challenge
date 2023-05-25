package crud.demo.model.entity;

import crud.demo.model.dto.CustomerDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerEntity {

    public CustomerEntity(CustomerDto customerDto){
        this.addres = customerDto.getAddres();
        this.age = customerDto.getAge();
        this.email = customerDto.getEmail();
        this.name = customerDto.getName();
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "customer_name")
    private String name;

    @Column(name = "customer_age")
    private Integer age;

    @Column(name = "customer_email")
    private String email;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private AddressEntity addres;

    public void updateEntity(CustomerDto dto, Long idUpdate){
        setAddres(dto.getAddres() == null ? getAddres() : dto.getAddres());
        setId(getId() == null ? idUpdate : idUpdate != null ? idUpdate : null);
        setAge(dto.getAge() == null ? this.getAge() : dto.getAge());
        setName(dto.getName() == null ? this.getName() : dto.getName());
        setEmail(dto.getEmail() == null ? this.getEmail() : dto.getEmail());
    }
}
