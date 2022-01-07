package com.mycompany.book.service.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.domain.logic.IEntityDAService;
import com.mycompany.domain.logic.IHaveId;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Slf4j
public class EntityDAService<Entity extends IHaveId> implements IEntityDAService<Entity> {
    private static int MIN_OBJECT_INDEX = 0;
    protected List<Entity> entitiesStore = new ArrayList<>();
    private Class<Entity[]> arrayOfType;
    private Class<Entity> itemType;

    public EntityDAService() {
        this.itemType = Tools.getTypeArgument(this);
        this.arrayOfType = Tools.getTypeArgumentOfArray(this);
    }

    public List<Entity> getAll() {
        return entitiesStore;
    }

    public Entity save(Entity item) {
        Optional<Entity> authorMaxId = entitiesStore.stream().max(Comparator.comparing(Entity::getId));
        int lastIndex = authorMaxId.map(Entity::getId).orElseGet(() -> MIN_OBJECT_INDEX);
        item.setId(++lastIndex);
        entitiesStore.add(item);
        return item;
    }

    public Entity getById(int id) {
        Entity[] results = entitiesStore.stream()
                .filter(e -> e.getId() == id )
                .toArray(e -> Tools.newArrayInstance(itemType, e));
        Entity[] resultsCloned = Tools.cloneDeep(results);
        return (results != null && resultsCloned.length == 1) ? resultsCloned[0] : null;
    }
}
