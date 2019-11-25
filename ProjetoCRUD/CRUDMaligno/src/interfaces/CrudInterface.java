package interfaces;

import java.sql.SQLException;

public interface CrudInterface<T> {

	public void create(T entidade) throws Exception;
	public boolean raExiste(String cod) throws Exception;
	public void update(T entidade) throws Exception;
	public void delete(String cod) throws Exception;
}
