package com.sqa.project_sqa.payload.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DanhSachThueMuonDongDTO {

    private int loaiThueId;

    private int idThue;
}
