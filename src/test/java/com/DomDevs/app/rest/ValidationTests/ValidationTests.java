//package com.DomDevs.app.rest.ValidationTests;
//
//import com.DomDevs.app.rest.Exceptions.ValidationException;
//import com.DomDevs.app.rest.Models.User;
//import com.DomDevs.app.rest.Repo.UserRepo;
//import com.DomDevs.app.rest.SpringBootCrudRestApiApplication;
//import jakarta.validation.ConstraintViolation;
//import jakarta.validation.Validator;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.validation.BindingResult;
//
//import java.util.Set;
//
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.mockito.Mockito.when;
//
//@AutoConfigureMockMvc
//@SpringBootTest(classes = SpringBootCrudRestApiApplication.class)
//public class ValidationTests {
//
//    @MockBean
//    private UserRepo userRepo;
//
//    @Mock
//    private BindingResult mockBindingResult;
//
//
//    @Autowired
//    private Validator validator;
//
//
//    @Test
//    void shouldThrowValidationErrorWhenFirstNameIsEmpty() throws Exception {
//        User user = new User();
//        user.setId(5L);
//        user.setFirstName("");
//        user.setLastName("Doe");
//        user.setAge(25);
//        when(userRepo.save(user)).thenThrow(new ValidationException(mockBindingResult));
//        Set<ConstraintViolation<User>> violations = validator.validate(user);
//
//        System.out.println(violations.toString());
//
//        assertFalse(violations.isEmpty());
//
//
//    }
//
//
//    @Test
//    void shouldThrowValidationErrorWhenLastNameIsEmpty() throws Exception {
//        User user = new User();
//        user.setId(5L);
//        user.setFirstName("John");
//        user.setLastName("");
//        user.setAge(25);
//        when(userRepo.save(user)).thenThrow(new ValidationException(mockBindingResult));
//        Set<ConstraintViolation<User>> violations = validator.validate(user);
//
//        System.out.println(violations.toString());
//
//        assertFalse(violations.isEmpty());
//
//
//    }
//
//    @Test
//    void shouldThrowValidationErrorWhenAgeIsLowerThan1() throws Exception {
//        User user = new User();
//        user.setId(5L);
//        user.setFirstName("");
//        user.setLastName("Doe");
//        user.setAge(0);
//        when(userRepo.save(user)).thenThrow(new ValidationException(mockBindingResult));
//        Set<ConstraintViolation<User>> violations = validator.validate(user);
//        assertFalse(violations.isEmpty());
//
//    }
//
//    @Test
//    void shouldThrowValidationErrorWhenAgeIsHigherThan150() throws Exception {
//        User user = new User();
//        user.setId(5L);
//        user.setFirstName("");
//        user.setLastName("");
//        user.setAge(151);
//        when(userRepo.save(user)).thenThrow(new ValidationException(mockBindingResult));
//        Set<ConstraintViolation<User>> violations = validator.validate(user);
//
//        System.out.println(violations.toString());
//
//        assertFalse(violations.isEmpty());
//
//    }
//}