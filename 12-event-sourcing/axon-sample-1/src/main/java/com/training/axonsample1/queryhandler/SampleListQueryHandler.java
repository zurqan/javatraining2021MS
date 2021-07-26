package com.training.axonsample1.queryhandler;

import com.training.axonsample1.common.query.LoadNameQuery;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class SampleListQueryHandler {


    @QueryHandler
    public List<String> loadNames(LoadNameQuery loadNameQuery){

        return Arrays.asList("Ahmad","Mahmood","Mohammad");
    }

}
