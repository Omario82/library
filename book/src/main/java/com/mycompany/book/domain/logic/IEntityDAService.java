package book.domain.logic;

import java.util.List;

public interface IEntityDAService<TEntity> {

    List<TEntity> getAll() ;
    TEntity save(TEntity item) ;
    List<TEntity> findById(int id);
}
