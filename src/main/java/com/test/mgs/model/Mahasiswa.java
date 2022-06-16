package com.test.mgs.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "mahasiswa")
public class Mahasiswa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nama")
    private String nama;

    @Column(name = "jurusan")
    private String jurusan;

    @Column(name = "fakultas")
    private String fakultas;

    // buat connect model dari parent(mapped by child)
//    @OneToMany(mappedBy = "mahasiswa", cascade = CascadeType.PERSIST)
//    private List<MataKuliah> mataKuliah;
}
