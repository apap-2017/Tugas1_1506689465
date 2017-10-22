package com.example.tugas1.service;

import java.math.BigInteger;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.example.tugas1.model.KelurahanModel;

public interface KelurahanService {
KelurahanModel getKelurahan(BigInteger id_kelurahan);
List <KelurahanModel> getAllKelurahan();
List<KelurahanModel> getAllKelurahanByKC(BigInteger id_kecamatan);
}
