package com.example.tugas1.model;

import java.math.BigInteger;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KeluargaModel {
	private BigInteger id;
	private String nomor_kk; 
	private String alamat;
	private String rt;
	private String rw;
	private BigInteger id_kelurahan;
	private Boolean is_tidak_berlaku;
	List<PendudukModel> members;

	public void setRT(String rt) {
		this.rt = rt;
	}
	
	public void setRW(String rw) {
		this.rw = rw;
	}
	
	public void setRt(String rt) {
		this.rt = rt;
	}
	
	public void setRw(String rw) {
		this.rw = rw;
	}
	
	public String getRT() {
		
		return this.rt;
	
	}
	public String getRW() {
		return this.rw;
	}
	
	public String getRt() {
		
		return this.rt;
	
	}
	public String getRw() {
		return this.rw;
	}
}
