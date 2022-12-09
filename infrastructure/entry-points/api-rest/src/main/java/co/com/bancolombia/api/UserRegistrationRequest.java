package co.com.bancolombia.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegistrationRequest implements Serializable {
    private String username;
    private String email;
    private String mobileNumber;
}
