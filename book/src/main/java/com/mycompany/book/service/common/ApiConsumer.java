package com.mycompany.book.service.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.book.domain.logic.IEntityDAService;
import com.mycompany.book.domain.model.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public abstract class ApiConsumer<Entity extends Object, Config extends IHaveUri> implements IEntityDAService<Entity> {

    private Class<Entity[]> type;

    public ApiConsumer(Class<Entity[]> type) { this.type = type; }

    @Autowired
    private Config apiConfig;

    public List<Entity> getAll() throws IOException {
        // @see https://spring.io/blog/2009/03/27/rest-in-spring-3-resttemplate/
        String authorUri = apiConfig.getUri();
        String responseStream = new RestTemplate().getForObject(authorUri + "/authors", String.class);

        // @see https://www.baeldung.com/java-json
        Entity[] entities = new ObjectMapper().readValue(responseStream, type);
        return Arrays.asList(entities);
    }

    public Entity save(Author author) {
        throw new NotImplementedException();
    }

    public List<Entity> findById(int id) {
        throw new NotImplementedException();
    }
}
