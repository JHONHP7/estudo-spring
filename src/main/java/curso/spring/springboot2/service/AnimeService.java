package curso.spring.springboot2.service;

import curso.spring.springboot2.domain.Anime;
import curso.spring.springboot2.mapper.AnimeMapper;
import curso.spring.springboot2.repository.AnimeRepository;
import curso.spring.springboot2.requests.AnimePostRequestBody;
import curso.spring.springboot2.requests.AnimePutRequestBody;
import exception.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnimeService {

    private final AnimeRepository animeRepository;

    public List<Anime> listAll() {
        return animeRepository.findAll();
    }
    public List<Anime> findByName(String name) {
        return animeRepository.findByName(name);
    }

    public Anime findByIdOrThrowBadRequestException(Long id) {
        return animeRepository.findById(id).orElseThrow(() -> new BadRequestException("Anime not Found"));
    }

    public Anime save(AnimePostRequestBody animePostRequestBody) {
        Anime anime = AnimeMapper.INSTANCE.toAnimePost(animePostRequestBody);
        return animeRepository.save(anime);
    }


    public void delete(Long id) {
        animeRepository.delete(findByIdOrThrowBadRequestException(id));
    }

    public void replace(AnimePutRequestBody animePutRequestBody) {
        Anime savedAnime = findByIdOrThrowBadRequestException(animePutRequestBody.getId());
        Anime anime = AnimeMapper.INSTANCE.toAnimePut(animePutRequestBody);
        anime.setId(savedAnime.getId());
        animeRepository.save(anime);
    }
}
