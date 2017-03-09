package repository;

import java.util.Map;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.interceptor.SimpleKeyGenerator;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import entity.Employee;
/*
 * 
 * caching employee objects in redis database
 * 
 * 
 * */
@Repository
@Primary
public class EmployeeRedisRepositoryImpl implements EmployeeRepository {

	@Autowired
	private RedisTemplate<Object, Object> redisTemplate;
	private HashOperations<Object, Object, Object> hashOps;
	private static String KEY = "Employee";
	

	@PostConstruct
	private void init() {
		hashOps = redisTemplate.opsForHash();
	}

	@Override
	@CachePut(value="EmployeeRedis",key="#emp.id")
	public void saveEmployee(Employee emp) {
		hashOps.put(KEY, emp.getId(), emp);

	}

	@Override
	@CachePut(value="EmployeeRedis",key="#id") // caching, cache name=EmployeeRedis
	public Employee findEmployee(String id) {
		return (Employee) hashOps.get(KEY, id);
	}

	@Override
	@CachePut(value="EmployeeRedis",key="#emp.id")
	public void updateEmployee(Employee emp) {
		hashOps.put(KEY, emp.getId(), emp);
	}

	@Override
	@CachePut(value="EmployeeRedis") // caching, cache name=EmployeeRedis
	public Map<Object, Object> findAllEmployee() {
		return hashOps.entries(KEY);
	}

	@Override
	@CacheEvict(value="EmployeeRedis",key="#id")
	public void deleteEmployee(String id) {
		hashOps.delete(KEY, id);
	}
	
	

}
