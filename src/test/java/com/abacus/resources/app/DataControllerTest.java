package com.abacus.resources.app;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@WebMvcTest(value = DataController.class, secure = false)
public class DataControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void putPerson() throws Exception {
        MockHttpServletRequestBuilder builder =
                MockMvcRequestBuilders.put("/resources/data/put")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content("{\"id\":23,\"name\":\"Test User\",\"age\":100,\"locale\":\"en_US\",\"type\":\"Person\"}");
        this.mockMvc.perform(builder)
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.message").value("SUCCESS"));
                //.andDo(print());
    }

    @Test
    public void getPerson() throws Exception {
        MockHttpServletRequestBuilder builder =
                MockMvcRequestBuilders.put("/resources/data/put")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content("{\"id\":23,\"name\":\"Test User\",\"age\":100,\"locale\":\"en_US\",\"type\":\"Person\"}");
        this.mockMvc.perform(builder);

        MockHttpServletRequestBuilder builder2 =
                MockMvcRequestBuilders.get("/resources/data/23")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON);
        this.mockMvc.perform(builder2)
                .andExpect(jsonPath("$.[0].id").value(23));
                //.andDo(print());
    }

    @Test
    public void deletePerson() throws Exception {
        MockHttpServletRequestBuilder builder =
                MockMvcRequestBuilders.put("/resources/data/put")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content("{\"id\":23,\"name\":\"Test User\",\"age\":100,\"locale\":\"en_US\",\"type\":\"Person\"}");
        this.mockMvc.perform(builder);

        MockHttpServletRequestBuilder builder2 =
                MockMvcRequestBuilders.delete("/resources/data/23")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON);
        this.mockMvc.perform(builder)
                .andExpect(jsonPath("$.id").value(23));
                //.andDo(print());
    }
}

