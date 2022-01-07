package com.mycompany.book.service.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.domain.logic.IEntityDAService;
import com.mycompany.domain.logic.IHaveId;
import com.mycompany.domain.logic.IHaveUri;
import com.mycompany.domain.model.Author;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Slf4j
public abstract class ApiConsumer<Entity extends IHaveId, Config extends IHaveUri> implements IEntityDAService<Entity> {

    private Class<Entity[]> arrayOfType;
    private Class<Entity> itemType;

    public ApiConsumer() {
        this.itemType = Tools.getTypeArgument(this);
        this.arrayOfType = Tools.getTypeArgumentOfArray(this);
    }

    @Autowired
    private Config apiConfig;

    public List<Entity> getAll() {

        String authorsUri = apiConfig.getUri() + "/authors";
        return Arrays.asList(getEntities(authorsUri, arrayOfType));
    }

    public Entity save(Author author) {
        throw new NotImplementedException();
    }

    public Entity getById(int id) {
        String authorsUri = apiConfig.getUri() + "/authors/"+id;
        Entity entities = getEntities(authorsUri, itemType);
        return entities;
    }

    // helpers
    private <T> T getEntities(String authorsUri, Class<T> responseType) {
        // @see https://spring.io/blog/2009/03/27/rest-in-spring-3-resttemplate/
        log.info(String.format("Calling uri: %s", authorsUri));
        String responseStream = new RestTemplate().getForObject(authorsUri, String.class);

        // @see https://www.baeldung.com/java-json
        T entities = null;
        try {
            entities = new ObjectMapper().readValue(responseStream, responseType);
        } catch (IOException e) {
            log.error(String.format("unable to read response for %s.", authorsUri));
            e.printStackTrace();
        }
        log.info(String.format("response received from uri (%s) .", authorsUri));
        return entities;
    }
}
