package com.palmen.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.palmen.web.models.Item;
import com.palmen.web.services.INewsService;

@Controller
@RequestMapping("/news")
public class NewsController {

	@Autowired
	private INewsService newsService;

	@GetMapping("/latestNews")
	public String news(@RequestParam(value = "categoria", required = false) String categoria, Model model) {
		List<Item> news;

		if (categoria != null && !categoria.isEmpty()) {
			news = newsService.getNewsByCategory(categoria);
		} else {
			news = newsService.getAllNews();
		}

		model.addAttribute("news", news);
		return "news";
	}
}
