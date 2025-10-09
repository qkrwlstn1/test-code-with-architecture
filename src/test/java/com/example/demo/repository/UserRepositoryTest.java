package com.example.demo.repository;

import com.example.demo.model.UserStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest(showSql = true)
@TestPropertySource("classpath:test.properties")
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void UserRepository_가_제대로_연결됨(){
        //given
        UserEntity ue = UserEntity.builder()
                .email("aldhfldns@naver.com")
                .address("Seoul")
                .nickname("진수")
                .status(UserStatus.ACTIVE)
                .certificationCode("aaaaaaaa-aaaaaaa-aaaaaaaaaa")
                .build();

        //when

        UserEntity result = userRepository.save(ue);

        //than
        assertNotNull(result.getId());
    }

    @Test
    void findByIdAndStatus_로_유저_데이터를_찾아올_수_있다(){
        //given
        UserEntity ue = UserEntity.builder()
                .email("aldhfldns@naver.com")
                .address("Seoul")
                .nickname("진수")
                .status(UserStatus.ACTIVE)
                .certificationCode("aaaaaaaa-aaaaaaa-aaaaaaaaaa")
                .build();
        UserEntity saveUser = userRepository.save(ue);
        //when


        Optional<UserEntity> result = userRepository.findByIdAndStatus(saveUser.getId(), UserStatus.ACTIVE);
        //than
        assertTrue(result.isPresent());
    }


    @Test
    void findByIdAndStatus_는_데이터가_없으면_Optional_empty_를_내려준다(){
        //given
        UserEntity ue = UserEntity.builder()
                .email("aldhfldns@naver.com")
                .address("Seoul")
                .nickname("진수")
                .status(UserStatus.ACTIVE)
                .certificationCode("aaaaaaaa-aaaaaaa-aaaaaaaaaa")
                .build();
        userRepository.save(ue);
        //when


        Optional<UserEntity> result = userRepository.findByIdAndStatus(2, UserStatus.ACTIVE);


        //than
        assertTrue(result.isEmpty());
    }

}

