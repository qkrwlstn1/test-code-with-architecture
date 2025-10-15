package com.example.demo.service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.UserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource("classpath:test.properties")
@SqlGroup({
        @Sql(value = "/sql/user-service-test-data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
        @Sql(value = "/sql/delete-all-data.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
})

class UserServiceTest {

    @Autowired
    private UserService userService;


    @Test
    void getByEmail은_ACTIVE_상태인_유저를_찾아올_수_있다(){
        //given
        String email = "aldhfldns@naver.com";

        // when
        UserEntity result = userService.getByEmail(email);

        //then
        assertEquals(result.getEmail(), "aldhfldns@naver.com");

    }

    @Test
    void getByEmail은_PENDING_상태인_유저를_찾아올_수_없다(){
        //given
        String email = "aldhfldns1@naver.com";

        // when

        //then
        assertThrows(ResourceNotFoundException.class, () -> {
            UserEntity result = userService.getByEmail(email);
        });

    }

    @Test
    void getById는_ACTIVE_상태인_유저를_찾아올_수_있다(){
        //given
        // when
        UserEntity result = userService.getByid(1L);

        //then
        assertEquals(result.getId(), 1L);

    }

    @Test
    void getById는_PENDING_상태인_유저를_찾아올_수_없다(){
        //given
        // when

        //then
        assertThrows(ResourceNotFoundException.class, () -> {
            UserEntity result = userService.getByid(2L);
        });

    }


}