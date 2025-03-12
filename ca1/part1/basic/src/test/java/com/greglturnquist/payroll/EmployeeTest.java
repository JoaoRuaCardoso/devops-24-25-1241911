package com.greglturnquist.payroll;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class EmployeeTest {
    @Test
    void shouldCreateEmployee() throws Exception{
        //arrange
        Employee employee = new Employee("Joseph","Green","CEO",10,"jg@gmail.com");
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
        assertThrows(Exception.class, () -> new Employee(null,"Green","CEO",10,"jg@gmail.com"));
    }
    @Test
    void shouldCreateAnExceptionWhenFirstNameIsEmpty(){
        //assert
        assertThrows(Exception.class, () -> new Employee("","Green","CEO",10,"jg@gmail.com"));
    }
    @Test
    void shouldCreateAnExceptionWhenLastNameIsNull(){
        //assert
        assertThrows(Exception.class, () -> new Employee("Joseph",null,"CEO",10,"jg@gmail.com"));
    }
    @Test
    void shouldCreateAnExceptionWhenLastNameIsEmpty(){
        //assert
        assertThrows(Exception.class, () -> new Employee("Joseph","","CEO",10,"jg@gmail.com"));
    }
    @Test
    void shouldCreateAnExceptionWhenDescriptionIsNull(){
        //assert
        assertThrows(Exception.class, () -> new Employee("Joseph","Green",null,10,"jg@gmail.com"));
    }
    @Test
    void shouldCreateAnExceptionWhenDescriptionIsEmpty(){
        //assert
        assertThrows(Exception.class, () -> new Employee("Joseph","Green","",10,"jg@gmail.com"));
    }
    @Test
    void shouldCreateAnExceptionWhenJobYearsIsNegative(){
        //assert
        assertThrows(Exception.class, () -> new Employee("Joseph","Green","CEO",-1,"jg@gmail.com"));
    }
    @Test
    void shouldCreateEmployeeWhenJobYearsEqualToZero() throws Exception{
        //arrange
        Employee employee = new Employee("Joseph","Green","CEO",0,"jg@gmail.com");
        //assert
        assertNotNull(employee);
    }
    @Test
    void shouldCreateAnExceptionWhenEmailHasNoAtSym() throws Exception{
        //assert
        assertThrows(Exception.class, () -> new Employee("Joseph","Green","CEO",1,"jggmail.com"));
    }
    @Test
    void shouldCreateAnExceptionWhenEmailIsNull() throws Exception{
        //assert
        assertThrows(Exception.class, () -> new Employee("Joseph","Green","CEO",1,null));
    }

    //equals
    @Test
    void shouldReturnTrueWhenObjectsAreInSameLocation() throws Exception{
        //arrange
        Employee employee = new Employee("Joseph","Green","CEO",10,"jg@gmail.com");
        Employee employee1 = employee;
        //act
        boolean result = employee.equals(employee1);
        //assert
        assertTrue(result);
    }
    @Test
    void shouldReturnFalseWhenObjectsAreFromDifferentInstances() throws Exception{
        //arrange
        Employee employee = new Employee("Joseph","Green","CEO",10,"jg@gmail.com");
        Object o = new Object();
        //act
        boolean result = employee.equals(o);
        //assert
        assertFalse(result);
    }
    @Test
    void shouldReturnFalseWhenObjectsToCompareIsNull() throws Exception{
        //arrange
        Employee employee = new Employee("Joseph","Green","CEO",10,"jg@gmail.com");
        Employee employee1 = null;
        //act
        boolean result = employee.equals(employee1);
        //assert
        assertFalse(result);
    }
    @Test
    void shouldReturnTrueWhenObjectsHaveSameContent() throws Exception{
        //arrange
        Employee employee = new Employee("Joseph","Green","CEO",10,"jg@gmail.com");
        Employee employee1 = new Employee("Joseph","Green","CEO",10,"jg@gmail.com");
        //act
        boolean result = employee.equals(employee1);
        //assert
        assertTrue(result);
    }
    @Test
    void shouldReturnFalseWhenObjectsHaveDifferentFirstNames() throws Exception{
        //arrange
        Employee employee = new Employee("Joseph","Green","CEO",10,"jg@gmail.com");
        Employee employee1 = new Employee("Josephs","Green","CEO",10,"jg@gmail.com");
        //act
        boolean result = employee.equals(employee1);
        //assert
        assertFalse(result);
    }
    @Test
    void shouldReturnFalseWhenObjectsHaveDifferentLastNames() throws Exception{
        //arrange
        Employee employee = new Employee("Joseph","Green","CEO",10,"jg@gmail.com");
        Employee employee1 = new Employee("Joseph","Greens","CEO",10,"jg@gmail.com");
        //act
        boolean result = employee.equals(employee1);
        //assert
        assertFalse(result);
    }
    @Test
    void shouldReturnFalseWhenObjectsHaveDifferentDescriptions() throws Exception{
        //arrange
        Employee employee = new Employee("Joseph","Green","CEO",10,"jg@gmail.com");
        Employee employee1 = new Employee("Joseph","Green","CFO",10,"jg@gmail.com");
        //act
        boolean result = employee.equals(employee1);
        //assert
        assertFalse(result);
    }
    @Test
    void shouldReturnFalseWhenObjectsHaveDifferentJobYears() throws Exception{
        //arrange
        Employee employee = new Employee("Joseph","Green","CEO",10,"jg@gmail.com");
        Employee employee1 = new Employee("Joseph","Greens","CEO",11,"jg@gmail.com");
        //act
        boolean result = employee.equals(employee1);
        //assert
        assertFalse(result);
    }
    @Test
    void shouldReturnFalseWhenObjectsHaveDifferentId() throws Exception {
        //arrange
        Employee employee = new Employee("Joseph", "Green", "CEO", 10,"jg@gmail.com");
        Employee employee1 = new Employee("Joseph", "Greens", "CEO", 10,"jg@gmail.com");
        employee.setId(1L);
        employee1.setId(2L);
        //act
        boolean result = employee.equals(employee1);
        //assert
        assertFalse(result);
    }
    @Test
    void shouldReturnFalseWhenObjectsHaveDifferentEmail() throws Exception{
        //arrange
        Employee employee = new Employee("Joseph","Green","CEO",10,"jg@gmail.com");
        Employee employee1 = new Employee("Joseph","Green","CEO",11,"jog@gmail.com");
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
        Employee employee = new Employee("Joseph", "Green", "CEO", 10,"jg@gmail.com");
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
        Employee employee = new Employee("Joseph", "Green", "CEO", 10,"jg@gmail.com");
        Employee employee1 = new Employee("Josephs", "Greens", "CEO", 10,"jg@gmail.com");
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
        Employee employee = new Employee("Joseph", "Green", "CEO", 10,"jg@gmail.com");
        employee.setId(1L);
        //act
        Long result = employee.getId();
        //assert
        assertEquals(1L,result);
    }
    @Test
    void shouldReturnFirstName() throws Exception{
        //arrange
        Employee employee = new Employee("Joseph", "Green", "CEO", 10,"jg@gmail.com");
        //act
        String result = employee.getFirstName();
        //assert
        assertEquals("Joseph",result);
    }
    @Test
    void shouldReturnLastName() throws Exception{
        //arrange
        Employee employee = new Employee("Joseph", "Green", "CEO", 10,"jg@gmail.com");
        //act
        String result = employee.getLastName();
        //assert
        assertEquals("Green",result);
    }
    @Test
    void shouldReturnDescription() throws Exception{
        //arrange
        Employee employee = new Employee("Joseph", "Green", "CEO", 10,"jg@gmail.com");
        //act
        String result = employee.getDescription();
        //assert
        assertEquals("CEO",result);
    }
    @Test
    void shouldReturnJobYears() throws Exception{
        //arrange
        Employee employee = new Employee("Joseph", "Green", "CEO", 10,"jg@gmail.com");
        //act
        int result = employee.getJobYears();
        //assert
        assertEquals(10,result);
    }
    @Test
    void shouldReturnEmail() throws Exception{
        //arrange
        Employee employee = new Employee("Joseph", "Green", "CEO", 10,"jg@gmail.com");
        //act
        String result = employee.getEmail();
        //assert
        assertEquals("jg@gmail.com",result);
    }
    //set
    @Test
    void shouldReturnSetFirstName() throws Exception{
        //arrange
        Employee employee = new Employee("Joseph", "Green", "CEO", 10,"jg@gmail.com");
        //act
        employee.setFirstName("Alfred");
        //assert
        assertEquals("Alfred",employee.getFirstName());
    }
    @Test
    void shouldReturnSetLastName() throws Exception{
        //arrange
        Employee employee = new Employee("Joseph", "Green", "CEO", 10,"jg@gmail.com");
        //act
        employee.setLastName("Blue");
        //assert
        assertEquals("Blue",employee.getLastName());
    }
    @Test
    void shouldReturnSetDescription() throws Exception{
        //arrange
        Employee employee = new Employee("Joseph", "Green", "CEO", 10,"jg@gmail.com");
        //act
        employee.setDescription("CFO");
        //assert
        assertEquals("CFO",employee.getDescription());
    }
    @Test
    void shouldReturnSetJobYears() throws Exception{
        //arrange
        Employee employee = new Employee("Joseph", "Green", "CEO", 10,"jg@gmail.com");
        //act
        employee.setJobYears(9);
        //assert
        assertEquals(9,employee.getJobYears());
    }
    @Test
    void shouldReturnSetEmail() throws Exception{
        //arrange
        Employee employee = new Employee("Joseph", "Green", "CEO", 10,"jg@gmail.com");
        //act
        employee.setEmail("gj@gmail.com");
        //assert
        assertEquals("gj@gmail.com",employee.getEmail());
    }
    //toString

    @Test
    void shouldReturnResult() throws Exception{
        //arrange
        Employee employee = new Employee("Jose", "Verde", "CEO", 10,"JG@gmail.com");
        employee.setId(1L);
        String expected = "Employee{" +
                "id=1" +
                ", firstName='Jose'" +
                ", lastName='Verde'" +
                ", description='CEO'" +
                ", jobYears='10'" +
                ", email='JG@gmail.com'}";
        //act
        String result = employee.toString();
        //assert
        assertEquals(expected,result);
    }

}