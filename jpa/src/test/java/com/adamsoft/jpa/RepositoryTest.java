package com.adamsoft.jpa;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;

import com.adamsoft.jpa.entity.Memo;
import com.adamsoft.jpa.persistence.MemoRepository;

@SpringBootTest
public class RepositoryTest {
	//Repository 주입
	@Autowired
	MemoRepository memoRepository;
	
	//주입 테스트
	//@Test
	public void DITest(){
		System.out.println(memoRepository);
	}
	
	//@Test
	public void insert(){
		//데이터 100개 삽입
		IntStream.rangeClosed(1,  100)
			.forEach(i -> {
				Memo memo = 
					Memo.builder()
					.memoText("Sample..." + i)
					.build();
				memoRepository.save(memo);
			});
	}
	
	//@Test
	public void update(){
		//기본키의 값이 존재하면 수정
		//존재하지 않으면 삽입
		Memo memo = Memo.builder()
				.mno(1L)
				.memoText("수정")
				.build();
		memoRepository.save(memo);
	}

	//테이블의 전체 데이터 조회
	//@Test
	public void getList() {
		List<Memo> list = 
				memoRepository.findAll();
		list.stream().forEach(memo -> {
			System.out.println(memo);
		});
		
	}
	
	//@Test
	public void getMemo() {
		Optional <Memo> result = 
			memoRepository.findById(101L);
		if(result.isPresent()) {
			System.out.println(result.get());
		}else {
			System.out.println("데이터 없음");
		}
	}
	
	//@Test
	public void deleteMemo() {
		memoRepository.deleteById(100L);
		memoRepository.delete(
				Memo.builder().mno(99L).build());
		
	}
	
	//페이지 단위로 가져오기
	//@Test
	public void paging() {
		//0번 페이지 10개의 데이터 조회
		Pageable page = 
			PageRequest.of(0,  10);
		Page<Memo> result = 
				memoRepository.findAll(page);
		
		result.get().forEach(memo -> {
			System.out.println(memo);
		});
	}
	
	//정렬 수행
	//@Test
	public void sorting() {
		//0번 페이지 10개의 데이터 조회
		Sort sort = Sort.by("mno").descending();
		Pageable page = 
			PageRequest.of(0,  10, sort);
		Page<Memo> result = 
				memoRepository.findAll(page);
		
		result.get().forEach(memo -> {
			System.out.println(memo);
		});
	}
	
	//@Test
	public void naming1() {
		List<Memo> list = 
			memoRepository.findByMnoBetween(
				10L, 30L);
		list.stream().forEach(memo -> {
			System.out.println(memo);
		});
	}
	
	//@Test
	public void naming2() {
		Pageable page = PageRequest.of(1,  5);
		Page<Memo> list = 
			memoRepository.findByMnoBetween(
				10L, 30L, page);
		list.get().forEach(memo -> {
			System.out.println(memo);
		});
	}
	
	//@Test
	public void naming3() {
		List<Memo> list = 
			memoRepository.
				findByMnoBetweenOrderByMnoDesc(
				10L, 30L);
		list.stream().forEach(memo -> {
			System.out.println(memo);
		});
	}
	
	//@Test
	@Commit
	@Transactional
	//삽입 이나 삭제 및 수정하는 메서드를 호출할 때는
	//Transaction을 적용해주는 것이 좋습니다.
	//삭제의 경우는 필수로 지정해야 하는 경우가 많음
	//트랜잭션은 Service 계층에서 적용합니다.
	public void naming4() {
		memoRepository.
		deleteByMnoLessThan(10L);
	}
	
	//@Test
	public void queryMethod1() {
		Long mno = 10L;
		String memoText = "데이터 수정";
		memoRepository.updateMemoText(
				mno, memoText);
	}
	
	//@Test
	public void queryMethod2() {
		Memo memo = Memo.builder()
				.mno(11L)
				.memoText("데이터를 수정")
				.build();
		memoRepository.updateMemoText(
				memo);
	}
	
	//@Test
	public void testSelectQuery() {
		Pageable pageable = 
			PageRequest.of(0,  10, 
				Sort.by("mno").descending());
		Page <Memo> page = 
			memoRepository.getListWithQuery(
				50L, pageable);
		page.get().forEach(memo -> {
			System.out.println(memo);
		});

	}
	

	//@Test
	public void testSelectQueryObject() {
		Pageable pageable = 
			PageRequest.of(0,  10, 
				Sort.by("mno").descending());
		Page <Object[]> page = 
			memoRepository.getListWithQueryObject(
				50L, pageable);
		for(Object [] ar : page) {
			//System.out.println(Arrays.toString(ar));
			//mno 만 추출해서 사용
			Long mno = (Long)ar[0];
			System.out.println(mno);
		}

	}
	
	@Test
	public void nativeSQL() {
		List<Object []> list = 
				memoRepository.getNativeSQL();
		list.stream().forEach(ar -> {
			System.out.println(
					Arrays.toString(ar));
		});
	}

	
}




