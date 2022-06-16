package com.test.mgs.controller;

import com.test.mgs.model.MataKuliah;
import com.test.mgs.repository.MahasiswaRepository;
import com.test.mgs.repository.MataKuliahRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class MataKuliahController {

    @Autowired
    private MataKuliahRepository mataKuliahRepository;
    @Autowired
    private MahasiswaRepository mahasiswaRepository;

    @GetMapping("/mahasiswa/{mahasiswaId}/mataKuliah")
    public ResponseEntity<List<MataKuliah>> getAllMataKuliahByMahasiswaId(@PathVariable(value = "mahasiswaId") Long mahasiswaId) {
        if (!mahasiswaRepository.existsById(mahasiswaId)) {
            throw new ResourceAccessException("Not found Mahasiswa with id = " + mahasiswaId);
        }
        List<MataKuliah> mataKuliahs = mataKuliahRepository.findByMahasiswaId(mahasiswaId);
        return new ResponseEntity<>(mataKuliahs, HttpStatus.OK);
    }

    @GetMapping("/mataKuliah/{id}")
    public ResponseEntity<MataKuliah> getMataKuliahByMahasiswaId(@PathVariable(value = "id") Long id) {
        MataKuliah mataKuliah = mataKuliahRepository.findById(id)
                .orElseThrow(() -> new ResourceAccessException("Not Found MataKuliah with id = " + id));
        return new ResponseEntity<>(mataKuliah, HttpStatus.OK);
    }

    @PostMapping("/mahasiswa/{mahasiswaId}/mataKuliah")
    public ResponseEntity<MataKuliah> createMataKuliah(@PathVariable(value = "mahasiswaId") Long mahasiswaId, @RequestBody MataKuliah mataKuliahRequest) {
        MataKuliah mataKuliah = mahasiswaRepository.findById(mahasiswaId).map(mahasiswa -> {
            mataKuliahRequest.setMahasiswa(mahasiswa);
            return mataKuliahRepository.save(mataKuliahRequest);
        }).orElseThrow(()-> new ResourceAccessException("Not found Mahasiswa with id = " + mahasiswaId));
        return new ResponseEntity<>(mataKuliah, HttpStatus.CREATED);
    }

    @PutMapping("/mataKuliah/{id}")
    public ResponseEntity<MataKuliah> updateComment(@PathVariable("id") long id, @RequestBody MataKuliah mataKuliahRequest) {
        MataKuliah mataKuliah = mataKuliahRepository.findById(id)
                .orElseThrow(() -> new ResourceAccessException("MataKuliahId " + id + "not found"));
        mataKuliah.setMataKuliah(mataKuliah.getMataKuliah());
        return new ResponseEntity<>(mataKuliahRepository.save(mataKuliah), HttpStatus.OK);
    }

    @DeleteMapping("/mahasiswa/{mahasiswaId}/mataKuliah")
    public ResponseEntity<List<MataKuliah>> deleteAllMataKuliahOfMahasiswa(@PathVariable(value = "mahasiswaId") Long mahasiswaId) {
        if (!mahasiswaRepository.existsById(mahasiswaId)) {
            throw new ResourceAccessException("Not found Mahasiswa with id = " + mahasiswaId);
        }
        mataKuliahRepository.deleteByMahasiswaId(mahasiswaId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
