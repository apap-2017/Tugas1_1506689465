package com.example.tugas1.dao;

import java.math.BigInteger;
import java.util.List;

import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.tugas1.model.*;
@Mapper
public interface KotaMapper {
	@Select("select * from kota where id = #{id_kota}")
    KotaModel getKota(@Param("id_kota") BigInteger id_kota);
	
	
	@Select("select * from kota")
    List <KotaModel> getAllKota();
}
