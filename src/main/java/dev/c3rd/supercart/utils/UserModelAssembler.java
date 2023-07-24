package dev.c3rd.supercart.utils;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import dev.c3rd.supercart.controller.UserController;
import dev.c3rd.supercart.model.User;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class UserModelAssembler implements RepresentationModelAssembler<User, EntityModel<User>> {
    @Override
    public EntityModel<User> toModel(User entity) {
        return EntityModel.of(entity,
                    linkTo(methodOn(UserController.class).one(entity.getId())).withSelfRel(),
                    linkTo(methodOn(UserController.class).all()).withRel("users")
                );
    }
}
