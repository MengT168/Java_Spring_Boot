package com.meng.java.phoneshopnight.phoneshopnight.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.meng.java.phoneshopnight.phoneshopnight.dto.ColorDTO;
import com.meng.java.phoneshopnight.phoneshopnight.entity.Color;

@Mapper
public interface ColorMapper {
	ColorMapper INSTAND = Mappers.getMapper(ColorMapper.class);
	Color toColor(ColorDTO colorDTO);
	ColorDTO toColorDTO(Color color);
}
