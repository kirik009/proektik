package lab5.service;

import java.util.List;

import lab5.entity.AbstractEntity;

public interface Service<T extends AbstractEntity> {
	T read(Long id);

	List<T> read();

	void save(T entity);

	void delete(Long id);
	
	void edit (T entity);
}
