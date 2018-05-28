package com.piterpan.sipr.RestCont;

import com.piterpan.sipr.Exception.ResourceNotFoundException2;
import com.piterpan.sipr.Interface.MatakuliahInter;
import com.piterpan.sipr.Model.Matakuliah;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class MatakuliahRestCont {
    @Autowired
    MatakuliahInter matakuliahInter;

    //Post matakuliah
    @PostMapping("/post-mk")
    public Matakuliah addMk(@Valid @RequestBody Matakuliah mk){
        return matakuliahInter.save(mk);
    }

    //Get all mk
    @GetMapping("/get-mk")
    public List<Matakuliah> getAllMk(){
        return matakuliahInter.findAll();
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
        matakuliah.setJamSelesai(mk.getJamSelesai());
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
        matakuliah.setJamSelesai(mk.getJamSelesai());
        matakuliah.setKapasitasMk(mk.getKapasitasMk());
        matakuliah.setKodeGrupFk(mk.getKodeGrupFk());
        matakuliah.setKodeMk(mk.getKodeMk());
        matakuliah.setNamaMk(mk.getNamaMk());
        matakuliah.setSks(mk.getSks());
        matakuliah.setStatusMk("Non-Active");
        Matakuliah mkBaru = matakuliahInter.save(matakuliah);
        return mkBaru;
    }
}
