package com.prachimehar.journalApp.controller;


import com.prachimehar.journalApp.Entity.User;
import com.prachimehar.journalApp.api.response.QuoteResponse;
import com.prachimehar.journalApp.api.response.WeatherResponse;
import com.prachimehar.journalApp.repository.UserRepository;
import com.prachimehar.journalApp.service.QuoteService;
import com.prachimehar.journalApp.service.UserService;
import com.prachimehar.journalApp.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private  WeatherService weatherService;

    @Autowired
    private QuoteService quoteService;

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User userInDb = userService.findByUsername(username);
        userInDb.setUsername(user.getUsername());
        userInDb.setPassword(user.getPassword());
        userService.saveNewUser(userInDb);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userRepository.deleteByUsername(authentication.getName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/weather")
    public ResponseEntity<?> greeting() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        WeatherResponse weatherResponse = weatherService.getWeather("Mumbai");
        String greeting = "";
        if(weatherResponse != null){
            greeting = ", Weather feels like "+ weatherResponse.getCurrent().getFeelslike();
        }
        return new ResponseEntity<>("Hii "+ authentication.getName() + greeting,HttpStatus.OK);
    }

    @GetMapping("/quote")
    public ResponseEntity<?> getQuote() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        QuoteResponse quoteResponse = quoteService.getQuote();

        String greeting;
        if (quoteResponse != null && quoteResponse.getQ() != null) {
            greeting = ", Today's quote is: \"" + quoteResponse.getQ() + "\" — " + quoteResponse.getA();
        } else {
            greeting = ", but we couldn't fetch a quote at the moment.";
        }

        return new ResponseEntity<>("Hi " + authentication.getName() + greeting, HttpStatus.OK);
    }

}
