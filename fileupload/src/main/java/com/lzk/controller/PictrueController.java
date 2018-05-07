package com.lzk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

@Controller
public class PictrueController {

    @RequestMapping(value = "/index",method = RequestMethod.GET)
    @ResponseBody
    public String index(){
        return "hello,world";
    }

    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    @ResponseBody
    public String upload(@RequestParam("desc")String desc, MultipartFile file, HttpServletRequest request){
        String path = request.getSession().getServletContext().getRealPath("/WEB-INF/upload");
        String fileName = file.getOriginalFilename();
        System.out.println("desc:" + desc);
        File dir = new File(path,fileName);
        if(!dir.exists()){
            dir.mkdirs();
        }
        try {
            file.transferTo(dir);
            return "file upload success";
        } catch (IOException e) {
            e.printStackTrace();
            return "file upload fail";
        }
    }

    @RequestMapping(value = "/download",method = RequestMethod.GET)
    @ResponseBody
    public void download(HttpServletRequest request,HttpServletResponse response) throws Exception{
        String path = request.getSession().getServletContext().getRealPath("/WEB-INF/upload");
        //模拟文件需要下载的文件
        String fileName = "2.jpg";
        //获取输入流
        InputStream bis = new BufferedInputStream(new FileInputStream(new File(path +File.separator + fileName)));
        //假如以中文名下载的话
        String filename = "下载文件.jpg";
        //转码，免得文件名中文乱码
        filename = URLEncoder.encode(filename,"UTF-8");
        //设置文件下载头
        response.addHeader("Content-Disposition", "attachment;filename=" + filename);
        //1.设置文件ContentType类型，这样设置，会自动判断下载文件类型
        response.setContentType("image/*");
        BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
        int len = 0;
        byte[] b = new byte[1024];
        while((len = bis.read(b)) != -1){
            out.write(b,0,len);
            out.flush();
        }
        out.close();
    }
}
