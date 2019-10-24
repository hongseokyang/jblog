package kr.co.itcen.jblog.controller.api;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.itcen.jblog.dto.JSONResult;
import kr.co.itcen.jblog.service.CategoryService;
import kr.co.itcen.jblog.vo.CategoryVo;
import kr.co.itcen.jblog.vo.UserVo;
import kr.co.itcen.web.Pagination;

@Controller("categoryApiController")
@RequestMapping("/api/category")
public class CategoryController {
	@Autowired
	private CategoryService categoryService;
	
	@ResponseBody
	@RequestMapping("/add")
	public JSONResult addCategory(@RequestBody CategoryVo categoryVo, HttpSession session) {
		UserVo userVo = (UserVo)session.getAttribute("authUser");
		categoryVo.setBlogId(userVo.getId());
		Boolean exist = categoryService.add(categoryVo);
		
		return JSONResult.success(exist);
	}
	
	@ResponseBody
	@RequestMapping("/getList")
	public Map<String, Object> getList(HttpSession session
			, @RequestParam("curPage") int curPage ) {
		UserVo userVo = (UserVo)session.getAttribute("authUser");
		Map<String, Object> map = new HashMap<String, Object>();
		
		int listCnt = categoryService.getListCnt(userVo.getId());
		Pagination pagination = new Pagination(listCnt, curPage);
		
		map.put("category", categoryService.getListContainCount(userVo.getId(), pagination));
		map.put("pagination", pagination);
		return map;
	}
	
	@ResponseBody
	@RequestMapping("/delete")
	public JSONResult delete(@RequestBody Map<String, Long> map, HttpSession session) {
		// 접근제어
		UserVo userVo = (UserVo)session.getAttribute("authUser");
		
		Boolean exist = categoryService.delete(map.get("categoryNo"), userVo.getId());
		return JSONResult.success(exist);
	}
}