package com.greglturnquist.payroll;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
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