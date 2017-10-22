package com.example.tugas1.service;

import java.math.BigInteger;

import org.apache.ibatis.annotations.Param;

import com.example.tugas1.model.KeluargaModel;
public interface KeluargaService {
	

KeluargaModel getKeluarga(BigInteger id_keluarga);
KeluargaModel selectKeluarga (String nomor_kk);
void addKeluarga(KeluargaModel keluarga);
void updateKeluarga(KeluargaModel keluarga);
void nonaktifKeluarga(BigInteger id);
}
