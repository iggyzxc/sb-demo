package com.springboot.sbdemo.controller;

import com.springboot.sbdemo.bean.Student;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {

    @GetMapping("student")
    public Student getStudent() {
        return new Student(1, "John", "Smith");
    }

    @GetMapping("students")
    public List<Student> getStudents() {
        List<Student> students = new ArrayList<>();
            students.add(new Student(1,"John", "Smith"));
            students.add(new Student(2,"Jake", "Blue"));
            students.add(new Student(3,"Lisa", "White"));
            students.add(new Student(4, "Peter", "Pan"));
        return students;
    }

    @GetMapping("students/{id}/{first-name}/{last-name}")
    public Student studentPathVariable(@PathVariable("id") int studentId,
                                       @PathVariable("first-name") String firstName,
                                       @PathVariable("last-name") String lastName) {
        return new Student(studentId, firstName, lastName);
    }

    @GetMapping("students/query")
    public Student requestVariable(@RequestParam int id,
                                   @RequestParam String firstName,
                                   @RequestParam String lastName) {
        return  new Student(id, firstName, lastName);
    }

    @PostMapping("students/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Student createStudent(@RequestBody Student student) {
        System.out.println(student.getId());
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        return student;
    }

    @PutMapping("students/{id}/update")
    public Student updateStudents(@RequestBody Student student, @PathVariable("id") int studentId) {
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        return student;
    }

    @DeleteMapping("students/{id}/delete")
    public String deleteStudent(@PathVariable("id") int studentId) {
        System.out.println(studentId);
        return "Student deleted successfully!";
    }
}
