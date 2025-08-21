package com.luv2code.springboot.thymeleafdemo.controller;

import com.luv2code.springboot.thymeleafdemo.entity.Employee;
import com.luv2code.springboot.thymeleafdemo.service.EmployeeService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService theEmployeeService) {
        employeeService = theEmployeeService;
    }

    @GetMapping("/list")
    public String listEmployees(Model model) {
        List<Employee> employees = employeeService.findAll();

        model.addAttribute("employees", employees);

        return "employees/list-employees";
    }

    @GetMapping("/showFormForSave")
    public String showFormForAdd(Model model) {

        model.addAttribute("employee", new Employee());

        return "employees/save-employee-form";
    }

    @PostMapping("/processForm")
    public String processForm(@ModelAttribute("employee") Employee employee) {
        System.out.println("processForm with " + employee);

        employeeService.save(employee);

        System.out.println("redirecting");
        // Redirect and make a get request to employees/list, instead of returning a thymeleaf html page
        // this prevents duplicate submissions in case user refreshes browser
        // This is the "post/redirect/get" pattern
        return "redirect:/employees/list";
    }

    /// UPDATE
    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(HttpServletRequest req, Model model) {
        int id = Integer.parseInt(req.getParameter("employeeId"));  // Could also use "@RequestParam("employeeId") int id" in the parameters to get the id
        Employee emp = employeeService.findById(id);

        model.addAttribute("employee", emp);

        return "employees/save-employee-form";
    }
}
