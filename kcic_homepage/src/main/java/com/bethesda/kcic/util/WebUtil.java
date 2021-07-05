package com.bethesda.kcic.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


public class WebUtil {
    protected static final Logger log = LogManager.getLogger();

    /**
     * 파일다운로드
     *
     * @param file
     * @param fileNm
     */
    public static void downFile(File file, String fileNm) {
        downFile(file, fileNm, false);
    }


    /**
     * 파일다운로드
     *
     * @param file
     * @param fileNm
     * @param isDelFile
     */
    public static void downFile(File file, String fileNm, boolean isDelFile) {
        try {
            HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            HttpServletResponse res = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();

            res.reset();

            res.setContentType("application/octet-stream; charset=utf-8");
            res.setContentLength((int)file.length());
            res.setHeader("Content-Transfer-Encoding", "binary");

            // 파일 인코딩
            String browser = req.getHeader("User-Agent");
            if(browser.contains("MSIE") || browser.contains("Trident") || browser.contains("Edge")){
                fileNm = URLEncoder.encode(fileNm,"UTF-8").replaceAll("\\+", "%20");
            } else {
                if (System.getProperty("catalina.home") != null) {
                    // Tomcat
                    fileNm = new String(fileNm.getBytes("UTF-8"), "ISO-8859-1");
                }
            }

            res.setHeader("Content-Disposition", "attachment;fileName=\"" + fileNm + "\"");

            OutputStream os = res.getOutputStream();
            FileInputStream fis = new FileInputStream(file);

            FileCopyUtils.copy(fis, os);
        } catch (UnsupportedEncodingException e) {
            log.error("UnsupportedEncodingException");
            throw new SystemException(e);
        } catch (IOException e) {
            log.error("IOException");
            throw new SystemException(e);
        } catch (RuntimeException e) {
            log.error("RuntimeException");
            throw new SystemException(e);
        } finally {
            if (isDelFile && file != null && file.exists()) {
                file.delete();
            }
        }
    }
}

