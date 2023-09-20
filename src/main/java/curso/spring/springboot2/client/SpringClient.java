package curso.spring.springboot2.client;

import curso.spring.springboot2.domain.Anime;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@Log4j2
public class SpringClient {
    /**
     * FAZENDO MANIPULAÇÃO DO OBJETO ANIME
     * @param args
     */
    public static void main(String[] args) {
        ResponseEntity<Anime> entity = new RestTemplate().getForEntity("http://localhost:8080/animes/{id}", Anime.class,1);
        log.info(entity);
        Anime object = new RestTemplate().getForObject("http://localhost:8080/animes/{id}", Anime.class, 1);
        log.info(object);
    }
}
