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
public class Subject implements Serializable {
    private Long id;
    private String name;
    private Teacher teacher;
}
