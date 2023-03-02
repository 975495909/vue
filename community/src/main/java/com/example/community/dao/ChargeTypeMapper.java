package com.example.community.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.community.domain.ChargeType;
import com.example.community.domain.Resources;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ChargeTypeMapper extends BaseMapper<ChargeType> {
}
