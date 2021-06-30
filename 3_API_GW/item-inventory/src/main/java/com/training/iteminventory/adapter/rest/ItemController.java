package com.training.iteminventory.adapter.rest;

import com.training.iteminventory.adapter.rest.dto.ItemDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/items")
public class ItemController {

    @GetMapping("/{itemId}")
    public ItemDTO loadingItem(@PathVariable String itemId){

    }
}
