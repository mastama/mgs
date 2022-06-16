package com.test.mgs.repository;

import com.test.mgs.model.Mahasiswa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MahasiswaRepository extends JpaRepository<Mahasiswa, Long> {
    List<Mahasiswa> findByNama(String nama);
    List<Mahasiswa> findByFakultas(String fakultas);
}
