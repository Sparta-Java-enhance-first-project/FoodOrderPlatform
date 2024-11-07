package com.example.foodorderplatform.entity;


import com.example.foodorderplatform.auditing.Timestamped;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

@Getter
@Entity
@Table(name = "p_region")
public class Region extends Timestamped {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long region_no;
	@Column(nullable = false)
	private String region_nm;
}