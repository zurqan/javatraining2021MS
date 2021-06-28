package com.training.serviceav2.adapter.rest;

import com.training.serviceav2.adapter.rest.client.UserClient;
import com.training.serviceav2.adapter.rest.dto.PurchaseOrder;
import com.training.serviceav2.adapter.rest.dto.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("pos")
public class PurchaseOrderController {

    Map<String, PurchaseOrder> orders = new ConcurrentHashMap(){{
        put("A1",new PurchaseOrder("A1","1000","Amman",2000));
        put("A2",new PurchaseOrder("A2","1000","Amman",430));
        put("A3",new PurchaseOrder("A3","1001","Irbid",2000));

    }};

    private final UserClient userClient;

    public PurchaseOrderController(UserClient userClient) {

//        System.out.println("userClient.getClass() = " + userClient.getClass());
        this.userClient = userClient;
    }

    @GetMapping("/{poId}")
    public Tuple<PurchaseOrder, User> loading(@PathVariable String poId){
        PurchaseOrder purchaseOrder = orders.get(poId);
        String userId = purchaseOrder.getCreatedBy();

        User user = userClient.userInfo(userId);
        return new Tuple<>(purchaseOrder,user);
    }

}
