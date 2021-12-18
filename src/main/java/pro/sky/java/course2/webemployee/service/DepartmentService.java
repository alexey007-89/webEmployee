package pro.sky.java.course2.webemployee.service;

import pro.sky.java.course2.webemployee.data.Employee;

import java.util.Collection;
import java.util.Optional;

public interface DepartmentService {

    long countSumSalary(int departmentID);

    Optional<Employee> findMinSalary(int departmentID);

    Optional<Employee> findMaxSalary(int departmentID);

    Collection<Employee> getAll(int departmentID);

    Collection<Employee> getAll();

}
