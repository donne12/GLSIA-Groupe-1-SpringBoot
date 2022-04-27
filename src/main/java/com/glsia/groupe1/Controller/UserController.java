package com.glsia.groupe1.Controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.glsia.groupe1.models.Role;
import com.glsia.groupe1.models.User;
import com.glsia.groupe1.repository.UserRepository;
import com.glsia.groupe1.service.UserService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    private UserRepository userRepository;


    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<User>> getAllUser(){
       /* List<User> userList = userService.showAll();
        return new ResponseEntity<>(userList, HttpStatus.OK);*/
        return  ResponseEntity.ok().body(userService.showAll());
    }

    @GetMapping(value = "/find/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getOneUser(@PathVariable("id") int id){
        User user = userService.find(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> addOneUser(@RequestBody User user){
       /* userService.save(user);
        return new ResponseEntity<>(HttpStatus.CREATED);*/
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/users/add").toUriString());
        return  ResponseEntity.created(uri).body(userService.save(user));
    }

    @PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> updateUser(@RequestBody User user){
       /* userService.save(user);
        return new ResponseEntity<>(HttpStatus.OK);*/
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/users/update").toUriString());
        return  ResponseEntity.created(uri).body(userService.save(user));
    }

    @PostMapping(value = "/addtouser", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addRoleToUser(@RequestBody RoleToUserForm form){
        userService.addRoleToUser(form.getUsername(), form.getRolename());
        return  ResponseEntity.ok().build();
    }

    @GetMapping(value = "/token/refresh", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void refresh_token(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){
            try {
                String refresh_token = authorizationHeader.substring("Bearer ".length());
                Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(refresh_token);
                String username = decodedJWT.getSubject();
                User user = userRepository.findByUsername(username);

                String access_token = JWT.create()
                        .withSubject(user.getUsername())
                        .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim("roles",user.getRoles().stream().map(Role::getName).collect(Collectors.toList()))
                        .sign(algorithm);

                Map<String,String> tokens = new HashMap<>();
                tokens.put("access_token",access_token);
                tokens.put("refresh_token",refresh_token);
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(),tokens);

            }catch (Exception e){
                response.setHeader("error",e.getMessage());
                response.setStatus(FORBIDDEN.value());
                //response.sendError(FORBIDDEN.value());
                Map<String,String> error = new HashMap<>();
                error.put("error_message",e.getMessage());
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(),error);

            }

        }else{
            throw  new RuntimeException("refresh token are missing ...");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable("id") int id){
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Data
    class RoleToUserForm{
        private String username;
        private String rolename;
    }


}
