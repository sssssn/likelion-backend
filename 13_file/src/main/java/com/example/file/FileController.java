package com.example.file;

import com.example.file.dto.ResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;

@Slf4j
@RestController
public class FileController {
    @PostMapping(
            value = "/multipart",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseDto multipart(
            @RequestParam("name") String name,
            @RequestParam("photo") MultipartFile multipartFile
    ) throws IOException {
        // 저장할 경로 생성
        Files.createDirectories(Path.of("media"));
        // 저장할 파일 이름을 포함한 경로 작성
        // Screenshot From 2023-06-23 10:38:00.png
        LocalDateTime now = LocalDateTime.now();
        log.info(now.toString());
        String filename = now.toString().replace(":", "");
        Path uploadTo
                = Path.of(String.format("media/%s.png", filename));
//        Path uploadTo = Path.of("media/filename.png");
        // 저장!
        multipartFile.transferTo(uploadTo);

//        방법 (2)
//        File file = new File("./filename.png");
//        try (OutputStream outputStream = new FileOutputStream(file)) {
//            byte[] fileBytes = multipartFile.getBytes();
//            // 여기에서 byte[]를 활용
//            outputStream.write(fileBytes);
//        }

        ResponseDto response = new ResponseDto();
        response.setMessage(String.format("/static/%s.png", filename));
        return response;
    }
}
