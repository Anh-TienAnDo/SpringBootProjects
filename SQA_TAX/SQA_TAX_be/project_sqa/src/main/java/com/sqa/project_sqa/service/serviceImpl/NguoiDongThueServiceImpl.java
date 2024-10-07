package com.sqa.project_sqa.service.serviceImpl;

import com.sqa.project_sqa.entities.NguoiDongThue;
import com.sqa.project_sqa.entities.User;
import com.sqa.project_sqa.payload.dto.NguoiDongThueDTO;
import com.sqa.project_sqa.repositories.NguoiDongThueRepository;
import com.sqa.project_sqa.service.NguoiDongThueService;
import com.sqa.project_sqa.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

@Service
public class NguoiDongThueServiceImpl implements NguoiDongThueService {
    @Autowired
    private NguoiDongThueRepository nguoiDongThueRepository;


    public List<NguoiDongThue> getAll() {
        return nguoiDongThueRepository.findAll();
    }

    @Override
    public NguoiDongThue getNguoiDongThueByMaSoThue(String mst) {
        return nguoiDongThueRepository.findByMst(mst);
    }


    public boolean testNotAcceptMst(List<String> mstList) {
        Pattern pattern = Pattern.compile("^[0-9]{10}$|^[0-9]{13}$");

        for (String mst : mstList) {
            // Kiểm tra nếu mst khớp với mẫu và là một số dương
            if (pattern.matcher(mst).matches()) {
                try {
                    // Sử dụng BigInteger để xử lý số có độ dài lớn
                    if (new java.math.BigInteger(mst).compareTo(java.math.BigInteger.ZERO) > 0) {
                        return false; // Trả về false nếu số có 10 hoặc 13 chữ số và lớn hơn 0
                    }
                } catch (NumberFormatException e) {
                    // Nếu xảy ra lỗi khi chuyển đổi, bỏ qua giá trị này (có thể log lỗi nếu cần)
                }
            }
        }
        return true; // Trả về true nếu không có số nào thỏa mãn cả hai điều kiện
    }


    public boolean testAcceptMst(List<String> mstList) {
        Pattern pattern = Pattern.compile("^[0-9]{10}$|^[0-9]{13}$");
        for (String mst : mstList) {
            if (pattern.matcher(mst).matches()) {
                // Kiểm tra số có lớn hơn 0
                try {
                    if (new BigInteger(mst).compareTo(BigInteger.ZERO) > 0) {
                        continue;
                    }
                } catch (NumberFormatException e) {
                    return false;
                }
            }
        }
        return true;
    }
}
