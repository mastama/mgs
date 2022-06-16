package com.test.mgs.controller;

import com.test.mgs.model.Mahasiswa;
import com.test.mgs.repository.MahasiswaRepository;
import org.hibernate.ResourceClosedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.ResourceAccessException;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("api/v1")
public class MahasiswaController {

    @Autowired
    private MahasiswaRepository mahasiswaRepository;

    @GetMapping("/mahasiswa")
    public ResponseEntity<List<Mahasiswa>> getAllMahasiswa(@RequestParam(required = false) String nama) {
        List<Mahasiswa> mahasiswas = new ArrayList<Mahasiswa>();
        if (nama == null)
            mahasiswaRepository.findAll().forEach(mahasiswas::add);
        else
            mahasiswaRepository.findByNama(nama).forEach(mahasiswas::add);
        if (mahasiswas.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(mahasiswas, HttpStatus.OK);
    }

    @GetMapping("/mahasiswa/{id}")
    public ResponseEntity<Mahasiswa> getMahasiswaById(@PathVariable("id") long id) {
        Mahasiswa mahasiswa = mahasiswaRepository.findById(id)
                .orElseThrow(() -> new ResourceAccessException("Not found Tutorial with id = " + id));
        return new ResponseEntity<>(mahasiswa, HttpStatus.OK);
    }

    @PostMapping("/mahasiswa")
    public ResponseEntity<Mahasiswa> createMahasiswa(@RequestBody Mahasiswa mahasiswa) {
        Mahasiswa mahasiswa1 = mahasiswaRepository
                .save(new Mahasiswa(mahasiswa.getId(), mahasiswa.getNama(), mahasiswa.getJurusan(), mahasiswa.getFakultas()));
        return new ResponseEntity<>(mahasiswa1, HttpStatus.CREATED);
    }

    @PutMapping("/mahasiswa/{id}")
    public ResponseEntity<Mahasiswa> updateMahasiswa(@PathVariable("id") long id, @RequestBody Mahasiswa mahasiswa) {
        Mahasiswa mahasiswa1 = mahasiswaRepository.findById(id)
                .orElseThrow(() -> new ResourceAccessException("Not found Tutorial with id = " + id));
        mahasiswa1.setNama(mahasiswa.getNama());
        mahasiswa1.setFakultas(mahasiswa.getFakultas());
        mahasiswa1.setJurusan(mahasiswa.getJurusan());

        return new ResponseEntity<>(mahasiswaRepository.save(mahasiswa1), HttpStatus.OK);
    }

    @DeleteMapping("/mahasiswa/{id}")
    public ResponseEntity<HttpStatus> deleteMahasiswa(@PathVariable("id") long id) {
        mahasiswaRepository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
