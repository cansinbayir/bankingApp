package com.bankingApp.oredata.mapper;

import com.bankingApp.oredata.model.UserDto;
import com.bankingApp.oredata.entity.User;

public class UserMapper {
    public static UserDto mapToUserDto(User user){
        return new UserDto(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getEmail(),
                user.getCreatedAt(),
                user.getUpdatedAt(),
                AccountMapper.mapToAccountDtoSet(user.getAccounts())
        );
    }

    public static User mapToUser(UserDto userDto, String encodedPass) {
        return new User(
                userDto.getId(),
                userDto.getUsername(),
                encodedPass,
                userDto.getEmail(),
                userDto.getCreatedAt(),
                userDto.getUpdatedAt(),
                null
        );
    }
}

