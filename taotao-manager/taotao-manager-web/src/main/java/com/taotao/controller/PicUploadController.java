package com.taotao.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.taotao.pojo.PictureResult;
import com.taotao.service.impl.PropertieService;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Date;

/**
 * @Author GJ1e
 * @Create 2020/2/7
 * @Time 22:15
 */
@Controller
@RequestMapping("/pic")
public class PicUploadController {
    @Autowired
    PropertieService propertieService;

    private static final Logger LOGGER = LoggerFactory.getLogger(PicUploadController.class);

    // 允许上传的格式
    private static final String[] IMAGE_TYPE = new String[] { ".bmp", ".jpg", ".jpeg", ".gif", ".png" };

    private static final ObjectMapper mapper = new ObjectMapper();

    @RequestMapping("/upload")
    @ResponseBody
    public PictureResult upload(@RequestParam("uploadFile") MultipartFile uploadFile, HttpServletRequest request)
            throws Exception {

        // 校验图片格式
        boolean isLegal = false;
        for (String type : IMAGE_TYPE) {
            if (StringUtils.endsWithIgnoreCase(uploadFile.getOriginalFilename(), type)) {
                isLegal = true;
                break;
            }
        }

        // 封装Result对象，并且将文件的byte数组放置到result对象中
        PictureResult fileUploadResult = new PictureResult();

        // 状态
        fileUploadResult.setError(isLegal ? 0 : 1);

        // 文件新路径
        String filePath = getFilePath(uploadFile.getOriginalFilename());

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Pic file upload .[{}] to [{}] .", uploadFile.getOriginalFilename(), filePath);
        }

        // 生成图片的绝对引用地址
        String picUrl = StringUtils.replace(StringUtils.substringAfter(filePath, this.propertieService.REPOSITORY_PATH),
                "\\", "/");
        request.getSession().getServletContext().getRealPath(picUrl);
        fileUploadResult.setUrl(this.propertieService.IMAGE_BASE_URL + picUrl);

        File newFile = new File(filePath);

        // 写文件到磁盘
        uploadFile.transferTo(newFile);

        // 校验图片是否合法
        isLegal = false;
        try {
            BufferedImage image = ImageIO.read(newFile);
            if (image != null) {

                isLegal = true;
            }
        } catch (Exception e) {

        }

        // 状态
        fileUploadResult.setError(isLegal ? 0 : 1);

        if (!isLegal) {
            // 不合法，将磁盘上的文件删除
            newFile.delete();
        }

        // 将对象序列化成json数据
        return fileUploadResult;
    }

    private String getFilePath(String sourceFileName) {
        //D:\\IdeaProgram\\taotao-parent\\taotao-manager\\taotao-manager-web\\src\\main\\webapp\\WEB-INF\images
        String baseFolder = this.propertieService.REPOSITORY_PATH + File.separator + "upload";
        Date nowDate = new Date();
        // yyyy/MM/dd
//        String fileFolder = baseFolder + File.separator + new DateTime(nowDate).toString("yyyy")
//                + File.separator + new DateTime(nowDate).toString("MM") + File.separator
//                + new DateTime(nowDate).toString("dd");
        File file = new File(baseFolder);
        if (!file.isDirectory()) {
            // 如果目录不存在，则创建目录
            file.mkdirs();
        }
        // 生成新的文件名
        String fileName = new DateTime(nowDate).toString("yyyyMMddhhmmssSSSS")
                + RandomUtils.nextInt(100, 9999) + "." + StringUtils.substringAfterLast(sourceFileName, ".");
//        return fileFolder + File.separator + fileName;
        return baseFolder + File.separator + fileName;
    }


}
