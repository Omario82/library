package com.mycompany.book.service.common;

import com.mycompany.book.domain.logic.IEntityDAService;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
public class EntityDAService<TEntity extends IHaveId> implements IEntityDAService<TEntity> {
    private static int MIN_OBJECT_INDEX = 0;
    protected List<TEntity> entitiesStore;

    public EntityDAService(List<TEntity> entitiesStore) {
        if (this.entitiesStore == null)
            this.entitiesStore = new ArrayList<>();
        if (entitiesStore != null){
            this.entitiesStore.addAll(entitiesStore);
            log.info(String.format("%d books were loaded on %s", entitiesStore.size(), getClass().getSimpleName()));
        }
    }

    public List<TEntity> getAll() {
        return entitiesStore;
    }

    public TEntity save(TEntity item) {
        Optional<TEntity> authorMaxId = entitiesStore.stream().max(Comparator.comparing(TEntity::getId));
        int lastIndex = authorMaxId.map(TEntity::getId).orElseGet(() -> MIN_OBJECT_INDEX);
        item.setId(++lastIndex);
        entitiesStore.add(item);
        return item;
    }

    public TEntity getById(int id) {
        List<TEntity> results = entitiesStore.stream().filter(e -> e.getId() == id ).collect(Collectors.toList());
        return (results != null && results.size() == 1) ? results.get(0) : null;
    }
}
