package com.example.tugas1.controller;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.tugas1.model.KecamatanModel;
import com.example.tugas1.model.KeluargaModel;
import com.example.tugas1.model.KelurahanModel;
import com.example.tugas1.model.KotaModel;
import com.example.tugas1.model.PendudukModel;
import com.example.tugas1.service.*;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
@Controller
public class MainController {
	@Autowired
	PendudukService pendudukDAO;
	
	@Autowired
	KeluargaService keluargaDAO;
	
	@Autowired
	KelurahanService kelurahanDAO;
	
	@Autowired
	KecamatanService kecamatanDAO;
	

	@Autowired
	KotaService kotaDAO;
	
	

	 @RequestMapping("/penduduk/ubah/{nik}")
	    public String update(Model model, @PathVariable(value = "nik") String nik){
	    
	   PendudukModel penduduk  = pendudukDAO.selectPenduduk(nik);
	    model.addAttribute("penduduk", penduduk);
		if(nik.equals(null) || penduduk ==null){
			model.addAttribute("message", "Halaman tidak ditemukan.");
		return "not-found";
		}
	    return "ubah-penduduk";
		
	    }
	 
	 @RequestMapping("/penduduk/{nik}")
	    public String viewPenduduk(Model model, @PathVariable(value = "nik") String nik){
	    
	   PendudukModel penduduk  = pendudukDAO.selectPenduduk(nik);
	    model.addAttribute("penduduk", penduduk);
		if(nik.equals(null) || penduduk ==null){
			model.addAttribute("message", "Halaman tidak ditemukan.");
		return "not-found";
		}
	    return "viewPenduduk";
		
	    }
	 
	 private void generateNik(PendudukModel penduduk, String oldNik) throws Exception {
			Date date = new SimpleDateFormat("yyyy-MM-dd").parse(penduduk.getTanggal_lahir());
			String day = new SimpleDateFormat("dd").format(date);
			String month = new SimpleDateFormat("MM").format(date);
			String year = new SimpleDateFormat("YY").format(date);

			day = penduduk.getJenis_kelamin() ==1 ? "" + (Integer.parseInt(day) + 40) : day;
			
			KelurahanModel kelurahan = kelurahanDAO.getKelurahan(keluargaDAO.getKeluarga(penduduk.getId_keluarga()).getId_kelurahan());
			
			String nik = kelurahan.getKode_kelurahan().substring(0, 6) + day + month + year + "0001"; 
			PendudukModel pendudukChecker = pendudukDAO.selectPenduduk(nik); 
			while(pendudukChecker != null && !oldNik.equals(nik)) {
				nik = "" + (Long.parseLong(nik) + 1);
				pendudukChecker = pendudukDAO.selectPenduduk(nik);
			}
			penduduk.setNik(nik);
		}
	 
	 private void generateNkk(KeluargaModel keluarga, String oldNkk) throws Exception {
		 	Date date = new Date();
			String day = (new SimpleDateFormat("dd").format(date));
			String month = new SimpleDateFormat("MM").format(date);
			String year = new SimpleDateFormat("YY").format(date);
			
			KelurahanModel kelurahan = kelurahanDAO.getKelurahan(keluarga.getId_kelurahan());			
			String nkk = kelurahan.getKode_kelurahan().substring(0, 6) + day + month + year + "0001"; 
			
			KeluargaModel keluargaChecker = keluargaDAO.selectKeluarga(nkk); 
			while(keluargaChecker != null && !nkk.equals(oldNkk)) {
				nkk = "" + (Long.parseLong(nkk) + 1);
				keluargaChecker = keluargaDAO.selectKeluarga(nkk);
			}
			keluarga.setNomor_kk(nkk);
		}
	 
