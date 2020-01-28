package com.microservice.tollparking;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.microservice.tollparking.controller.TollparkingController;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class TollparkingApplicationTests {

    @Autowired
    private TollparkingController tollParkingController;

    @Test
    void contextLoads() {
        assertNotNull(tollParkingController);
    }
}
