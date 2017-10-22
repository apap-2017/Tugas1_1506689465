package com.example.tugas1.service;

import java.math.BigInteger;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tugas1.model.*;

import lombok.extern.slf4j.Slf4j;

import com.example.tugas1.dao.*;

@Slf4j
@Service
public class KeluargaServiceDatabase implements KeluargaService{
	@Autowired
    private KeluargaMapper keluargaMapper;
	
	public KeluargaModel getKeluarga(BigInteger id_keluarga){
		return keluargaMapper.getKeluarga(id_keluarga);
	}
	
	 @Override
	    public KeluargaModel selectKeluarga(String nomor_kk)
	    {
	        return keluargaMapper.selectKeluarga (nomor_kk);
	    }
	 
	 public void addKeluarga(KeluargaModel keluarga) {
		 
		 keluargaMapper.addKeluarga(keluarga);
	 }
	 
	 public void updateKeluarga(KeluargaModel keluarga) {
		 keluargaMapper.updateKeluarga(keluarga);
	 }
	 
	 public void nonaktifKeluarga(BigInteger id){
		 keluargaMapper.nonaktifKeluarga(id);
	 }
}
