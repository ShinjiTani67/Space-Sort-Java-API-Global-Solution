package com.space.sort.fiap.service;
@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository repository;

    private UserDTO convertToDTO(User user) {

        UserDTO dto = new UserDTO();

        dto.setUuid(user.getUuid());
        dto.setNome(user.getNome());

        return dto;
    }

    private User convertToEntity(UserDTO dto) {

        User user = new User();

        if (dto.getUuid() != null) {
            user.setUuid(dto.getUuid());
        }

        user.setNome(dto.getNome());
        user.setEmail(dto.getEmail());

        return user;
    }

    public UserDTO save(UserDTO userDTO) {

        User user = convertToEntity(userDTO);

        // NÃO gerar UUID manualmente
        user = repository.save(user);

        return convertToDTO(user);
    }

    // LISTAR TODOS
    public List<UserDTO> getUser() {

        return repository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // BUSCAR POR ID
    public UserDTO findById(UUID uuid) {

        User user = repository.findById(uuid)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Funcionário com id " + uuid + " não encontrado"
                        )
                );

        return convertToDTO(user);
    }

    public UserDTO findByEmail(String email) {

        User user = repository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Funcionário com email " + email + " não encontrado"
                        )
                );

        return convertToDTO(user);
    }

    public void deleteById(UUID uuid) {

        if (!repository.existsById(uuid)) {
            throw new RuntimeException(
                    "Funcionário com id " + uuid + " não encontrado"
            );
        }

        repository.deleteById(uuid);
    }

    public UserDTO update(UUID uuid, UserDTO dto) {

        User user = repository.findByUuid(uuid)
                .orElseThrow(() ->
                        new RuntimeException("Funcionário não encontrado"));

        user.setNome(dto.getNome());
        user.setEmail(dto.getEmail());
        user.setSenha(dto.getSenha());
        user.setCargo(dto.getCargo());

        user = repository.save(user);

        return convertToDTO(user);
    }
}
