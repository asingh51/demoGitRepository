package com.example.demo.amazondemo;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller                   // RestController if you want a Rest API. and @Controller if you want a normal application 
public class ItemController {

	@Autowired
	ItemRepository itemRepo;
	
	@RequestMapping("/")
	public String home() {
		System.out.println("Hi");
		return "home.jsp";
	}
	
	@RequestMapping("items")
	@ResponseBody
	public List<Item> getItems() {
		List<Item> items= (List<Item>) itemRepo.findAll();		
		return items;
	}
	
	@RequestMapping(path="item/{i}", produces= {"application/xml"})
	@ResponseBody
	public Optional<Item> getItem(@PathVariable int i) {
		Optional<Item> item= itemRepo.findById(i);
		return item;
	}
	
	@PostMapping("item/add")
	@ResponseBody
	private void addItem(@RequestBody Item item) {
		itemRepo.save(item);
	}
	
	@DeleteMapping("item/delete/{id}")
	@ResponseBody
	private String deleteItem(@PathVariable int id) {
		Item item= itemRepo.getOne(id);
		itemRepo.delete(item);
		return "Deleted";
	}
	
	@PutMapping("item")
	@ResponseBody
	private Item saveOrUpdateItem(@RequestBody Item item) {
		itemRepo.save(item);
		return item;
	}
}
