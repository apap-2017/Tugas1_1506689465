package com.example.tugas1.service;


import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tugas1.model.*;

import lombok.extern.slf4j.Slf4j;

import com.example.tugas1.dao.*;

@Slf4j
@Service
public class KotaServiceDatabase implements KotaService {
	@Autowired
    private KotaMapper kotaMapper;
	
	public KotaModel getKota(BigInteger id_kota){
		return kotaMapper.getKota(id_kota);
	}
	public List<KotaModel> getAllKota(){
		return kotaMapper.getAllKota();
	}
	
}
