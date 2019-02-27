package com.ylzinfo.forum.util;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class UploadUtil {
    public static String imageUpload(MultipartFile multipartFile, String preUrl) throws IOException {
        String suffix = "." + StrUtil.subAfter(multipartFile.getOriginalFilename(), ".", true);
        String resource = ResourceUtil.getResource("static").getPath() + "/topic/images/";
        FileUtil.mkdir(resource);
        File file = new File(resource + IdUtil.fastSimpleUUID() + suffix);
        multipartFile.transferTo(file);
        return preUrl + "/topic/images/" + file.getName();
    }
}
