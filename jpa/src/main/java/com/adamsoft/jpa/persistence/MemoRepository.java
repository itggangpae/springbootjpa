package com.adamsoft.jpa.persistence;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.adamsoft.jpa.entity.Memo;

public interface MemoRepository 
	extends JpaRepository<Memo, Long>{
	
	//mno 2개를 입력받아서 2개 사이에 있는 데이터를 조회
	public List<Memo> findByMnoBetween(
			Long from, Long to);

	public Page<Memo> findByMnoBetween(
			Long from, Long to, Pageable page);
	
	public List<Memo> 
		findByMnoBetweenOrderByMnoDesc(
			Long from, Long to);
	
	//삭제하는 메서드
	public void deleteByMnoBetween(
			Long from, Long to);
	
	
	public void deleteByMnoLessThan(
			Long from);
	
	//데이터를 수정하는 메서드
	//Memo는 테이블 이름이 아니고 Entity 이름입니다.
	//속성도 Entity에서 속성 이름을 사용해야 합니다.
	@Transactional
	@Modifying
	@Query("update Memo m "
			+ "set m.memoText = :memoText "
			+ "where m.mno = :mno")
	public int updateMemoText(
			@Param("mno") Long mno,
			@Param("memoText") String memoText);
	
	@Transactional
	@Modifying
	@Query("update Memo m "
			+ "set m.memoText = :#{#param.memoText} "
			+ "where m.mno = :#{#param.mno}")
	public int updateMemoText(
			@Param("param") Memo memo);
	
	//페이지 단위로 조회
	//countQuery 가 없어도 조회는 가능하지만
	//나중에 페이지 개수를 알 수 없음
	@Query(value="select m "
			+ "from Memo m "
			+ "where m.mno > :mno",
			countQuery = "select count(m) "
					+ "from Memo m "
					+ "where m.mno > :mno")
	Page<Memo> getListWithQuery(
			@Param("mno") Long mno,
			Pageable pageable);
	
	
	@Query(value="select m.mno, m.memoText, CURRENT_DATE "
			+ "from Memo m "
			+ "where m.mno > :mno",
			countQuery = "select count(m) "
					+ "from Memo m "
					+ "where m.mno > :mno")
	Page<Object[]> getListWithQueryObject(
			@Param("mno") Long mno,
			Pageable pageable);
	
	@Query(value="select * "
			+ "from tbl_memo "
			+ "where mno > 0",
			nativeQuery=true)
	List<Object []> getNativeSQL();
}







