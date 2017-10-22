package com.example.tugas1.service;

import com.example.tugas1.model.KelurahanModel;

import java.math.BigInteger;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tugas1.model.*;
import lombok.extern.slf4j.Slf4j;
import com.example.tugas1.dao.KelurahanMapper;
import com.example.tugas1.dao.PendudukMapper;

@Slf4j
@Service
public class KelurahanServiceDatabase implements KelurahanService {
	@Autowired
    private KelurahanMapper kelurahanMapper;

	public KelurahanModel getKelurahan(BigInteger id_kelurahan){
		return kelurahanMapper.getKelurahan(id_kelurahan);
	}
	public List<KelurahanModel> getAllKelurahan(){
		return kelurahanMapper.getAllKelurahan();
	}
	public List<KelurahanModel> getAllKelurahanByKC(BigInteger id_kecamatan){
		return kelurahanMapper.getAllKelurahanByKC(id_kecamatan);
	}
}
