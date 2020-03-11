package me.jmix.brothertakeaway;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "me.jmix.brothertakeaway.dao.mapper")
public class BrotherTakeawayApplication {

    public static void main(String[] args) {
        SpringApplication.run(BrotherTakeawayApplication.class, args);
    }

}
