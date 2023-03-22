package com.sist.exhibition.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.sist.exhibition.dao.ExhibitionDAO;
import com.sist.exhibition.entity.ExhibitionEntity;

@Controller
public class MainController {
	@Autowired
	private ExhibitionDAO dao;
	
	@GetMapping("/")
	public String main_page(Model model)
	{
		List<ExhibitionEntity> list=dao.exhibitionAprilListData();
		List<ExhibitionEntity> flist=dao.exhibitionFreeListData();
		model.addAttribute("list",list);
		model.addAttribute("flist",flist);
		model.addAttribute("main_html","main/home");
		return "main";
	}
}
