package com.example.jpa;

import com.example.jpa.entities.StudentEntity;
import com.example.jpa.repos.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppService {
    // JpaRepository
    private final StudentRepository studentRepository;

    public AppService(
//            AppRepository repository,
            StudentRepository studentRepository
    ) {
//        this.repository = repository;
        this.studentRepository = studentRepository;
    }

    // MEMO <CREATE>
    public void createStudent(
            String name,
            Integer age,
            String phone,
            String email
    ) {
        // 새로운(new) Student(Entity)를 만들고 싶다.
        StudentEntity newEntity = new StudentEntity();
        newEntity.setName(name);
        newEntity.setAge(age);
        newEntity.setPhone(phone);
        newEntity.setEmail(email);
        // repository.save()
        this.studentRepository.save(newEntity);
    }

    // MEMO <READ>
    public void readStudent(Long id) {
        Optional<StudentEntity> optionalStudentEntity
                = this.studentRepository.findById(id);
        // 1. 실제 데이터가 있을 때에만
        if (optionalStudentEntity.isPresent()) {
            // 출력한다.
            System.out.println(optionalStudentEntity.get());
        }
        // 2. 결과가 null 이 되었을 경우
        else {
            System.out.println("no result");
        }
//        System.out.println(
//                this.studentRepository.findById(id));
    }

    // MEMO <READ ALL>
    public void readStudentAll() {
        System.out.println(this.studentRepository.findAll());
    }

    // MEMO <UPDATE>
    public void updateStudent(
            // 어떤 대상을 수정할지 지정해줘야 한다.
            Long id,
            // 수정할 데이터
            String name
    ) {
        // 수정할 Entity 회수
        Optional<StudentEntity> optionalEntity
                = this.studentRepository.findById(id);
        // 수정할 Entity 를 찾은 경우
        if (optionalEntity.isPresent()) {
            // 실제 객체 회수
            StudentEntity target = optionalEntity.get();
            // 수정할 데이터 적용
            target.setName(name);
            // save
            this.studentRepository.save(target);
        } else {
            // 없으면 없다고 알려줌
            System.out.println("could not find");
        }
    }

    // MEMO <DELETE>
    public void deleteStudent(Long id) {
        Optional<StudentEntity> optionalEntity
                = this.studentRepository.findById(id);
        // 삭제할 Entity 를 찾은 경우
        if (optionalEntity.isPresent()) {
            StudentEntity studentEntity = optionalEntity.get();
            // 삭제
            this.studentRepository.delete(studentEntity);
        } else {
            System.out.println("could not find");
        }
    }

    // MEMO <findAllBy>
    public void findAllByTest() {
        // Name 오름차순
        System.out.println("findAllByOrderByName");
        List<StudentEntity> studentEntities
                = this.studentRepository.findAllByOrderByName();
        for (int i = 0; i < 5; i++) {
            System.out.println(studentEntities.get(i));
        }
        System.out.println("...");

        // Age 내림차순
        System.out.println("findAllByOrderByAgeDesc");
        studentEntities
                = this.studentRepository.findAllByOrderByAgeDesc();
        for (int i = 0; i < 5; i++) {
            System.out.println(studentEntities.get(i));
        }
        System.out.println("...");

        // Age 조건
        System.out.println("findAllByAgeLessThan");
        studentEntities
                = this.studentRepository.findAllByAgeLessThan(21);
        for (int i = 0; i < 5; i++) {
            System.out.println(studentEntities.get(i));
        }
        System.out.println("...");

        // Phone 조건
        System.out.println("findAllByPhoneStartingWith");
        studentEntities
                = this.studentRepository.findAllByPhoneStartingWith("010-");
        for (int i = 0; i < 5; i++) {
            System.out.println(studentEntities.get(i));
        }
        System.out.println("...");
    }

//    //    List<Student> studentList = new ArrayList<>();
//    private final AppRepository repository;
//
//    /* 주된 비즈니스 로직이 구현되는 공간
//    Controller -> Service
//    Service
//    1. 데이터베이스 조회
//    2. Component 사용
//    3. 모은 데이터를 가지고 응답 (의사결정) */
//    public List<Object> readStudentAll() {
//        List<Object> queryResult = repository.selectStudentAll();
//        // some business logic
//        return queryResult;
//    }
}
