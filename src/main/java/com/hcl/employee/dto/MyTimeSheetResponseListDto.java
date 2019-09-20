package com.hcl.employee.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MyTimeSheetResponseListDto {
	private List<MyTimeSheetResponseDto> myTimeSheetResponseDto;
}
