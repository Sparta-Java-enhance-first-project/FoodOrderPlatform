package com.example.foodorderplatform.entity;

import com.example.foodorderplatform.auditing.Timestamped;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.UUID;

@Getter
@Entity
@Table(name = "p_address")
public class Address extends Timestamped {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID addressId;
	@Column(nullable = false)
	private String addressName;
}
