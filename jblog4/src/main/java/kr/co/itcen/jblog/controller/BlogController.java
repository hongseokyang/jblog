package kr.co.itcen.jblog.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import kr.co.itcen.jblog.security.Auth;
import kr.co.itcen.jblog.service.BlogService;
import kr.co.itcen.jblog.service.CategoryService;
import kr.co.itcen.jblog.service.PostService;
import kr.co.itcen.jblog.vo.BlogVo;
import kr.co.itcen.jblog.vo.PostVo;

@Controller
@RequestMapping( "/{id:(?!assets|images).*}" )
public class BlogController {
	@Autowired
	private BlogService blogService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private PostService postService;
	
	@RequestMapping( {"", "/{pathNo1}", "/{pathNo1}/{pathNo2}" } )
	public String index( 
		@PathVariable("id") String id,
		@PathVariable Optional<Long> pathNo1,
		@PathVariable Optional<Long> pathNo2,
		Model model ) {

		Long categoryNo = 0L;
		Long postNo = 0L;
		
		if( pathNo2.isPresent() ) {
			categoryNo = pathNo2.get();
			postNo = pathNo1.get();
		} else if( pathNo1.isPresent() ){
			categoryNo = pathNo1.get();
		}
		model.addAttribute("map", blogService.getAll( id, categoryNo, postNo ) );
		model.addAttribute("blogVo", blogService.getBasic(id));
		model.addAttribute("categoryNo", categoryNo);
		
		return "blog/blog-main";
	}
	
	@Auth
	@RequestMapping( value="/admin/basic", method=RequestMethod.GET )
	public String basic( @PathVariable("id") String userId, Model model ) {
		        
		model.addAttribute("blogVo", blogService.getBasic(userId));
		return "blog/blog-admin-basic";
	}
	
	@RequestMapping( value="/admin/basic", method=RequestMethod.POST )
	public String basic( @PathVariable String id
			, @RequestParam(value = "title", required = true, defaultValue = "") String title 
			, @RequestParam(value = "logo-file", required = false, defaultValue = "") MultipartFile logoFile ) {
		BlogVo blogVo = new BlogVo();
		blogVo.setUserId(id);
		blogVo.setTitle(title);
		blogService.restore(blogVo, logoFile);

		return "redirect:/"+id;
	}
	
	@Auth
	@RequestMapping( value="/admin/category", method=RequestMethod.GET )
	public String category( @PathVariable String id, Model model ) {
		model.addAttribute("blogVo", blogService.getBasic(id));
		return "blog/blog-admin-category";
	}
	
	@Auth
	@RequestMapping( value="/admin/write", method=RequestMethod.GET )
	public String write( @PathVariable String id, Model model ) {
		model.addAttribute("categoryList", categoryService.getList(id));
		model.addAttribute("blogVo", blogService.getBasic(id));
		
		return "blog/blog-admin-write";
	}
	
	@RequestMapping( value="/admin/write", method=RequestMethod.POST )
	public String write( @PathVariable String id, PostVo postVo ) {
		postVo.setUserId(id);
		postService.addPost(postVo);
		
		return "redirect:/"+id;
	}
}
