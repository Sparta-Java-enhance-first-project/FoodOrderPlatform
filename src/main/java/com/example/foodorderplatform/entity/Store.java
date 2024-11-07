package com.example.foodorderplatform.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

import java.sql.Time;
import java.util.List;

@Getter
@Entity
@Table(name = "p_store")
public class Store {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long store_no;
	@Column(nullable = false)
	private String store_nm;
	@Column(nullable = false)
	private Boolean store_state;
	@Column
	private String store_rest_day;
	@Column
	private String store_img;
	@Column
	private String store_description;
	@Column(nullable = false)
	private Time store_open_hour;
	@Column(nullable = false)
	private Time store_close_hour;
	@Column
	private String ingredient_origin;

	@ManyToOne
	@JoinColumn(name = "user_no")
	private User user;

	@ManyToOne
	@JoinColumn(name = "region_no")
	private Region region;

	@OneToOne(mappedBy = "store")
	private BusinessInfo businessInfo;

}
