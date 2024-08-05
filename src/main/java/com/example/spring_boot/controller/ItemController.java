package com.example.spring_boot.controller;

import com.example.spring_boot.model.Item;
import com.example.spring_boot.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/items")
public class ItemController {

    @Autowired
    private ItemRepository itemRepository;

    // Create a new Item
    @PostMapping
    public Item createItem(@RequestBody Item item) {
        return itemRepository.save(item);
    }

    // Get all Items
    @GetMapping
    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    // Get an Item by ID
    @GetMapping("/{id}")
    public ResponseEntity<Item> getItemById(@PathVariable Long id) {
        Optional<Item> item = itemRepository.findById(id);
        if (item.isPresent()) {
            return ResponseEntity.ok(item.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Update an Item
    @PutMapping("/insert")
    public ResponseEntity<Item> updateItem(@PathVariable Long id, @RequestBody Item itemDetails) {
        Optional<Item> item = itemRepository.findById(id);
        if (item.isPresent()) {
            Item existingItem = item.get();
            existingItem.setName(itemDetails.getName());
            existingItem.setDescription(itemDetails.getDescription());
            final Item updatedItem = itemRepository.save(existingItem);
            return ResponseEntity.ok(updatedItem);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete an Item
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
        Optional<Item> item = itemRepository.findById(id);
        if (item.isPresent()) {
            itemRepository.delete(item.get());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

