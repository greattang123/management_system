package com.example.management_system.information_test;

import com.example.management_system.entity.Invigilation;
import com.example.management_system.service.InvigilationService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class InvigilationTest {
    @Autowired
    private InvigilationService is;
    @Test
    public void test(){
        List<Invigilation>list=is.list();
        for(Invigilation i:list){
            log.debug("{}", i.getState());
        }
    }
}
