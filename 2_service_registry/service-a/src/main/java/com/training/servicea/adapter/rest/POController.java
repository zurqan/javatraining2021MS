package com.training.servicea.adapter.rest;

import com.training.servicea.adapter.rest.dto.PurchaseOrder;
import com.training.servicea.adapter.rest.dto.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RequestMapping("pos")
@RestController
public class POController {

    Map<String,PurchaseOrder> orders = new ConcurrentHashMap(){{
        put("A1",new PurchaseOrder("A1","1000","Amman",2000));
        put("A2",new PurchaseOrder("A2","1000","Amman",430));
        put("A3",new PurchaseOrder("A3","1001","Irbid",2000));

    }};

    private final RestTemplate restTemplate;

    public POController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    @GetMapping("/{poId}")
    public Tuple<PurchaseOrder, User> loading(@PathVariable String poId){
        PurchaseOrder purchaseOrder = orders.get(poId);
        String userId = purchaseOrder.getCreatedBy();

        User user = restTemplate.getForObject("http://service-b/users/{id}", User.class, userId);
        String strUser = restTemplate.getForObject("http://service-b/users/{id}", String.class, userId);
        System.out.println("strUser = " + strUser);
        return new Tuple<>(purchaseOrder,user);
    }
}
