# CA1: Version Control with Git: Technical Report

**Author:** João Cardoso

**Date:** 07/03/2025

**Discipline:** DevOps

**Program:** SWitCH DEV

**Institution:** Instituto Superior de Engenharia do Porto

## Table of Contents

- [Introduction](#introduction)
- [Environment Setup](#environment-setup)
- [Part 1: Development Without Branches](#part-1-development-without-branches)
    - [Goals and Requirements](#goals-and-requirements)
    - [Key Developments](#key-developments)
- [Conclusion](#conclusion)

## Introduction
This report covers the Version Control with Git assignment for the DevOps course. The assignment consists of two sections: Part 1, which focuses on basic version control without branches, and Part 2, which introduces branching for feature development and bug fixes.

## Environment Setup
First, I created a local copy of the Tutorial React.js and Spring Data REST application by cloning an existing repository. After that, I set up my own repository to manage the class assignments, ensuring that all progress and modifications were properly tracked using version control.

** Creating My Repository:** Created new folders on my local machine, .gitignore and Readme. Initialized it as a Git repository.
```shell
mkdir -p ~/Desktop/Switch/devops-24-25-1241911/ca1/part1
touch ~/Desktop/Switch/devops-24-25-1241911/ca1/README.md
touch ~/Desktop/Switch/devops-24-25-1241911/.gitignore
cd ~/Desktop/Switch/devops-24-25-1241911
git init
```
**Linking to GitHub:**
```gitbash
git remote add origin <repository-URL>
```
**First Commit:**
```shell
git add .
git commit -m "added README and .gitignore"
```
**Pushing to Remote:** Finally, I pushed my initial commit to the GitHub repository, officially starting the version history of my assignments in a remote location.
```shell
git push -u origin master
```

## Part 1: Development Without Branches

### Goals and Requirements
-   The initial part of the assignment focuses on understanding and utilizing basic version control operations without branching.
-   Tasks include setting up the project environment, making changes directly to the master branch, and committing those changes.
-   A key requirement is to introduce a new feature (e.g., adding a `jobYears` field to an Employee object) and ensuring proper version tagging, starting with an initial version and updating it after adding the new feature.
-   The emphasis is on practicing commits, understanding the commit history, and using tags for versioning.

### Key Developments
In the first part, all development was done in the master branch. The steps included:

1. **Copy the code of the Tutorial React.js and Spring Data REST Application into  `CA1`.**

These commands were used to copy the tutorial directory recursively to `CA1` and pom.xml:
```shell
cp -r ~/Desktop/Switch/tut-react-and-spring-data-rest/basic ~/Desktop/Switch/devops-24-25-1241911/CA1/part1
cp ~/Desktop/Switch/tut-react-and-spring-data-rest/pom.xml ~/Desktop/Switch/devops-24-25-1241911/CA1/part1

```
2. **Commit the changes (and push them).**

Once the `CA1` directory was set up with the Tutorial application, I proceeded to commit these changes to the master branch with the following commands:
```shell
git add .
git commit -m "copied the code"
git push
```
3. **Tagging the repository to mark the version of the application.**

Following the versioning pattern outlined in the assignment, major.minor.revision, I tagged the initial setup as `v1.1.0` and subsequently pushed this tag to the remote repository:
```shell
git tag v1.1.0 
git push origin v1.1.0
```
4. **Develop a new feature to add a new field to the application.**

The core task of this first part was to develop a new feature by adding a `jobYears` field to the application, which records the number of years an employee has been with the company. Additionally, were implemented unit tests to ensure the creation of Employees and the validation of their attributes were functioning correctly.
The following files were modified to incorporate this new feature:
- **Employee.java**: This Java class, representing the employee model, was updated to include a new integer field named `jobYears`. The modification involved adding the field itself along with its getter and setter methods to allow for data encapsulation and access and the validation of all parameters. Below are the key additions and modifications made to the `Employee` class to support the new functionality and ensure robust data validation:

```java
public Employee(String firstName, String lastName, String description, int jobYears) throws Exception {
  if(!isNameValid(firstName)) throw new Exception("First name cannot be empty");
  this.firstName = firstName;
  if(!isNameValid(lastName)) throw new Exception("Last name cannot be empty");
  this.lastName = lastName;
  if(!isNameValid(description)) throw new Exception("Description cannot be empty");
  this.description = description;
  if(!isJobYearsValid(jobYears)) throw new Exception("Job Years cannot be negative");
  this.jobYears = jobYears;
}
private boolean isNameValid(String text){
  if (text == null || text.isBlank()) return false;
  return true;
}
private boolean isJobYearsValid(int years){
  if (years < 0) return false;
  return true;
}

@Override
public boolean equals(Object o) {
  if (this == o) return true;
  if (o == null || getClass() != o.getClass()) return false;
  Employee employee = (Employee) o;
  return Objects.equals(id, employee.id) &&
          Objects.equals(firstName, employee.firstName) &&
          Objects.equals(lastName, employee.lastName) &&
          Objects.equals(description, employee.description);
}

```
- **EmployeeTest.java**: To ensure the reliability of the new `jobYears` field, this file was updated to include unit tests. Key aspects of the testing include:
  - Validation Tests: Conducted tests to validate that the constructor and setters reject invalid inputs (null or empty Strings and  negative `jobYears`), safeguarding against improper object creation.
  - Positive Scenarios: Confirmed that valid inputs result in successful object creation, with no exceptions thrown, ensuring the `Employee` class functions as intended under correct usage.
  - Equality and Hashing: Verified the correct implementation of `equals` and `hashCode` methods, essential for accurate object comparison.
  - String Representation: Tested the `toString` method to ensure it accurately represents `Employee` object details, facilitating easier debugging and logging.

Here are some examples of the tests implemented:
```java


class EmployeeTest {
  @Test
  void shouldCreateEmployee() throws Exception{
    //arrange
    Employee employee = new Employee("Joseph","Green","CEO",10);
    //assert
    assertNotNull(employee);
  }
  @Test
  void shouldCreatAnEmployeeUsingAnEmptyConstructor() {
    // Arrange
    Employee employee = new Employee();

    // Assert
    assertNotNull(employee);
  }
  @Test
  void shouldCreateAnExceptionWhenFirstNameIsNull(){
    //assert
    assertThrows(Exception.class, () -> new Employee(null,"Green","CEO",10));
  }
  @Test
  void shouldCreateAnExceptionWhenFirstNameIsEmpty(){
    //assert
    assertThrows(Exception.class, () -> new Employee("","Green","CEO",10));
  }
  @Test
  void shouldCreateAnExceptionWhenLastNameIsNull(){
    //assert
    assertThrows(Exception.class, () -> new Employee("Joseph",null,"CEO",10));
  }
  @Test
  void shouldCreateAnExceptionWhenLastNameIsEmpty(){
    //assert
    assertThrows(Exception.class, () -> new Employee("Joseph","","CEO",10));
  }
  @Test
  void shouldCreateAnExceptionWhenDescriptionIsNull(){
    //assert
    assertThrows(Exception.class, () -> new Employee("Joseph","Green",null,10));
  }
  @Test
  void shouldCreateAnExceptionWhenDescriptionIsEmpty(){
    //assert
    assertThrows(Exception.class, () -> new Employee("Joseph","Green","",10));
  }
  @Test
  void shouldCreateAnExceptionWhenJobYearsIsNegative(){
    //assert
    assertThrows(Exception.class, () -> new Employee("Joseph","Green","",-1));
  }
  @Test
  void shouldCreateEmployeeWhenJobYearsEqualToZero() throws Exception{
    //arrange
    Employee employee = new Employee("Joseph","Green","CEO",0);
    //assert
    assertNotNull(employee);
  }

  //equals
  @Test
  void shouldReturnTrueWhenObjectsAreInSameLocation() throws Exception{
    //arrange
    Employee employee = new Employee("Joseph","Green","CEO",10);
    Employee employee1 = employee;
    //act
    boolean result = employee.equals(employee1);
    //assert
    assertTrue(result);
  }
  @Test
  void shouldReturnFalseWhenObjectsAreFromDifferentInstances() throws Exception{
    //arrange
    Employee employee = new Employee("Joseph","Green","CEO",10);
    Object o = new Object();
    //act
    boolean result = employee.equals(o);
    //assert
    assertFalse(result);
  }
  @Test
  void shouldReturnFalseWhenObjectsToCompareIsNull() throws Exception{
    //arrange
    Employee employee = new Employee("Joseph","Green","CEO",10);
    Employee employee1 = null;
    //act
    boolean result = employee.equals(employee1);
    //assert
    assertFalse(result);
  }
  @Test
  void shouldReturnTrueWhenObjectsHaveSameContent() throws Exception{
    //arrange
    Employee employee = new Employee("Joseph","Green","CEO",10);
    Employee employee1 = new Employee("Joseph","Green","CEO",10);
    //act
    boolean result = employee.equals(employee1);
    //assert
    assertTrue(result);
  }
  @Test
  void shouldReturnFalseWhenObjectsHaveDifferentFirstNames() throws Exception{
    //arrange
    Employee employee = new Employee("Joseph","Green","CEO",10);
    Employee employee1 = new Employee("Josephs","Green","CEO",10);
    //act
    boolean result = employee.equals(employee1);
    //assert
    assertFalse(result);
  }
  @Test
  void shouldReturnFalseWhenObjectsHaveDifferentLastNames() throws Exception{
    //arrange
    Employee employee = new Employee("Joseph","Green","CEO",10);
    Employee employee1 = new Employee("Joseph","Greens","CEO",10);
    //act
    boolean result = employee.equals(employee1);
    //assert
    assertFalse(result);
  }
  @Test
  void shouldReturnFalseWhenObjectsHaveDifferentDescriptions() throws Exception{
    //arrange
    Employee employee = new Employee("Joseph","Green","CEO",10);
    Employee employee1 = new Employee("Joseph","Green","CFO",10);
    //act
    boolean result = employee.equals(employee1);
    //assert
    assertFalse(result);
  }
  @Test
  void shouldReturnFalseWhenObjectsHaveDifferentJobYears() throws Exception{
    //arrange
    Employee employee = new Employee("Joseph","Green","CEO",10);
    Employee employee1 = new Employee("Joseph","Greens","CEO",11);
    //act
    boolean result = employee.equals(employee1);
    //assert
    assertFalse(result);
  }
  @Test
  void shouldReturnFalseWhenObjectsHaveDifferentId() throws Exception {
    //arrange
    Employee employee = new Employee("Joseph", "Green", "CEO", 10);
    Employee employee1 = new Employee("Joseph", "Greens", "CEO", 11);
    employee.setId(1L);
    employee1.setId(2L);
    //act
    boolean result = employee.equals(employee1);
    //assert
    assertFalse(result);
  }

  //hashCode
  @Test
  void shouldReturnTheSameForTheSameObject() throws Exception{
    //arrange
    int loc = 10;
    Employee employee = new Employee("Joseph", "Green", "CEO", 10);
    //act
    int hash1 = employee.hashCode();
    int hash2 = employee.hashCode();
    //assert
    assertEquals(hash1,hash2);
  }
  @Test
  void shouldReturnDifferentForTheDifferentObject() throws Exception{
    //arrange
    int loc = 10;
    Employee employee = new Employee("Joseph", "Green", "CEO", 10);
    Employee employee1 = new Employee("Josephs", "Greens", "CEO", 10);
    //act
    int hash1 = employee.hashCode();
    int hash2 = employee1.hashCode();
    //assert
    assertNotEquals(hash1,hash2);
  }

  //get

  @Test
  void shouldReturnID() throws Exception{
    //arrange
    Employee employee = new Employee("Joseph", "Green", "CEO", 10);
    employee.setId(1L);
    //act
    Long result = employee.getId();
    //assert
    assertEquals(1L,result);
  }
  @Test
  void shouldReturnFirstName() throws Exception{
    //arrange
    Employee employee = new Employee("Joseph", "Green", "CEO", 10);
    //act
    String result = employee.getFirstName();
    //assert
    assertEquals("Joseph",result);
  }
  @Test
  void shouldReturnLastName() throws Exception{
    //arrange
    Employee employee = new Employee("Joseph", "Green", "CEO", 10);
    //act
    String result = employee.getLastName();
    //assert
    assertEquals("Green",result);
  }
  @Test
  void shouldReturnDescription() throws Exception{
    //arrange
    Employee employee = new Employee("Joseph", "Green", "CEO", 10);
    //act
    String result = employee.getDescription();
    //assert
    assertEquals("CEO",result);
  }
  @Test
  void shouldReturnJobYears() throws Exception{
    //arrange
    Employee employee = new Employee("Joseph", "Green", "CEO", 10);
    //act
    int result = employee.getJobYears();
    //assert
    assertEquals(10,result);
  }
  //set
  @Test
  void shouldReturnSetFirstName() throws Exception{
    //arrange
    Employee employee = new Employee("Joseph", "Green", "CEO", 10);
    //act
    employee.setFirstName("Alfred");
    //assert
    assertEquals("Alfred",employee.getFirstName());
  }
  @Test
  void shouldReturnSetLastName() throws Exception{
    //arrange
    Employee employee = new Employee("Joseph", "Green", "CEO", 10);
    //act
    employee.setLastName("Blue");
    //assert
    assertEquals("Blue",employee.getLastName());
  }
  @Test
  void shouldReturnSetDescription() throws Exception{
    //arrange
    Employee employee = new Employee("Joseph", "Green", "CEO", 10);
    //act
    employee.setDescription("CFO");
    //assert
    assertEquals("CFO",employee.getDescription());
  }
  @Test
  void shouldReturnSetJobYears() throws Exception{
    //arrange
    Employee employee = new Employee("Joseph", "Green", "CEO", 10);
    //act
    employee.setJobYears(9);
    //assert
    assertEquals(9,employee.getJobYears());
  }
  //toString

  @Test
  void shouldReturnResult() throws Exception{
    //arrange
    Employee employee = new Employee("Joseph", "Green", "CEO", 10);
    employee.setId(1L);
    String expected = "Employee{" +
            "id=1" +
            ", firstName='Joseph'" +
            ", lastName='Green'" +
            ", description='CEO'" +
            ", jobYears='10'}";
    //act
    String result = employee.toString();
    //assert
    assertEquals(expected,result);
  }

}
```
- **DatabaseLoader.java**: The `DatabaseLoader` class was updated to include `jobYears` for sample `employees`, ensuring the new field is functional from the start. Below is the modified code:
```java
@Override
public void run(String... strings) throws Exception { // <4>
  this.repository.save(new Employee("Frodo", "Baggins", "ring bearer",5));
}
```
- **app.js**: The React components within `app.js` were modified to support the display of the new `jobYears` field within the employee list. The `EmployeeList` and `Employee` components now include a column for "Job Years" in the rendered table, allowing users to view the number of years an employee has been with the company alongside their other details.
  The following code snippet illustrates the changes made to `app.js` to incorporate the `jobYears` field into the application's frontend:
```javascript
class EmployeeList extends React.Component{
  render() {
    const employees = this.props.employees.map(employee =>
            <Employee key={employee._links.self.href} employee={employee}/>
    );
    return (
            <table>
              <tbody>
                <tr>
                  <th>First Name</th>
                  <th>Last Name</th>
                  <th>Description</th>
                  <th>jobYears</th>
                </tr>
                {employees}
              </tbody>
            </table>
    )
  }
}
```
```javascript
class Employee extends React.Component{
  render() {
    return (
            <tr>
              <td>{this.props.employee.firstName}</td>
              <td>{this.props.employee.lastName}</td>
              <td>{this.props.employee.description}</td>
              <td>{this.props.employee.jobYears}</td>
            </tr>
    )
  }
}

```

5. **Debug the server and client parts of the solution.**

After verifying the `jobYears` field's integration, I ran the application using `./mvnw spring-boot:run` to test its real-time functionality at `http://localhost:8080/`. This step ensured smooth feature operation and compatibility. I also reviewed the code to verify `jobYears` handling on both server and client sides.

6. **End of the assignment**

Once satisfied with the stability and performance of the new feature, I committed the changes to the repository with a descriptive message outlining the enhancements. Following this, the updated code was pushed to the remote server to share the advancements with the team and maintain the project's collaborative workflow. To mark this significant update, I tagged the commit with `v1.2.0`, following the semantic versioning pattern adopted for the project. At the end of the assignment.

