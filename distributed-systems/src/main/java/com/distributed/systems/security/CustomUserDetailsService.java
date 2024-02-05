package com.distributed.systems.security;

import com.distributed.systems.entity.Staff;
import com.distributed.systems.repository.StaffRepository;
import com.distributed.systems.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.distributed.systems.entity.User;
import com.distributed.systems.entity.Role;

import java.util.Collections;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;
    private StaffRepository staffRepository;

    public CustomUserDetailsService(UserRepository userRepository,StaffRepository staffRepository){
        this.userRepository = userRepository;
        this.staffRepository = staffRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username);
        Staff staff = staffRepository.findByEmail(username);

        if(user!=null){
            return new org.springframework.security.core.userdetails.User(
                    user.getUsername(),
                    user.getPassword(),
                    user.getRoles().stream().map((role) -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList()));
        }else if (staff!=null) {
            return new org.springframework.security.core.userdetails.User(
                    staff.getEmail(),
                    staff.getPassword(),
                    Collections.singletonList(new SimpleGrantedAuthority("ADMIN")));
        }else{
            throw new UsernameNotFoundException("Invalid email or password");
        }
    }

}
