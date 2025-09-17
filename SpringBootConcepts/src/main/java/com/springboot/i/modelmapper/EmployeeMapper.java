package com.springboot.i.modelmapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {

	@Autowired
	private ModelMapper modelMapper;
	
	
	@Autowired
	public EmployeeMapper(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
		configureMapping();
	}

	private void configureMapping() {
		
		// DTO → Entity
		modelMapper.typeMap(EmployeeDto.class, EmployeEntity.class).addMappings(mapper -> {
		    mapper.map(EmployeeDto::getEmployeeId, EmployeEntity::setId);
		    mapper.map(EmployeeDto::getEmployeeName, EmployeEntity::setName);
		});

		// Entity → DTO
		modelMapper.typeMap(EmployeEntity.class, EmployeeDto.class).addMappings(mapper -> {
		    mapper.map(EmployeEntity::getId, EmployeeDto::setEmployeeId);
		    mapper.map(EmployeEntity::getName, EmployeeDto::setEmployeeName);
		});
		
	}

	public EmployeeDto toDTO(EmployeEntity emEntity) {
		return modelMapper.map(emEntity, EmployeeDto.class);
	}

	public EmployeEntity toEntity(EmployeeDto dto) {
		return modelMapper.map(dto, EmployeEntity.class);
	}

}