	 @RequestMapping("/penduduk/cari")
	    public String searchPenduduk(Model model, @RequestParam(value = "kt", required = false) BigInteger kota, @RequestParam(value = "kc", required = false) BigInteger kecamatan, @RequestParam(value = "kl", required = false) BigInteger kelurahan) throws ParseException{
	    
		 if(kota==null) {
			 List<KotaModel> cities =  kotaDAO.getAllKota();
			 model.addAttribute("cities", cities);
			 return "cari-penduduk";
		 }
		 else if(kecamatan == null) {
			 KotaModel selectedCity = kotaDAO.getKota(kota);
			 List<KecamatanModel> districts = kecamatanDAO.getAllKecamatan(kota);
			 model.addAttribute("selectedCity", selectedCity);
			 model.addAttribute("districts", districts);
			 return "cari-penduduk";
		 }
		 else if(kelurahan == null) {
			 KotaModel selectedCity = kotaDAO.getKota(kota);
			 KecamatanModel selectedDistrict = kecamatanDAO.getKecamatan(kecamatan);
			 List<KelurahanModel> villages = kelurahanDAO.getAllKelurahanByKC(kecamatan);
			
			 model.addAttribute("selectedCity", selectedCity);
			 model.addAttribute("selectedDistrict", selectedDistrict);
			 model.addAttribute("villages", villages);
			 return "cari-penduduk";
		 }
		 else {
			List<PendudukModel> listPenduduk = pendudukDAO.getPendudukByKL(kelurahan);
			PendudukModel old = pendudukDAO.getOldestPenduduk(kelurahan);
			PendudukModel young = pendudukDAO.getYoungestPenduduk(kelurahan);
			 Date tanggal_old = new SimpleDateFormat("yyyy-MM-dd").parse(old.getTanggal_lahir());
			   String formatted = new SimpleDateFormat("dd MMMM yyyy").format(tanggal_old);
			   old.setTanggal_lahir(formatted);
			   Date tanggal_young = new SimpleDateFormat("yyyy-MM-dd").parse(young.getTanggal_lahir());
			   formatted = new SimpleDateFormat("dd MMMM yyyy").format(tanggal_young);
			   young.setTanggal_lahir(formatted);
			KelurahanModel kel = kelurahanDAO.getKelurahan(kelurahan);
			KecamatanModel kec = kecamatanDAO.getKecamatan(kel.getId_kecamatan());
			KotaModel kot = kotaDAO.getKota(kec.getId_kota());
		
			model.addAttribute("kel", kel);
			model.addAttribute("kec", kec);
			model.addAttribute("kot", kot);
			model.addAttribute("old", old);
			model.addAttribute("young", young);
			model.addAttribute("listPenduduk", listPenduduk);
			 return "result-cari-penduduk";
		 }
	    }
	 
	 @RequestMapping("/penduduk")
	    public String lihatPenduduk(Model model, @RequestParam(value = "nik") String nik) throws ParseException{
	    
	   PendudukModel penduduk  = pendudukDAO.selectPenduduk(nik);
	   if(nik.equals(null) || penduduk ==null ){
		   model.addAttribute("message", "Halaman tidak ditemukan.");
			return "not-found";
			}
	   Date tanggal = new SimpleDateFormat("yyyy-MM-dd").parse(penduduk.getTanggal_lahir());
	   String formatted = new SimpleDateFormat("dd MMMM yyyy").format(tanggal);
	   penduduk.setTanggal_lahir(formatted);
	    model.addAttribute("penduduk", penduduk);
		
		KeluargaModel keluarga = keluargaDAO.getKeluarga(penduduk.getId_keluarga());
		model.addAttribute("keluarga", keluarga);
		KelurahanModel kelurahan = kelurahanDAO.getKelurahan(keluarga.getId_kelurahan());
		model.addAttribute("kelurahan", kelurahan);
		KecamatanModel kecamatan = kecamatanDAO.getKecamatan(kelurahan.getId_kecamatan());
		model.addAttribute("kecamatan", kecamatan);
		KotaModel kota =kotaDAO.getKota(kecamatan.getId_kota());
		model.addAttribute("kota", kota);
		String kewarganegaraan = "WNA";
		if(penduduk.getIs_wni()==true) {
			kewarganegaraan = "WNI";
			
		}
		model.addAttribute("kewarganegaraan", kewarganegaraan);
		String status = "Hidup";
		if(penduduk.getIs_wafat()==true) {
			status = "Wafat";
		}
		model.addAttribute("status", status);
	    return "viewPenduduk";
		
	    }
	 
	 
	 @RequestMapping("/")
	    public String index(){
		 return "index";
		
	    }
	 
	 @RequestMapping("/penduduk/tambah")
	    public String addPenduduk(Model model){
		 model.addAttribute("penduduk", new PendudukModel());
		 return "add-penduduk";
		
	    }

