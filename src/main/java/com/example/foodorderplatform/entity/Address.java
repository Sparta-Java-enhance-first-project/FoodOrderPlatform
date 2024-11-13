package com.example.foodorderplatform.entity;

import com.example.foodorderplatform.auditing.Timestamped;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
	private UUID addressId;
	@Column(nullable = false)
	private String addressName;

	public Address(String addressName){
		this.addressName = addressName;
	}

	public void setAddressName(String addressName){
		this.addressName = addressName;
	}
}
