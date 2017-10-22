package com.example.tugas1.service;


import java.math.BigInteger;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tugas1.model.*;

import lombok.extern.slf4j.Slf4j;

import com.example.tugas1.dao.*;

@Slf4j
@Service
public class KecamatanServiceDatabase implements KecamatanService{
	@Autowired
    private KecamatanMapper kecamatanMapper;
	
	public KecamatanModel getKecamatan(BigInteger id_kecamatan){
		return kecamatanMapper.getKecamatan(id_kecamatan);
	}
	public List<KecamatanModel> getAllKecamatan(BigInteger id_kota){
		return kecamatanMapper.getAllKecamatan(id_kota);
	}
	public List <KecamatanModel> fetchAllKecamatan(){
		return kecamatanMapper.fetchAllKecamatan();
	}
}
