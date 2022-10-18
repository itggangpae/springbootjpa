package com.adamsoft.jpa;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.adamsoft.jpa.domain.MemoDTO;
import com.adamsoft.jpa.service.MemoService;

@SpringBootTest
public class ServiceTest {
	@Autowired
	MemoService memoService;
	
	@Test
	public void testInsert() {
		MemoDTO dto = MemoDTO.builder()
				.memoText("서비스 테스트")
				.build();
		MemoDTO result = 
				memoService.insertMemo(dto);
		System.out.println(result);
	}

}
