package com.algaworks.patrimonio.resource;

import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.algaworks.patrimonio.model.Item;
import com.algaworks.patrimonio.repository.ItemRepository;

@RestController
@CrossOrigin("${origem-permitida}")	//	Permite que acessem nossa API
public class ItemResource {

	@Autowired
	private ItemRepository itemRepository;
	
	@GetMapping(path = "/itens")
	@ResponseStatus(HttpStatus.OK)
	public List<Item> listar(){
		return itemRepository.findAll();
	}
	
	@PostMapping(path = "/itens")
	@ResponseStatus(HttpStatus.CREATED)
	public Item adicionar(@RequestBody @Valid Item item, BindingResult result){
		if(result.hasErrors()){
			//	HttpStatus.BAD_REQUEST
			return null;
		}
		return itemRepository.save(item);
	}
}
