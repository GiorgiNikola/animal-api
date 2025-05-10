package com.animalapi;

import com.animalapi.model.Animal;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AnimalApiApplicationTests {

    @Autowired
    private MockMvc mvc;

    @Test
    void contextLoads() {
    }

    @Test
    void test() throws Exception {
        String result = this.mvc.perform(MockMvcRequestBuilders.get("/api/animals"))
                .andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }

    @Test
    void addAnimalTest() throws Exception {
        Animal animal = new Animal(3, "Bojack", 15, new String[]{"peacock","melon"});
        String animalJson = new ObjectMapper().writeValueAsString(animal);
        ResultActions resultActions = this.mvc.perform(MockMvcRequestBuilders.post("/api/animals")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(animalJson)).andExpect(status().isConflict());
        String resultJson = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println(resultJson);
    }

}
