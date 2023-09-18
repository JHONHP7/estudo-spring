package curso.spring.springboot2.mapper;

import curso.spring.springboot2.domain.Anime;
import curso.spring.springboot2.requests.AnimePostRequestBody;
import curso.spring.springboot2.requests.AnimePutRequestBody;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class AnimeMapper {
    public static final AnimeMapper INSTANCE = Mappers.getMapper(AnimeMapper.class);
    public abstract Anime toAnimePost(AnimePostRequestBody animePostRequestBody);

    public abstract Anime toAnimePut(AnimePutRequestBody animePutRequestBody);
}
