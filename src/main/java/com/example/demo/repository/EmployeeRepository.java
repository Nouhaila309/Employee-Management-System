package com.example.demo.repository;
import com.example.demo.entity.Employee;
import com.example.demo.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
  /* @Query(value = "SELECT * FROM EMPLOYEE", nativeQuery = true)
   List<Employee> findAllEmployees();*/
  // EmployeeDto updateEmployeeById(Integer id, Employee updatedEmployee);
   // EmployeeDto employeeDto(EmployeeDto employeeDto);
  Employee findByRole(Role role);
}

