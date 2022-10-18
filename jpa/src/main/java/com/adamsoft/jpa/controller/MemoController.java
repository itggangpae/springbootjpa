package com.adamsoft.jpa.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.adamsoft.jpa.domain.MemoDTO;
import com.adamsoft.jpa.service.MemoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MemoController {
	private final MemoService memoService;

	//데이터 삽입 요청
	@PostMapping("/insert")
	public ResponseEntity<MemoDTO>
		insertMemo(
			@RequestBody MemoDTO memoDTO){
		MemoDTO result = 
			memoService.insertMemo(memoDTO);
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(result);
	}
}










