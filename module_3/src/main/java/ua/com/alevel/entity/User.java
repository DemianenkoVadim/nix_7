package ua.com.alevel.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "user_data", schema = "module_3")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String userFirstName;

    @Column(name = "last_name", nullable = false)
    private String userLastName;

    @Column(name = "email", nullable = false, unique = true)
    private String userEmail;

    @Column(name = "telephone_number", nullable = false, length = 13, unique = true)
    private String userTelephoneNumber;

    @Column(name = "user_password", nullable = false, unique = true)
    private String userPassword;

    @OneToMany(mappedBy = "users")
    private Set<BankAccount> bankAccount;
}
