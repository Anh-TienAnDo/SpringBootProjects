package com.sqa.project_sqa.repositories;

import com.sqa.project_sqa.entities.NguoiDongThue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NguoiDongThueRepository extends JpaRepository<NguoiDongThue,String> {
    boolean existsByEmail(String email);
    boolean existsByMst(String mst);
    boolean existsByCCCD(String cccd);
    boolean existsBySdt(String sdt);

    NguoiDongThue findByMst(String mst);
    NguoiDongThue findById(int id);

}
