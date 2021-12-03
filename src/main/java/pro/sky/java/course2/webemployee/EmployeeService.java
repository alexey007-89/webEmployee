package pro.sky.java.course2.webemployee;

public interface EmployeeService {
    Employee findEmployee(String firstName, String lastName);
    void addEmployee(String firstName, String lastName);
    void removeEmployee(String firstName, String lastName);
}
