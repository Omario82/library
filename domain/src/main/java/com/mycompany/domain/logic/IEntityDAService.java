package com.mycompany.domain.logic;

import java.util.List;

public interface IEntityDAService<TEntity> {

    List<TEntity> getAll() ;
    TEntity save(TEntity item) ;
    TEntity getById(int id);
}
