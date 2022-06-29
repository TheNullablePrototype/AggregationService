package com.prototype.aggregationservice.helper;

import com.prototype.aggregationservice.data.VideoCameraData;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.GsonTester;

@JsonTest
public class GsonHelperTest {

    @Autowired
    private GsonTester<VideoCameraData> gson;

    private final String expected = "{\"id\":1,\"sourceDataUrl\":\"http://www.mocky.io\",\"tokenDataUrl\":\"http://www.mocky.io\"}";

    @Test
    void serialize() throws Exception {
        VideoCameraData cameraData = new VideoCameraData(1, "http://www.mocky.io", "http://www.mocky.io");
        Assertions.assertThat(this.gson.write(cameraData)).isEqualToJson(expected);
    }

    @Test
    void deserialize() throws Exception {
        Assertions.assertThat(gson.parseObject(expected)).isEqualTo(new VideoCameraData(1, "http://www.mocky.io", "http://www.mocky.io"));
    }

}
