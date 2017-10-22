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
public interface PendudukMapper {
	@Select("select * from penduduk where nik = #{nik}")
    PendudukModel selectPenduduk(@Param("nik") String nik);

	
	@Update("UPDATE penduduk SET "
			+ "nik=#{nik}, nama=#{nama}, tempat_lahir=#{tempat_lahir}, "
			+ "tanggal_lahir=#{tanggal_lahir}, jenis_kelamin=#{jenis_kelamin}, "
			+ "is_wni=#{is_wni}, id_keluarga=#{id_keluarga}, agama=#{agama}, "
			+ "pekerjaan=#{pekerjaan}, status_perkawinan=#{status_perkawinan},"
			+ "status_dalam_keluarga=#{status_dalam_keluarga}, "
			+ "golongan_Darah=#{golongan_darah}, is_wafat=#{is_wafat} "
			+ "WHERE id=#{id}")
    void updatePenduduk(PendudukModel penduduk);
	
	@Insert("insert into penduduk (nik, nama, tempat_lahir, tanggal_lahir, jenis_kelamin," +
			"is_wni, id_keluarga, agama, pekerjaan, status_perkawinan, status_dalam_keluarga," + 
			"golongan_darah, is_wafat) VALUES (#{nik}, #{nama}, #{tempat_lahir}, #{tanggal_lahir}," +
			"#{jenis_kelamin}, #{is_wni}, #{id_keluarga}, #{agama}, #{pekerjaan}, #{status_perkawinan}," +
			"#{status_dalam_keluarga}, #{golongan_darah}, #{is_wafat})")
	void addPenduduk(PendudukModel penduduk);
	
	@Select("select penduduk.* from penduduk join keluarga where penduduk.id_keluarga = keluarga.id AND keluarga.id_kelurahan = #{id_kelurahan}")
    List<PendudukModel> getPendudukByKL(@Param("id_kelurahan") BigInteger id_kelurahan);
	
	@Select("select penduduk.* from penduduk join keluarga where penduduk.id_keluarga = keluarga.id AND keluarga.id_kelurahan = #{id_kelurahan} ORDER BY tanggal_lahir ASC LIMIT 1")
    PendudukModel getOldestPenduduk(@Param("id_kelurahan") BigInteger id_kelurahan);
	
	@Select("select penduduk.* from penduduk join keluarga where penduduk.id_keluarga = keluarga.id AND keluarga.id_kelurahan = #{id_kelurahan} ORDER BY tanggal_lahir DESC LIMIT 1")
	PendudukModel getYoungestPenduduk(@Param("id_kelurahan") BigInteger id_kelurahan);
}
