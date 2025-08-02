package com.prachimehar.journalApp.service;

import com.prachimehar.journalApp.Entity.User;
import com.prachimehar.journalApp.repository.UserRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Disabled
    @Test
    public void testFindByUserName(){
        User user = userRepository.findByUsername("ram");
        assertFalse(user.getJournalEntries().isEmpty());
        assertNotNull(user);
    }
    @Disabled
    @ParameterizedTest
    @ValueSource(strings = {
            "ram",
            "shyam",
            "yash"
    })
    public void test2FindByUserName(String name){
        User user = userRepository.findByUsername(name);
        assertNotNull(user,"failed for: "+name);
    }

    @Disabled
    @ParameterizedTest
    @ArgumentsSource(CustomStringProvider.class)
    public void test3FindByUserName(User user){
        assertTrue(userService.saveNewUser(user));
    }

    @Disabled
    @ParameterizedTest
    @CsvSource({
            "1,1,2",
            "3,5,8",
            "6,6,12"
    })
    public void test(int a, int b,int expected){
        assertEquals(expected,a+b);
    }



}
