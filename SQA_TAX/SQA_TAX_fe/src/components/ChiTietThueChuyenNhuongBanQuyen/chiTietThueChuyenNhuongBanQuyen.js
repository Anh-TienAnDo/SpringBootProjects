import React from 'react'

 function ChiTietThueChuyenNhuongBanQuyen(item) {
  return (
    <div>
        <table>
    <thead>
        <tr>
            <th>ID</th>
            <th>Hạn Nộp Đến Ngày</th>
            <th>Hạn Nộp Từ Ngày</th>
            <th>MST</th>
            <th>Nội Dung</th>
            <th>Thu Nhập Chịu Thuế</th>
            <th>Thu Nhập Đến Ngày</th>
            <th>Thu Nhập Từ Ngày</th>
            <th>Tổng Thuế Phải Nộp</th>
            <th>Trạng Thái Đã Đóng</th>
            <th>Loại Thuế ID</th>
        </tr>
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
    </div>
  )
}
export default ChiTietThueChuyenNhuongBanQuyen
