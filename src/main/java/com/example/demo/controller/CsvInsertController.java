package com.example.demo.controller;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.bean.ItemCsv;
import com.example.demo.domain.Item;
import com.example.demo.repository.ItemRepository;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.google.gson.reflect.TypeToken;

@Controller
@RequestMapping("/insert")

public class CsvInsertController {

	@Autowired
	private ItemRepository repository;

	
	@Autowired
	private ModelMapper modelMapper;

	@GetMapping("/csv")
	public ResponseEntity<byte[]> downloadCsv() throws IOException {
	    Iterable<Item> items = repository.findAll();
	    Type listType = new TypeToken<List<ItemCsv>>() {}.getType();
	    List<ItemCsv> csv = modelMapper.map(items, listType);
	    
	    CsvMapper mapper = new CsvMapper();
	    CsvSchema schema = mapper.schemaFor(ItemCsv.class).withHeader();
	    String csvString = mapper.writer(schema).writeValueAsString(csv);
	    
	    byte[] csvBytes = csvString.getBytes("UTF-8");
	    
	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
	    headers.setContentDispositionFormData("attachment", "data.csv");
	    
	    return ResponseEntity
	            .ok()
	            .headers(headers)
	            .body(csvBytes);
	}
}
