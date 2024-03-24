package com.ua.fishingforum.common.service.impl;

import com.ua.fishingforum.common.exception.CustomException;
import com.ua.fishingforum.common.service.ImageStorageService;
import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.errors.*;
import lombok.RequiredArgsConstructor;
import org.apache.commons.compress.utils.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MinioImageStorageService implements ImageStorageService {

    private final MinioClient minioClient;

    @Value("${minio.bucket.name}")
    private String bucketName;

    public String uploadImage(MultipartFile file) {
        String fileName = generateFileName(file);
        try (InputStream is = file.getInputStream()) {
            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(bucketName)
                            .object(fileName).stream(is, file.getSize(), -1)
                            .contentType(file.getContentType())
                            .build());
            return fileName;
        } catch (Exception e) {
            throw new CustomException("Failed to store image file.", e);
        }
    }

    public byte[] getImage(String imageUrl) {
        try (InputStream stream =
                     minioClient.getObject(GetObjectArgs
                             .builder()
                             .bucket(bucketName)
                             .object(imageUrl)
                             .build())) {
            return IOUtils.toByteArray(stream);
        } catch (ServerException | InsufficientDataException | ErrorResponseException | IOException |
                 NoSuchAlgorithmException | InvalidKeyException | InvalidResponseException | XmlParserException |
                 InternalException e) {
            throw new CustomException("Cant get file from Minio", e);
        }
    }

    private String generateFileName(MultipartFile file) {
        return new Date().getTime() + "-" + UUID.randomUUID() + "-" + Objects.requireNonNull(file.getOriginalFilename()).replace(" ", "_");
    }
}
