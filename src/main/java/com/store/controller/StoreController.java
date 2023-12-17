package com.store.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.store.model.Item;
import com.store.service.ItemService;

@RestController
@RequestMapping("/store")
public class StoreController {
	
	@Autowired
	private ItemService itemService;
	
	@GetMapping
	ResponseEntity<List<Item>> getItens() {
		List<Item> itens = itemService.getItens();
		if(itens.isEmpty())
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(itens);
		return ResponseEntity.ok(itens);
	}
	
	@GetMapping("/{id}")
	ResponseEntity<Item> getItem(@PathVariable Long id){
		try {
			return ResponseEntity.ok(itemService.getItem(id));
		}catch (Exception e){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Item());
		}
	}
	
	@PostMapping
	ResponseEntity<Item> setItem(@RequestBody Item item) {
		itemService.setItem(item);
		return ResponseEntity.created(null).body(item);
	}
	
	@PutMapping("/{id}")
	ResponseEntity<Item> setItem(@RequestBody Item item, @PathVariable Long id){
		try {
			return ResponseEntity.accepted().body(itemService.setItem(id, item));
		} catch (Exception e){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Item());
		}
	}
	
	@DeleteMapping("/{id}")
	ResponseEntity<Item> deleteItem(@PathVariable Long id){
		try {
			itemService.deleteItem(id);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Item());
		} catch (Exception e){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Item());
		}
	}
	
}
