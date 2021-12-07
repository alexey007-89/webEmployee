package pro.sky.java.course2.webemployee;

import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final Employee[] employees;

    public EmployeeServiceImpl() {
        this.employees = new Employee[10];
    }

    @Override
    public Employee findEmployee(String firstName, String lastName) {
        for (Employee employee : employees) {
            if (firstName.equals(employee.getFirstName()) && lastName.equals(employee.getLastName())) {
                return employee;
            }
        }
        throw new EmployeeNotFound();
    }

    @Override
    public void addEmployee(String firstName, String lastName) {
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] == null) {
                Employee newEmployee = new Employee(firstName, lastName);
                employees[i] = newEmployee;
                return;
            }
        }
        throw new EmployeeArrayIsFull();
    }

    @Override
    public void removeEmployee(String firstName, String lastName) {
        for (int i = 0; i < employees.length; i++) {
            if (firstName.equals(employees[i].getFirstName()) && lastName.equals(employees[i].getLastName())) {
                employees[i] = null;
                return;
            }
        }
        throw new EmployeeNotFound();
    }
}
