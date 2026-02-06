package com.example.boardv1.upload;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UploadController {

    @PostMapping("/upload")
    @ResponseBody
    public String upload(MultipartFile file) throws Exception {

        String filename = UUID.randomUUID() + "_" + file.getOriginalFilename();

        Path path = Paths.get("uploads/" + filename);

        Files.write(path, file.getBytes());

        return "/uploads/" + filename;
    }
}
