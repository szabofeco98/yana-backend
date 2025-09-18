package com.fec.yana.authentication.mapper.entity;

import com.fec.yana.authentication.dto.requests.RegistrationRequest;
import com.fec.yana.authentication.model.SecurityUser;
import com.fec.yana.common.mapper.BaseMapperConfig;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(config = BaseMapperConfig.class)
public interface SecurityUserMapper {
    SecurityUserMapper INSTANCE = Mappers.getMapper(SecurityUserMapper.class);

    @Mappings({
        @Mapping(source = "registrationRequest.username", target = "username"),
        @Mapping(source = "encodedPassword", target = "password")
    })
    SecurityUser toSecurityUser(RegistrationRequest registrationRequest, String encodedPassword);
}
