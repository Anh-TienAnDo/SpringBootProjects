import { Card, Row, Col, Badge, Table, Button, Spin } from "antd";
import "./style.css";
import { useContext } from "react";
import TaxWantPay from "../../context/taxWantPay";
import TaxPayer from "../../context/taxPayer";
import UnpaidTax from "../../context/unpaidTax";
import { readVietnameseNumber } from "../../utils/readNumber";
import { useLocation } from "react-router-dom";

function capitalizeFirstLetter(string) {
  return string.charAt(0).toUpperCase() + string.slice(1);
}

function ReceiptTax() {
  const location = useLocation()
  const {taxWantPay} = location.state
  const { taxPayer, setTaxPayer } = useContext(TaxPayer);
  const { unpaidTax, setUnpaidTax } = useContext(UnpaidTax);

  var date = new Date();

  // Lấy ngày
  var day = date.getDate();

  // Lấy tháng (lưu ý rằng tháng trong JavaScript được đánh số từ 0 đến 11)
  var month = date.getMonth() + 1; // Cộng thêm 1 vì tháng được đánh số từ 0

  // Lấy năm
  var year = date.getFullYear();

  let tongTienCuoiCungPhaiNop = 0;
  if (taxWantPay?.length > 0) {
    taxWantPay.forEach((item) => {
      tongTienCuoiCungPhaiNop += item.tongThuePhaiNop;
    });
  }

  return (
    <>
      <div className="container">
        <Row>
          <Col span={6}>
            <div className="header__left">
              <p>BỘ TÀI CHÍNH</p>
              <p>TỔNG CỤC THUẾ </p>
              <p>----------</p>
              <p>Cơ quan thu:</p>
              <p>Sở thuế nhà nước</p>
            </div>
          </Col>
          <Col span={12}>
            <div className="header__center">
              <p>CỘNG HÒA XÃ HỘI CHỦ NGHĨA VIỆT NAM</p>
              <p>Độc lập - Tự do - Hạnh phúc</p>
              <p> ----------------- </p>
              <p style={{ fontSize: "larger" }}>BIÊN LAI THU THUẾ</p>
            </div>
          </Col>
          <Col span={6}>
            <div className="header__right">
              <p>
                Mẫu: CTT50{" "}
                <span>
                  <br />
                </span>
                (Ban hành kèm theo Thông tư số 78/2021/TT-BTC ngày 17 tháng 9
                năm 2021 của Bộ Tài chính)
                <span>
                  {" "}
                  <br />{" "}
                </span>
                Ký hiệu:
                <span>
                  {" "}
                  <br />{" "}
                </span>
                Số:
              </p>
            </div>
          </Col>
        </Row>
        <Row>
          <Col span={24}>
            <div className="content__one">
              <p>Người nộp thuế: {taxPayer.hoVaTen}</p>
              <p>Mã số thuế: {taxPayer.mst}</p>
              <p>
                Địa chỉ: {taxPayer.dchk_soNhaDuongXom} -{" "}
                {taxPayer.dchk_xaPhuong} - {taxPayer.dchk_QuanHuyen} -{" "}
                {taxPayer.dchk_tinhThanhPho}
              </p>
            </div>
          </Col>
        </Row>
        <Row>
          <Col span={24}>
            <div className="content__two">
              <p>Danh sách thuế muốn nộp: </p>
              <div className="content__two-list-tax">
                {taxWantPay?.length>0 && (
                  <table className="table__list-tax">
                    <thead style={{width: 500}}>
                      <th>Nội dung</th>
                      <th>Tổng tiền (VND)</th>
                    </thead>
                    <tbody>
                      {taxWantPay.map((item) => {
                        return (
                          <>
                            <tr>
                              <td>Thuế bất động sản</td>
                              <td>{item.tongThuePhaiNop.toLocaleString("de-DE")} </td>
                            </tr>
                          </>
                        );
                      })}
                    </tbody>
                  </table>
                )}
              </div>
              <p>
                Tổng số tiền thuế phải nộp:{" "}
                {tongTienCuoiCungPhaiNop.toLocaleString("de-DE")} đồng
              </p>
              <p>
                Số tiền bằng chữ phải nộp:{" "}
                {capitalizeFirstLetter(
                  readVietnameseNumber(tongTienCuoiCungPhaiNop)
                )}
              </p>
            </div>
          </Col>
        </Row>
        <Row>
          <Col span={8} offset={4}>
            <br />
            <br />
            <div className="footer__left">
              <p>NGƯỜI THU TIỀN</p>
              <p> (Kí, ghi rõ họ, tên) </p>
            </div>
          </Col>

          <Col span={8} offset={4} style={{ textAlign: "center" }}>
            <div className="footer__right">
              <p>
                Ngày {day} tháng {month} năm {year}
              </p>
              <p>NGƯỜI NỘP THUẾ </p>
              <p> (Kí, ghi rõ họ, tên) </p>
            </div>
          </Col>
        </Row>
      </div>
      <Button  type="primary">IN</Button>
    </>
  );
}

export default ReceiptTax;
