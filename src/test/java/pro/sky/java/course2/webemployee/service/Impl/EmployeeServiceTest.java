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
import static pro.sky.java.course2.webemployee.constant.EmployeeConstants.*;

class EmployeeServiceTest {
    private final EmployeeService out = new EmployeeServiceImpl();
    List<Employee> expected = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        expected.add(EMPLOYEE_1);
        expected.add(EMPLOYEE_2);
        expected.add(EMPLOYEE_3);
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
        expected.remove(EMPLOYEE_3);
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
        assertEquals(EMPLOYEE_1, out.findEmployee("Alexey", "Ivanov"));
        assertEquals(EMPLOYEE_2, out.findEmployee("Ivan", "Kashtanov"));
        assertEquals(EMPLOYEE_3, out.findEmployee("Kiril", "Kucherov"));
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
        assertEquals(EMPLOYEE_3, out.findMinSalary());
    }

    @Test
    void shouldFindMaxSalaryWorkCorrect() {
        out.addEmployee("Alexey", "Ivanov", 1, 50_000);
        out.addEmployee("Ivan", "Kashtanov", 2, 60_000);
        out.addEmployee("Kiril", "Kucherov", 3, 20_000);
        assertEquals(EMPLOYEE_2, out.findMaxSalary());
    }

    @Test
    void shouldReturnListOfEmployeesWhenGetEmployeesCalled() {
        out.addEmployee("Alexey", "Ivanov", 1, 50_000);
        out.addEmployee("Ivan", "Kashtanov", 2, 60_000);
        out.addEmployee("Kiril", "Kucherov", 3, 20_000);
        assertIterableEquals(expected, out.getEmployees());
    }
}