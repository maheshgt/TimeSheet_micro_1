package com.hcl.employee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.employee.dto.ApprovedDto;
import com.hcl.employee.dto.MyTimeSheetResponseListDto;
import com.hcl.employee.dto.SubmitTimeSheetDto;
import com.hcl.employee.dto.SubmitTimeSheetResponceDto;
import com.hcl.employee.service.EmployeeService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

	/**
	 * @author User1
	 * @param id
	 * @param submitDto
	 * @return
	 */
	@PostMapping("/submit/{id}")
	public ResponseEntity<SubmitTimeSheetResponceDto> submitTimeSheet(@PathVariable int id,
			@RequestBody SubmitTimeSheetDto submitDto) {
		log.info("user sumbmit time sheet controller method");

		ResponseEntity<SubmitTimeSheetResponceDto> submitTimeSheetResponceDto = employeeService.submitTimeSheet(id,
				submitDto);
		return submitTimeSheetResponceDto;

	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	@PutMapping("/approved/{id}")
	public ResponseEntity<ApprovedDto> approvedTimeSheet(@PathVariable int id) {
		log.info("RM approved time sheet controller method");
		return new ResponseEntity<>(employeeService.approvedTimeSheet(id), HttpStatus.OK);
	}

	/**
	 * @param id
	 * @return
	 */
	@GetMapping("/view/{id}")
	public ResponseEntity<MyTimeSheetResponseListDto> viewMyTimeSheet(@PathVariable int id) {
		log.info("user view the status of his sumbmitted time sheet controller method");
		return new ResponseEntity<>(employeeService.viewTimeSheet(id), HttpStatus.OK);
	}

}
