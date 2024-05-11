package com.example.infrastructure.mappers;

import com.example.domain.entities.UserDomain;
import com.example.domain.models.UserModel;
import com.example.infrastructure.inputAdapters.dto.RequestDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static com.example.common.utils.Shield.blindStr;

@Component
public class UserMapper {

    public static UserDomain ToDomainFromDto(RequestDto request) {
        UserDomain userDomain = new UserDomain();
        userDomain.setName(blindStr(request.getName()));
        userDomain.setCountry(blindStr(request.getCountry()));
        return userDomain;
    }

    public static UserModel ToDtoFromDomain(UserDomain userDomain) {
        UserModel userModel = new UserModel();
        userModel.setId(userDomain.getId());
        userModel.setName(blindStr(userDomain.getName()));
        userModel.setCountry(blindStr(userDomain.getCountry()));
        return userModel;
    }

    public static List<UserModel> ToDtoListFromToDomainList(List<UserDomain> userDomainList) {
        List<UserModel> userModelList = new ArrayList<>();
        for (UserDomain userDomain : userDomainList) {
            UserModel userModel = ToDtoFromDomain(userDomain);
            userModelList.add(userModel);
        }
        return userModelList;
    }
}
