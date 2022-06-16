package com.test.mgs.repository;

import com.test.mgs.model.MataKuliah;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface MataKuliahRepository extends JpaRepository<MataKuliah, Long> {
    List<MataKuliah> findByMahasiswaId(Long postId);

    @Transactional
    void deleteByMahasiswaId(long mahasiswaId);
}
