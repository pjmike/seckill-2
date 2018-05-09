package com.wizz.seckill.Controller;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@Slf4j
public class seckillControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void resHandler() throws Exception {
        String requestBody = "{\"phoneNum\":\"13572446538\",\"stuId\":\"16010130033\",\"stuName\":\"彭军\"}";
        String result = mockMvc.perform(MockMvcRequestBuilders.post("/tickets")
                .content(requestBody)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();
        log.info("result:{}",result);
    }
}