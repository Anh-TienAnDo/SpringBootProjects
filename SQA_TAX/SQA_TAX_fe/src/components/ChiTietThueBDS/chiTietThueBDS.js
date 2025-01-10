import React from "react";
import "./chiThietThueBDS.css";
function ChiTietThueBDS(item) {
  return (
    <table>
      <thead>
        <thead>
          <th>Nội dung</th>
          <th>Địa chỉ</th>
          <th>Loại đất</th>
          <th>Giá trị chuyển nhượng</th>
          <th>Thu nhập từ ngày</th>
          <th>Thu nhập đến ngày</th>
          <th>Hạn nộp từ ngày</th>
          <th>Hạn nộp đến ngày</th>
          <th>Tổng thuế phải nộp</th>
          <th>Trạng thái</th>
        </thead>
      </thead>
      <tbody>
        <tr>
          <td>{item.noiDung}</td>
          <td>{item.diaChi}</td>
          <td>{item.loaiDat}</td>
          <td>{item.giaChiChuyenNhuong}</td>
          <td>{item.thuNhapTuNgay}</td>
          <td>{item.thuNhapDenNgay}</td>
          <td>{item.hanNopTuNgay}</td>
          <td>{item.hanNopDenNgay}</td>
          <td>{item.tongThuePhaiNop}</td>
          <td>{item.trangThai}</td>
        </tr>
      </tbody>
    </table>
  );
}
export default ChiTietThueBDS;
