package at.ac.fhcampuswien.newsanalyzer.ctrl;

import at.ac.fhcampuswien.newsapi.NewsApi;
import at.ac.fhcampuswien.newsapi.beans.Article;
import at.ac.fhcampuswien.newsapi.beans.NewsResponse;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Controller {

	public static final String APIKEY = "37b97f32871b48b0b49b01387445d3ff";  //TODO add your api key
	// List for all URLs
	public static List<String> urli = new ArrayList<>();

	public void process(NewsApi news) {
		if(!urli.isEmpty()){
			urli.clear();
		}
		//TODO implement Error handling
		NewsResponse newsResponse = null;
		List<Article> articles = null;
		//TODO load the news based on the parameters

		//TODO implement methods for analysis
		try{
			newsResponse = news.getNews();
			articles = newsResponse.getArticles();
		}catch(Exception e){
			System.out.println("Exception: " + e);
			System.out.println("Keine News vorhanden");
		}

		if(newsResponse != null && !articles.isEmpty()){
			//articles = newsResponse.getArticles();
			articles.stream().forEach(article -> System.out.println(article.toString()+ "\n"));

			// Fetches all URL´s
			articles.stream().forEach(article -> urli.add(article.getUrl()));


			System.out.println("----Geordnet----");
			List<Article> result = articles.stream().sorted((object1, object2) -> object1.getTitle().compareTo(object2.getTitle())).collect(Collectors.toList());
			result.stream().forEach(article -> System.out.println("\t"+article.toString()+ "\n"));

			System.out.println("Es wurden "+articles.size() + " Artikeln ausgegeben");

			String getProvider =
					articles.stream()
							.collect(Collectors.groupingBy(article -> article.getSource().getName(),Collectors.counting()))
							.entrySet()
							.stream()
							.max(Comparator.comparingInt(t->t.getValue().intValue())).get().getKey();
			System.out.println("Provider: "+getProvider);
			try{
				String ShortestAuthor =
						articles.stream().filter(article -> Objects.nonNull(article.getAuthor()))
								.min(Comparator.comparingInt(article -> article.getAuthor().length()))
								.get().getAuthor();

				System.out.println("Shortest Author: "+ShortestAuthor);
			}catch(NullPointerException e){
				System.out.println(e);
			}catch(Exception e){
				System.out.println("goin");
			}
		}
	}
	

	public Object getData() {
		
		return null;
	}
}
