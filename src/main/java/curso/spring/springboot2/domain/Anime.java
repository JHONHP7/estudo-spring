package curso.spring.springboot2.domain;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Anime {

    private Long id;
    private String name;

}
