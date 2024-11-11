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
@Table(name = "p_region")
public class Region extends Timestamped {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID regionId;
	@Column(nullable = false)
	private String regionNm;
}
