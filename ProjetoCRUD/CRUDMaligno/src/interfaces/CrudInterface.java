package interfaces;

import java.sql.SQLException;

public interface CrudInterface<T, K> {

    public void create(T entidade) throws Exception;

    public boolean existe(K cod) throws Exception;

    public void update(T entidade) throws Exception;

    public void delete(K cod) throws Exception;
}
