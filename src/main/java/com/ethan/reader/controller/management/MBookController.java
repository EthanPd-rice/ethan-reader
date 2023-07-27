package com.ethan.reader.controller.management;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ethan.reader.entity.Book;
import com.ethan.reader.service.BookService;
import com.ethan.reader.utils.ResponseUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.swing.text.Element;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/management/book")
public class MBookController {
    @Resource
    private BookService bookService;
    @GetMapping("/list")
    public ResponseUtils list(Integer page,Integer rows){
        if(page == null){
            page = 1;
        }
        if(rows == null){
            rows = 10;
        }
        ResponseUtils responseUtils = null;
        try{
            IPage p = bookService.selectBookMap(page, rows);
            responseUtils = new ResponseUtils().put("list",p.getRecords()).put("count",p.getTotal());
        }catch (Exception e){
            e.printStackTrace();
            responseUtils = new ResponseUtils(e.getClass().getSimpleName(),e.getMessage());
        }
        return responseUtils;

    }
    @PostMapping("/upload")
    public Map upload(@RequestParam("img") MultipartFile file , HttpServletRequest request) throws IOException {
        String uploadPath = request.getServletContext().getResource("/").getPath() + "upload/" ;
        System.out.println("----------uploadPath----------"+uploadPath);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String fileName = sdf.format(new Date());
        System.out.println("---------fileName-----------"+fileName);
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        System.out.println("---------suffix-----------"+suffix);
        file.transferTo(new File(uploadPath+fileName+suffix));
        System.out.println("--------uploadPath+fileName+suffix----------"+uploadPath+fileName+suffix);
        Map result = new LinkedHashMap();
        result.put("errno",0);
        //访问的uri是文件地址而不是文件路径
        result.put("data",new String[]{"/upload/"+fileName+suffix});
        return result;
    }
    @PostMapping("/create")
    public ResponseUtils create(Book book){
        ResponseUtils responseUtils = null;
        try{
            System.out.println(book.getDescription());
            Document doc = Jsoup.parse(book.getDescription());
            Elements elements = doc.select("img");
            if(elements.size() == 0){
                responseUtils = new ResponseUtils("ImageNotFoundError","图书描述未包含封面图");
                return responseUtils;
            }
            String cover = elements.first().attr("src");
            book.setCover(cover);
            book.setEvaluationQuantity(0);
            book.setEvaluationScore(0f);
            Book b = bookService.createBook(book);
            responseUtils = new ResponseUtils().put("book",b);
        }catch (Exception e){
            e.printStackTrace();
            responseUtils = new ResponseUtils(e.getClass().getSimpleName(),e.getMessage());
        }
        return responseUtils;
    }



}
