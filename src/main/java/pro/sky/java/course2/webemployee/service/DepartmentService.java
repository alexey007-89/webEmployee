package pro.sky.java.course2.webemployee.service;

import pro.sky.java.course2.webemployee.data.Employee;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface DepartmentService {

    long countSumSalary(int departmentID);

    Employee findMinSalary(int departmentID);

    Employee findMaxSalary(int departmentID);

    Collection<Employee> getAll(int departmentID);

    Map<Integer, List<Employee>> getAll();

}
