package com.DomDevs.app.rest.ExceptionTests;

import com.DomDevs.app.rest.Controller.ApiController;
import com.DomDevs.app.rest.Exceptions.AgeNotFoundException;
import com.DomDevs.app.rest.Exceptions.FirstNameNotFoundException;
import com.DomDevs.app.rest.Exceptions.LastNameNotFoundException;
import com.DomDevs.app.rest.Exceptions.UserNotFoundException;
import com.DomDevs.app.rest.Repo.UserRepo;
import com.DomDevs.app.rest.UserService.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ApiController.class)
public class ExceptionTests {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserService userService;
    @MockBean
    private UserRepo userRepo;


    @Test
    public void shouldReturn404WhenUserIsNotFound() throws Exception {
        when(userRepo.findById(1L))
                .thenThrow(new UserNotFoundException(1L));
        mockMvc
                .perform(get("/users/id/1")).andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(content().string("Could Not Find User 1"));
    }

    @Test
    public void shouldReturn404WhenUserIsNotFoundByFirstName() throws Exception {
        when(userService.findAllByFirstName("John"))
                .thenThrow(new FirstNameNotFoundException("John"));
        mockMvc
                .perform(get("/users/firstname/John")).andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(content().string("Could Not Find User with first name: John"));
    }

    @Test
    public void shouldReturn404WhenUserIsNotFoundByLastName() throws Exception {
        when(userService.findAllByFirstName("doe"))
                .thenThrow(new LastNameNotFoundException("doe"));
        mockMvc
                .perform(get("/users/firstname/doe")).andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(content().string("Could Not Find User with first name: doe"));
    }

    @Test
    public void shouldReturn404WhenUserIsNotFoundByAge() throws Exception {
        when(userService.findAllByAge(10))
                .thenThrow(new AgeNotFoundException(10));
        mockMvc
                .perform(get("/users/age/10"))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(content().string("Could Not Find Users With Age: 10"));
    }

}

