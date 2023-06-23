package com.spring.blog.controller;

import com.spring.blog.entity.Blog;
import com.spring.blog.exception.NotFoundBlogIdException;
import com.spring.blog.service.BlogService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

//URL을 분석 할 수 있는 기능이 @Controller에 있다.
@Controller     //@Controller는 (1) bean 등록  + (2) url mapping 처리 기능을 함께 가지고 있으므로 다른 annotation과 교환해서 쓸 수 없다.
@RequestMapping("/blog")
@Log4j2 //sout이 아닌
//sout은 로그파일에 남지 않는다. log.info()는 로그에 남는다.
public class BlogController {
    //controller layer는 service layer를 직접 호출한다.
    private BlogService blogService;
   //생성자 주입
    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }
    //GET 방식
    @RequestMapping("/list")
    public String findAll(Model model){
        List<Blog> blogList = blogService.findAll();
        model.addAttribute("blogList", blogList);
        System.out.println("getting blog list");
        return "/blog/list";
    }

    @RequestMapping("/detail/{blogId}")
    public String detail(@PathVariable long blogId, Model model){
        Blog blog = blogService.findById(blogId);
//        if(blog==null){
//            throw new NotFoundBlogIdException("없는 blogId로 조회했습니다. 조회번호: " +blogId);
//        }
        if(blog==null){
            try {
                throw new NotFoundBlogIdException("없는 blogId로 조회했습니다. 조회번호: " + blogId);
            } catch(NotFoundBlogIdException e){
                return "blog/NotFoundBlogIdExceptionPage";
            }
        }

        log.info("finding a blog by Id: "+ blogId);
        model.addAttribute("blogId", blog.getBlogId());
        model.addAttribute("blogTitle", blog.getBlogTitle());
        model.addAttribute("writer", blog.getWriter());
        model.addAttribute("blogContent", blog.getBlogContent());
        model.addAttribute("publishedAt", blog.getPublishedAt());
        model.addAttribute("updatedAt", blog.getUpdatedAt());
        model.addAttribute("blogCoung", blog.getBlogCount());
        return "blog/detail";
    }

    //form 페이지와 실제 등록 url은 같은 url을 쓰도록 한다.
    //대신 form 페이지는 GET 방식으로 접속했을 때 연결해주고
    //form에서

    @RequestMapping(value="/insert", method= RequestMethod.GET)
    public String insert(){
        return "blog/form";
    }

    @RequestMapping(value="/insert", method=RequestMethod.POST)
    public String insert(Blog blog){
        blogService.save(blog);
        return "redirect:/blog/list";
    }

   //글 번호만으로 삭제를 진행해야한다.
   //detail 삭제버튼을 추가하고, 해당 버튼을 클릭했을 때 삭제번호가 전달되어서
   //전달받은 번호를 토대로 삭제
    @RequestMapping("/delete")
    public String delete(long blogId){
        blogService.deleteById(blogId);
        return "redirect:/blog/list";
    }

    //update form 페이지 보여주는 메서드
    //해당 글 정보를 획득한 다음, form 페이지에 model.addattribute()로 보내준다.
    //그 다음 수정용 form페이지에 해당 자료를 채운 채 연결해주면 된다
    //value= 를 이용하면 미리 원하는 내용으로 form을 채워둘 수 있다.
    @RequestMapping(value="/updateform", method = RequestMethod.POST)
    public String update(long blogId, Model model){
        //blogId를 이용해서 blog 객체를 받아온다.
        Blog blog = blogService.findById(blogId);
        //.jsp로 보내기 위해 적재한다.
        model.addAttribute("blog", blog);
        //jsp파일 위치
        return "blog/update";
    }

    @RequestMapping(value = "/update", method=RequestMethod.POST)
    public String update(Blog blog){
        blogService.update(blog);
        log.info("글번호 " + blog.getBlogId() + ": 수정되었습니다.");
        return "redirect:/blog/detail/" + blog.getBlogId();
//        return "redirect:blog/list";
    }

    //해당 글 번호의 detail 페이지로 넘어가게 하기

}
