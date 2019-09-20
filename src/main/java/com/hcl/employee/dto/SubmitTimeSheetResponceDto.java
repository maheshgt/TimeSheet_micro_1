package com.hcl.employee.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SubmitTimeSheetResponceDto {

	private LocalDate fromDate;
	private LocalDate toDate;
	private int totalHrs;
	private int noOfDays;

}
