package com.palmen.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.palmen.web.models.Item;
import com.palmen.web.models.NewsResponse;
import com.palmen.web.services.INewsService;

@Controller
@RequestMapping("/api/web")
public class NewsDetailsController {

	@Autowired
	private INewsService newsService;

	@GetMapping("/mostrarNoticia")
	public String mostrarNoticia(@RequestParam("id") Long id, Model model) {
		NewsResponse newsReponse = newsService.getNewsById(id);
		
        Item newsItem = newsReponse.getPage().getItems().get(0);
        
		model.addAttribute("news", newsItem);
		return "news-details";
	}

	@PostMapping("/buscarNoticia")
	public String buscarNoticia(@RequestParam("id") Long id) {
		return "redirect:/api/web/mostrarNoticia?id=" + id;
	}
}
