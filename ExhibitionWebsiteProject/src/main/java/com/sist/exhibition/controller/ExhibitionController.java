package com.sist.exhibition.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sist.exhibition.dao.ExhibitionDAO;
import com.sist.exhibition.entity.ExhibitionEntity;

import java.util.*;

@ComponentScan(basePackages= {"com.sist.exhibition.entity","com.sist.exhibition.dao","com.sist.exhibition.controller"})
@Controller
public class ExhibitionController {
	@Autowired
	private ExhibitionDAO dao;
	
	@GetMapping("exhibition/exhibition_detail")
	public String exhibition_detail(int geno,Model model)
	{
		ExhibitionEntity vo=dao.findByGeno(geno);
		
		String area=vo.getArea();
		if(area==null)
			area=" ";
		String loc2=vo.getLoc2();
		if(loc2==null)
			loc2=" ";
		String time=vo.getTime();
		if(time==null)
			time=" ";
		String price=vo.getPrice();
		if(price==null)
			price=" ";
		
		vo.setArea(area);
		vo.setLoc2(loc2);
		vo.setTime(time);
		vo.setPrice(price);
		
		model.addAttribute("vo",vo);
		model.addAttribute("main_html","exhibition/exhibition_detail");
		return "main";
	}
	//현재 진행 중인 전시
	@GetMapping("exhibition/exhibition_now")
	public String exhibition_now(String page,Model model)
	{
		if(page==null)
			page="1";
		
		int curpage=Integer.parseInt(page);
		int rowSize=12;
		int start=(curpage*rowSize)-rowSize;
		
		//현재 진행 중인 전시
		List<ExhibitionEntity> list=dao.exhibitionNowList(start);
		int totalpage=dao.exhibitionNowTotalPage();
		
		final int BLOCK=10;
		int startPage=((curpage-1)/BLOCK*BLOCK)+1;
		int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		if(endPage>totalpage)
			endPage=totalpage;
		
		model.addAttribute("curpage",curpage);
		model.addAttribute("totalpage",totalpage);
		model.addAttribute("startPage",startPage);
		model.addAttribute("endPage",endPage);
		model.addAttribute("list",list);
		model.addAttribute("main_html","exhibition/exhibition_now");
		return "main";
	}
	//현재 종료된 전시
	@GetMapping("exhibition/exhibition_end")
	public String exhibition_end(String page,Model model)
	{
		if(page==null)
			page="1";
		
		int curpage=Integer.parseInt(page);
		int rowSize=12;
		int start=(curpage*rowSize)-rowSize;
		
		//현재 진행 중인 전시
		List<ExhibitionEntity> list=dao.exhibitionEndList(start);
		int totalpage=dao.exhibitionEndTotalPage();
		
		final int BLOCK=10;
		int startPage=((curpage-1)/BLOCK*BLOCK)+1;
		int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		if(endPage>totalpage)
			endPage=totalpage;
		
		model.addAttribute("curpage",curpage);
		model.addAttribute("totalpage",totalpage);
		model.addAttribute("startPage",startPage);
		model.addAttribute("endPage",endPage);
		model.addAttribute("list",list);
		model.addAttribute("main_html","exhibition/exhibition_end");
		return "main";
	}
	//전시찾기
	@RequestMapping("exhibition/exhibition_find")
	public String exhibition_find(String word,String page,Model model)
	{
		if(word==null)
			word="2023";
		if(page==null)
			page="1";
		
		int curpage=Integer.parseInt(page);
		int rowSize=12;
		int start=(curpage*rowSize)-rowSize;
		
		List<ExhibitionEntity> list=dao.exhibitionFindList(word,start);
		int totalpage=dao.exhibitionFindTotalPage(word);
		
		final int BLOCK=10;
		int startPage=((curpage-1)/BLOCK*BLOCK)+1;
		int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		if(endPage>totalpage)
			endPage=totalpage;
		
		//검색결과수
		int findCount=dao.exhibitionFindCount(word);
		
		model.addAttribute("curpage",curpage);
		model.addAttribute("totalpage",totalpage);
		model.addAttribute("startPage",startPage);
		model.addAttribute("endPage",endPage);
		model.addAttribute("list",list);
		model.addAttribute("word",word);
		model.addAttribute("findCount",findCount);
		model.addAttribute("main_html","exhibition/exhibition_find");
		return "main";
	}
}
