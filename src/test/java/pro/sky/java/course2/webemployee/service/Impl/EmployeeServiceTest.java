package pro.sky.java.course2.webemployee.service.Impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pro.sky.java.course2.webemployee.data.Employee;
import pro.sky.java.course2.webemployee.exceptions.EmployeeAlreadyExistException;
import pro.sky.java.course2.webemployee.exceptions.EmployeeNotFoundException;
import pro.sky.java.course2.webemployee.exceptions.WrongRequestException;
import pro.sky.java.course2.webemployee.service.EmployeeService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeServiceTest {
    private final EmployeeService out = new EmployeeServiceImpl();
    List<Employee> expected = new ArrayList<>();
    Employee employee1;
    Employee employee2;
    Employee employee3;

    @BeforeEach
    public void setUp() {
        employee1 = new Employee("Alexey", "Ivanov", 1, 50_000);
        employee2 = new Employee("Ivan", "Kashtanov", 2, 60_000);
        employee3 = new Employee("Kiril", "Kucherov", 3, 20_000);
        expected.add(employee1);
        expected.add(employee2);
        expected.add(employee3);
    }

    @Test
    void shouldAddEmployeeWorkCorrect() {
        out.addEmployee("Alexey", "Ivanov", 1, 50_000);
        out.addEmployee("Ivan", "Kashtanov", 2, 60_000);
        out.addEmployee("Kiril", "Kucherov", 3, 20_000);
        assertEquals(expected, out.getEmployees());
    }


    @Test
    void shouldAddEmployeeTrowExceptionIfEmployeeAlreadyExist() {
        out.addEmployee("Alexey", "Ivanov", 1, 50_000);
        assertThrows(EmployeeAlreadyExistException.class, () -> out.addEmployee("Alexey", "Ivanov", 1, 50_000));
    }

    @Test
    void shouldAddEmployeeTrowExceptionIfEmployeeNotValid() {
        assertThrows(WrongRequestException.class, () -> out.addEmployee("Alexey123", "Ivanov456", 1, 50_000));
    }

    @Test
    void shouldRemoveEmployeeWorkCorrect() {
        out.addEmployee("Alexey", "Ivanov", 1, 50_000);
        out.addEmployee("Ivan", "Kashtanov", 2, 60_000);
        out.addEmployee("Kiril", "Kucherov", 3, 20_000);
        out.removeEmployee("Kiril", "Kucherov");
        expected.remove(employee3);
        assertEquals(expected, out.getEmployees());
    }

    @Test
    void shouldRemoveEmployeeThrowExceptionIfEmployeeNotFound() {
        out.addEmployee("Alexey", "Ivanov", 1, 50_000);
        out.addEmployee("Ivan", "Kashtanov", 2, 60_000);
        assertThrows(EmployeeNotFoundException.class, () -> out.removeEmployee("Kiril", "Kucherov"));
    }

    @Test
    void shouldFindEmployeeWorkCorrect() {
        out.addEmployee("Alexey", "Ivanov", 1, 50_000);
        out.addEmployee("Ivan", "Kashtanov", 2, 60_000);
        out.addEmployee("Kiril", "Kucherov", 3, 20_000);
        assertEquals(employee1, out.findEmployee("Alexey", "Ivanov"));
        assertEquals(employee2, out.findEmployee("Ivan", "Kashtanov"));
        assertEquals(employee3, out.findEmployee("Kiril", "Kucherov"));
    }

    @Test
    void shouldFindEmployeeThrowExceptionIfEmployeeNotFound() {
        out.addEmployee("Alexey", "Ivanov", 1, 50_000);
        out.addEmployee("Ivan", "Kashtanov", 2, 60_000);
        assertThrows(EmployeeNotFoundException.class, () -> out.findEmployee("Kiril", "Kucherov"));
    }

    @Test
    void shouldCountSumSalaryWorkCorrect() {
        out.addEmployee("Alexey", "Ivanov", 1, 50_000);
        out.addEmployee("Ivan", "Kashtanov", 2, -60_000);
        out.addEmployee("Kiril", "Kucherov", 3, 0);
        assertEquals(-10_000, out.countSumSalary());
    }

    @Test
    void shouldFindMinSalaryWorkCorrect() {
        out.addEmployee("Alexey", "Ivanov", 1, 50_000);
        out.addEmployee("Ivan", "Kashtanov", 2, 60_000);
        out.addEmployee("Kiril", "Kucherov", 3, 20_000);
        assertEquals(employee3, out.findMinSalary());
    }

    @Test
    void shouldFindMaxSalaryWorkCorrect() {
        out.addEmployee("Alexey", "Ivanov", 1, 50_000);
        out.addEmployee("Ivan", "Kashtanov", 2, 60_000);
        out.addEmployee("Kiril", "Kucherov", 3, 20_000);
        assertEquals(employee2, out.findMaxSalary());
    }

    @Test
    void shouldReturnListOfEmployeesWhenGetEmployeesCalled() {
        out.addEmployee("Alexey", "Ivanov", 1, 50_000);
        out.addEmployee("Ivan", "Kashtanov", 2, 60_000);
        out.addEmployee("Kiril", "Kucherov", 3, 20_000);
        assertIterableEquals(expected, out.getEmployees());
    }
}