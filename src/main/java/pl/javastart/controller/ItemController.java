package pl.javastart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.javastart.item.Item;
import pl.javastart.item.ItemRepository;

import java.util.Optional;

@Controller
@RequestMapping("/potrawy")
public class ItemController {

    private ItemRepository repository;

    @Autowired
    public ItemController(ItemRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/{name}")
    public String getItem(@PathVariable String name, Model model) {
        String nameWithoutDashes = name.replaceAll("-", " ");
        Optional<Item> item = repository.findByNameIgnoreCase(nameWithoutDashes);
        item.ifPresent(it -> model.addAttribute("item", it));
        return item.map(it -> "item").orElse("redirect:/");
    }
}
