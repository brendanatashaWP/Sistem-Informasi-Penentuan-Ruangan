package com.piterpan.sipr.RestCont;

import com.piterpan.sipr.Exception.ResourceNotFoundException2;
import com.piterpan.sipr.Interface.MatakuliahInter;
import com.piterpan.sipr.Model.Matakuliah;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static java.lang.Double.parseDouble;
import static java.lang.Float.parseFloat;

@RestController
@RequestMapping("/api")
public class MatakuliahRestCont {
    @Autowired
    MatakuliahInter matakuliahInter;
    int a = 0;

    //Post matakuliah
    @PostMapping("/post-mk")
    public Matakuliah addMk(@Valid @RequestBody Matakuliah mk){
//        int[] b = new int[mk.getSks()];
//        if (mk.getJamMulai().equals("7,30") || mk.getJamMulai().equals("07,30")){
//            this.a = 0;
//        }
//        if (mk.getJamMulai().equals("8,30") || mk.getJamMulai().equals("08,30")){
//            this.a = 1;
//        }
//        if (mk.getJamMulai().equals("9,30") || mk.getJamMulai().equals("09,30")){
//            this.a = 2;
//        }
//        if (mk.getJamMulai().equals("10,30")){
//            this.a = 3;
//        }
//        if (mk.getJamMulai().equals("11,30")){
//            this.a= 4;
//        }
//        if (mk.getJamMulai().equals("12,30")){
//            this.a= 5;
//        }
//        if (mk.getJamMulai().equals("13,30")){
//            this.a= 6;
//        }
//        if (mk.getJamMulai().equals("14,30")){
//            this.a= 7;
//        }
//        if (mk.getJamMulai().equals("15,30")){
//            this.a= 8;
//        }
//        if (mk.getJamMulai().equals("16,30")){
//            this.a= 9;
//        }
//        if (mk.getJamMulai().equals("17,30")){
//            this.a = 10;
//        }
//        System.out.println(this.a);
//
//        for (int x = 0; x < mk.getSks() ; x++) {
//           if (x == 0){
//               b[x] = this.a;
//               this.a++;
//           }
//           else {
//               b[x] = this.a;
//               this.a++;
//           }
//           System.out.println(b);
//        }
//        this.a=0;
//        mk.setIdxJamTerpakai(b);
        return matakuliahInter.save(mk);
    }

    //Get all mk
    @GetMapping("/get-all-mk/{idKaprodi}")
    public List<Matakuliah> getAllMk(@PathVariable(value = "idKaprodi") int id){
        return matakuliahInter.findMatakuliahsByIdKaprodi(id);
    }

    //Get 1 mk
    @GetMapping("/get-mk/{id}")
    public Matakuliah getOneMk(@PathVariable(value = "id") int id) {
        return matakuliahInter.findById(id)
                .orElseThrow(()->new ResourceNotFoundException2("Matakuliah", "idMk", id));
    }

    //Update mk
    @PutMapping("/put-mk/{id}")
    public Matakuliah updateMk(@PathVariable(value = "id") Integer id, @Valid @RequestBody Matakuliah mk){
        Matakuliah matakuliah = matakuliahInter.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException2("Matakuliah", "idMk", id));
        matakuliah.setJamSelesai(Double.parseDouble(mk.getJamSelesai()));
        matakuliah.setKapasitasMk(mk.getKapasitasMk());
        matakuliah.setKodeGrupFk(mk.getKodeGrupFk());
        matakuliah.setKodeMk(mk.getKodeMk());
        matakuliah.setNamaMk(mk.getNamaMk());
        matakuliah.setSks(mk.getSks());
        matakuliah.setStatusMk(mk.getStatusMk());
        Matakuliah mkBaru = matakuliahInter.save(matakuliah);
        return mkBaru;
    }

    //Delete mk
    @PutMapping("/delete-mk/{id}")
    public Matakuliah deleteMk(@PathVariable(value = "id") Integer id, @Valid @RequestBody Matakuliah mk){
        Matakuliah matakuliah = matakuliahInter.findById(id)
                .orElseThrow(()->new ResourceNotFoundException2("Matakuliah", "idMk", id));
//        matakuliah.setJamSelesai(Double.parseDouble(mk.getJamSelesai()));
        matakuliah.setKapasitasMk(mk.getKapasitasMk());
        matakuliah.setKodeGrupFk(mk.getKodeGrupFk());
        matakuliah.setKodeMk(mk.getKodeMk());
        matakuliah.setNamaMk(mk.getNamaMk());
        matakuliah.setSks(mk.getSks());
        if (mk.getStatusMk().equals("Active")){
            matakuliah.setStatusMk("Non-Active");
        } else {
            matakuliah.setStatusMk("Active");
        }
        Matakuliah mkBaru = matakuliahInter.save(matakuliah);
        return mkBaru;
    }
}
