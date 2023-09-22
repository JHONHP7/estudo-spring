package curso.spring.springboot2.util;

import curso.spring.springboot2.domain.Anime;

public class AnimeCreator {

    public static Anime createAnimeToBeSaved() {
        return Anime.builder()
                .name("One piece")
                .build();
    }
    public static Anime createValidAnime() {
        return Anime.builder()
                .name("One piece")
                .id(1L)
                .build();
    }
    public static Anime createValidUpdatedAnime() {
        return Anime.builder()
                .name("One piece 2")
                .id(1L)
                .build();
    }
}
