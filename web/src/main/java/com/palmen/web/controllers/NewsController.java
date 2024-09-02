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
@RequestMapping("/api/web")
public class NewsController {

	@Autowired
	private INewsService newsService;

	@GetMapping("/latestNews")
	public String news(@RequestParam(value = "categoria", required = false) String categoria,
			@RequestParam(defaultValue = "1") int page, Model model) {
		int pageSize = 28; // Número de elementos por página
		List<Item> news;

		// Obtener noticias según la categoría, si se especifica
		if (categoria != null && !categoria.isEmpty()) {
			news = newsService.getNewsByCategory(categoria);
		} else {
			news = newsService.getAllNews();
		}

		// Calcular la sublista para la página actual
		int startItem = (page - 1) * pageSize;
		int endItem = Math.min(startItem + pageSize, news.size());

		List<Item> paginatedNews = news.subList(startItem, endItem);

		// Añadir la sublista y la información de paginación al modelo
		model.addAttribute("news", paginatedNews);
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", (int) Math.ceil((double) news.size() / pageSize));
		model.addAttribute("categoria", categoria); // Pasar la categoría actual para la navegación

		return "news";

	}
}
