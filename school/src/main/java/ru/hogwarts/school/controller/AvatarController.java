package ru.hogwarts.school.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.service.AvatarCoverService;
import ru.hogwarts.school.model.Avatar;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@RestController
@RequestMapping("cover")
public class AvatarController {

    private AvatarCoverService avatarCoverService;

    public AvatarController(AvatarCoverService avatarCoverService) {
        this.avatarCoverService = avatarCoverService;
    }

    //Загружаем аватар студента
    @PostMapping(value = "{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadCover(@PathVariable Long id,
                                              @RequestParam MultipartFile cover) throws IOException {
        if (cover.getSize() >= 1024 * 300) {
            return ResponseEntity.badRequest().body("Файл большой по размеру");
        }
        avatarCoverService.uploadCover(id, cover);
        return ResponseEntity.ok().build();
    }

    // Получаем аватар студента из базы данных
    @GetMapping(value = "/{id}/cover/data")
    public ResponseEntity<byte[]> downloadCover(@PathVariable Long id) {
        Avatar avatar = avatarCoverService.findAvatar(id);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(avatar.getMediaType()));
        headers.setContentLength(avatar.getData().length);

        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(avatar.getData());
    }

    // Получаем аватар студента
    @GetMapping(value = "/{id}/cover")
    public void downloadCover(@PathVariable Long id, HttpServletResponse response) throws IOException {
        Avatar avatar = avatarCoverService.findAvatar(id);

        Path path = Path.of(avatar.getFilePath());

        try (InputStream is = Files.newInputStream(path);
             OutputStream os = response.getOutputStream();) {
            response.setContentType(avatar.getMediaType());
            response.setContentLength((int) avatar.getFileSize());
            is.transferTo(os);
        }
    }

    // Получаем все аватары студентов
    @GetMapping
    public ResponseEntity<List<Avatar>> getAllAvatar(@RequestParam("page") Integer pageNumber,
                                     @RequestParam("size") Integer pageSize) {
        List<Avatar> avatars = avatarCoverService.getAllAvatar(pageNumber, pageSize);
        return ResponseEntity.ok(avatars);
    }

}
