package com.example.tugas1.service;

import java.math.BigInteger;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.example.tugas1.model.*;

public interface PendudukService {
	PendudukModel selectPenduduk (String nik);
	 void updatePenduduk (PendudukModel penduduk);
	 void addPenduduk(PendudukModel penduduk);
	 List<PendudukModel> getPendudukByKL(BigInteger id_kelurahan);
	 PendudukModel getOldestPenduduk(BigInteger id_kelurahan);
	 PendudukModel getYoungestPenduduk(BigInteger id_kelurahan);
}
