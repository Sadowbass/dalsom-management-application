package com.dalsom.management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ManagementApplication {

//    @Autowired
//    private InitData initData;

    public static void main(String[] args) {
        SpringApplication.run(ManagementApplication.class, args);
    }

//    @PostConstruct
//    public void init(){
//        initData.init();
//    }
}
