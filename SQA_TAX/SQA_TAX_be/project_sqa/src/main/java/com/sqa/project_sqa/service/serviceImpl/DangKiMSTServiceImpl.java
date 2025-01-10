package com.sqa.project_sqa.service.serviceImpl;

import com.sqa.project_sqa.entities.NguoiDongThue;
import com.sqa.project_sqa.payload.dto.NguoiDongThueDTO;
import com.sqa.project_sqa.repositories.NguoiDongThueRepository;
import com.sqa.project_sqa.service.DangKiMSTService;
import com.sqa.project_sqa.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

@Service
public class DangKiMSTServiceImpl implements DangKiMSTService {
    @Autowired
    private NguoiDongThueRepository nguoiDongThueRepository;
    @Override
    public ResponseEntity<?> registerTaxCode(NguoiDongThueDTO nguoiDongThueDTO) {
        NguoiDongThue nguoiDongThue = new NguoiDongThue();
        if (!isValidNguoiDongThueDTO(nguoiDongThueDTO)) {
            return ResponseUtil.getResponseEntity("04", "Dữ liệu không hợp lệ.", HttpStatus.BAD_REQUEST);
        }
        if(nguoiDongThueRepository.existsByEmail(nguoiDongThueDTO.getEmail())){

//            return new ResponseEntity<>("Email đã được đăng kí.", HttpStatus.BAD_REQUEST);
            return ResponseUtil.getResponseEntity("01", "Email đã được đăng kí.",HttpStatus.BAD_REQUEST);
        }
        if(nguoiDongThueRepository.existsBySdt(nguoiDongThueDTO.getSdt())){
            return ResponseUtil.getResponseEntity("02", "Số điện thoại đã được đăng kí.",HttpStatus.BAD_REQUEST);
        }

        if(nguoiDongThueRepository.existsByCCCD(nguoiDongThueDTO.getSoGiayTo())){
            return ResponseUtil.getResponseEntity("03", "Số CCCD đã được đăng kí MST.",HttpStatus.BAD_REQUEST);

        }



        nguoiDongThue.setHoVaTen(nguoiDongThueDTO.getHoVaTen());
        nguoiDongThue.setGioiTinh(nguoiDongThueDTO.getGioiTinh());
        nguoiDongThue.setNgaySinh(nguoiDongThueDTO.getNgaySinh());
        nguoiDongThue.setSdt(nguoiDongThueDTO.getSdt());
        nguoiDongThue.setEmail(nguoiDongThueDTO.getEmail());


        nguoiDongThue.setCCCD(nguoiDongThueDTO.getSoGiayTo());
        nguoiDongThue.setCCCD_ngayCap(nguoiDongThueDTO.getNgayCap());
        nguoiDongThue.setCCCD_noiCap(nguoiDongThueDTO.getNoiCap());


        nguoiDongThue.setDcct_QuanHuyen(nguoiDongThueDTO.getDcct_QuanHuyen());
        nguoiDongThue.setDcct_xaPhuong(nguoiDongThueDTO.getDcct_xaPhuong());
        nguoiDongThue.setDcct_tinhThanhPho(nguoiDongThueDTO.getDcct_tinhThanhPho());
        nguoiDongThue.setDcct_soNhaDuongXom(nguoiDongThueDTO.getDcct_soNhaDuongXom());

        nguoiDongThue.setDchk_QuanHuyen(nguoiDongThueDTO.getDchk_QuanHuyen());
        nguoiDongThue.setDchk_xaPhuong(nguoiDongThueDTO.getDchk_xaPhuong());
        nguoiDongThue.setDchk_tinhThanhPho(nguoiDongThueDTO.getDchk_tinhThanhPho());
        nguoiDongThue.setDchk_soNhaDuongXom(nguoiDongThueDTO.getDchk_soNhaDuongXom());

        nguoiDongThue.setTaxAgency(nguoiDongThueDTO.getTaxAgency());

        String mst;
        do{
            mst=generateRandomNumber();
        }while (nguoiDongThueRepository.existsByMst(mst));

        nguoiDongThue.setMst(mst);

        nguoiDongThueRepository.save(nguoiDongThue);


        return new ResponseEntity<>(mst,HttpStatus.OK);
    }



