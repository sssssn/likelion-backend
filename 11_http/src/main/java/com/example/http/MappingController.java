package com.example.http;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Slf4j // log 변수를 만들어준다.
@Controller
public class MappingController {
//    private static final Logger logger = LoggerFactory.getLogger(MappingController.class);

    // '/path' 로 오는 GET 요청에 대해서
    // 메서드를 실행하고 싶을 때
    @RequestMapping(
            value = "/path",
            method = RequestMethod.GET
    )
    public String getPath() {
        log.info("GET /path");
        return "index";
    }

    // '/path' 로 오는 POST 요청에 대해서
    //
    // 메서드를 실행하고 싶을 때
    @RequestMapping(
            value = "/path",
            method = RequestMethod.POST
    )
    public String getPOST() {
        log.info("POST /path");
        return "index";
    }

    // '/path' 로 오는 PUT 또는 DELETE 요청에 대해서
    // 메서드를 실행하고 싶을 때
    @RequestMapping(
            value = "/path",
            method = { RequestMethod.PUT, RequestMethod.DELETE }
    )
    public String putOrDeletePath() {
        log.info("PUT or DELETE /path");
        return "index";
    }

    // '/path' 로 오는 POST 요청이면서 JSON 데이터를 포함하는 요청에 대해서
    // 메서드를 실행하고 싶을 때
    @RequestMapping(
            value = "/path",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public String consumes() {
        log.info("POST /path Content-Type: application/json");
        return "index";
    }

    // '/path' 로 오는 POST 요청이면서 헤더에 x-likelion 이라는 값이 hello로 지정되어 있을 때
    // 메서드를 실행하고 싶을 때
    @RequestMapping(
            value = "/path",
            method = RequestMethod.POST,
            headers = "x-likelion=hello"
    )
    public String headerWith() {
        log.info("POST /path with x-likelion=hello");
        return "index";
    }

    // '/path' 로 오는 POST 요청이면서 Query Parameter 로 likelion 이 hello 로 할당되어 있을 때
    // 메서드를 실행하고 싶을 때
//    @RequestMapping(
//            value = "/path",
//            method = RequestMethod.POST,
//            params = "likelion=hello"
//    )
    @RequestMapping(
            value = "/path",
            params = "likelion=hello"
    )
    public String params() {
        log.info("POST /path with parameter likelion");
        return "index";
    }
}
