package com.example.tugas1.model;
import java.math.BigInteger;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KelurahanModel {
	private BigInteger id;
	private String kode_kelurahan;
	private BigInteger id_kecamatan;
	private String nama_kelurahan;
	private String kode_pos;
	
	
	

}
