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
public class Grade implements Serializable {
    private Long id;
    private Student student;
    private Subject subject;
    private Double grade;
}
