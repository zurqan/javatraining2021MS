package com.trinaing.rdsdb.adapter.repository.impl.rds;

import lombok.Value;

@Value
public class ActiveStatistic {

    private boolean active;

    private long count;
}
