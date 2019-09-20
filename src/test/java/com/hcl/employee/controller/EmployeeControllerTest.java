package com.hcl.employee.controller;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import com.hcl.employee.dto.ApprovedDto;
import com.hcl.employee.dto.MyTimeSheetResponseDto;
import com.hcl.employee.dto.MyTimeSheetResponseListDto;
import com.hcl.employee.dto.SubmitTimeSheetDto;
import com.hcl.employee.dto.SubmitTimeSheetResponceDto;
import com.hcl.employee.service.EmployeeServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeControllerTest {

	@Mock
	EmployeeServiceImpl employeeServiceImpl;
	@InjectMocks
	EmployeeController employeeController;

	@Test
	public void testSubmitTimeSheet() {
		SubmitTimeSheetDto submitTimeSheetDto = new SubmitTimeSheetDto();
		submitTimeSheetDto.setFromDate(LocalDate.of(2019, 9, 16));
		submitTimeSheetDto.setToDate(LocalDate.of(2019, 9, 20));
		submitTimeSheetDto.setHrsPerDay(9);

		SubmitTimeSheetResponceDto submitTimeSheetResponceDto = new SubmitTimeSheetResponceDto();
		submitTimeSheetResponceDto.setFromDate(LocalDate.of(2019, 9, 16));
		submitTimeSheetResponceDto.setToDate(LocalDate.of(2019, 9, 20));
		// Mockito.when(employeeServiceImpl.submitTimeSheet(1,
		// Mockito.any())).thenReturn(submitTimeSheetResponceDto);
		ResponseEntity<SubmitTimeSheetResponceDto> submitTimeSheetResponceDto1 = employeeController.submitTimeSheet(1,
				submitTimeSheetDto);

	}

	@Test
	public void testApprovedTimeSheet() {
		ApprovedDto dto = new ApprovedDto();
		dto.setMessage("successfully");
		Mockito.when(employeeServiceImpl.approvedTimeSheet(1)).thenReturn(dto);
		ResponseEntity<ApprovedDto> dto1 = employeeController.approvedTimeSheet(1);
		assertEquals(dto1.getBody().getMessage(), dto.getMessage());
	}

	@Test
	public void testViewMyTimeSheet() {
		MyTimeSheetResponseListDto list = new MyTimeSheetResponseListDto();
		MyTimeSheetResponseDto myTimeSheetResponseDto = new MyTimeSheetResponseDto();
		List<MyTimeSheetResponseDto> list1 = new ArrayList<>();
		myTimeSheetResponseDto.setFromDate(LocalDate.of(2019, 9, 16));
		myTimeSheetResponseDto.setToDate(LocalDate.of(2019, 9, 20));
		list1.add(myTimeSheetResponseDto);
		MyTimeSheetResponseDto myTimeSheetResponseDto1 = new MyTimeSheetResponseDto();
		myTimeSheetResponseDto1.setFromDate(LocalDate.of(2019, 9, 12));
		myTimeSheetResponseDto1.setToDate(LocalDate.of(2019, 9, 14));
		list1.add(myTimeSheetResponseDto1);
		list.setMyTimeSheetResponseDto(list1);

		Mockito.when(employeeServiceImpl.viewTimeSheet(1)).thenReturn(list);
		ResponseEntity<MyTimeSheetResponseListDto> listA = employeeController.viewMyTimeSheet(1);
		assertEquals(listA.getBody().getMyTimeSheetResponseDto().size(), list.getMyTimeSheetResponseDto().size());
	}

}
