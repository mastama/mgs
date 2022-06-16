package com.test.mgs.dto;

import com.test.mgs.model.MataKuliah;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MahasiswaDto {

    private Long id;
    private String nama;
    private String jurusan;
    private String fakultas;

}
