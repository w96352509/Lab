package com.study.springmvc.lab.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.study.springmvc.lab.entity.Fund;
import com.study.springmvc.lab.entity.Fundstock;
import com.study.springmvc.lab.service.FundService;
import com.study.springmvc.lab.service.FundstockService;

@Controller
@RequestMapping("/lab/fundstock")
public class FundstockController {

	@Autowired
	private FundstockService fundstockService;
	
	@Autowired
	private FundService fundService;
	
	//預設分頁
	private int pageNumber =-1;
	
	@GetMapping("/")
	public String index(@ModelAttribute Fundstock fundstock , Model model){
	    return "redirect:./page/"+pageNumber;
	}
	
	@GetMapping("/page/{pageNumber}")
	public String page(
			@ModelAttribute Fundstock fundstock , 
			Model model , 
			@PathVariable("pageNumber") int pageNumber) {
		this.pageNumber = pageNumber;
		// 第一頁 = 1-1*5 = 0 offset=0(開始)
		// 第二頁 = 2-1*5 = 5 offset=5(開始)
	    int offset = (pageNumber-1) *  5 ;
	    //總分面數
	    int pageTotalCount  = fundstockService.count() / 5;
	    List<Fundstock> fundstocks = fundstockService.queryPage(offset);
		List<Fund> funds = fundService.queryAll();
	    model.addAttribute("_method" , "POST");
	    model.addAttribute("funds" , funds);
		model.addAttribute("fundstocks" , fundstocks);
		model.addAttribute("count" , fundstockService.count());
		model.addAttribute("pageTotalCount" , pageTotalCount);
		return "lab/Fundstock";
	}
	
	@GetMapping("/{sid}")
	public String get(@PathVariable("sid") int sid , Model model ) {
		int pageTotalCount  = fundstockService.count() / 5;
	    List<Fundstock> fundstocks = fundstockService.queryAll();
	    Fundstock fundstock = fundstockService.get(sid);
		List<Fund> funds = fundService.queryAll();
	    model.addAttribute("_method" , "PUT");
	    model.addAttribute("funds" , funds);
		model.addAttribute("fundstocks" , fundstocks);
		model.addAttribute("fundstock"  , fundstock );
		model.addAttribute("count" , fundstockService.count());
		model.addAttribute("pageTotalCount" , pageTotalCount);
		return "lab/Fundstock";
	}
	
	@PostMapping("/")
	public String add(Fundstock fundstock) {
		fundstockService.add(fundstock);
		return "redirect:./";
	}
	
	@PutMapping("/{sid}")
	public String update(Fundstock fundstock) {
		fundstockService.update(fundstock);
		return "redirect:./";
	}
	
	@DeleteMapping("/{sid}")
	public String delete(@PathVariable("sid") int sid) {
		fundstockService.delete(sid);
		return "redirect:./";
	}
	
}