	 @RequestMapping(value = "/penduduk/mati", method = RequestMethod.POST)
	    public String matikanPenduduk(Model model, @RequestParam(value = "nik") String nik){
		 PendudukModel penduduk  = pendudukDAO.selectPenduduk(nik);
		 penduduk.setIs_wafat(true);
		 pendudukDAO.updatePenduduk(penduduk);
		 KeluargaModel keluarga = keluargaDAO.getKeluarga(penduduk.getId_keluarga());
		 keluarga = keluargaDAO.selectKeluarga(keluarga.getNomor_kk());
		 boolean isAktif = false;
		 for(PendudukModel saudara : keluarga.getMembers()) {
			 if(!saudara.getIs_wafat()) {
				 isAktif = true;
				 break;
			 }
		 }
		 if(!isAktif) {
			 keluarga.setIs_tidak_berlaku(true);
			 keluargaDAO.updateKeluarga(keluarga);
		 }
			model.addAttribute("message", "Penduduk dengan NIK " + penduduk.getNik() + " sudah tidak aktif.");
		 return "success-update";
		
	    }
	 @RequestMapping("/keluarga/tambah")
	    public String addKeluarga(Model model){
		 model.addAttribute("kelurahans", kelurahanDAO.getAllKelurahan());
		 model.addAttribute("allKecamatan", kecamatanDAO.fetchAllKecamatan());
		 model.addAttribute("allKota", kotaDAO.getAllKota());
		 model.addAttribute("keluarga", new KeluargaModel());
		 return "add-keluarga";
		
	    }
	 @RequestMapping(value = "/keluarga/tambah" , method = RequestMethod.POST)
	    public String tambahKeluarga(@ModelAttribute ("keluarga") KeluargaModel keluarga, Model model) throws Exception{
			if(keluarga == null){
				model.addAttribute("message", "Gagal menambahkan keluarga.");
				return "not-found";
			}
			else{
		 	generateNkk(keluarga, "");
			model.addAttribute("message", "Keluraga dengan NKK " + keluarga.getNomor_kk() + " berhasil ditambah.");
			keluarga.setIs_tidak_berlaku(false);
			keluargaDAO.addKeluarga(keluarga);
			return "success-update";
			}
	    }
	 @RequestMapping("/keluarga")
	    public String viewKeluarga(Model model, @RequestParam(value = "nkk") String nkk){
	    
		 KeluargaModel keluarga  = keluargaDAO.selectKeluarga(nkk);
		 if(nkk==null || keluarga ==null){
			 model.addAttribute("message", "Halaman tidak ditemukan.");
				return "not-found";
				}
		 KelurahanModel kelurahan = kelurahanDAO.getKelurahan(keluarga.getId_kelurahan());
		KecamatanModel kecamatan = kecamatanDAO.getKecamatan(kelurahan.getId_kecamatan());
		KotaModel kota = kotaDAO.getKota(kecamatan.getId_kota());
		
	    model.addAttribute("keluarga", keluarga);
	    model.addAttribute("kelurahan", kelurahan);
	    model.addAttribute("kecamatan", kecamatan);
	    model.addAttribute("kota", kota);
		
	    return "viewKeluarga";
		
	    }
	 
	@RequestMapping(value = "/penduduk/ubah/{nik}" , method = RequestMethod.POST)
    public String updateSubmit(@ModelAttribute ("penduduk") PendudukModel penduduk, @PathVariable(value = "nik") String nik, Model model) throws Exception{
		
		generateNik(penduduk, nik);
		pendudukDAO.updatePenduduk(penduduk);
		model.addAttribute("message", "Penduduk dengan NIK " + nik + " berhasil diubah.");
		return "success-update";
    }
		
	@RequestMapping(value = "/penduduk/tambah" , method = RequestMethod.POST)
    public String addPenduduk(@ModelAttribute ("student") PendudukModel penduduk, Model model) throws Exception{
		
		KeluargaModel fam = keluargaDAO.getKeluarga(penduduk.getId_keluarga());
		if(fam == null){
		model.addAttribute("message", "Gagal menambahkan penduduk. Keluarga dengan ID " + penduduk.getId_keluarga() + " tidak ditemukan.");
			return "not-found";
		}else{
		generateNik(penduduk, "");
		pendudukDAO.addPenduduk(penduduk);
		model.addAttribute("message", "Penduduk dengan NIK " + penduduk.getNik() + " berhasil ditambahkan.");
		
		return "success-update";
		}
    }
	
	@RequestMapping("/keluarga/ubah/{nomor_kk}")
    public String ubahKeluarga(Model model, @PathVariable(value = "nomor_kk") String nomor_kk){
    
   KeluargaModel keluarga  = keluargaDAO.selectKeluarga(nomor_kk);
	if(nomor_kk == null || keluarga ==null){
		model.addAttribute("message", "Halaman tidak ditemukan.");
	return "not-found";
	}
    model.addAttribute("keluarga", keluarga);

	 model.addAttribute("kelurahans", kelurahanDAO.getAllKelurahan());
	 
	 model.addAttribute("allKecamatan", kecamatanDAO.fetchAllKecamatan());
	 model.addAttribute("allKota", kotaDAO.getAllKota());

    return "ubah-keluarga";
	
    }
	
	@RequestMapping(value = "/keluarga/ubah/{nomor_kk}" , method = RequestMethod.POST)
    public String updateKeluarga(@ModelAttribute ("keluarga") KeluargaModel keluarga, @PathVariable(value = "nomor_kk") String nomor_kk, Model model) throws Exception{
		String old = keluarga.getNomor_kk();
		model.addAttribute("old", old);
		generateNkk(keluarga, nomor_kk);
		model.addAttribute("message", "Keluraga dengan NKK " + old + " berhasil diubah.");
		keluargaDAO.updateKeluarga(keluarga);
		keluarga = keluargaDAO.selectKeluarga(keluarga.getNomor_kk());
		System.out.println(keluarga.getMembers());
		for(PendudukModel penduduk : keluarga.getMembers()) {
			generateNik(penduduk, penduduk.getNik());
		}
		return "success-update";
    }
}
