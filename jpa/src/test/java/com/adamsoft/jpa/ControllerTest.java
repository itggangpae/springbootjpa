package com.adamsoft.jpa;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.adamsoft.jpa.controller.MemoController;
import com.adamsoft.jpa.domain.MemoDTO;
import com.adamsoft.jpa.service.MemoService;
import com.google.gson.Gson;

@WebMvcTest(MemoController.class)
public class ControllerTest {
	@Autowired
	private MockMvc mockMvc;

	//테스트에 필요한 인스턴스 생성
	@MockBean
	MemoService memoService;
	
	@Test
	public void insertTest() {
		MemoDTO memoDTO = 
				MemoDTO.builder()
				.memoText("코드로 Controller 테스트")
				.build();
		//DTO를 JSON 문자열로 변환
		Gson gson = new Gson();
		String param = gson.toJson(memoDTO);
		
		try {
		mockMvc.perform(
				post("/insert")
				.content(param)
				.contentType(
					MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andDo(print());
		}catch(Exception e) {}
		
	}
}



