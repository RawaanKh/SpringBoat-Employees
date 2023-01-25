package com.example.springboatday04.Controller;

import com.example.springboatday04.Pojo.ApiResponse;
import com.example.springboatday04.Pojo.Employees;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.Errors;
import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/employee")
public class EmployeesController {
    ArrayList<Employees> employees=new ArrayList<>();
    @GetMapping("/get")
    public ResponseEntity getAllEmployees(){
        return ResponseEntity.status(200).body(employees);
    }

    @PostMapping("/add")
    public ResponseEntity addEmployees(@Valid @RequestBody Employees employee, Errors error){
        if( error.hasErrors()){
            String message=error.getFieldError().getDefaultMessage();
            return  ResponseEntity.status(400).body(new ApiResponse(message));
        }
        employees.add(employee);
        return ResponseEntity.status(200).body(new ApiResponse("Employee added!"));
    }
    @PutMapping("update/{index}")
    public ResponseEntity updateEmployee( @PathVariable int index,@RequestBody @Valid Employees employee, Errors errors){
        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }

        employees.set(index, employee);
        return ResponseEntity.status(200).body(new ApiResponse("Employee Updated! "));
    }
    @DeleteMapping("/delete/{index}")
    public ResponseEntity deleteEmployee(@PathVariable int index , @Valid @RequestBody Employees employee, Errors error){
        if( error.hasErrors()){
            String message=error.getFieldError().getDefaultMessage();
            return  ResponseEntity.status(400).body(new ApiResponse(message));
        }
        employees.set(index,employee);
        return ResponseEntity.status(200).body(new ApiResponse("User deleted!"));
    }
    @PutMapping("/leave/{id}")
    public ResponseEntity applyForLeave(@PathVariable String id){
        for(Employees e: employees){
                    if(e.getId().equals(id)){
                    if (e.isOnLeave()) {
                    if (e.getAnnualLeave() >= 1) {
                        e.setOnLeave(true);
                        e.setAnnualLeave(e.getAnnualLeave() - 1);
                        return ResponseEntity.status(200).body(new ApiResponse("Happy Vacation!(عقبالنا)"));
                    }
                }else ResponseEntity.status(400).body(new ApiResponse("Employee does not have on leave days left or already on leave"));
            }
        }
        return ResponseEntity.status(400).body(new ApiResponse("Employee does not have on leave days left or already on leave"));
    }
}
