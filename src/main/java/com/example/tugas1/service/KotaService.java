package com.example.tugas1.service;

import java.math.BigInteger;
import java.util.List;

import com.example.tugas1.model.KotaModel;

public interface KotaService {
KotaModel getKota(BigInteger id_kota);
List <KotaModel> getAllKota();
}
