package me.jmix.brothertakeaway;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Slf4j
public class LoggerTest {
    // private final Logger logger = LoggerFactory.getLogger(LoggerTest.class);

    @Test
    @Disabled
    void test1() {
        String name = "imooc";
        String password = "123456";
        log.info("name: " + name + ", password: " + password);
        log.info("name: {}, password: {}", name, password);
        log.debug("debug...");
        log.info("info...");
        log.warn("warn...");
        log.error("error");
    }
}
