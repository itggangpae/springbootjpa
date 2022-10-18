package com.adamsoft.jpa.service;

import com.adamsoft.jpa.domain.MemoDTO;

public interface MemoService {
	//데이터 삽입하는 메서드
	//JPA에서는 삽입이나 수정 작업 후에
	//삽입 이나 수정한 Entity 가 리턴됩니다.
	public MemoDTO insertMemo(MemoDTO memoDTO);
}
