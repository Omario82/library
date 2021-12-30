package com.mycompany.book.service.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.book.domain.logic.IEntityDAService;
import com.mycompany.book.domain.model.Author;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public abstract class ApiConsumer<Entity extends IHaveId, Config extends IHaveUri> implements IEntityDAService<Entity> {

    private Class<Entity[]> type;

    public ApiConsumer(Class<Entity[]> type) { this.type = type; }

    @Autowired
    private Config apiConfig;

    public List<Entity> getAll() {

        String authorsUri = apiConfig.getUri() + "/authors";
        return Arrays.asList(getEntities(authorsUri));
    }

    public Entity save(Author author) {
        throw new NotImplementedException();
    }

    public Entity getById(int id) {
        Map<String, String> variable = new HashMap<String, String>();
        variable.put("id", id+"");
        String authorsUri = apiConfig.getUri() + "/authors/"+id;
        Entity[] entities = getEntities(authorsUri);
        return entities.length>0 ? entities[0]: null;
    }

    // helpers
    private Entity[] getEntities(String authorsUri) {
        // @see https://spring.io/blog/2009/03/27/rest-in-spring-3-resttemplate/
        log.info(String.format("Calling uri: %s", authorsUri));
        String responseStream = new RestTemplate().getForObject(authorsUri, String.class);

        // @see https://www.baeldung.com/java-json
        Entity[] entities = null;
        try {
            entities = new ObjectMapper().readValue(responseStream, type);
        } catch (IOException e) {
            log.error(String.format("unable to read response for %s.", authorsUri));
            e.printStackTrace();
        }
        log.info(String.format("response of uri (%s) with %d entities.", authorsUri, entities.length));
        return entities;
    }
}
