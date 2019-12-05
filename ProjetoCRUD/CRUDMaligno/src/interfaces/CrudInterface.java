package interfaces;

import java.sql.SQLException;
import java.util.List;

public interface CrudInterface<T, K> {

    public boolean create(T entidade) throws Exception;
    
    public List<T> read(K filtro);
    
    public List<T> readAll();

    public boolean existe(K cod) throws Exception;

    public void update(T entidade) throws Exception;

    public void delete(K cod) throws Exception;
}
