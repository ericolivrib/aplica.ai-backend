package br.com.coderico.aplicai.service;

import br.com.coderico.aplicai.dto.UserCreateRequest;
import br.com.coderico.aplicai.dto.UserCreatedResponse;
import br.com.coderico.aplicai.entity.Role;
import br.com.coderico.aplicai.entity.User;
import br.com.coderico.aplicai.exception.EntityAlreadyExistsException;
import br.com.coderico.aplicai.exception.EntityNotFoundException;
import br.com.coderico.aplicai.mapper.UserMapper;
import br.com.coderico.aplicai.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository repository;
    private final UserMapper mapper;
    private final PasswordEncoder encoder;

    public User getUser(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));
    }

    public UserCreatedResponse createUser(UserCreateRequest request) {
        boolean userExists = repository.existsByEmail(request.email());

        if (userExists) {
            throw new EntityAlreadyExistsException("O email informado já está vinculado a outro usuário");
        }

        User user = mapper.fromUserCreateRequest(request, encoder);

        repository.save(user);
        return mapper.toUserCreatedResponse(user);
    }
}
