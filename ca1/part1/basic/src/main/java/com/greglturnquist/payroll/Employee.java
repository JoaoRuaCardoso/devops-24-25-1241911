/*
 * Copyright 2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.greglturnquist.payroll;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author Greg Turnquist
 */
// tag::code[]
@Entity // <1>
public class Employee {

	private @Id @GeneratedValue Long id; // <2>
	private String firstName;
	private String lastName;
	private String description;
	private int jobYears;
	private String email;


	public Employee() {}

	public Employee(String firstName, String lastName, String description, int jobYears, String email) throws Exception {
		if(!isNameValid(firstName)) throw new Exception("First name cannot be empty");
		this.firstName = firstName;
		if(!isNameValid(lastName)) throw new Exception("Last name cannot be empty");
		this.lastName = lastName;
		if(!isNameValid(description)) throw new Exception("Description cannot be empty");
		this.description = description;
		if(!isJobYearsValid(jobYears)) throw new Exception("Job Years cannot be negative");
		this.jobYears = jobYears;
		if(!isEmailValid(email)) throw new Exception("Invalid Parameter");
		this.email = email;
	}
	private boolean isNameValid(String text){
		if (text == null || text.isBlank()) return false;
		return true;
	}
	private boolean isJobYearsValid(int years){
		if (years < 0) return false;
		return true;
	}
	private boolean isEmailValid(String email) {
		return email != null && email.contains("@");
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Employee employee = (Employee) o;
		return Objects.equals(id, employee.id) &&
				Objects.equals(firstName, employee.firstName) &&
				Objects.equals(lastName, employee.lastName) &&
				Objects.equals(description, employee.description)&&
				Objects.equals(jobYears,employee.jobYears)&&
				Objects.equals(email,employee.email);
	}

	@Override
	public int hashCode() {

		return Objects.hash(id, firstName, lastName, description, jobYears, email);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getJobYears() {return jobYears;}

	public void setJobYears(int jobYears){this.jobYears = jobYears;}

	public String getEmail() {return email;}

	public void setEmail(String email) {this.email = email;}

	@Override
	public String toString() {
		return "Employee{" +
				"id=" + id +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", description='" + description + '\'' +
				", jobYears='" + jobYears + '\'' +
				", email='" + email + '\''+
				'}';
	}
}
// end::code[]
