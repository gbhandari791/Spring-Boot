package com.springboot.i.modelmapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ModelMappingController {
	
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private EmployeeMapper employeeMapper;
	
	@GetMapping("/userMapping")
	public ResponseEntity<String> mappperExample(){
		
		UserDto dto = new UserDto(1, "Jogn", "john@gmail.com");		
		UserEntity entity = new UserEntity(2, "peter", "peter@gmail.com");
		
		System.out.println(userMapper.dtoToEntity(entity));
		System.out.println(userMapper.entityToDto(dto));
		
		System.out.println("---------------------------------------");
		
		EmployeeDto employeeDto = new EmployeeDto(1, "Emp-1");
		EmployeEntity employeEntity = new EmployeEntity(2, "Emp-2");
		
		System.out.println(this.employeeMapper.toDTO(employeEntity));
		System.out.println(this.employeeMapper.toEntity(employeeDto));
		
		return new ResponseEntity<String>("", HttpStatus.OK);
	}

}
