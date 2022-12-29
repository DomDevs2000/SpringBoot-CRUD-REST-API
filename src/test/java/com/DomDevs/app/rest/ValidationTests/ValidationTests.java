package com.DomDevs.app.rest.ValidationTests;

import com.DomDevs.app.rest.Exceptions.ValidationException;
import com.DomDevs.app.rest.Models.User;
import com.DomDevs.app.rest.Repo.UserRepo;
import com.DomDevs.app.rest.UserService.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class ValidationTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;
    @MockBean
    private UserRepo userRepo;

    private final ObjectMapper objectMapper = new ObjectMapper();

//    @Test
//    void shouldThrowValidationErrorWhenSettersAreEmpty() throws Exception {
//        User user = new User();
//        user.setId(5L);
//        user.setFirstName("");
//        user.setLastName("");
//        user.setAge(0);
//        when(userService.findAllByAge(0)).thenThrow(new ValidationException(null));
//        mockMvc.perform(post("/users/create")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(toJson(user)))
//                .andExpect(status().isBadRequest());
//    }
//
//    private String toJson(User obj) throws JsonProcessingException {
//        return objectMapper.writeValueAsString(obj);
//}
}