package com.example.tugas1.service;
import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tugas1.model.*;

import lombok.extern.slf4j.Slf4j;
import com.example.tugas1.dao.PendudukMapper;

@Slf4j
@Service
public class PendudukServiceDatabase implements PendudukService{
	 @Autowired
	    private PendudukMapper pendudukMapper;


	    @Override
	    public PendudukModel selectPenduduk (String nik)
	    {
	        
	        return pendudukMapper.selectPenduduk (nik);
	    }
	    
	    @Override
	    public void updatePenduduk (PendudukModel penduduk){
	    	
	    	pendudukMapper.updatePenduduk(penduduk);  
	    	}
	    
	    public void addPenduduk(PendudukModel penduduk){
	    	pendudukMapper.addPenduduk(penduduk);
	    }
	    
	    public List <PendudukModel>   getPendudukByKL(BigInteger id_kelurahan){
	    	return pendudukMapper.getPendudukByKL(id_kelurahan);
	    }
	    
	    public PendudukModel getOldestPenduduk(BigInteger id_kelurahan){
	    	return pendudukMapper.getOldestPenduduk(id_kelurahan);
	    }
	    
	    public PendudukModel getYoungestPenduduk(BigInteger id_kelurahan){
	    	return pendudukMapper.getYoungestPenduduk(id_kelurahan);
	    }
}
