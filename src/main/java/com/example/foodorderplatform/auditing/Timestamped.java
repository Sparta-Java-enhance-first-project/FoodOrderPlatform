package com.example.foodorderplatform.auditing;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass	// 이 클래스를 상속받는 엔티티에 매핑 정보만 제공하는 어노테이션
@EntityListeners(AuditingEntityListener.class)	// Auditing 이벤트 리스너 등록
public abstract class Timestamped {

	@CreatedDate	// entity 생성 시 시간 자동 저장
	@Column(updatable = false)	// null, update 불가
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime createAt;

	@CreatedBy	// entity 생성 시 사용자 자동 저장
	@Column(updatable = false)	// null, update 불가
	private String createdBy;

	@LastModifiedDate	// entity 수정 시 시간 자동 저장
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime modifiedAt;

	@LastModifiedBy	// entity 수정 시 사용자 자동 저장
	@Column
	private String modifiedBy;

	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime deletedAt;

	@LastModifiedBy	// 삭제 시 사용자 자동 저장
	@Column
	private String deletedBy;
}
