package com.fec.yana.authentication.mapper.response;

import com.fec.yana.authentication.dto.response.RegistrationResponse;
import com.fec.yana.authentication.model.SecurityUser;
import com.fec.yana.common.mapper.BaseMapperConfig;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(config = BaseMapperConfig.class)
public interface RegistrationResponseMapper {
    RegistrationResponseMapper INSTANCE = Mappers.getMapper(RegistrationResponseMapper.class);

    @Mappings({
        @Mapping(source = "userId", target = "userId"),
        @Mapping(source = "username", target = "username")
    })
    RegistrationResponse toRegistrationResponse(SecurityUser securityUser);
}
