package com.example.foodorderplatform.entity;


import com.example.foodorderplatform.auditing.Timestamped;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

import java.util.UUID;
import lombok.NoArgsConstructor;

/*
* check
* */

@Getter
@Entity
@Table(name = "p_region")
@NoArgsConstructor
public class Region extends Timestamped {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID regionId;
	@Column(nullable = false)
	private String regionName;

	@OneToMany(mappedBy = "userId")
	private List<User> userList = new ArrayList<>();

	public Region(String regionName){
		this.regionName = regionName;
	}
}
