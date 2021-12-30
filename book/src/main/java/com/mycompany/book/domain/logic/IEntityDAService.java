package com.mycompany.book.domain.logic;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

public interface IEntityDAService<TEntity> {

    List<TEntity> getAll() throws IOException;
    TEntity save(TEntity item) ;
    List<TEntity> findById(int id);
}
