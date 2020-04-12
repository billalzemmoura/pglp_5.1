package pglp.pglp51;

public interface DAO <T>{
public T create(T obj);
public T find(T obj);
public T update(T obj);
public T delete(T obj);
public DAO <T> getDAO();

}
