package com.votrihieu.web26t9.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "[User]") // Đặt tên bảng là "[User]" vì User là từ khóa trong SQL Server
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String fullname;

	@Column(nullable = false, unique = true)
	private String email;

	@Column(nullable = false)
	private String password;

	private String phone;

	@Enumerated(EnumType.STRING) // Lưu role dưới dạng chuỗi (USER, ADMIN) trong DB
	@Column(nullable = false)
	private Role role;

	// Mối quan hệ một-nhiều với Product
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Product> products = new HashSet<>();

	// Mối quan hệ nhiều-nhiều với Category
	@ManyToMany
	@JoinTable(name = "UserCategory",
			joinColumns = @JoinColumn(name = "userid"),
			inverseJoinColumns = @JoinColumn(name = "categoryid")
	)
	private Set<Category> categories = new HashSet<>();

	// Constructor bỏ qua id cho create, mặc định role là USER
	public User(String fullname, String email, String password, String phone) {
		this.fullname = fullname;
		this.email = email;
		this.password = password;
		this.phone = phone;
		this.role = Role.USER; // Mặc định tất cả user mới tạo là USER
	}
}