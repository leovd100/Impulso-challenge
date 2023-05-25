package crud.demo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressDto {
    private String street;

    private Integer number;

    private Integer zipCode;

    private String state;

    private String neighborhood;
}
