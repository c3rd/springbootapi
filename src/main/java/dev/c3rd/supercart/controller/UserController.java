package dev.c3rd.supercart.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import dev.c3rd.supercart.model.User;
import dev.c3rd.supercart.service.IUserService;
import dev.c3rd.supercart.utils.UserModelAssembler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final IUserService userService;
    private final UserModelAssembler assembler;

    public UserController(IUserService userService, UserModelAssembler assembler) {
        this.userService = userService;
        this.assembler = assembler;
    }

    @GetMapping
    public CollectionModel<EntityModel<User>> all() {
        List<EntityModel<User>> users = userService.all().stream().map(assembler::toModel).collect(Collectors.toList());

        return CollectionModel.of(users, linkTo(methodOn(UserController.class).all()).withSelfRel());
    }

    @GetMapping("{id}")
    public EntityModel<User> one(@PathVariable Long id) {
        User user = userService.one(id);

        return assembler.toModel(user);
    }

    @PostMapping
    public ResponseEntity<?> store(@RequestBody User user) {
        EntityModel<User> newUser = assembler.toModel(userService.store(user));

        return ResponseEntity.created(newUser.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(newUser);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> update(@RequestBody User user, @PathVariable Long id) {

        User updatedUser = userService.update(user, id);

        EntityModel<User> updatedUserModel = assembler.toModel(updatedUser);

        return ResponseEntity.noContent().build();

    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        userService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
