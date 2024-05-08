package com.innogent.training.empServiceImpl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.innogent.training.IService.IEmpService;
import com.innogent.training.entity.Employee;
import com.innogent.training.mapper.EmpMapper;
import com.innogent.training.model.EmployeeModel;
import com.innogent.training.repository.EmpRepo;

@Service
public class EmpServiceImpl implements IEmpService {

	@Autowired
	private EmpRepo repo;

	@Autowired
	private EmpMapper mapper;

	@Override
	public EmployeeModel getEmp(Long id) {
		return mapper.entityToModel(repo.findById(id).get());
	}

	@Override
	public EmployeeModel addEmp(EmployeeModel employee) {
		Employee empEntity = mapper.modelToEntity(employee);
		Employee empResult = repo.save(empEntity);
		return mapper.entityToModel(empResult);
	}

	@Override
	public EmployeeModel updateEmp(Long id, EmployeeModel emp) {
		emp.setEmpId(id);
		Employee e = mapper.modelToEntity(emp);
		return mapper.entityToModel(repo.save(e));
	}

	@Override
	public String deleteEmp(Long id) {
		Employee e = repo.findById(id).get();
		if (e == null)
			return "Student Not Found";
		repo.deleteById(id);
		return "Student Deleted";
	}

	@Override
	public List<EmployeeModel> getAllEmp() {
		return mapper.entityToModel(repo.findAll());
	}

	@Override
	public EmployeeModel getEmpById(Long id) {
		Employee e = repo.findById(id).get();
		if (e != null)
			return mapper.entityToModel(e);
		return null;
	}

	@Override
	public List<EmployeeModel> getAllEmpOrderByName(String order) {
		if (order.equals("asc"))
			return mapper.entityToModel(repo.findAll(Sort.by(Direction.ASC, "empName")));
		else if (order.equals("desc"))
			return mapper.entityToModel(repo.findAll(Sort.by(Direction.DESC, "empName")));
		return null;
	}

	@Override
	public List<EmployeeModel> getAllEmpOrderBySal(String order) {
		if (order.equals("asc"))
			return mapper.entityToModel(repo.findAll(Sort.by(Direction.ASC, "sal")));
		else if (order.equals("asc"))
			return mapper.entityToModel(repo.findAll(Sort.by(Direction.DESC, "sal")));
		return null;
	}

	@Override
	public EmployeeModel getEmpByName(String name) {
		return mapper.entityToModel(repo.findByEmpName(name));
	}

	@Override
	public List<EmployeeModel> getEmpSalGreaterThan(Double sal) {
		return mapper.entityToModel(repo.findBySalGreaterThan(sal));
	}

	@Override
	public List<EmployeeModel> getEmpSalLesserThan(Double sal) {
		return mapper.entityToModel(repo.findBySalLessThan(sal));
	}

	@Override
	public String allMultipleEmp(List<EmployeeModel> empList) {
		repo.saveAll(mapper.modelToEntity(empList));
		return "All Employees Added";
	}

	@Override
	public List<EmployeeModel> getEmpByPage(Integer pagesize) {
		Pageable p = PageRequest.of(1, pagesize, Sort.by(Direction.ASC, "sal"));
		Page<Employee> pageList = repo.findAll(p);
		List<Employee> empList = pageList.getContent();
		return mapper.entityToModel(empList);
	}

	@Override
	public EmployeeModel getEmpBySal(Double sal) {
		Employee e = repo.getEmpBySal(sal);
		if (e == null)
			return null;
		return mapper.entityToModel(e);
	}

	@Override
	public List<EmployeeModel> getEmpOfAdd(String add) {
		List<Employee> e = repo.getEmpOfCity(add);
		if (e == null)
			return null;
		return mapper.entityToModel(e);
	}

	@Override
	public Map<String, Integer> getEmpCountByAddress() {
		List<Object[]> o = repo.getEmpWithAddress();
		return o.stream().collect(Collectors.toMap(a -> (String) a[0], a -> Integer.valueOf(a[1] + "")));
	}

	@Override
	public Map<String, Double> getEmpSalByAddress() {
		List<Object[]> o = repo.getEmpSalGroupByAddress();
		return o.stream().collect(Collectors.toMap(a -> (String) a[0], a -> Double.valueOf(a[1] + "")));
	}

}
