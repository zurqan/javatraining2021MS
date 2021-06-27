package com.training.session2.adapter.rest;

import com.training.session2.adapter.rest.dto.UserDTO;
import com.training.session2.model.User;
import com.training.session2.adaper.repository.UserManagement;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserManagement userManagement;

    public UserController(UserManagement userManagement) {

        System.out.println("userManagement.getClass().getSimpleName() = " + userManagement.getClass().getSimpleName());
        this.userManagement = userManagement;
    }

    @PostMapping
    public String savingUser(@RequestBody @Valid UserDTO user){

        return userManagement.save(toUserModel().apply(user));
    }

    @PutMapping("/{id}")
    public boolean updating(@PathVariable String id,@RequestBody UserDTO userDTO){
        return userManagement.update(toUserModel().apply(userDTO));
    }

    @DeleteMapping("/{id}")
    public boolean removing(@PathVariable String id){
        return userManagement.remove(id);
    }

    @GetMapping("/{id}")
    public UserDTO loadingUser(@PathVariable String id) {

        return userManagement
                .byId(id)
                .map(toUserDto())
                .orElseThrow(() -> new RuntimeException("Not found"));
    }

    @GetMapping
    public List<UserDTO> users(){
        return userManagement
                .all()
                .stream()
                .map(toUserDto())
                .collect(Collectors.toList());
    }
    private Function<User, UserDTO> toUserDto() {
        return user -> UserDTO
                .builder()
                .userAge(user.getAge())
                .birthDay(user.getBirthDay())
                .id(user.getId())
                .name(user.getFirstName()+" "+user.getLastName())
                .build();
    }

    private  Function<UserDTO,User> toUserModel() {

        return dto->User
                .builder()
                .firstName(dto.getName())
                .age(dto.getUserAge())
                .id(dto.getId())
                .build();
    }


}
