package domain.mapping.dto;

public record StudentDto(Long id,
                         String name,
                         String email,
                         String semester) {
}
