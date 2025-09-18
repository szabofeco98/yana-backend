package com.fec.yana.common.mapper;

import com.fec.yana.common.dto.exceptions.BaseException;
import com.fec.yana.common.dto.response.BaseErrorResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(config = BaseMapperConfig.class)
public interface BaseErrorResponseMapper {
    BaseErrorResponseMapper INSTANCE = Mappers.getMapper( BaseErrorResponseMapper.class );

    @Mappings({
        @Mapping(source = "httpStatus", target = "status"),
        @Mapping(source = "errorCode", target = "errorCode"),
        @Mapping(source = "message", target = "message")
    })
    BaseErrorResponse toBaseErrorResponse(BaseException e);

}
