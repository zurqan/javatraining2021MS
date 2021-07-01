package com.training.iteminventory.adapter.rest;

import com.training.iteminventory.adapter.rest.dto.ItemDTO;
import com.training.iteminventory.model.Item;
import com.training.iteminventory.service.ItemService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.function.Function;

@RestController
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;

    @Value("${server.port:8080}")
    private int serverPort;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/{itemId}")
    public ItemDTO loadingItem(@PathVariable String itemId){

        System.out.println("serverPort = " + serverPort);

        if(itemId.equals("1001")){
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return itemService
                .getItem(itemId)
                .map(toDTO())
                .orElseThrow(() -> new RuntimeException("Item Not found"));
    }

    private Function<Item,ItemDTO> toDTO() {

        return item->
                ItemDTO
                    .builder()
                        .itemId(item.getItemId())
                        .fullDescription(item.getFullDescription())
                        .shortDescription(item.getShortDescription())
                        .imgUrl(item.getImgUrl())
                        .price(item.getPrice())
                        .name(item.getName())
                        .manfId(item.getManfId())
                        .qty(item.getQty())
                        .build();
    }
}
