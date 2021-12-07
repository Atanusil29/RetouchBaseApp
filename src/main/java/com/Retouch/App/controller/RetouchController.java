package com.Retouch.App.controller;

import java.util.HashMap;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

@Controller
public class RetouchController {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@GetMapping("/GetOTDSTicket")
	public String getOTDSToken(Model model){
		
		System.out.println("Request access token");
		String otdsUrl = "https://win-v8tbqs6gv8i:8443/otdsws/rest/authentication/credentials";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HashMap<String, String> map = new HashMap<>();
		map.put("userName", "otadmin@otds.admin");
		map.put("password", "goal@2020");
		HttpEntity<HashMap<String, String>> entity = new HttpEntity<HashMap<String, String>>(map,headers);
		//HttpEntity<String> entity = new HttpEntity<String>("requestJson", headers);
		ResponseEntity<String> response = restTemplate.exchange(otdsUrl, HttpMethod.POST, entity, String.class);
		//System.out.println(response.getBody());
		JSONObject jsonObject = new JSONObject(response.getBody());
		
		model.addAttribute("message", jsonObject.toString());
		return "index";	
//		return exportDocumentresponse.toString();	
	}
	
}