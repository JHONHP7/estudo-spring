package curso.spring.springboot2.client;

import curso.spring.springboot2.domain.Anime;
import lombok.extern.log4j.Log4j2;
import org.apache.catalina.util.ParameterMap;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Log4j2
public class SpringClient {
    /**
     * FAZENDO MANIPULAÇÃO DO OBJETO ANIME
     *
     * @param args
     */
    public static void main(String[] args) {
//        ResponseEntity<Anime> entity = new RestTemplate().getForEntity("http://localhost:8080/animes/{id}", Anime.class, 2);
//        log.info(entity);
//
//        Anime object = new RestTemplate().getForObject("http://localhost:8080/animes/{id}", Anime.class, 2);
//        log.info(object);
//
//        Anime[] animes = new RestTemplate().getForObject("http://localhost:8080/animes/all", Anime[].class);
//        log.info(Arrays.toString(animes));
//
//        ResponseEntity<List<Anime>> exchange = new RestTemplate().exchange("http://localhost:8080/animes/all", HttpMethod.GET, null,
//                new ParameterizedTypeReference<>() {
//                });
//        log.info(exchange.getBody());
//
//        Anime kingdom = Anime.builder().name("kingdom").build();
//        Anime kingdomSaved = new RestTemplate().postForObject("http://localhost:8080/animes", kingdom, Anime.class);
//        log.info("saved anime {} ", kingdomSaved);
//
//        Anime bokuNoHero = Anime.builder().name("Boku no hero").build();
//        Anime bokuNoHeroSaved = new RestTemplate().postForObject("http://localhost:8080/animes", bokuNoHero, Anime.class);
//        log.info("saved anime {} ", bokuNoHeroSaved);

        Anime samurai = Anime.builder().name("Samurai").build();
        ResponseEntity<Anime> samuraiSaved = new RestTemplate().exchange("http://localhost:8080/animes", HttpMethod.POST, new HttpEntity<>(samurai, createJsonHeader()), Anime.class);
        log.info("saved anime {} ", samuraiSaved);
    }

    private static HttpHeaders createJsonHeader(){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return httpHeaders;
    }
}
