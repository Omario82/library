package com.mycompany.book.domain.logic;

import com.mycompany.book.service.common.IHaveId;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

public interface IEntityDAService<TEntity extends IHaveId> {

    List<TEntity> getAll();
    TEntity save(TEntity item) ;
    TEntity getById(int id);
}
