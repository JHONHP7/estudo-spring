package curso.spring.springboot2.repository;

import curso.spring.springboot2.domain.Anime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@DisplayName("Tests for Anime repository")
class AnimeRepositoryTest {
    /**
     * Usar Autowired apenas em test
     */
    @Autowired
    private AnimeRepository animeRepository;

    @Test
    @DisplayName("Saves create anime when Successful")
    public void save_PersistAnime_WhenSuccessful() {
        Anime anime = createAnime();

    }

    private Anime createAnime() {
        return Anime.builder()
                .name("One piece")
                .build();
    }
}