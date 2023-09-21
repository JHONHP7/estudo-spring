package curso.spring.springboot2.repository;

import curso.spring.springboot2.domain.Anime;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@DisplayName("Tests for Anime repository")
class AnimeRepositoryTest {
    /**
     * Usar Autowired apenas em test
     */
    @Autowired
    private AnimeRepository animeRepository;

    /**
     * verificar se a entidade foi salva usando assertions
     * os testes estão verificando se o anime não é nulo, se o id não é nulo
     * e verificando se o nome do anime salvo é igual ao nome do anime criado para ser salvo
     */
    @Test
    @DisplayName("Saves create anime when Successful")
    public void save_PersistAnime_WhenSuccessful() {
        Anime animeToBeSaved = createAnime();
        Anime animeSaved = this.animeRepository.save(animeToBeSaved);
        Assertions.assertThat(animeSaved).isNotNull();
        Assertions.assertThat(animeSaved.getId()).isNotNull();
        Assertions.assertThat(animeSaved.getName()).isEqualTo(animeToBeSaved.getName());
    }

    private Anime createAnime() {
        return Anime.builder()
                .name("One piece")
                .build();
    }
}