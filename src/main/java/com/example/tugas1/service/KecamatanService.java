package com.example.tugas1.service;

import java.math.BigInteger;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.example.tugas1.model.KecamatanModel;
import com.example.tugas1.model.KeluargaModel;
import com.example.tugas1.model.KotaModel;
import com.example.tugas1.model.PendudukModel;

public interface KecamatanService {
KecamatanModel getKecamatan(BigInteger id_kecamatan);
List<KecamatanModel> getAllKecamatan( BigInteger id_kota);

List <KecamatanModel> fetchAllKecamatan();
}
