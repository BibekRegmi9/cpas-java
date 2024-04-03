package org.example.cpas.converter;
import org.example.cpas.dto.RoleDto;
import org.example.cpas.dto.UserDto;
import org.example.cpas.entities.User;
import org.example.cpas.entities.UserRoleMapping;
import org.springframework.beans.BeanUtils;

import java.util.stream.Collectors;

public class DtoConverter {

    public static UserDto convert(User entity){
        if(entity == null)
            return null;

        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(entity, userDto);
        userDto.setRoleDtos(entity.getRoles().stream().map(DtoConverter::convert).collect(Collectors.toList()));
        return  userDto;
    }

    public static RoleDto convert(UserRoleMapping entity){
        if(entity == null)
            return null;

        RoleDto roleDto = new RoleDto();
        BeanUtils.copyProperties(entity.getRole(), roleDto);
        return  roleDto;
    }

}
