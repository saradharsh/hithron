package com.example.hithron.api.model;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
public class UserEntity {	
	@Id
	@GeneratedValue
	private Integer userId;
	
	@NotNull(message = "firstName is mandatory")
	@NotBlank(message = "firstName is mandatory")
	private String firstName;	
	
	@NotNull(message = "lastName is mandatory")
	@NotBlank(message = "lastName is mandatory")
	private String lastName;	
	
	@Max(90)
	@Min(1)
	private Integer age;	
	
	@DateTimeFormat(pattern = "MM-DD-YYYY")
	@NotBlank(message = "DOB required")
	private String dateOfBirth;
	
	@NotNull(message="gender is mandatory")
	@NotBlank(message = "gender is mandatory")
	private String gender;	
	
	@NotNull
	private String type;
	
	@NotNull
	private String line1;
	
	@NotNull
	private String line2;
	
	@NotNull
	private String postCode;
	
	@NotNull
	private String city;
	
	@NotNull
	private String state;
		
	
}
