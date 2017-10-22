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
public interface KelurahanMapper {
	@Select("select * from kelurahan where id = #{id_kelurahan}")
    KelurahanModel getKelurahan(@Param("id_kelurahan") BigInteger id_kelurahan);
	
	
	@Select("select * from kelurahan")
	List <KelurahanModel> getAllKelurahan();
	
	@Select("select * from kelurahan where id_kecamatan = #{id_kecamatan}")
    List<KelurahanModel> getAllKelurahanByKC(@Param("id_kecamatan") BigInteger id_kecamatan);
}
