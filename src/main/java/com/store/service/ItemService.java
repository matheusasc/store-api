package com.store.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import com.store.model.Item;
import com.store.repository.ItemRepository;

@Service
public class ItemService {
	
	List<Item> itens = new ArrayList<>();
	
	@Autowired
	private ItemRepository itemRepository;
	
	@GetMapping
	public List<Item> getItens() {
		return itemRepository.findAll();
	}
	
	public Item getItem(Long id) {
		Optional<Item> optionalItem = itemRepository.findById(id);
		if (optionalItem.isPresent())
			return optionalItem.get();
		return new Item();
		
	}
	
	public Item setItem(Item item) {
		return itemRepository.save(item);
	}
	
	public Item setItem(Long id, Item item) {
		Optional<Item> optionalItem = itemRepository.findById(id);
		if (optionalItem.isPresent()) {
			item.setId(id);
			return itemRepository.save(item);
		}
		return new Item();
	}
	
	
	public void deleteItem(Long id) {
		Optional<Item> optionalItem = itemRepository.findById(id);
		if (optionalItem.isPresent())
			itemRepository.deleteById(id);
	}
	

}
