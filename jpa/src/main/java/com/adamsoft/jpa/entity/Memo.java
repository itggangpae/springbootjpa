package com.adamsoft.jpa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="tbl_memo")

@ToString
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Memo {
	//기본키 설정 - Hibernate 가 알아서 결정
	@Id
	@GeneratedValue(
			strategy=GenerationType.AUTO)
	private Long mno;
	@Column(length=200, nullable=false)
	private String memoText;
}







