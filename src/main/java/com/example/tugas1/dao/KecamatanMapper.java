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
public interface KecamatanMapper {
	
	@Select("select * from kecamatan where id = #{id_kecamatan}")
    KecamatanModel getKecamatan(@Param("id_kecamatan") BigInteger id_kecamatan);
	
	@Select("select * from kecamatan where id_kota = #{id_kota}")
    List<KecamatanModel> getAllKecamatan(@Param("id_kota") BigInteger id_kota);
	
	@Select("select * from kecamatan")
	 List<KecamatanModel> fetchAllKecamatan();
	
	
	
}
