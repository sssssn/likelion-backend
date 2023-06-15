package com.example.jpa;

import com.example.jpa.dto.StudentDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// 사용자의 입력을 받는 요소
//@Controller
@RestController // 모든 메서드에 @ResponseBody 를 붙이는 용도
public class AppController {
    private final AppService service;

    public AppController(AppService service) {
        this.service = service;
    }

    @GetMapping("create")
     /*@Controller 의 Mapper 메서드에 @ResponseBody 가 붙으면
     View 를 반환하는 것이 아닌 데이터 (Http Response Body) 를 반환한다.
     @RestController 의 경우 모든 메서드가 @ResponseBody 가 붙은 것 처럼 동작한다. (생략 가능)*/
    public @ResponseBody String create() {
        this.service.createStudent(
                "alex",
                35,
                "010-1234-5678",
                "alex@gmail.com"
        );
        return "done";
    }

    @GetMapping("read")
    public @ResponseBody String readOne() {
        this.service.readStudent(1L);
        return "done-read-one";
    }

    @GetMapping("read-all")
    public @ResponseBody List<StudentDto> readAll() {
        this.service.readStudentAll();
//        return "done-read-all";
        return this.service.readStudentAll();
    }

    @GetMapping("update")
    public @ResponseBody String update() {
        this.service.updateStudent(1L, "tofu");
        return "done-update";
    }

    @GetMapping("delete")
    public @ResponseBody String delete() {
        this.service.deleteStudent(1L);
        return "done-delete";
    }

    @GetMapping("find")
    public @ResponseBody String find() {
        this.service.findAllByTest();
        return "done-find";
    }

//    // RequestMapping 과 같이 사용
//    @RequestMapping("students")
//    public void students() {
//        List<Object> result = service.readStudentAll();
//    }
//
//    @GetMapping("home")
//    public String home() {
//        return "home";
//    }
//
//    @GetMapping("body")
//    public String body() {
//        return "body";
//    }
}
