package com.example.foodorderplatform.entity;

import com.example.foodorderplatform.auditing.Timestamped;
import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "p_address")
@NoArgsConstructor
public class Address extends Timestamped {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	@Column(nullable = false)
	private String addressName;

	@OneToOne(mappedBy = "address")
	private User user;

	public Address(String addressName){
		this.addressName = addressName;
	}

	public void setAddressName(String addressName){
		this.addressName = addressName;
	}
}
