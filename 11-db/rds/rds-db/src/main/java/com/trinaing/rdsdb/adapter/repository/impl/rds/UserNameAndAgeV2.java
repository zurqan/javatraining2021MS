package com.trinaing.rdsdb.adapter.repository.impl.rds;

import org.springframework.beans.factory.annotation.Value;

public interface UserNameAndAgeV2 {

    @Value("#{'*** '+target.name+ ' - ' +target.age+' ****'}")
    String getData();
}
