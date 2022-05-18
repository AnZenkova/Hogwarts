package ru.hogwarts.school;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

@RestController
@RequestMapping("cover")
public class AvatarController {

    private AvatarCoverService avatarCoverService;

    public AvatarController(AvatarCoverService avatarCoverService) {
        this.avatarCoverService = avatarCoverService;
    }

    @PostMapping(value = "{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadCover(@PathVariable Long id,
                                              @RequestParam MultipartFile cover) throws IOException {
        if (cover.getSize() >= 1024 * 300) {
            return ResponseEntity.badRequest().body("Файл большой по размеру");
        }
        avatarCoverService.uploadCover(id, cover);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/{id}/cover/data")
    public ResponseEntity<byte[]> downloadCover(@PathVariable Long id) {
        Avatar avatar = avatarCoverService.findAvatar(id);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(avatar.getMediaType()));
        headers.setContentLength(avatar.getData().length);

        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(avatar.getData());
    }

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

}
