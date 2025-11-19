package br.com.coderico.aplicai.service;

import br.com.coderico.aplicai.entity.User;
import br.com.coderico.aplicai.exception.EntityNotFoundException;
import br.com.coderico.aplicai.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository repository;

    public User getUser(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));
    }
}