    private String generateRandomNumber() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 13; i++) {
            sb.append(random.nextInt(10)); // Sinh một chữ số từ 0 đến 9
        }
        return sb.toString();
    }




    public boolean isValidNguoiDongThueDTO(NguoiDongThueDTO nguoiDongThueDTO) {
        // Kiểm tra các trường không được để trống
        if (nguoiDongThueDTO.getHoVaTen() == null || nguoiDongThueDTO.getHoVaTen().isEmpty() ||
                nguoiDongThueDTO.getGioiTinh() == null || nguoiDongThueDTO.getGioiTinh().isEmpty() ||
                nguoiDongThueDTO.getNgaySinh() == null ||
                nguoiDongThueDTO.getSdt() == null || nguoiDongThueDTO.getSdt().isEmpty() ||
                nguoiDongThueDTO.getEmail() == null || nguoiDongThueDTO.getEmail().isEmpty() ||
                nguoiDongThueDTO.getSoGiayTo() == null || nguoiDongThueDTO.getSoGiayTo().isEmpty() ||
                nguoiDongThueDTO.getNgayCap() == null ||
                nguoiDongThueDTO.getNoiCap() == null || nguoiDongThueDTO.getNoiCap().isEmpty() ||
                nguoiDongThueDTO.getDchk_soNhaDuongXom() == null || nguoiDongThueDTO.getDchk_soNhaDuongXom().isEmpty() ||
                nguoiDongThueDTO.getDchk_tinhThanhPho() == null || nguoiDongThueDTO.getDchk_tinhThanhPho().isEmpty() ||
                nguoiDongThueDTO.getDchk_QuanHuyen() == null || nguoiDongThueDTO.getDchk_QuanHuyen().isEmpty() ||
                nguoiDongThueDTO.getDchk_xaPhuong() == null || nguoiDongThueDTO.getDchk_xaPhuong().isEmpty() ||
                nguoiDongThueDTO.getDcct_soNhaDuongXom() == null || nguoiDongThueDTO.getDcct_soNhaDuongXom().isEmpty() ||
                nguoiDongThueDTO.getDcct_tinhThanhPho() == null || nguoiDongThueDTO.getDcct_tinhThanhPho().isEmpty() ||
                nguoiDongThueDTO.getDcct_QuanHuyen() == null || nguoiDongThueDTO.getDcct_QuanHuyen().isEmpty() ||
                nguoiDongThueDTO.getDcct_xaPhuong() == null || nguoiDongThueDTO.getDcct_xaPhuong().isEmpty() ||
                nguoiDongThueDTO.getTaxAgency() == null || nguoiDongThueDTO.getTaxAgency().isEmpty()) {
            return false;
        }

        // Kiểm tra định dạng email
        String emailRegex = "^[a-zA-Z0-9]+([.]?[a-zA-Z0-9]+)*@[a-zA-Z0-9]+([.-]?[a-zA-Z0-9]+)*\\.[a-zA-Z]{2,}$";
        Pattern emailPattern = Pattern.compile(emailRegex);
        if (!emailPattern.matcher(nguoiDongThueDTO.getEmail()).matches()) {
            return false;
        }

        // Kiểm tra số điện thoại có 10 chữ số và không chứa kí tự đặc biệt
        String phoneRegex = "^[0-9]{10}$";
        Pattern phonePattern = Pattern.compile(phoneRegex);
        if (!phonePattern.matcher(nguoiDongThueDTO.getSdt()).matches()) {
            return false;
        }

        // Kiểm tra số giấy tờ chỉ chứa số và có đúng 12 chữ số
        String idCardRegex = "^[0-9]{12}$";
        Pattern idCardPattern = Pattern.compile(idCardRegex);
        if (!idCardPattern.matcher(nguoiDongThueDTO.getSoGiayTo()).matches()) {
            return false;
        }

        // Kiểm tra họ và tên không chứa số và kí tự đặc biệt, có thể chứa tiếng Việt có dấu
        String nameRegex = "^[a-zA-Z\\p{L}\\s]+$"; // Chỉ chấp nhận kí tự chữ cái, khoảng trắng và tiếng Việt có dấu
        Pattern namePattern = Pattern.compile(nameRegex);
        if (!namePattern.matcher(nguoiDongThueDTO.getHoVaTen()).matches()) {
            return false;
        }

        return true;
    }

}
