package com.luv2code.springboot.cruddemo.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.luv2code.springboot.cruddemo.entity.Employee;
import com.luv2code.springboot.cruddemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

    private EmployeeService employeeService;

    private ObjectMapper objectMapper;

    @Autowired
    public EmployeeRestController(EmployeeService employeeService, ObjectMapper objectMapper) {
        this.employeeService = employeeService;
        this.objectMapper = objectMapper;
    }


    // READ
    // Expose /employees and return a list of all employees
    @GetMapping("/employees")
    public List<Employee> findAll() {
        return employeeService.findAll();
    }

    @GetMapping("/employees/{id}")
    public Employee findById(@PathVariable int id) {
        Employee employee = employeeService.findById(id);

        if (employee == null)
            throw new RuntimeException("Employee id " + id + " was not found.");

        return employee;
    }


    // INSERT
    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee employee) {

        // In case they pass in an id in the JSON, we will set it to 0, so that
        // entityManager.merge() will add a new row instead of update
        employee.setId(0);

        return employeeService.save(employee);
    }


    // UPDATE
    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee employee) {

        return employeeService.save(employee);
    }

    @PatchMapping("/employees/{id}")  // patchPayload is the JSON body/payload with fields to update
    public Employee patchEmployee(@PathVariable int id, @RequestBody Map<String, Object> patchPayload) {
        Employee tempEmployee = employeeService.findById(id);

        if (tempEmployee == null)
            throw new RuntimeException("Employee with id " + id + " not found.");

        // body must not contain an id, to maintain integrity of our id data
        if (patchPayload.containsKey("id"))
            throw new RuntimeException("Employee id is not allowed in the request body. Remove id: " + patchPayload.get("id") + " from the body.");

        // Update the fields on the java object
        Employee patchedEmployee = apply(patchPayload, tempEmployee);

        // Save to database and return the resulting object
        return employeeService.save(patchedEmployee);
    }

    private Employee apply(Map<String, Object> patchPayload, Employee tempEmployee) {

        // Convert employee object to a JSON object node
        ObjectNode employeeNode = objectMapper.convertValue(tempEmployee, ObjectNode.class);

        ObjectNode patchNode = objectMapper.convertValue(patchPayload, ObjectNode.class);

        employeeNode.setAll(patchNode);

        // Return the final employee object that has all original values + patched values
        return objectMapper.convertValue(employeeNode, Employee.class);
    }


    // DELETE
    @DeleteMapping("/employees/{id}")
    public String deleteEmployee(@PathVariable int id) {
        Employee emp = employeeService.findById(id);

        if (emp == null)
            throw new RuntimeException("Employee id not found: " + id);

        employeeService.deleteById(id);

        return "Deleted employee with id " + id + " from the database.";
    }
}