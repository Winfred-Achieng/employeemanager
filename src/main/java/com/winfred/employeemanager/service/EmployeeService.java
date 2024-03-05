package com.winfred.employeemanager.service;

import com.winfred.employeemanager.exception.UserNotFoundException;
import com.winfred.employeemanager.model.Employee;
import com.winfred.employeemanager.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee addEmployee(Employee employee){
        try {
            employee.setEmployeeCode(UUID.randomUUID().toString());
            return employeeRepository.save(employee);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to add employee. Please try again later.");
        }    }

    public List<Employee> findAllEmployees(){
        return employeeRepository.findAll();
    }
    public Employee findEmployeeById(Long id){
        Optional<Employee> employeeOptional = employeeRepository.findById(id);

        if(employeeOptional.isPresent()){
            return employeeOptional.get();
        }else {
           throw new UserNotFoundException(" Employee not found with ID: " +id);
        }    }

    public Employee updateEmployee(Employee employee){
        return employeeRepository.save(employee);
    }

    public void deleteEmployee(Long id){
       employeeRepository.deleteById(id);
    }


}
