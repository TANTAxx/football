package hu.football.models.dto.users;

import hu.football.models.entities.users.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressDto {

    private Long id;
    private String zip;
    private String city;
    private String address;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public AddressDto(Address address){
        if(address.getId() == null) return;
        this.id = address.getId();
        this.zip=address.getZip();
        this.city=address.getCity();
        this.address=address.getAddress();
        this.createdAt=address.getCreatedAt();
    }
}
