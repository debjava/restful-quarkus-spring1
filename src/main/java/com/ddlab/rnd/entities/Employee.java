package com.ddlab.rnd.entities;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Schema(name="Employee", description="Model for the Employee object")
@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
	@JsonProperty("empId")
	private int empId;

	@JsonProperty("firstName")
	private String firstName;

	@JsonProperty("lastName")
	private String lastName;
}
