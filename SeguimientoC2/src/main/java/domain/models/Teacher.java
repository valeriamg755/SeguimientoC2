package domain.models;

import jakarta.enterprise.context.SessionScoped;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder

@SessionScoped
public class Teacher implements Serializable {
    private Long id;
    private String name;
    private String email;
}
