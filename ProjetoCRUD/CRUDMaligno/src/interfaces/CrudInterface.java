package interfaces;

import java.util.List;

public interface CrudInterface<T, K> {

    public boolean create(T entidade) throws Exception;
    
    public List<T> read(K filtro) throws Exception;
    
    public List<T> readAll() throws Exception;

    public boolean existe(K cod) throws Exception;

    public boolean update(T entidade) throws Exception;

    public void delete(K cod) throws Exception;
}
