package com.bastien_corre.cleanjava;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import(PostgreSQLContainerTests.class)
@SpringBootTest
class CleanJavaApplicationTests {

    @Test
    void contextLoads() {
    }

}
