package com.adamsoft.jpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adamsoft.jpa.domain.MemoDTO;
import com.adamsoft.jpa.entity.Memo;
import com.adamsoft.jpa.persistence.MemoRepository;

import lombok.RequiredArgsConstructor;

@Service
//final 속성을 주입받는 생성자 - @Autowired가 필요없음
@RequiredArgsConstructor
public class MemoServiceImpl implements MemoService {
	//@Autowired
	private final MemoRepository memoRepository;
	
	@Override
	public MemoDTO insertMemo(MemoDTO memoDTO) {
		//MemoDTO 를 가지고 데이터베이스 작업에 사용할 Entity로 변환
		Memo memo = Memo.builder()
				.memoText(memoDTO.getMemoText())
				.build();
		//데이터베이스 작업 수행
		Memo m = memoRepository.save(memo);
		
		//Entity를 Controller에게 전송하기 위해서
		//DTO로 변환
		MemoDTO result = MemoDTO.builder()
				.mno(m.getMno())
				.memoText(m.getMemoText())
				.build();
		
		return result;
	}

}
