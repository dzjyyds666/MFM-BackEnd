package com.Aaron.MFM.web.app.controller.file;


import com.Aaron.MFM.common.result.Result;
import com.Aaron.MFM.web.app.service.IFileUploadService;
import io.minio.errors.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/app/file")
@Tag(name = "app文件管理")
public class FileUploadController {

    @Autowired
    private IFileUploadService fileUploadService;

    @PostMapping("/upload")
    @Operation(summary = "上传文件")
    public Result<String> upload(@RequestParam MultipartFile file) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        return Result.ok(fileUploadService.upload(file));
    }

}
