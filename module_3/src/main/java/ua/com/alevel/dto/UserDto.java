package ua.com.alevel.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.com.alevel.entity.User;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;

    public UserDto(User user) {
        this.id = user.getId();
        this.firstName = user.getUserFirstName();
        this.lastName = user.getUserLastName();
        this.email = user.getUserEmail();
        this.phoneNumber = user.getUserTelephoneNumber();
    }

    @Override
    public String toString() {
        return "User " +
                " ID  - " + id + '\'' +
                " First name " + firstName + '\'' +
                " Last name " + lastName + '\'' +
                " Email " + email + '\'' +
                " Phone number " + phoneNumber;
    }
}
