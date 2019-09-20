package com.hcl.employee.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.hcl.employee.dto.ApprovedDto;
import com.hcl.employee.dto.MyTimeSheetResponseListDto;
import com.hcl.employee.dto.Submit;
import com.hcl.employee.dto.SubmitTimeSheetDto;
import com.hcl.employee.dto.SubmitTimeSheetResponceDto;
import com.hcl.employee.entity.Employee;
import com.hcl.employee.exception.RMException;
import com.hcl.employee.repository.EmployeeRepository;
import com.hcl.employee.util.EmpUtil;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;

	RestTemplate restTemplate = new RestTemplate();

	@Override
	public ResponseEntity<SubmitTimeSheetResponceDto> submitTimeSheet(int id, SubmitTimeSheetDto submitDto)
			throws HttpStatusCodeException {

		log.info("user sumbmit time sheet service method");

		Optional<Employee> emp = employeeRepository.findById(id);
		Submit submit = new Submit();
		submit.setRmId(emp.get().getId());
		BeanUtils.copyProperties(submitDto, submit);
		submit.setNoOfHrsPerDay(submitDto.getHrsPerDay());

		ResponseEntity<SubmitTimeSheetResponceDto> submitTimeSheetResponceDto = restTemplate
				.postForEntity(EmpUtil.SUBMIT + id, submit, SubmitTimeSheetResponceDto.class);
		return submitTimeSheetResponceDto;
	}

	@Override
	public ApprovedDto approvedTimeSheet(int id) {

		log.info("rm approved  time sheet service method");

		Optional<Employee> emp = employeeRepository.findById(id);
		if (!emp.get().getRole().equals(EmpUtil.RM))
			throw new RMException(EmpUtil.RM_EXCEPTION);
		ApprovedDto approvedDto = restTemplate.getForObject(EmpUtil.Approve + id, ApprovedDto.class);
		return approvedDto;
	}

	@Override
	public MyTimeSheetResponseListDto viewTimeSheet(int id) {

		log.info("user view the sumbmitted time sheet service method");

		MyTimeSheetResponseListDto myTimeSheetResponseListDto = restTemplate.getForObject(EmpUtil.VIEW + id,
				MyTimeSheetResponseListDto.class);
		return myTimeSheetResponseListDto;
	}

}
