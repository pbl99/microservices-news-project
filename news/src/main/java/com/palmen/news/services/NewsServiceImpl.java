package com.palmen.news.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.palmen.news.entities.News;
import com.palmen.news.entities.persistence.INewsRepository;
import com.palmen.news.models.Item;
import com.palmen.news.models.NewsResponse;
import com.palmen.news.models.Page;

import reactor.core.publisher.Flux;

@Service
public class NewsServiceImpl implements INewsService {

	private final INewsRepository newsRepository;

	private final WebClient webClient;

	public NewsServiceImpl(INewsRepository newsRepository, WebClient.Builder webClientBuilder) {
		this.newsRepository = newsRepository;
		this.webClient = webClientBuilder.baseUrl("https://api.rtve.es").build();
	}

	@Override
	public NewsResponse getLatestNews() {
		List<String> uris = List.of("/api/noticias.json?size=60&page=1", "/api/noticias.json?size=60&page=2");

		Flux<NewsResponse> responses = Flux.fromIterable(uris)
				.flatMap(uri -> webClient.get().uri(uri).retrieve().bodyToMono(NewsResponse.class));

		return responses.collectList().map(newsResponses -> {
			NewsResponse combined = new NewsResponse();
			Page combinedPage = new Page();

			for (NewsResponse response : newsResponses) {
				if (response != null && response.getPage() != null) {
					combinedPage.getItems().addAll(response.getPage().getItems());
				}
			}

			combined.setPage(combinedPage);
			return combined;
		}).block();
	}

	@Override
	public List<Item> getNewsByCategory(String mainCategory) {
		NewsResponse response = webClient.get().uri("/api/noticias.json?size=60").retrieve()
				.bodyToMono(NewsResponse.class).block();

		NewsResponse response2 = webClient.get().uri("/api/noticias.json?size=60&page=2").retrieve()
				.bodyToMono(NewsResponse.class).block();

		// Filtrar los elementos de la primera página que comienzan con el prefijo dado
		List<Item> itemsResponsePage1 = response.getPage().getItems().stream()
				.filter(item -> item.getMainCategory().startsWith(mainCategory)).collect(Collectors.toList());

		// Filtrar los elementos de la segunda página que comienzan con el prefijo dado
		List<Item> itemsResponsePage2 = response2.getPage().getItems().stream()
				.filter(item -> item.getMainCategory().startsWith(mainCategory)).collect(Collectors.toList());

		List<Item> itemsJuntos = new ArrayList<>();
		itemsJuntos.addAll(itemsResponsePage1);
		itemsJuntos.addAll(itemsResponsePage2);

		return itemsJuntos;
	}

	@Override
	public void save(News news) {
		newsRepository.save(news);
	}

	@Override
	public NewsResponse findNewsById(Long id) {
		NewsResponse itemEncontrado = webClient.get().uri("/api/noticias/" + id + ".json").retrieve()
				.bodyToMono(NewsResponse.class).block();

		return itemEncontrado;
	}

	@Override
	public List<News> findAll() {
		return newsRepository.findAll();
	}

}
