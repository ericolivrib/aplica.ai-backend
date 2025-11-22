package br.com.coderico.aplicai;

import br.com.coderico.aplicai.entity.Role;
import br.com.coderico.aplicai.entity.User;
import br.com.coderico.aplicai.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class DataLoader implements CommandLineRunner {

    private final UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        loadUsers();
    }

    private void loadUsers() {
        long count = userRepository.count();

        if (count == 0) {
            User user = new User();
            user.setName("John Doe");
            user.setEmail("john.doe@test.com");
            user.setPassword("$2a$12$0xHdLUl2QLtXO3X4tNXYSO3BT7qD7LQhB3NYpZFCVq2Sh1K.32/4W");
            user.setProfession("Software Engineer");
            user.setRole(Role.USER);
            userRepository.save(user);
        }

        log.info("Carregando usuários de teste");
    }
}
