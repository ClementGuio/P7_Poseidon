 package com.nnk.springboot.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.nnk.springboot.annotations.ValidPassword;

@Entity
@Table(name = "Users")
public class User {

	@Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "Id")
    private Integer id;
    
	@Size(max=125)
	@NotBlank(message = "Username is mandatory")
	@Column(name = "username")
    private String username;
    
	@ValidPassword(message = "Password must have at least 8 characters, 1 upper case, 1 number and 1 special character.")
	@NotBlank(message = "Password is mandatory")
	@Column(name = "password")
    private String password;
    
	@Size(max=125)
	@NotBlank(message = "FullName is mandatory")
	@Column(name = "fullname")
    private String fullname;
    
	@Size(max=125)
	@NotBlank(message = "Role is mandatory")
	@Column(name = "role")
    private String role;

    public User() {
		super();
	}

	public User(@Size(max = 125) @NotBlank(message = "Username is mandatory") String username,
			@Size(max = 125) @NotBlank(message = "Password is mandatory") String password,
			@Size(max = 125) @NotBlank(message = "FullName is mandatory") String fullname,
			@Size(max = 125) @NotBlank(message = "Role is mandatory") String role) {
		super();
		this.username = username;
		this.password = password;
		this.fullname = fullname;
		this.role = role;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
