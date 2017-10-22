package com.example.tugas1.model;

import java.math.BigInteger;
import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KecamatanModel {
	private BigInteger id;
	private String kode_kecamatan; 
	private String nama_kecamatan;
	private BigInteger id_kota;
	List<KelurahanModel> listKelurahan; 
	
}
