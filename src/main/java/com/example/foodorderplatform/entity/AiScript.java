package com.example.foodorderplatform.entity;

import com.example.foodorderplatform.auditing.Timestamped;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.UUID;

/*
* check
* */

@Getter
@Entity
@Table(name = "p_ai_script")
public class AiScript extends Timestamped {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	@Column
	private String scriptQuestion;	// 스크립트질문
	@Column
	private String scriptAnswer;	// 스크립트 답변

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
}
