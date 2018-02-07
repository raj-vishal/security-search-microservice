package com.optus.springboot.search;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
public class SearchController {

	private static int KEYVALUEINDEX = 1;

    @GetMapping("/counter-api")
    public String welcomeMessage(@AuthenticationPrincipal final UserDetails userDetails) {

        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
        authorities
                .forEach(System.out::println);

        return "Hello... Welcome to Counter Micro Service!";
    }
    
    @SuppressWarnings("unchecked")
	@RequestMapping(value = "/counter-api/searchAll", method = RequestMethod.GET,headers="Accept=application/json")
	public Map<String, Integer> searchAll(Model counts) {

		RestTemplate remoteServiceInvoker = new RestTemplate();
		Map<String, Integer> listOfWords = remoteServiceInvoker.getForObject("http://localhost:9002/loadFile", Map.class);
		return listOfWords;
		
	}
    
	/**
	 * Task 1: Search the following texts, which will return the counts respectively.
	 * @param queryString
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/counter-api/search", method = RequestMethod.POST,headers="Accept=application/json")
	public Map<String, Integer> search(@RequestBody String queryString ) {

		RestTemplate remoteServiceInvoker = new RestTemplate();

		// MOVE TO LOAD-DATA-SERVICE ///////////////
		List<String> keyWordList = new ArrayList<String>();
		try {

			String queryDecoded = URLDecoder.decode(queryString, "UTF-8");
			
			List<String> KeyValueQuery = new ArrayList<String>(Arrays.asList(queryDecoded.split(":")));
			
			if (!KeyValueQuery.isEmpty()) {
				keyWordList = new ArrayList(Arrays.asList(KeyValueQuery.get(KEYVALUEINDEX).split("\\W+")));
			}
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Map<String, Integer> counterMap = remoteServiceInvoker.getForObject("http://localhost:9002/wordCounter/"+ keyWordList, Map.class);

		return counterMap;
		
	}
	
	/**
	 * Task 2: Provide the top 20 (as Path Variable) Texts, which has the
	 * highest counts in the Sample Paragraphs provided.
	 * @param count
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/counter-api/top/{count}", method = RequestMethod.GET,headers="Accept=text/csv")
	public Map<String, Integer> wordsWithTopCount(@PathVariable("count") String count) {
		
		RestTemplate remoteServiceInvoker = new RestTemplate();
		Map<String, Integer> counterMap = remoteServiceInvoker.getForObject("http://localhost:9002/topWordsCounter/"+ count, Map.class);

		return counterMap;		
	}
}
