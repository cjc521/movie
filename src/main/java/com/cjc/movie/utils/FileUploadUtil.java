package com.cjc.movie.utils;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.UUID;

/**
 * @description:
 * @author: cjc
 * @time: 2020/10/9 9:03
 */

public class FileUploadUtil{
    private static final File FILE_PATH=new File("D:/myUpload/movie/movie/");
    public static boolean upload(MultipartFile file, File filePath){
        try {
            file.transferTo(filePath);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public static void upload(byte[] file, String filePath, String fileName) throws IOException {
        File targetFile = new File(filePath);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
          FileOutputStream  out = new FileOutputStream(filePath + fileName);
            out.write(file);
            out.flush();
            out.close();

    }
    public  static boolean download(String downloadFile,String fileName, HttpServletResponse resp) throws UnsupportedEncodingException {
            String saveFileName=downloadFile;

            if(downloadFile.contains("/")){
                saveFileName=downloadFile.substring(downloadFile.lastIndexOf("/")+1);
            }

            String name=saveFileName;
            if(!"".equals(fileName) && fileName!=null){
                name=fileName+saveFileName.substring(saveFileName.lastIndexOf("."));
            }
              System.out.println(name);
            resp.setHeader("Content-Disposition","attachment;filename="+new String(name.getBytes("utf-8"),"ISO-8859-1"));

            byte [] bytes=new byte[1024];
            BufferedInputStream bis=null;
            OutputStream os=null;
            System.out.println(saveFileName);
            try {
                os=resp.getOutputStream();
                bis=new BufferedInputStream(new FileInputStream(FILE_PATH+"/"+saveFileName));
                int i=bis.read(bytes);
                while(i !=-1){
                    os.write(bytes,0,bytes.length);
                    os.flush();
                    i=bis.read(bytes);
                }
            } catch (IOException e) {
                e.printStackTrace();
               return false;
            }finally {
                if(bis!=null){
                    try {
                        bis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if(os !=null){
                    try {
                        os.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

        }
        return true;

    }

    //文件保存名
    public static String getFileName(String fileName){
        return UUID.randomUUID()+"."+ fileName.substring(fileName.lastIndexOf(".") + 1);
    }

}
