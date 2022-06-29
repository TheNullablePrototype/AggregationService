package com.prototype.aggregationservice.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AggregationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void contextLoads() throws Exception {
        String expectedContent = """
                [
                  {
                    "id": 1,
                    "urlType": "LIVE",
                    "videoUrl": "rtsp://127.0.0.1/1",
                    "value": "fa4b588e-249b-11e9-ab14-d663bd873d93",
                    "ttl": 120
                  },
                  {
                    "id": 20,
                    "urlType": "ARCHIVE",
                    "videoUrl": "rtsp://127.0.0.1/2",
                    "value": "fa4b5b22-249b-11e9-ab14-d663bd873d93",
                    "ttl": 60
                  },
                  {
                    "id": 3,
                    "urlType": "ARCHIVE",
                    "videoUrl": "rtsp://127.0.0.1/3",
                    "value": "fa4b5d52-249b-11e9-ab14-d663bd873d93",
                    "ttl": 120
                  },
                  {
                    "id": 2,
                    "urlType": "LIVE",
                    "videoUrl": "rtsp://127.0.0.1/20",
                    "value": "fa4b5f64-249b-11e9-ab14-d663bd873d93",
                    "ttl": 180
                  }
                ]""";
        this.mockMvc.perform(get("/aggregation"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(expectedContent));
    }

}
