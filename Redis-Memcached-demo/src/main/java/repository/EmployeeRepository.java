package repository;

import java.util.Map;
import entity.Employee;

public interface EmployeeRepository {

	void saveEmployee(Employee emp);

	Employee findEmployee(String id);

	void updateEmployee(Employee emp);

	Map<Object, Object> findAllEmployee();

	void deleteEmployee(String id);

}
