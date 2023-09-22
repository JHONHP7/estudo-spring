package curso.spring.springboot2.repository;

import curso.spring.springboot2.domain.Anime;
import lombok.extern.log4j.Log4j2;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@DisplayName("Tests for Anime repository")
@Log4j2
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
    @DisplayName("Saves persists anime when Successful")
    public void save_PersistAnime_WhenSuccessful() {
        Anime animeToBeSaved = createAnime();

        Anime animeSaved = this.animeRepository.save(animeToBeSaved);

        Assertions.assertThat(animeSaved).isNotNull();

        Assertions.assertThat(animeSaved.getId()).isNotNull();

        Assertions.assertThat(animeSaved.getName()).isEqualTo(animeToBeSaved.getName());
    }

    @Test
    @DisplayName("Saves update anime when Successful")
    public void save_UpdateAnime_WhenSuccessful() {
        Anime animeToBeSaved = createAnime();

        Anime animeSaved = this.animeRepository.save(animeToBeSaved);

        animeSaved.setName("Overlod");

        Anime animeUpdate = this.animeRepository.save(animeSaved);
        log.info("Anime {}", animeUpdate.getName());

        Assertions.assertThat(animeUpdate).isNotNull();

        Assertions.assertThat(animeUpdate.getId()).isNotNull();

        Assertions.assertThat(animeUpdate.getName()).isEqualTo(animeSaved.getName());
    }

    @Test
    @DisplayName("Delete removes anime when Successful")
    public void delete_RemovesAnime_WhenSuccessful() {
        Anime animeToBeSaved = createAnime();

        Anime animeSaved = this.animeRepository.save(animeToBeSaved);

        this.animeRepository.delete(animeSaved);

        Optional<Anime> animeOptional = this.animeRepository.findById(animeSaved.getId());

        Assertions.assertThat(animeOptional).isEmpty();

    }

    /**
     * Verificando se não é vazio e verificando se tem o anime na lista
     */
    @Test
    @DisplayName("Find By Name returns list of anime when Successful")
    public void findByName_ReturnListOfAnime_WhenSuccessful() {
        Anime animeToBeSaved = createAnime();

        Anime animeSaved = this.animeRepository.save(animeToBeSaved);

        String name = animeSaved.getName();

        List<Anime> animes = this.animeRepository.findByName(name);

        Assertions.assertThat(animes).isNotEmpty();

        Assertions.assertThat(animes).contains(animeSaved);

    }

    /**
     * Verificando se retorna vazio caso pesquise por um nome que não existe na lista
     */
    @Test
    @DisplayName("Find By Name returns empty list when no anime is found")
    public void findByName_ReturnEmptyList_WhenAnimeIsNotFound() {
        List<Anime> animes = this.animeRepository.findByName("animeNotExist");

        Assertions.assertThat(animes).isEmpty();
    }

    private Anime createAnime() {
        return Anime.builder()
                .name("One piece")
                .build();
    }
}