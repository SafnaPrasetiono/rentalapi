package com.rental.Models.Users;

import java.math.BigInteger;
import java.util.Collection;
import java.util.Collections;

import org.hibernate.action.internal.CollectionAction;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Table(name = "users")
public class UsersModels implements UserDetails {
    @Id
    @Column(name = "id_user", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id_user;

    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "username", nullable = false)
    private String username;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "born", nullable = true)
    private String born;
    @Column(name = "address", nullable = true)
    private String address;
    @Column(name = "phone", nullable = true)
    private String phone;
    @Column(name = "avatar", nullable = true)
    private String avatar;

    @Enumerated((EnumType.STRING))
    private AppUserRole appUserRole;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(appUserRole.name());
        return Collections.singletonList(authority);
        // TODO Auto-generated method stub 
        // throw new UnsupportedOperationException("Unimplemented method 'getAuthorities'");
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'isAccountNonExpired'");
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'isAccountNonLocked'");
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'isCredentialsNonExpired'");
    }

    @Override
    public boolean isEnabled() {
        return true;
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'isEnabled'");
    }
    
}
