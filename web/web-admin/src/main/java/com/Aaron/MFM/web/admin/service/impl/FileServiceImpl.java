package com.Aaron.MFM.web.admin.service.impl;

import com.Aaron.MFM.common.minio.MinioProperties;
import com.Aaron.MFM.web.admin.service.IFileService;
import io.minio.*;
import io.minio.errors.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Service
public class FileServiceImpl implements IFileService {

    @Autowired
    private MinioClient minioClient;

    @Autowired
    private MinioProperties minioProperties;


    @Override
    public String upLoad(MultipartFile file) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        boolean bucketExists = minioClient.bucketExists(BucketExistsArgs.builder().bucket(minioProperties.getBucketName()).build());
        if(!bucketExists){
            // 创建存储桶
            minioClient.makeBucket(MakeBucketArgs
                    .builder()
                    .bucket(minioProperties.getBucketName())
                    .build());

            String bucketPolicy = """
                        {
                          "Statement" : [ {
                            "Action" : "s3:GetObject",
                            "Effect" : "Allow",
                            "Principal" : "*",
                            "Resource" : "arn:aws:s3:::%s/*"
                          } ],
                          "Version" : "2012-10-17"
                        }""".formatted(minioProperties.getBucketName());
            // 设置存储桶权限
            minioClient.setBucketPolicy(SetBucketPolicyArgs
                    .builder()
                    .bucket(minioProperties.getBucketName())
                    .config(bucketPolicy)
                    .build());
        }

        String filename = new SimpleDateFormat("yyyyMMdd").format(new Date())+"/"+ UUID.randomUUID()+"-"+file.getOriginalFilename();
        minioClient.putObject(PutObjectArgs
                        .builder()
                        .bucket(minioProperties.getBucketName())
                        .stream(file.getInputStream(), file.getSize(), -1)
                        .contentType(file.getContentType())
                        .object(filename)
                        .build());

        return String.join("/", minioProperties.getEndpoint(), minioProperties.getBucketName(), filename);
    }

}
