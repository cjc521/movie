package com.cjc.movie.controller;

import com.cjc.movie.utils.FileUploadUtil;
import com.cjc.movie.utils.Resp;
import io.lettuce.core.dynamic.annotation.Value;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resources;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;


/**
 * @description:
 * @author: cjc
 * @time: 2020/10/15 17:27
 */
@RestController
public class FileController {

    private File dirPath = new File("D:/myUpload/movie");

/*//    @RequestMapping("/poster")
    @RequestMapping("/file")
    public Resp file(
            HttpServletRequest req,
            @RequestParam(required = false,value = "file") MultipartFile file
                ){
        Resp resp = new Resp();
        String s = req.getServletContext().getRealPath("/");
        System.out.println("public"+s);

        if(file.isEmpty()){
            resp.setCode(1);
            resp.setMsg("上传文件为空");
        }
        System.out.println("String--"+file.getOriginalFilename());
        // 获取文件名称,包含后缀
        String fileName = file.getOriginalFilename();

        // 存放在这个路径下：该路径是该工程目录下的static文件下：(注：该文件可能需要自己创建)
        // 放在static下的原因是，存放的是静态文件资源，即通过浏览器输入本地服务器地址，加文件名时是可以访问到的
        String path = ClassUtils.getDefaultClassLoader().getResource("").getPath()+"static/";
        System.out.println("filePath"+path);
        try {
//          file.transferTo(new File(path+"poster/"+fileName));
          file.transferTo(new File(s+fileName));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // 接着创建对应的实体类，将以下路径进行添加，然后通过数据库操作方法写入
        String savePath = new String("http://localhost:8081/poster/" + fileName);
        System.out.println(savePath);
        resp.setCode(0);
        resp.setMsg("上传图片成功");
        resp.setData(savePath);

        return resp;
    }*/
  /*  @RequestMapping("/files/{filename}")
    public void file(@PathVariable(required = false) MultipartFile filename){
        System.out.println("mutipart--"+filename.getOriginalFilename());

    }*/
    @RequestMapping(value = "/uploadImage", method = RequestMethod.POST)
    public void uploadImageFile(@RequestParam("img") MultipartFile uploadImage,
                                                      @RequestParam String token,
                                                      @RequestParam String type){
       /* JSONObject json = new JSONObject();
        try {
            if(uploadImage==null){
                json.put("STATUS", "ERROR");
                json.put("MSG", "上传失败，上传图片数据为空");
                return new ResponseEntity<JSONObject>(json, HttpStatus.BAD_REQUEST);
            }

            String suffix = uploadImage.getContentType().toLowerCase();//图片后缀，用以识别哪种格式数据
            suffix = suffix.substring(suffix.lastIndexOf("/")+1);

            if(suffix.equals("jpg") || suffix.equals("jpeg") || suffix.equals("png") || suffix.equals("gif")) {
                String fileName = type + "_" + UUID.randomUUID().toString().replaceAll("-", "") + "." + suffix;
                String imgFilePath = "D:\\logs\\";//新生成的图片

                File targetFile = new File(imgFilePath, fileName);
                if(!targetFile.getParentFile().exists()){ //注意，判断父级路径是否存在
                    targetFile.getParentFile().mkdirs();
                }
                //保存
                uploadImage.transferTo(targetFile);

                json.put("STATUS", "200");
                json.put("MSG", "上传图片成功");
                return new ResponseEntity<JSONObject>(json, HttpStatus.OK);
            }
            log.error("系统异常，上传图片格式非法");
            json.put("STATUS", "ERROR");
            json.put("MSG", "上传图片格式非法");
            return new ResponseEntity<JSONObject>(json, HttpStatus.BAD_REQUEST);//500,系统异常
        } catch (Exception e) {
            log.error("系统异常，上传图片失败,msg={}",e.getMessage());
            json.put("STATUS", "ERROR");
            json.put("MSG", "系统异常，上传图片失败");
            return new ResponseEntity<JSONObject>(json, HttpStatus.INTERNAL_SERVER_ERROR);//500,系统异常
        }*/
    }

    @RequestMapping("/filePicture")
    public Resp filePicture(

            @RequestParam(required = false,value = "file") MultipartFile file
    ){
        Resp resp = new Resp();
        if(file.isEmpty()){
            resp.setCode(1);
            resp.setMsg("上传文件为空");
            return resp;
        }
        String path=dirPath+"/"+"picture/";
        System.out.println(dirPath);
        // 获取文件名称,包含后缀
        String fileName = file.getOriginalFilename();
        String saveFileName = FileUploadUtil.getFileName(fileName);
        String saveFile=path+saveFileName;
        System.out.println("上传文件为："+saveFile);
        if(!dirPath.exists()){
            dirPath.mkdirs();
        }
        try {
            file.transferTo(new File(saveFile));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        String realPath= new String("http://localhost:8081/movie/"+saveFileName);
        // 接着创建对应的实体类，将以下路径进行添加，然后通过数据库操作方法写入
        resp.setCode(0);
        resp.setMsg("上传图片成功");
        resp.setData(realPath);

        return resp;
    }
    @RequestMapping("/filePoster")
    public Resp file(
            @RequestParam(required = false,value = "file") MultipartFile file
    ){
        Resp resp = new Resp();
        if(file.isEmpty()){
            resp.setCode(1);
            resp.setMsg("上传文件为空");
            return resp;
        }
        String path=dirPath+"/"+"poster/";
        // 获取文件名称,包含后缀
        String fileName = file.getOriginalFilename();
        String saveFileName = FileUploadUtil.getFileName(fileName);
        String saveFile=path+saveFileName;
        System.out.println("上传文件为："+saveFile);
        if(!dirPath.exists()){
            dirPath.mkdirs();
        }
        try {
            file.transferTo(new File(saveFile));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        String realPath= new String("http://localhost:8081/movie/poster/"+saveFileName);
        // 接着创建对应的实体类，将以下路径进行添加，然后通过数据库操作方法写入
        resp.setCode(0);
        resp.setMsg("上传图片成功");
        resp.setData(realPath);

        return resp;
    }

    @RequestMapping("/fileMovie")
    public Resp movieResource(
            @RequestParam(required = false,value = "file") MultipartFile file
    ){
        Resp resp = new Resp();
        if(file.isEmpty()){
            resp.setCode(1);
            resp.setMsg("上传文件为空");
            return resp;
        }
        String path=dirPath+"/"+"movie/";
        System.out.println(dirPath);
        // 获取文件名称,包含后缀
        String fileName = file.getOriginalFilename();
        String saveFileName = FileUploadUtil.getFileName(fileName);
        String saveFile=path+saveFileName;
        System.out.println("上传文件为："+saveFile);
        if(!dirPath.exists()){
            dirPath.mkdirs();
        }
        try {
            file.transferTo(new File(saveFile));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        String realPath= new String("http://localhost:8081/movie/movie/"+saveFileName);
        // 接着创建对应的实体类，将以下路径进行添加，然后通过数据库操作方法写入
        resp.setCode(0);
        resp.setMsg("上传图片成功");
        resp.setData(realPath);

        return resp;
    }

//    @ResponseBody
    @RequestMapping("/fileDownload")
    public String download(
            HttpServletResponse resp,
            @RequestParam(required = false,defaultValue = "") String filmName,
            @RequestParam("film") String path){
        boolean download=false;
        try {
            download = FileUploadUtil.download(path, filmName,resp);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "/err/404";
        }
        if(download){
            return null;
        }
        return "/err/404";
    }

}
