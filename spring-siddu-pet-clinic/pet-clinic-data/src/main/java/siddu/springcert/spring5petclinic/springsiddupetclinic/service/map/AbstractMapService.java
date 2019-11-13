package siddu.springcert.spring5petclinic.springsiddupetclinic.service.map;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

import siddu.springcert.spring5petclinic.springsiddupetclinic.model.BaseEntity;

public abstract class AbstractMapService<T extends BaseEntity,ID extends Long> {
	
	protected Map<ID,T> map = new HashMap<>();
	
	public T findById(ID id){
		return map.get(id);
	}
	
	public Set<T> findAll(){
		return new HashSet<>(map.values());
	}
	
	public T save(T object) {
		if(object!=null) {
			if(object.getId() == null) {
				object.setId(getNextId());
			}
			map.put((ID) object.getId(), object);
		}
		else {
			throw new RuntimeException("Object cannot be null");
		}
		return object;
	}
	
	public void deleteById(ID id) {
		map.remove(id);
	}
	
	public void delete(T object) {
		map.entrySet().removeIf(entry -> entry.getValue().equals(object));
	}
	
	private Long getNextId() {
		Long nextId=null;
		try {
			nextId = Collections.max(map.keySet())+1;
		}
		catch(NoSuchElementException e) {
			nextId=1L;
		}
		return nextId;
		
	}

}
