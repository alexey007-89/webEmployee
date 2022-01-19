package pro.sky.java.course2.webemployee.service.Impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.java.course2.webemployee.data.Employee;
import pro.sky.java.course2.webemployee.exceptions.EmployeeNotFoundException;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static pro.sky.java.course2.webemployee.constant.EmployeeConstants.*;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceTest {
    Collection<Employee> expected = new ArrayList<>();
    Employee employee1;
    Employee employee2;
    Employee employee3;
    Employee employee4;
    Employee employee5;

    @Mock
    private EmployeeServiceImpl employeeServiceMock;

    @InjectMocks
    private DepartmentServiceImpl out;

    @BeforeEach
    public void setUp() {
        expected.add(EMPLOYEE_2_1);
        expected.add(EMPLOYEE_2_2);
        expected.add(EMPLOYEE_2_3);
        expected.add(EMPLOYEE_2_4);
        expected.add(EMPLOYEE_2_5);
    }

    @Test
    void shouldCountSumSalaryWorkCorrect() {
        when(employeeServiceMock.getEmployees())
                .thenReturn(expected);
        assertEquals(100_000, out.countSumSalary(1));
        assertEquals(-40_000, out.countSumSalary(2));
        verify(employeeServiceMock, times(2)).getEmployees();
    }

    @Test
    void shouldFindMinSalaryWorkCorrect() {
        when(employeeServiceMock.getEmployees())
                .thenReturn(expected);
        assertEquals(EMPLOYEE_2_3, out.findMinSalary(1));
        assertEquals(EMPLOYEE_2_4, out.findMinSalary(2));
        verify(employeeServiceMock, times(2)).getEmployees();
    }

    @Test
    void shouldFindMinSalaryThrowExceptionWhenEmployeeNotFound() {
        when(employeeServiceMock.getEmployees())
                .thenThrow(EmployeeNotFoundException.class);
        assertThrows(EmployeeNotFoundException.class, () -> out.findMinSalary(1));
        assertThrows(EmployeeNotFoundException.class, () -> out.findMinSalary(2));
    }

    @Test
    void shouldFindMaxSalaryWorkCorrect() {
        when(employeeServiceMock.getEmployees())
                .thenReturn(expected);
        assertEquals(EMPLOYEE_2_2, out.findMaxSalary(1));
        assertEquals(EMPLOYEE_2_5, out.findMaxSalary(2));
        verify(employeeServiceMock, times(2)).getEmployees();
    }

    @Test
    void shouldFindMaxSalaryThrowExceptionWhenEmployeeNotFound() {
        when(employeeServiceMock.getEmployees())
                .thenThrow(EmployeeNotFoundException.class);
        assertThrows(EmployeeNotFoundException.class, () -> out.findMaxSalary(1));
        assertThrows(EmployeeNotFoundException.class, () -> out.findMaxSalary(2));
    }
    @Test
    void shouldGetAllWorkCorrect() {
        when(employeeServiceMock.getEmployees())
                .thenReturn(expected);
        List<Employee> expected1 = new ArrayList<>();
        expected1.add(EMPLOYEE_2_1);
        expected1.add(EMPLOYEE_2_2);
        expected1.add(EMPLOYEE_2_3);
        assertIterableEquals(expected1, out.getAll(1));
        List<Employee> expected2 = new ArrayList<>();
        expected2.add(EMPLOYEE_2_4);
        expected2.add(EMPLOYEE_2_5);
        assertIterableEquals(expected2, out.getAll(2));
        verify(employeeServiceMock, times(2)).getEmployees();

    }

    @Test
    void shouldTestGetAllWorkCorrect() {
        when(employeeServiceMock.getEmployees())
                .thenReturn(expected);
        List<Employee> expected1 = new ArrayList<>();
        expected1.add(EMPLOYEE_2_1);
        expected1.add(EMPLOYEE_2_2);
        expected1.add(EMPLOYEE_2_3);
        List<Employee> expected2 = new ArrayList<>();
        expected2.add(EMPLOYEE_2_4);
        expected2.add(EMPLOYEE_2_5);
        Map<Integer, List<Employee>> expectedMap = new HashMap<>();
        expectedMap.put(1, expected1);
        expectedMap.put(2, expected2);
        assertEquals(expectedMap, out.getAll());
        verify(employeeServiceMock, times(1)).getEmployees();
    }
}