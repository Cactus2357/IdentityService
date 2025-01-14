package com.example.demo.configuration;

import com.example.demo.constant.PredefinedRole;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;

@Configurable
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ApplicationInitConfig {

    PasswordEncoder passwordEncoder;

    @NonFinal
    static final String ADMIN_USERNAME = "admin";

    @NonFinal
    static final String ADMIN_PASSWORD = "12345678";

    @Bean
    @ConditionalOnProperty(prefix = "spring", value = "datasource.driverClassName", havingValue = "com.mysql.cj.jdbc.Driver")
    ApplicationRunner applicationRunner(UserRepository userRepository, RoleRepository roleRepository) {
        return args -> {
            log.info("Init application...");
            if (userRepository.findByUsername(ADMIN_USERNAME).isPresent()) {
                log.info("Application initialization completed .....");
                return;
            }

            roleRepository.save(Role.builder()
                    .name(PredefinedRole.USER_ROLE)
                    .description("User role")
                    .build());

            Role adminRole = roleRepository.save(Role.builder()
                    .name(PredefinedRole.ADMIN_ROLE)
                    .description("Admin role")
                    .build());

            HashSet<Role> roles = new HashSet<Role>();
            roles.add(adminRole);

            User user = User.builder()
                    .username(ADMIN_USERNAME)
                    .password(passwordEncoder.encode(ADMIN_PASSWORD))
                    .roles(roles)
                    .build();

            userRepository.save(user);
            log.warn("Admin user has been created with default password: 12345678, please change it.");
            log.info("Application initialization completed .....");
        };
    }
}
