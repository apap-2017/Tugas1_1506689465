package com.example.tugas1.model;
import java.math.BigInteger;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KotaModel {
	private BigInteger id;
	private String kode_kota;
	private String nama_kota;
	List<KecamatanModel> listKecamatan;
	
}
