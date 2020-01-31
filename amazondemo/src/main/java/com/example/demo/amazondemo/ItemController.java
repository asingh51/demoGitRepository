package com.example.demo.amazondemo;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ItemController {

	@Autowired
	ItemRepository itemRepo;
	
	@RequestMapping("items")
	public List<Item> getItems() {
		List<Item> items= (List<Item>) itemRepo.findAll();		
		return items;
	}
	
	@RequestMapping(path="item/{i}", produces= {"application/xml"})
	public Optional<Item> getItem(@PathVariable int i) {
		Optional<Item> item= itemRepo.findById(i);
		return item;
	}
	
	@PostMapping("item/add")
	private void addItem(@RequestBody Item item) {
		itemRepo.save(item);
	}
	
	@DeleteMapping("item/delete/{id}")
	private String deleteItem(@PathVariable int id) {
		Item item= itemRepo.getOne(id);
		itemRepo.delete(item);
		return "Deleted";
	}
	
	@PutMapping("item")
	private Item saveOrUpdateItem(@RequestBody Item item) {
		itemRepo.save(item);
		return item;
	}
}
