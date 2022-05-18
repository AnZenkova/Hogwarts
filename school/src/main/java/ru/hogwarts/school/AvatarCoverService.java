package ru.hogwarts.school;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface AvatarCoverService {

    void uploadCover(Long studentId, MultipartFile file) throws IOException;

    Avatar findAvatar(Long studentId);
}
