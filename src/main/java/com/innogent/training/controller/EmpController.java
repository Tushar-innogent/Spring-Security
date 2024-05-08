package com.innogent.training.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.innogent.training.IService.IEmpService;
import com.innogent.training.model.EmployeeModel;

@CrossOrigin
@RestController
public class EmpController {
	@Autowired
	private IEmpService service;

	@GetMapping("/get/all")
	public ResponseEntity<List<EmployeeModel>> getAllEmployee() {
		List<EmployeeModel> EmployeeModelList = service.getAllEmp();
		return ResponseEntity.status(HttpStatus.FOUND).body(EmployeeModelList);
	}

	@GetMapping("/get/id/{id}")
	public ResponseEntity<EmployeeModel> getEmployeeById(@PathVariable Long id) {
		EmployeeModel e = service.getEmpById(id);
		if (e != null) {
			return ResponseEntity.status(HttpStatus.FOUND).body(e);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping("/add")
	public ResponseEntity<?> saveEmployeeModel(@RequestBody EmployeeModel EmployeeModel) {
		try {
			EmployeeModel e = service.addEmp(EmployeeModel);
			return ResponseEntity.status(HttpStatus.CREATED).body(e);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error occured while saving an EmployeeModel");
		}
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateEmployeeModel(@PathVariable Long id, @RequestBody EmployeeModel EmployeeModel) {
		try {

			EmployeeModel e = service.getEmpById(id);
			if (e == null)
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

			EmployeeModel.setEmpId(id);
			EmployeeModel EmployeeModell = service.addEmp(EmployeeModel);

//			EmployeeModel e = service.updateEmployeeModel(id, EmployeeModel);
			return ResponseEntity.status(HttpStatus.OK).body(EmployeeModell);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Student Not Found");
		}
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteEmployeeModel(@PathVariable Long id) {
		try {
			String s = service.deleteEmp(id);
			return ResponseEntity.status(HttpStatus.OK).body(s);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Student Not Found");
		}
	}

	@GetMapping("/get/order/name/{order}")
	public ResponseEntity<?> getAllEmployeeModelOrderByName(@PathVariable String order) {
		List<EmployeeModel> EmployeeModelList = service.getAllEmpOrderByName(order);
		if (EmployeeModelList == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not Found");
		}
		return ResponseEntity.status(HttpStatus.FOUND).body(EmployeeModelList);
	}

	@GetMapping("/get/order/sal/{order}")
	public ResponseEntity<?> getAllEmployeeModelOrderBySal(@PathVariable String order) {
		List<EmployeeModel> EmployeeModelList = service.getAllEmpOrderBySal(order);
		if (EmployeeModelList == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not Found");
		}
		return ResponseEntity.status(HttpStatus.FOUND).body(EmployeeModelList);
	}

	@GetMapping("/get/EmployeeModel/{name}")
	public ResponseEntity<?> getEmployeeModelByName(@PathVariable String name) {
		EmployeeModel e = service.getEmpByName(name);
		if (e == null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not Found");
		return ResponseEntity.status(HttpStatus.FOUND).body(e);
	}

	@GetMapping("/get/sal/greater/{sal}")
	public ResponseEntity<?> getSalGreaterThan(@PathVariable Double sal) {
		List<EmployeeModel> EmployeeModelList = service.getEmpSalGreaterThan(sal);
		if (EmployeeModelList == null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not Found");
		return ResponseEntity.status(HttpStatus.FOUND).body(EmployeeModelList);
	}

	@GetMapping("/get/sal/lesser/{sal}")
	public ResponseEntity<?> getSalLesserThan(@PathVariable Double sal) {
		List<EmployeeModel> EmployeeModelList = service.getEmpSalLesserThan(sal);
		if (EmployeeModelList == null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not Found");
		return ResponseEntity.status(HttpStatus.FOUND).body(EmployeeModelList);
	}

	@PostMapping("/add/all")
	public String addMultipleEmployeeModel(@RequestBody List<EmployeeModel> EmployeeModelList) {
		return service.allMultipleEmp(EmployeeModelList);
	}

	@GetMapping("/get/emp/{size}")
	public List<EmployeeModel> getEmpByPage(@PathVariable Integer size) {
		return service.getEmpByPage(size);
	}

	@GetMapping("/get/sal/{sal}")
	public ResponseEntity<?> getEmpBysal(@PathVariable Double sal) {
		EmployeeModel emp = service.getEmpBySal(sal);
		if (emp == null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There is No Employee with salary " + sal);
		return ResponseEntity.status(HttpStatus.OK).body(emp);
	}

	@GetMapping("/get/address/{add}")
	public ResponseEntity<?> getEmpByAdd(@PathVariable String add) {
		List<EmployeeModel> emp = service.getEmpOfAdd(add);
		if (emp == null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There is No Employee with address " + add);
		return ResponseEntity.status(HttpStatus.OK).body(emp);
	}

	@GetMapping("/get/group/address/count")
	public ResponseEntity<?> getEmpCountGroupAddress() {
		try {
			Map<String, Integer> empMap = service.getEmpCountByAddress();
			return ResponseEntity.status(HttpStatus.FOUND).body(empMap);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.FOUND).body(e.getMessage());

		}
	}

	@GetMapping("/get/group/address/sal")
	public ResponseEntity<?> getEmpSalGroupAddress() {
		Map<String, Double> empMap = service.getEmpSalByAddress();
		return ResponseEntity.status(HttpStatus.FOUND).body(empMap);
	}

}
