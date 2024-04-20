package com.springboot.sbdemo.controller;

import com.springboot.sbdemo.bean.Student;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("students")
public class StudentController {

    // http://localhost://8080/student
    @GetMapping("student")
    public ResponseEntity<Student> getStudent() {
        Student student = new Student(1, "John", "Smith");
    // return new ResponseEntity<>(student, HttpStatus.OK);
    // return  ResponseEntity.ok(student);
        return ResponseEntity.ok()
                .header("custom-header","John")
                .body(student);
    }

    // http://localhost://8080/students
    @GetMapping
    public ResponseEntity<List<Student>> getStudents() {
        List<Student> students = new ArrayList<>();
            students.add(new Student(1,"John", "Smith"));
            students.add(new Student(2,"Jake", "Blue"));
            students.add(new Student(3,"Lisa", "White"));
            students.add(new Student(4, "Peter", "Pan"));
        // return students;
        return ResponseEntity.ok(students);
    }

    // Spring BOOT REST API with Path Variable
    // {id} - URI template variable
    // http://localhost:8080/students/1/John/Smith
    @GetMapping("{id}/{first-name}/{last-name}")
    public ResponseEntity<Student> studentPathVariable(@PathVariable("id") int studentId,
                                       @PathVariable("first-name") String firstName,
                                       @PathVariable("last-name") String lastName) {
        Student student = new Student(studentId, firstName, lastName);
        // return new Student(studentId, firstName, lastName);
        return ResponseEntity.ok(student);
    }

    // Spring boot REST API with Request Param
    // http://localhost:8080/students/query?id=1&firstName=John&lastName=Smith
    @GetMapping("query")
    public ResponseEntity<Student> requestVariable(@RequestParam int id,
                                   @RequestParam String firstName,
                                   @RequestParam String lastName) {
        Student student = new Student(id, firstName, lastName);
        // return  new Student(id, firstName, lastName);
        return ResponseEntity.ok(student);
    }

    // Spring boot REST API that handles HTTP POST Request - creating new resource
    // @PostMapping and @RequestBody
    @PostMapping("create")
//    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        System.out.println(student.getId());
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        //  return student;
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    // Spring boot REST API that handles HTTP PUT Request - updating existing resource
    @PutMapping("{id}/update")
    public ResponseEntity<Student> updateStudents(@RequestBody Student student, @PathVariable("id") int studentId) {
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        //  return student;
        return ResponseEntity.ok(student);
    }

    // Spring boot REST API that handles HTTP DELETE Request - deleting the existing resource
    @DeleteMapping("{id}/delete")
    public ResponseEntity<String> deleteStudent(@PathVariable("id") int studentId) {
        System.out.println(studentId);
        return ResponseEntity.ok("Student deleted successfully!");
    }
}
