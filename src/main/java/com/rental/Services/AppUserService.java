package com.rental.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.rental.Models.Users.UsersModels;
import com.rental.Repository.UsersRepository;
import com.rental.utils.PasswordEncoder;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class AppUserService implements UserDetailsService {


    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        return usersRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Unimplemented method 'loadUserByEmail'"));
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'loadUserByUsername'");
    }

    public UsersModels RegisterUser(UsersModels user){
        boolean userExists = usersRepository.findByEmail(user.getEmail()).isPresent();
        if(userExists){
            throw new RuntimeException(
                String.format("Email sudah terdaftar", user.getEmail())
            );
        }

        String encoderPassword = passwordEncoder.bCryptPasswordEncoder().encode(user.getEmail());
        user.setPassword(encoderPassword);

        return usersRepository.save(user);
    }
    
}
