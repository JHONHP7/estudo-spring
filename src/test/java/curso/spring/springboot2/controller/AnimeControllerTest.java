package curso.spring.springboot2.controller;

import curso.spring.springboot2.domain.Anime;
import curso.spring.springboot2.service.AnimeService;
import curso.spring.springboot2.util.AnimeCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testando controller
 */
@ExtendWith(SpringExtension.class)
class AnimeControllerTest {
    /**
     * Utilizar InjectMockos quando a classe em si é testada
     * nesse caso é o animeController
     *
     */
    @InjectMocks
    private AnimeController animeController;

    /**
     * O Mock é utilizado para todas as classes sendo usadas no animeController;
     */
    @Mock
    private AnimeService animeServiceMock;

    /**
     * quando alguém executar uma chamada para o animeServiceMock.ListAll()
     * sem importar qual argumento será passado, por isso o any()
     * quero que retorne o animePage
     */
    @BeforeEach
    void setUp(){
        PageImpl<Anime> animePage = new PageImpl<>(List.of(AnimeCreator.createValidAnime()));
        BDDMockito.when(animeServiceMock.listAll(ArgumentMatchers.any()))
                .thenReturn(animePage);
    }
    @Test
    @DisplayName("List returns list of anime inside page object when successful")
    void list_ReturnsListOfAnimesPageInsidePageObject_WhenSuccessful(){
        String expectedName = AnimeCreator.createValidAnime().getName();

        Page<Anime> animePage = animeController.list(null).getBody();

        Assertions.assertThat(animePage).isNotNull();

        Assertions.assertThat(animePage.toList())
                .isNotEmpty()
                .hasSize(1);

        Assertions.assertThat(animePage.toList().get(0).getName()).isEqualTo(expectedName);
    }
}