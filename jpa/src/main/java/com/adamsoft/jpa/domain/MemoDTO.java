package com.adamsoft.jpa.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
//매개변수가 없는 생성자 - Default Constructor
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MemoDTO {
	private Long mno;
	private String memoText;
}


