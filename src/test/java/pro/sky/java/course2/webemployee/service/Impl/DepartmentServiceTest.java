package pro.sky.java.course2.webemployee.service.Impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.java.course2.webemployee.data.Employee;
import pro.sky.java.course2.webemployee.exceptions.EmployeeNotFoundException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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
        employee1 = new Employee("Alexey", "Ivanov", 1, 50_000);
        employee2 = new Employee("Ivan", "Kashtanov", 1, 60_000);
        employee3 = new Employee("Kiril", "Kucherov", 1, -10_000);
        employee4 = new Employee("Darya", "Prihodko", 2, -40_000);
        employee5 = new Employee("Elena", "Golovach", 2, 0);
        expected.add(employee1);
        expected.add(employee2);
        expected.add(employee3);
        expected.add(employee4);
        expected.add(employee5);
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
        assertEquals(employee3, out.findMinSalary(1));
        assertEquals(employee4, out.findMinSalary(2));
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
        assertEquals(employee2, out.findMaxSalary(1));
        assertEquals(employee5, out.findMaxSalary(2));
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
        List<Employee> expected1 = expected.stream()
                .filter(employee -> employee.getDepartmentId() == 1)
                .collect(Collectors.toList());
        when(employeeServiceMock.getEmployees())
                .thenReturn(expected1);
        assertEquals(expected1, out.getAll(1));
        List<Employee> expected2 = expected.stream()
                .filter(employee -> employee.getDepartmentId() == 2)
                .collect(Collectors.toList());
        when(employeeServiceMock.getEmployees())
                .thenReturn(expected2);
        assertEquals(expected2, out.getAll(2));
        verify(employeeServiceMock, times(2)).getEmployees();

    }

    @Test
    void shouldTestGetAllWorkCorrect() {
        when(employeeServiceMock.getEmployees())
                .thenReturn(expected);
        Map<Integer, List<Employee>> expectedMap = expected.stream()
                .collect(Collectors.groupingBy(Employee::getDepartmentId));
        assertEquals(expectedMap, out.getAll());
        verify(employeeServiceMock, times(1)).getEmployees();
    }
}