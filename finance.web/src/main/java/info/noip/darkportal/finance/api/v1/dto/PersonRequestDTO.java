package info.noip.darkportal.finance.api.v1.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class PersonRequestDTO {
    private String firstName;
    private String lastName;
}
