package com.example.tugas1.dao;
import java.math.BigInteger;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.tugas1.model.*;
@Mapper
public interface KeluargaMapper {
	
	@Select("select * from keluarga where id = #{id_keluarga}")
    KeluargaModel getKeluarga(@Param("id_keluarga") BigInteger id_keluarga);
	
	
	@Select("select * from penduduk where penduduk.id_keluarga = #{id}")
    List<PendudukModel> selectMembers (@Param("id") String id);
    
    
    @Select("select * from keluarga where nomor_kk = #{nomor_kk}")
    @Results(value = {
    		@Result(property = "id", column ="id"),
    		@Result(property = "nomor_kk", column = "nomor_kk"),
    		@Result(property = "alamat", column = "alamat"),
    		@Result(property = "RT", column = "RT"),
    		@Result(property = "RW", column = "RW"),
    		@Result(property = "members", column ="id" ,
    				javaType = List.class, 
    			many = @Many(select = "selectMembers"))
    })
    KeluargaModel selectKeluarga (@Param("nomor_kk") String nomor_kk);
    
    
    @Insert("insert into keluarga (nomor_kk, alamat, rt, rw, id_kelurahan, is_tidak_berlaku) "
			+ "VALUES (#{nomor_kk}, #{alamat}, #{rt}, #{rw}, #{id_kelurahan}, #{is_tidak_berlaku})")
	void addKeluarga (KeluargaModel keluarga);
    
    
    @Update("UPDATE keluarga SET "
			+ "nomor_kk=#{nomor_kk}, alamat=#{alamat}, rt=#{rt}, "
			+ "rw=#{rw}, id_kelurahan=#{id_kelurahan}, "
			+ "is_tidak_berlaku=#{is_tidak_berlaku} "
			+ "WHERE id=#{id}")
    void updateKeluarga(KeluargaModel keluarga);
    
    @Update("UPDATE keluarga SET is_tidak_berlaku = true  WHERE id = #{id}")
    void nonaktifKeluarga(@Param("id") BigInteger id);

}

