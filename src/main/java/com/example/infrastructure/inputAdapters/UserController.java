package com.example.infrastructure.inputAdapters;

import com.example.infrastructure.inputAdapters.dto.ResponseDto;
import com.example.infrastructure.inputAdapters.dto.RequestDto;
import com.example.infrastructure.inputPorts.UserInputPort;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.example.common.utils.Constants.USER_NOT_FOUND;
import static com.example.common.utils.Shield.blindLong;
import static com.example.infrastructure.mappers.UserMapper.ToDtoFromDomain;
import static com.example.infrastructure.mappers.UserMapper.ToDtoListFromToDomainList;

@RestController
@RequestMapping("${controller.properties.base-path}/users")
@Tag(name = "User", description = "User Service")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserInputPort userInputPort;

    @Autowired
    public UserController(UserInputPort userInputPort) {
        this.userInputPort = userInputPort;
    }

    @GetMapping
    @Operation(summary = "Get all users", description = "Get all users")
    public ResponseEntity<ResponseDto> getAllUsers() {
        var users = this.userInputPort.getAllUsers();
        if (users == null) {
            logger.warn("Users not found");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            logger.info("Users found count: {}", users.size());
            return new ResponseEntity<>(ResponseDto.builder()
                    .status(200)
                    .message("Users found count: " + users.size())
                    .data(ToDtoListFromToDomainList(users))
                    .build(), HttpStatus.OK);
        }
    }

    @PostMapping
    @Operation(summary = "Create user", description = "Create user")
    public ResponseEntity<ResponseDto> createUser(@RequestBody RequestDto requestDto) {
        var user = this.userInputPort.createUser(requestDto);
        if (user != null) {
            logger.info("User created: {}", user.getId());
            return new ResponseEntity<>(ResponseDto.builder()
                    .status(201)
                    .message("User created: " + user.getId())
                    .data(ToDtoFromDomain(user))
                    .build(), HttpStatus.CREATED);
        } else {
            logger.warn("User not created");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{userId}")
    @Operation(summary = "Find user", description = "Find user by user Id")
    public ResponseEntity<ResponseDto> getUser(@PathVariable Long userId) {
        var user = this.userInputPort.getUserById(blindLong(userId));
        if (user != null) {
            logger.info("User found: {}", user.getId());
            return new ResponseEntity<>(ResponseDto.builder()
                    .status(200)
                    .message("User found: " + user.getId())
                    .data(ToDtoFromDomain(user))
                    .build(), HttpStatus.OK);
        } else {
            logger.warn(USER_NOT_FOUND, userId);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/{userId}")
    @Operation(summary = "Update user", description = "Update user by user Id")
    public ResponseEntity<ResponseDto> updateUser(@PathVariable Long userId, @RequestBody RequestDto requestDto) {
        var user = this.userInputPort.updateUser(blindLong(userId), requestDto);
        if (user != null) {
            logger.info("User updated: {}", user.getId());
            return new ResponseEntity<>(ResponseDto.builder()
                    .status(200)
                    .message("User updated: " + user.getId())
                    .data(ToDtoFromDomain(user))
                    .build(), HttpStatus.OK);
        } else {
            logger.warn(USER_NOT_FOUND, userId);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{userId}")
    @Operation(summary = "Delete user", description = "Delete user by user Id")
    public ResponseEntity<ResponseDto> deleteUser(@PathVariable Long userId) {
        var user = this.userInputPort.deleteUser(blindLong(userId));
        if (user != null) {
            logger.info("User deleted: {}", user.getId());
            return new ResponseEntity<>(ResponseDto.builder()
                    .status(200)
                    .message("User deleted: " + user.getId())
                    .data(ToDtoFromDomain(user))
                    .build(), HttpStatus.OK);
        } else {
            logger.warn(USER_NOT_FOUND, userId);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
