# CA1

**Author:** João Cardoso

**Date:** 07/03/2025

**Discipline:** DevOps

**Program:** SWitCH DEV

**Institution:** Instituto Superior de Engenharia do Porto

## Table of Contents
- [Part 1: Version Control with Git](#part-1-version-control-with-git)  
  - [Introduction Part 1](#introduction-part-1)
  - [Environment Setup Part1](#environment-setup-part-1)
  - [Part 1.1: Development Without Branches](#part-11-development-without-branches)
    - [Goals and Requirements](#goals-and-requirements)
    - [Key Developments](#key-developments)
  - [Part 1.2: Development Using Branches](#part-12-development-using-branches)
    - [Goals and Requirements](#goals-and-requirements-1)
    - [Key Developments](#key-developments)
  - [Final Results](#final-results)
    - [Implementation](#implementation)
    - [Branches](#branches)
    - [Tags](#tags)
    - [Issue Tracking](#issue-tracking)
  - [Alternative Solution](#alternative-solution)
    - [Comparison of Fossil and Git](#comparison-of-fossil-and-git)
    - [Utilizing Fossil for the Assignment](#utilizing-fossil-for-the-assignment)
  - [Conclusion Part 1](#conclusion-part-1)
- [Part 2: Build Tools with Gradle](#part-2-build-tools-with-gradle)
  - [Introduction Part 2](#introduction-part-2)
  - [Environment Setup Part 2](#environment-setup-part-2)
  - [Gradle Basic Demo](#gradle-basic-demo)
  - [Adding a runServer Task for Simplified Server Startup](#adding-a-runserver-task-for-simplified-server-startup)
  - [Add a unit test](#add-a-unit-test)
  - [Add a new task of type Copy](#add-a-new-task-of-type-copy)
  - [Add a new task of type Zip](#add-a-new-task-of-type-zip)
  - [Conclusion Part 2](#conclusion-part-2)
- [Part 3: Build Tools with Gradle](#part-3-build-tools-with-gradle)
  - [Introduction Part 3](#introduction-part-3)
  - [Setup Initial Gradle Project](#setup-initial-gradle-project)
  - [Configure Frontend Plugin for Gradle](#configure-frontend-plugin-for-gradle)
  - [Add Gradle Tasks for File Management](#add-gradle-tasks-for-file-management)
  - [Alternative Solution to Gradle](#alternative-solution-to-gradle)
  - [Conclusion Part 3](#conclusion-part-3)



## Part 1 Version Control With Git

## Introduction Part 1
This report covers the Version Control with Git assignment for the DevOps course. The assignment consists of two sections: Part 1, which focuses on basic version control without branches, and Part 2, which introduces branching for feature development and bug fixes.

## Environment Setup Part 1
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

## Part 1.1: Development Without Branches

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

After verifying the `jobYears` field's integration, I ran the application using `mvn spring-boot:run` to test its real-time functionality at `http://localhost:8080/`. This step ensured smooth feature operation and compatibility. I also reviewed the code to verify `jobYears` handling on both server and client sides.

6. **End of the assignment**

Once satisfied with the stability and performance of the new feature, I committed the changes to the repository with a descriptive message outlining the enhancements. Following this, the updated code was pushed to the remote server to share the advancements with the team and maintain the project's collaborative workflow. To mark this significant update, I tagged the commit with `v1.2.0`, following the semantic versioning pattern adopted for the project.
Create tag ca1-part1.1 as result of the end of the first assignment.

## Part 1.2: Development Using Branches

### Goals and Requirements

- The second part focuses on using branches for features and bug fixes, ensuring isolated development and proper merge strategies.
- It requires creating feature branches to avoid interfering with the main codebase until merging.
- The part ends with tagging the master branch after merges to mark new versions, highlighting effective version control.

### Key Developments

In the second part, branch-based development was used to improve features and fix bugs while keeping the master branch stable for publishing the Tutorial React.js and Spring Data REST Application.

Since the steps for adding features and fixing bugs are similar to Part 1, the code is not repeated. The key difference is the use of branches. Here are the main steps:

1. **Start using the master branch**

To ensure I was in the correct branch, especially the master branch for stable releases, I used the `git branch` command. This was crucial for verifying my current branch, marked by an asterisk (*) in the output.

2. **Develop new features in branches**

During the development phase of adding an `email field`, branch management was essential. A dedicated `email-field` branch was created to encapsulate all related changes. After switching to this branch, I used the `git branch` command to confirm the switch. The commands used were:
```shell
git branch
git branch email-field
git checkout email-field
git branch
```

3. **Integration and Testing of the Email Field**

The process of adding `email` field support and validation followed a similar approach to the `jobYears` field in Part 1. Here are the key steps:
- **Code Implementation**: Like the previous feature development, I extended the `Employee` class to include an `email` field with getter and setter methods. Data models, forms, and views were updated to integrate the new field into both the frontend and backend.
- **Unit Testing**: Following the established pattern, I wrote comprehensive unit tests to verify the correct creation of Employee instances with the new email field and to enforce validation rules, such as non-null and non-empty values for the email attribute.
- **Debugging**: The server and client were thoroughly debugged to resolve any issues from adding the email field, ensuring smooth operation and a seamless user experience.

4. **Merge the code with the master**

Completing the `email` field feature involved integrating changes into the main branch and updating the version. The finalized changes in `email-field` were committed and pushed. A no-fast-forward merge preserved history, followed by pushing the updates. Finally, the new version was tagged and pushed. The commands used were:
```shell
# Commit the feature changes:
git add .
git commit -m "added email field"

# Push the feature branch upstream:
git push --set-upstream origin email-field

# Switch to the main branch and merge changes:
git checkout master
git merge --no-ff email-field

# Push the merged changes to update the main branch:
git push

# Tag the new version and push the tag:
git tag V1.3.0 
git push origin V1.3.0
```

5. **Create a new branch to fix a bug**

To fix the email validation bug in the `Employee` class, a `fix-invalid-email` branch was created following the usual workflow. Development, testing, and merging followed previous practices, ensuring code integrity and stability.
The bug fix focused on enhancing the `Employee` class with validation logic to ensure the email field contains an "@" sign. The following code snippet illustrates the added validation:

```java
private boolean isEmailValid(String email) {
		return email != null && email.contains("@");
	}
```
6. **End of the assignment**

After implementing and testing the fix, the changes were merged into the master branch, and the version was updated to `V1.3.1` to reflect the minor fix. The repository was then tagged as `ca1-part2` to mark the assignment's completion.

## Final Results

### Implementation
Bellow follows all the new features, the final state of the application is illustrated below:

[![image.png](https://i.postimg.cc/N0DkdGJP/image.png)](https://postimg.cc/9449M2jG)

The initial model included First Name, Last Name, and Description. Development began with Job Years in Part 1 of CA1 to track tenure. In Part 2 of CA1, the Email field was added for contact information.

### Branches
The image below reveals the existing branches in the repository, as output by the `git branch` command.

[![image.png](https://i.postimg.cc/sxjbw21X/image.png)](https://postimg.cc/LYWys2tK)

Image bellow shows the chronological sequence os branches

[![image.png](https://i.postimg.cc/MHR2nLc0/image.png)](https://postimg.cc/T5RHSCJh)

This assignment taught me the value of using branches to isolate changes for specific features or fixes. This approach maintains codebase stability while ensuring a clear and structured change history.

### Tags

[![image.png](https://i.postimg.cc/c4fqBPFP/image.png)](https://postimg.cc/jLqks30h)

Using tags showed me how to mark key points in the project's history, making it easier to track progress and revert to previous versions when needed.

### Issue Tracking

During development, 18 issues were created on GitHub to track and manage problems. These issues were resolved and closed by including fixes #1 to fixes #18 in the commit messages. This practice ensures a clear history of problems and solutions while automatically closing issues when commits are pushed. Below is a visual representation of the issues created and closed during the assignment:

[![image.png](https://i.postimg.cc/x8JXZ9Xv/image.png)](https://postimg.cc/BP3ZL9cb)

Issues serve multiple purposes in a project, such as tracking bugs, feature requests, and tasks. They can be assigned, labeled, and linked to commits or pull requests. Moving forward, the goal is to integrate issues throughout development to improve task management, progress tracking, and team collaboration.

## Alternative Solution
In seeking an alternative to Git for version control, Fossil offers a distinct approach with its centralized model, contrasting Git's decentralized nature. This section compares SVN to Git in terms of version control features and describes how SVN could be utilized to achieve the goals set forth in this assignment.

### Comparison of Fossil and Git

# Fossil vs. Git Comparison

| Feature              | Fossil                                                                                             | Git                                                                                                                    |
|----------------------|--------------------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------------------|
| **Architecture**      | Centralized, with a single repository as the source of truth.                                      | Distributed, allowing multiple full-version repositories for redundancy and collaboration.                             |
| **Versioning Model**  | Snapshot-based, storing the state of the entire project at each commit.                           | Snapshot-based, tracking the state of the entire repository at each commit.                                           |
| **Branching & Merging**| Efficient branching and merging with simpler workflows compared to Git.                           | Efficient branching and merging, ideal for parallel development workflows.                                             |
| **Binary Files Handling**| Efficient handling of binary files, optimized with delta storage.                                | Stores full binary files per change, increasing repository size but ensuring all versions are accessible.              |

---

# Utilizing Fossil for the Assignment

### Repository Setup and Import
Set up a **Fossil** repository and import the project files:

```bash
# Create a new Fossil repository
fossil init /path/to/fossil_repository.fossil

# Import the project into the Fossil repository
fossil open /path/to/fossil_repository.fossil
fossil import /path/to/TutorialReactSpringDataREST
```
### Feature Development and Branching

```bash
# Create a branch for the new feature
fossil branch new-feature
```
### Committing and Tagging

```bash
# Commit changes in the working directory
fossil commit -m "Implemented new feature"

# Tag a stable release
fossil tag v1.0 "Tagging version 1.0"

```

### Merging and Deployment Preparation

```bash
# Merge the feature branch into the trunk
fossil merge new-feature

# Commit the merge
fossil commit -m "Merged feature branch into trunk"

```
## Conclusion Part 1

Completing the Version Control with Git assignment expanded my understanding of version control systems in software development. Part 1 focused on basic Git concepts, such as modifying the master branch and committing/tagging changes. Part 2 introduced branching, deepening my understanding of isolating changes for better project history and easier management.
The Final Results highlight the enhanced functionality through feature additions, demonstrating how version control principles apply in real-world development. The use of GitHub issues helped track and manage problems, providing a clear history of solutions.
Exploring Fossil as an alternative to Git offered insights into different version control models. Comparing Fossil's centralized approach with Git's distributed system broadened my perspective on how version control can be tailored to fit project needs.
This assignment enhanced my technical skills with Git and Fossil while emphasizing the role of version control in collaborative development, code integrity, and project management.

# Part 2 Build Tools With Gradle

## Introduction Part 2

This report summarizes the Build Tools with Gradle assignment for the DevOps course, covering key Gradle applications. It progresses from setup to advanced tasks like custom task creation and unit testing.

After the Environment Setup, the Gradle Basic Demo section presents a multithreaded chat server, illustrating how Gradle manages builds and execution.

The Add a New Task section details extending Gradle’s functionality, while Add a Unit Test emphasizes integrating tests for project reliability. Lastly, Add a Copy Task and Add a Zip Task demonstrate Gradle’s role in file handling for project distribution.

The report concludes by summarizing the learning outcomes, challenges faced, and practical skills gained in using Gradle for software development.

## Environment Setup Part 2

The setup began by creating a new directory, /CA1/Part2, and cloning the example application from the provided Bitbucket repository. This repository included a build.gradle file and the Gradle Wrapper, ensuring a consistent environment.

After installing Gradle, I verified the setup by running gradle -v. I then imported the project into my Gradle-supported IDE, leveraging its built-in tools. To confirm the configuration, I executed a basic Gradle build, ensuring all components were correctly set up.

These steps provided a solid foundation for the subsequent tasks in the assignment.

## Gradle Basic Demo

The Gradle Basic Demo was a hands-on exercise in managing a multithreaded chat server capable of handling multiple clients simultaneously.

**Gradle Build Process:**

To set up the demo, I ran `./gradlew` build in the project's root directory. This command compiled the code and generated an executable .jar file. The screenshot below verifies the successful build.


[![image.png](https://i.postimg.cc/QMf3Dv4h/image.png)](https://postimg.cc/gwL5qtbT)

**Server Launch:**

Following that, I launched the chat server using the command java `-cp build/libs/basic_demo-0.1.0.jar basic_demo.ChatServerApp 59001`. The screenshot below shows the server running and awaiting client connections.

[![image.png](https://i.postimg.cc/3JnrYLL8/image.png)](https://postimg.cc/MvfJYY2N)

[![image.png](https://i.postimg.cc/kGNQ2r2h/image.png)](https://postimg.cc/m1Dzn5NQ)

For the client side, I established connections to the chat server by executing `/gradlew runClient`, ensuring each client connected to localhost on port 59001. The build.gradle file was configured to allow easy modifications for different connection settings. To demonstrate the server's capability to manage multiple clients, I launched several client instances from different terminals. The screenshots below show the active chat sessions, highlighting the multi-client functionality in action.

[![image.png](https://i.postimg.cc/wj5DFYFq/image.png)](https://postimg.cc/ZWR9Kg12)

## Adding a runServer Task for Simplified Server Startup

I added a `runServer` task to the `build.gradle` file to streamline the server startup process. This task allows us to launch the chat server directly using a Gradle command, eliminating the need for manual inputs.

The `runServer` task is defined as follows in the `build.gradle` file, with the type set to JavaExec to execute Java applications. It depends on the classes task to ensure the necessary classes are compiled before starting the server, and it launches the ChatServerApp on port 59001:

```java

tasks.register('runServer', JavaExec){
    group = "DevOps"
    description = "Launches a chat Server"

    classpath = sourceSets.main.runtimeClasspath

    mainClass = "basic_demo.ChatServerApp"
    args '59001'
}
```
I tested this task by running `./gradlew runServer` from the command line, and the terminal feedback confirmed the server was successfully launched, as shown in the screenshot below.

[![image.png](https://i.postimg.cc/YCQ3vyPF/image.png)](https://postimg.cc/w1TJPFgx)

This integration into our Gradle build script showcases Gradle's flexibility and enhances productivity by automating routine tasks.

## Add a unit test

I added a unit test to verify the App class’s functionality. The test, located in `src/test/java/basic_demo/AppTest.java`, checks if the App class returns a non-null greeting message.

To set up the test environment, I included the JUnit dependency in the `build.gradle` file:

```java
testImplementation 'junit:junit:4.12'
```
This ensures that the project can recognize and run JUnit tests smoothly. Below is the content of AppTest.java, which contains the test case:

```java
package basic_demo;

import org.junit.Test;
import static org.junit.Assert.*;

public class AppTest {
  @Test public void testAppHasAGreeting() {
    App classUnderTest = new App();
    assertNotNull("app should have a greeting", classUnderTest.getGreeting());
  }
}
```
I executed the test using the command `./gradlew test`. The screenshot below shows the terminal output, confirming the test passed successfully.

[![image.png](https://i.postimg.cc/bwj69244/image.png)](https://postimg.cc/zV0SJ3zk)

## Add a new task of type Copy

The next task involved adding a `Copy` task to the `build.gradle` file, designed to create a backup of the source code. This ensures a reliable recovery point in case of unforeseen issues during development.

The backup task uses Gradle's Copy task type to replicate the contents of the `src` directory into a designated backup location within the project. This step is crucial for maintaining an up-to-date snapshot of the codebase, especially before significant changes or updates:

```java
task backup(type: Copy) {
  group = "DevOps"
  description = "Make a backup of the sources of the Application"

  from 'src'
  into 'backup'
}
```
After implementing the task, I tested its functionality by running `./gradlew backup` from the command line. The task executed successfully, as shown in the terminal output below, confirming that the source code was copied to the backup location and ensuring the task's effectiveness in safeguarding the project’s code.

[![image.png](https://i.postimg.cc/J0MNBbsQ/image.png)](https://postimg.cc/8JXJ2fBF)

## Add a new task of type Zip

The final task involved creating a Zip task to package the project's source code into a compressed .zip file. This task simplifies packaging the src directory, making it easier for backups or distribution. It’s essential for archiving project iterations or preparing the code for sharing.

Below is the task definition:

```java
task zipSource(type: Zip) {
group = "DevOps"
description = "Zips the source code files"

    from 'src'
    archiveFileName = 'src_archive.zip'
    destinationDir = file('archives')
}
```
After defining the zip task, I executed it with ./gradlew zip. The terminal output confirmed the successful execution, indicating that the src directory was compressed into a ZIP archive. Below is a screenshot of the terminal showing the successful archive creation.

[![image.png](https://i.postimg.cc/KjmZSYWz/image.png)](https://postimg.cc/HcPGbTdD)

## Conclusion Part 2

This assignment provided valuable insights into Gradle's practical applications as a build tool. The tasks performed demonstrated its adaptability and versatility in automating build processes, integrating unit tests, and executing file manipulation tasks, all essential for an efficient development workflow.

Adding tasks like `runServer`, `backup`, and `zipSource` showcased Gradle’s extensibility, streamlining the development process and enhancing the project's resilience and distribution capabilities. Integrating unit tests further highlighted the importance of testing in software development and how Gradle supports this.

Overall, this experience has deepened my understanding of Gradle and its role in software development, equipping me with knowledge to improve future projects through more efficient and reliable workflows

# Part 3 Build Tools With Gradle

## Introduction Part 3

This report summarizes the tasks completed in the third  part of Class Assignment 1 for the DevOps course, focusing on using Gradle for build automation. The assignment guides the migration of a Spring Boot application from Maven to Gradle, emphasizing its practical benefits in software development.

## Setup Initial Gradle Project

Setting up the Gradle project required key steps to transition from a Maven-based structure. First, a new branch was created to isolate and manage changes effectively using the command:

```bash
git checkout -b tut-basic-gradle
```
Next, a new Spring Boot project was initialized using Spring Initializr at start.spring.io, configured with essential dependencies like Rest Repositories, Thymeleaf, JPA, and H2.

The generated .zip file was downloaded and extracted into CA1/Part3/, providing the foundation for a Gradle-managed Spring application.

To confirm the setup and list available Gradle tasks, the following command was run in the project’s root directory:

```bash
./gradlew tasks
```
Executing this command the return outputs the list of tasks available as shown bellow :

```bash
$ ./gradlew tasks
Downloading https://services.gradle.org/distributions/gradle-8.13-bin.zip
.............10%.............20%.............30%.............40%.............50%.............60%.............70%.............80%.............90%.............100%

Welcome to Gradle 8.13!

Here are the highlights of this release:
 - Daemon JVM auto-provisioning
 - Enhancements for Scala plugin and JUnit testing
 - Improvements for build authors and plugin developers

For more details see https://docs.gradle.org/8.13/release-notes.html

Starting a Gradle Daemon (subsequent builds will be faster)

> Task :tasks

------------------------------------------------------------
Tasks runnable from root project 'react-and-spring-data-rest-basic'
------------------------------------------------------------

Application tasks
-----------------
bootRun - Runs this project as a Spring Boot application.
bootTestRun - Runs this project as a Spring Boot application using the test runtime classpath.

Build tasks
-----------
assemble - Assembles the outputs of this project.
bootBuildImage - Builds an OCI image of the application using the output of the bootJar task
bootJar - Assembles an executable jar archive containing the main classes and their dependencies.
build - Assembles and tests this project.
buildDependents - Assembles and tests this project and all projects that depend on it.
buildNeeded - Assembles and tests this project and all projects it depends on.
classes - Assembles main classes.
clean - Deletes the build directory.
jar - Assembles a jar archive containing the classes of the 'main' feature.
resolveMainClassName - Resolves the name of the application's main class.
resolveTestMainClassName - Resolves the name of the application's test main class.
testClasses - Assembles test classes.

Build Setup tasks
-----------------
init - Initializes a new Gradle build.
updateDaemonJvm - Generates or updates the Gradle Daemon JVM criteria.
wrapper - Generates Gradle wrapper files.

Documentation tasks
-------------------
javadoc - Generates Javadoc API documentation for the 'main' feature.

Help tasks
----------
artifactTransforms - Displays the Artifact Transforms that can be executed in root project 'react-and-spring-data-rest-basic'.
buildEnvironment - Displays all buildscript dependencies declared in root project 'react-and-spring-data-rest-basic'.
dependencies - Displays all dependencies declared in root project 'react-and-spring-data-rest-basic'.
dependencyInsight - Displays the insight into a specific dependency in root project 'react-and-spring-data-rest-basic'.
dependencyManagement - Displays the dependency management declared in root project 'react-and-spring-data-rest-basic'.
help - Displays a help message.
javaToolchains - Displays the detected java toolchains.
outgoingVariants - Displays the outgoing variants of root project 'react-and-spring-data-rest-basic'.
projects - Displays the sub-projects of root project 'react-and-spring-data-rest-basic'.
properties - Displays the properties of root project 'react-and-spring-data-rest-basic'.
resolvableConfigurations - Displays the configurations that can be resolved in root project 'react-and-spring-data-rest-basic'.
tasks - Displays the tasks runnable from root project 'react-and-spring-data-rest-basic'.

Verification tasks
------------------
check - Runs all checks.
test - Runs the test suite.

Rules
-----
Pattern: clean<TaskName>: Cleans the output files of a task.
Pattern: build<ConfigurationName>: Assembles the artifacts of a configuration.

To see all tasks and more detail, run gradlew tasks --all

To see more detail about a task, run gradlew help --task <task>

BUILD SUCCESSFUL in 26s
1 actionable task: 1 executed

```
The output verified the successful creation of tasks for building and running the application. This list highlighted Gradle’s capabilities, laying the groundwork for further customization and development in the next steps.

## Integrate Existing Code

At this stage, the existing codebase from a basic tutorial was incorporated into the Gradle project. The process was carefully executed to ensure all components functioned correctly under the new build system.

The integration followed these steps:

This phase involved integrating the existing tutorial codebase into the Gradle project while ensuring all components functioned correctly under the new build system.

Integration Steps:
- **Replace the Source Directory**: The original src folder was removed, and the tutorial’s src directory was copied into the Gradle project.
- **Include Additional Configuration Files**: webpack.config.js and package.json were added to maintain frontend build settings.
- **Remove Redundant Directories**: src/main/resources/static/built was deleted since Webpack regenerates it during the build process.

Fixing Compilation Errors:
- **Update Import Statements**: javax.persistence imports in Employee.java were replaced with jakarta.persistence to align with newer dependencies.
- **Set Package Manager Version**: "packageManager": "npm@9.6.7" was added to package.json for consistency across environments.

After completing the integration and configuration changes, the application underwent testing to verify its functionality and stability. The following steps were taken to ensure everything worked as expected:

- **Testing the Integration**:
Run the Backend: `./gradlew bootRun` was executed to start the application.
Check Frontend Behavior: Accessing http://localhost:8080 displayed an empty page, expected due to a missing Gradle plugin for frontend handling, which will be addressed later.
This integration established a solid foundation for further enhancements and feature additions in the Gradle-based project.

## Configure Frontend Plugin for Gradle

To align the build processes of the frontend with the newly adopted Gradle system, the `org.siouan.frontend-gradle-plugin` was introduced. This plugin is crucial for managing frontend assets, similar to how the `frontend-maven-plugin` is used in Maven projects.

- **Adding the Plugin**: The Gradle build script was updated to include the org.siouan.frontend plugin, compatible with Java 17. The following line was added to the plugins block in build.gradle:
```groovy
   id "org.siouan.frontend-jdk17" version "8.0.0"
   ```
- **Configuring the Plugin**: To properly manage frontend assets, configurations for the Node.js version and script commands were included in build.gradle. This setup defines the Node.js version in use and the scripts for building, cleaning, and verifying the frontend:
 ```groovy
    frontend {
        nodeVersion = "16.20.2"
        assembleScript = "run build"
        cleanScript = "run clean"
        checkScript = "run check"
    }
```
- **Updating package.json**: The scripts section in package.json was updated to manage the execution of Webpack and other frontend-related tasks:
    ```json
    "scripts": {
        "webpack": "webpack",
        "build": "npm run webpack",
        "check": "echo Checking frontend",
        "clean": "echo Cleaning frontend",
        "lint": "echo Linting frontend",
        "test": "echo Testing frontend"
    }
    ```

- **Testing the Configuration**: After setting up the frontend plugin, the build and runtime behaviors were tested:
  - Build Test: Running `./gradlew build` confirmed that the project builds successfully with the frontend integration.  
  - Application Execution: `./gradlew bootRun` was run, and the application was accessed at http://localhost:8080. Unlike before, the webpage now displayed frontend content, confirming that the Gradle plugin managed the frontend resources correctly during the build and serve processes.
  - This setup showcases the successful integration of frontend build management into Gradle, improving the project's ability to handle full-stack development workflows efficiently.

## Add Gradle Tasks for File Management
To improve file management, particularly for distribution and cleanup, two custom Gradle tasks were defined: copyJar and cleanWebpack.

1. **Task: `copyJar`**
  - **Purpose**: This task is responsible for copying the `.jar` file generated by the `bootJar` task directly from the output directory to a `dist` folder at the project root. This approach ensures that only the correct, fully assembled `.jar` file is targeted for distribution, minimizing errors and ensuring that deployments contain the most current build.
  - **Configuration**:
    ```groovy
    task copyJar(type: Copy) {
        dependsOn bootJar
        from bootJar.outputs
        into file("dist")
    }
    ```  
- **Dependencies**: It explicitly depends on the `bootJar` task, ensuring that the copy operation is executed only after the `bootJar` has successfully completed, thus maintaining a clear and reliable build dependency.

2. **Task: `cleanWebpack`**
  - **Purpose**: The task deletes all files generated by Webpack in the src/main/resources/static/built directory, keeping the build environment clean and ensuring only necessary files are included in each build, preventing conflicts from outdated files.
  - **Configuration**:
    ```groovy
    task cleanWebpack(type: Delete) {
        delete 'src/main/resources/static/built'
    }
    clean.dependsOn cleanWebpack
    ```
  - **Dependencies**: This task runs automatically before Gradle's standard clean task, integrating it into the regular cleanup process.

Both tasks were run to confirm their functionality.

- **Executing `copyJar`**:
  - Command: `./gradlew copyJar`
  - **Outcome**: The .jar file from the bootJar task was successfully copied to the dist directory, confirming the task's accuracy and integration into the build process for distribution.
  - 
- **Executing `cleanWebpack`**:
  - Command: `./gradlew cleanWebpack`
  - **Outcome**: The src/main/resources/static/built directory was successfully cleared, confirming the cleanup task's effectiveness in maintaining a clean build environment.
These tasks were added to the build process to automate file management, improving efficiency and reliability. Each executed correctly, ensuring smoother project maintenance.

## Alternative Solution to Gradle

**Implementing the Assignment Goals with Maven**
This guide outlines the steps required to configure Maven for a Spring Boot application, mirroring the setup and functionality achieved with Gradle. It includes frontend integration, custom build tasks, and file management. Below is a detailed step-by-step guide for setting up Maven, including the necessary pom.xml configurations.

```xml
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.example</groupId>
    <artifactId>spring-boot-app</artifactId>
    <version>1.0.0</version>

    <properties>
        <java.version>17</java.version>
    </properties>

    <dependencies>
        <!-- Spring Boot Starters -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-thymeleaf</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-rest</artifactId>
        </dependency>
        <dependency>
            <groupId>com.h2database</groupId>
            <artifactId>h2</artifactId>
            <scope>runtime</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <!-- Spring Boot Maven Plugin -->
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>

            <!-- Frontend Maven Plugin -->
            <plugin>
                <groupId>com.github.eirslett</groupId>
                <artifactId>frontend-maven-plugin</artifactId>
                <version>1.11.0</version>
                <configuration>
                    <nodeVersion>v16.20.2</nodeVersion>
                    <workingDirectory>src/main/resources/static</workingDirectory>
                </configuration>
                <executions>
                    <execution>
                        <id>install node and npm</id>
                        <goals>
                            <goal>install-node-and-npm</goal>
                        </goals>
                    </execution>
                    <execution>
                        <id>npm install</id>
                        <goals>
                            <goal>npm</goal>
                        </goals>
                        <configuration>
                            <arguments>install</arguments>
                        </configuration>
                    </execution>
                    <execution>
                        <id>npm run build</id>
                        <goals>
                            <goal>npm</goal>
                        </goals>
                        <configuration>
                            <arguments>run build</arguments>
                        </configuration>
                    </execution>
                </executions>
            </plugin>

            <!-- Maven Resources Plugin (Copy JAR Task) -->
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-resources-plugin</artifactId>
                <version>3.2.0</version>
                <executions>
                    <execution>
                        <id>copy-jar</id>
                        <phase>package</phase>
                        <goals>
                            <goal>copy-resources</goal>
                        </goals>
                        <configuration>
                            <outputDirectory>${project.build.directory}/dist</outputDirectory>
                            <resources>
                                <resource>
                                    <directory>${project.build.directory}</directory>
                                    <includes>
                                        <include>*.jar</include>
                                    </includes>
                                </resource>
                            </resources>
                        </configuration>
                    </execution>
                </executions>
            </plugin>

            <!-- Maven Clean Plugin (Delete Webpack Files Task) -->
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-clean-plugin</artifactId>
                <version>3.1.0</version>
                <executions>
                    <execution>
                        <id>delete-webpack-files</id>
                        <phase>clean</phase>
                        <goals>
                            <goal>clean</goal>
                        </goals>
                        <configuration>
                            <filesets>
                                <fileset>
                                    <directory>src/main/resources/static/built</directory>
                                    <includes>
                                        <include>*</include>
                                    </includes>
                                </fileset>
                            </filesets>
                        </configuration>
                    </execution>
                </executions>
            </plugin>
        </plugins>
    </build>
</project>
```
**Comparison Between Maven and Gradle**

| Feature                  | Maven                                                       | Gradle                                                      |
|--------------------------|-------------------------------------------------------------|-------------------------------------------------------------|
| **Build Language**       | XML-based configuration.                                    | Uses Groovy or Kotlin DSL for configuration scripts.        |
| **Performance**          | Generally slower due to its linear phase-dependent approach.| Faster, supports incremental builds and up-to-date checks.  |
| **Flexibility**          | Less flexible, rigid lifecycle phases.                      | Highly customizable and flexible due to scripting support.  |
| **Dependency Management**| Uses centralized repository system.                         | Offers powerful dependency management with dynamic versions.|
| **Ease of Use**          | Simple to set up with standardized lifecycle phases.        | Steeper learning curve but more powerful due to flexibility.|
| **Plugins**              | Wide range of available plugins but adding new plugins can be more complex.| Extensive plugin ecosystem; easier to write and apply custom plugins.|
| **Community and Support**| Well-established, large community and lots of resources.    | Growing community, well-supported with ample documentation. |
| **Use Case**             | Better suited for conventional Java projects.               | Ideal for multi-project builds and projects needing high customization.|

Maven serves as a reliable alternative to Gradle for managing and building Spring Boot projects. 
Through its detailed setup and configuration, Maven showcases strong capabilities in dependency management, build automation, and plugin integration. 
Its structured approach, featuring a predictable build lifecycle and a rich plugin ecosystem, makes it ideal for projects requiring a standardized build process. 
Although Maven may not match Gradle’s flexibility or performance optimizations—such as incremental builds and dynamic scripting—its simplicity and widespread adoption in the Java community ensure its relevance in many development scenarios.

## Conclusion Part 3
This report outlines the migration of a Spring Boot application from Maven to Gradle, emphasizing Gradle’s strengths in dependency management, frontend integration, and customizable build tasks. By comparing Maven and Gradle, we’ve highlighted their unique advantages: Maven’s structured, standardized approach and Gradle’s flexibility and scripting capabilities.
This process has deepened my understanding of build automation tools and reinforced the importance of selecting the right tool based on project needs and team expertise. These insights will inform future decisions, ensuring efficient and effective project management.








