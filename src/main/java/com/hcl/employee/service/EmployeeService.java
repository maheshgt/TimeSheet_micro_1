package com.hcl.employee.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hcl.employee.dto.ApprovedDto;
import com.hcl.employee.dto.MyTimeSheetResponseListDto;
import com.hcl.employee.dto.SubmitTimeSheetDto;
import com.hcl.employee.dto.SubmitTimeSheetResponceDto;

@Service
public interface EmployeeService {

	ResponseEntity<SubmitTimeSheetResponceDto> submitTimeSheet(int id, SubmitTimeSheetDto submitDto);

	ApprovedDto approvedTimeSheet(int id);

	MyTimeSheetResponseListDto viewTimeSheet(int id);

}
