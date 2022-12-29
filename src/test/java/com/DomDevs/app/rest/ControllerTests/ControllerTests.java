package com.DomDevs.app.rest.ControllerTests;

import com.DomDevs.app.rest.Controller.ApiController;
import com.DomDevs.app.rest.Models.User;
import com.DomDevs.app.rest.Repo.UserRepo;
import com.DomDevs.app.rest.UserService.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ApiController.class)
public class ControllerTests {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserService userService;
    @MockBean
    private UserRepo userRepo;


    @Test
    public void shouldReturnUser() throws Exception {
        User user = new User();
        user.setAge(25);
        user.setFirstName("John");
        user.setLastName("Doe");

        when(userRepo.findAll()).thenReturn(Collections.singletonList(user));
        mockMvc
                .perform(MockMvcRequestBuilders.get("/users"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].firstName").value("John"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].lastName").value("Doe"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].age").value("25"));
    }

    @Test
    public void shouldGetUsersPaginated() throws Exception {
        mockMvc.perform(get("/users/page=3/size=4"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void shouldGetUsersByAge() throws Exception {
        User user = new User();
        user.setAge(16);
        when(userService.findAllByAge(16))
                .thenReturn(Collections.singletonList(user));
        mockMvc.perform(get("/users/age/16"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].age").value("16"));
    }

    @Test
    public void shouldGetUsersByFirstName() throws Exception {
        User user = new User();
        user.setFirstName("John");

        when(userService.findAllByFirstName("John")).thenReturn(Collections.singletonList(user));

        mockMvc.perform(get("/users/firstname/john"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].firstName").value("John"));
    }

    @Test
    public void shouldGetUsersByLastName() throws Exception {
        User user = new User();
        user.setLastName("Doe");
        when(userRepo.findAllByLastName("Doe"))
                .thenReturn(Collections.singletonList(user));
        mockMvc.perform(get("/users/lastname/doe"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].lastName").value("Doe"));
    }

    @Test
    public void shouldGetUsersById() throws Exception {
        User user = new User();
        user.setId(20L);
        user.setAge(25);
        user.setFirstName("John");
        user.setLastName("Doe");
        when(userRepo.findById(20L)).thenReturn(Optional.of(user));
        mockMvc.perform(get("/users/id/20"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void shouldCreateNewUser() throws Exception {
        mockMvc.perform(post("/users/create").contentType(MediaType.APPLICATION_JSON).content("{\"firstName\": \"John\", \"lastName\":\"Doe\", \"age\":\"22\"}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("User Created"));
    }

    @Test
    public void shouldDeleteUser() throws Exception {
        User user = new User();
        user.setId(1L);
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setAge(12);

        when(userRepo.findById(1L)).thenReturn(Optional.of(user));

        mockMvc.perform(MockMvcRequestBuilders.delete("/users/delete/1").param("id", "1")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("Deleted User With The id: " + user.getId()));

    }


    @Test
    public void shouldUpdateUser() throws Exception {
        User user = new User();
        user.setId(10L);
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setAge(22);

        when(userRepo.save(user)).thenReturn(user);

        mockMvc.perform(MockMvcRequestBuilders.put("/users/update/").content(new ObjectMapper().writeValueAsString(user)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[].id()").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].firstName").value("John"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].lastName").value("Doe"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].age").value("22"));

    }


}

