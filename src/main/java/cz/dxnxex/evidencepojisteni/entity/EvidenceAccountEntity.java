package cz.dxnxex.evidencepojisteni.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Setter
@Getter
@Entity
public class EvidenceAccountEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private boolean admin;

        // region: UserDetails Methods

            public Collection<? extends GrantedAuthority> getAuthorities() {
                SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + (admin ? "ADMIN" : "USER"));
                return List.of(authority);
            }

            @Override
            public String getUsername() {
                return "";
            }

            public boolean isAccountNonExpired() {
                return true;
            }


            public boolean isAccountNonLocked() {
                return true;
            }


            public boolean isCredentialsNonExpired() {
                return true;
            }


            public boolean isEnabled() {
                return true;
            }

            // endregion


}