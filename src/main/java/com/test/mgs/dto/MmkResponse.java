package com.test.mgs.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MmkResponse {
    private String nama;
    private String nim;
    private String jurusan;
    private String fakultas;
    private String mataKuliah;
}
