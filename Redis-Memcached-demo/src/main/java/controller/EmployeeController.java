package controller;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import entity.Employee;
import repository.EmployeeRepository;

@RestController
@RequestMapping(value = "demo/api/employee")
public class EmployeeController {

	@Autowired
	private EmployeeRepository empRepo;

	@RequestMapping(method = RequestMethod.POST)
	public void saveEmployee(@RequestBody Employee emp) {
		empRepo.saveEmployee(emp);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Employee findEmployee(@PathVariable("id") String id) {
		return empRepo.findEmployee(id);
	}

	@RequestMapping(method = RequestMethod.PUT)
	public void updateEmployee(@RequestBody Employee emp) {
		empRepo.updateEmployee(emp);

	}

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public Map<Object, Object> findAllEmployee() {
		return empRepo.findAllEmployee();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void deleteEmployee(@PathVariable("id") String id) {
		empRepo.deleteEmployee(id);
	}

}
