package repository;

import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import entity.Employee;

/*
 * 
 * caching employee objects in Memcached database
 * 
 * 
 * */

@Repository
public class EmployeeMemcachedRepositoryImpl implements EmployeeRepository {

	@Autowired
	private CacheManager cacheMgr;
	private Cache cache;
	private String cn = "EmployeeMem";

	@PostConstruct
	public void init() {
		this.cache = this.cacheMgr.getCache(this.cn);

	}

	@Override
	public void saveEmployee(Employee emp) {
		this.cache.put(emp.getId(), emp);
	}

	@Override
	public Employee findEmployee(String id) {

		return (Employee) this.cache.get(id, Employee.class);

	}

	@Override
	public void updateEmployee(Employee emp) {
		Employee empl = this.cache.get(emp.getId(), Employee.class);
		empl.setId(emp.getId());

		this.cache.put(emp.getId(), emp);

	}

	/*
	 * not implemented yet
	 */
	@Override
	public Map<Object, Object> findAllEmployee() {
		// write code to return all objects from Memcached cache
		return null;
	}

	@Override
	public void deleteEmployee(String id) {
		this.cache.evict(id);
	}
}
