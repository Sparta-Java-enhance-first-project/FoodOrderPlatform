package com.example.foodorderplatform.entity;

import com.example.foodorderplatform.auditing.Timestamped;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.UUID;
import lombok.NoArgsConstructor;

/*
* check
* */

@Getter
@Entity
@Table(name = "p_ai_script")
@NoArgsConstructor
public class AiScript extends Timestamped {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	@Column(columnDefinition = "TEXT")
	private String scriptQuestion;	// 스크립트질문
	@Column(columnDefinition = "TEXT")
	private String scriptAnswer;	// 스크립트 답변

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	public AiScript(String scriptQuestion, String scriptAnswer, User user){
		this.scriptQuestion = scriptQuestion;
		this.scriptAnswer = scriptAnswer;
		this.user = user;
	}
}
