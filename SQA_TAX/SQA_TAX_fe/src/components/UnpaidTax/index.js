import { Alert, Button, Checkbox, Modal, notification, Radio, Tooltip } from "antd";
import "./listUnpaidTax.css";
import {ReloadOutlined} from "@ant-design/icons"
import { useContext, useState, useEffect } from "react";
import AuthenTaxPayer from "../../context/afterAuthenTaxPayer";
import { getUnpaidTax } from "../../services/unpaidTax";
import Search from "../../context/search";
import UnpaidTax from "../../context/unpaidTax";
import { useNavigate } from "react-router-dom";
import TaxWantPay from "../../context/taxWantPay";
import TaxPayer from "../../context/taxPayer";
import ChiTietThueTienLuongCong from "../ChiTietThueTienLuongCong/chiTietThueTienLuongCong";
import ChiTietThueBDS from "../ChiTietThueBDS/chiTietThueBDS";
import ChiTietThueDauTuVon from "../ChiTietThueDauTuVon/chiTietThueDauTuVon";
import ChiTietThueQuaTang from "../ChiTietThueQuaTang/chiTietThueQuaTang";
import ChiTietThueNhuongQuyenThuongMai from "../ChiTietThueNhuongQuyenThuongMai/chiTietThueNhuongQuyenThuongMai";
import ChiTietThueTrungThuong from "../ChiTietThueTrungThuong/chiTietThueTrungThuong";
import ChiTietThueChuyenNhuongBanQuyen from "../ChiTietThueChuyenNhuongBanQuyen/chiTietThueChuyenNhuongBanQuyen";
import { saveReceipt } from "../../services/hoaDonService";
import formatDateTime from "../../utils/FormatDateTime.js";

function ListUnpaidTax() {
  const [notificationApi, contextHolder] = notification.useNotification();
  const [isModalOpen, setIsModalOpen] = useState(false);
  const { afterAuthenTaxPayer, setAfterAuthenTaxPayer } =
    useContext(AuthenTaxPayer);
  const { search, setSearch } = useContext(Search);
  const { unpaidTax, setUnpaidTax } = useContext(UnpaidTax);
  const { taxWantPay, setTaxWantPay } = useContext(TaxWantPay);
  const { taxPayer, setTaxPayer } = useContext(TaxPayer);
  const [isModalOpenVerifyTaxWantPay, setIsModalOpenVerifyTaxWantPay] = useState(false);
  const [isTaxPay, setIsTaxPay] = useState(false);
  const user = localStorage.getItem('info');
  
  

  const showModal = () => {
    setIsModalOpen(true);
  };
  const handleOk = () => {
    setIsModalOpen(false);
  };
  const handleCancel = () => {
    setIsModalOpen(false);
  };

  const showModalVerifyTaxWantPay = () => {
      setIsModalOpenVerifyTaxWantPay(true);
  };
  
  const handleCancelVerifyTaxWantPay = () => {
    setIsModalOpenVerifyTaxWantPay(false);
  };


  const handleOkVerifyTaxWantPay = () => {
    const now = new Date()
    setIsModalOpenVerifyTaxWantPay(false);
    const receipt = {
      tongThuePhaiDong: tongTienCuoiCungPhaiNop,
      nguoiDongThueId: taxPayer.id,
      thoiGianNopThue: formatDateTime(now),
      userId: JSON.parse(user).id,
      danhSachThueMuonDong: [
        ...taxWantPay.map( (item) => {
          return {
            loaiThueId: item.loaiThueId,
            idThue: item.id
          }
        })
      ]
    }
    const fetch = async () => {
      const data = await saveReceipt(receipt,'api/v1/hoa-don/save')
      if(data.statusCodeValue===200){
        setIsTaxPay(true)
        notificationApi.success({
          message: "Đóng thuế thành công",
          duration: 3,
        });
      }
      else{
        console.log(data)
        notificationApi.error({
          message: "Đóng thuế không thành công",
          duration: 3,
        });
      }
      
    }
    fetch()
  };
  useEffect(() => {
    const get = async () => {
      try {
        if (search?.type?.length > 0) {
          const data = search.type.map(async (item) => {
            // Sử dụng `await` trong một arrow function đồng bộ
            const res = await getUnpaidTax(`api/v1/to-khai-${item}/getAll`);
            return res;
          });

          Promise.all(data).then((result) => {
            if (result === undefined) {
              console.log("getUnpaidTax Error");
            } else {
              const data = result.flat().filter( (item) => item.trangThaiDaDong===false&&taxPayer.mst===item.mst)
              setUnpaidTax(data);
            }
          });
        } else {
          setTaxWantPay([]);
        }
      } catch (error) {
        // Handle errors that occurred during the API call or data processing
        console.error("An error occurred while fetching unpaid taxes:", error);
        // Optionally set some state to show an error message to the user
      }
    };
    get();
  }, [search,taxWantPay]);

  console.log(taxWantPay)

  let tongTienCuoiCungPhaiNop = 0;
  if (taxWantPay?.length > 0) {
    taxWantPay.forEach((item) => {
      tongTienCuoiCungPhaiNop += item.tongThuePhaiNop;
    });
  }

  const handleClickTaxWantPay = (taxItemWantPay, checked) => {
    if (checked) {
      // Tạo một bản sao mới của mảng và thêm item mới
      const newTaxWantPay = [...taxWantPay, taxItemWantPay];
      setTaxWantPay(newTaxWantPay);
    } else {
      // Tạo một bản sao mới của mảng khi lọc item ra
      const newTaxWantPay = taxWantPay.filter(
        (item) =>
          item.id !== taxItemWantPay.id ||
          item.loaiThueId !== taxItemWantPay.loaiThueId
      );
      setTaxWantPay(newTaxWantPay);
    }
  };
  // const handleClickPaymentTax = (e) => {
  //   navigate("/receipt-tax", { state: { taxWantPay: taxWantPay } });
  // };

  const handleClickPaymentTax = (e) => {};
  const handleClickUpdateTable = () => {
    if(isTaxPay&&taxWantPay?.length>0){
      setTaxWantPay([])
      setIsTaxPay(false)
    }
  }
  console.log(isTaxPay)
  return (
    <>
    {contextHolder}
    {
    (afterAuthenTaxPayer &&
    search?.type?.length > 0)  ? (
      <div className="content__list-unpaid-tax">
        
        <div class="content__grid-list-unpaid-tax-container">
          <div class="grid-item-header">Nội dung khoản nộp NSNN</div>
          <div class="grid-item-header">Xem chi tiết</div>
          <div class="grid-item-header">Tổng số tiền (VND)</div>
          <div class="grid-item-header">Chọn khoản nộp</div>
          <div className="container__unpaid-tax">
            {unpaidTax &&
              unpaidTax.map((item, index) => {
                return (
                  <div key={index}>
                    <div className="content__grid-list-unpaid-tax-container">
                      <div class="grid-item">{item.noiDung}</div>
                      <div class="grid-item">
                        <Button type="link" onClick={showModal}>
                          Xem
                        </Button>
                        <Modal
                          title={<h3>Chi tiết thuế phải nộp</h3>}
                          open={isModalOpen}
                          onOk={handleOk}
                          onCancel={handleCancel}
                          width={1400}
                        >
                          <>
                            {() => {
                              if (item.loaiThueId === 1) {
                                return <ChiTietThueTienLuongCong item={item} />;
                              } else if (item.loaiThueId === 2) {
                                return <ChiTietThueBDS item={item} />;
                              } else if (item.loaiThueId === 3) {
                                return <ChiTietThueDauTuVon item={item} />;
                              } else if (item.loaiThueId === 4) {
                                return <ChiTietThueQuaTang item={item} />;
                              } else if (item.loaiThueId === 5) {
                                return (
                                  <ChiTietThueNhuongQuyenThuongMai
                                    item={item}
                                  />
                                );
                              } else if (item.loaiThueId === 6) {
                                return <ChiTietThueTrungThuong item={item} />;
                              } else if (item.loaiThueId === 7) {
                                return (
                                  <ChiTietThueChuyenNhuongBanQuyen
                                    item={item}
                                  />
                                );
                              }
                            }}
                          </>
                        </Modal>
                      </div>
                      <div class="grid-item">
                        {item.tongThuePhaiNop.toLocaleString("de-DE")}{" "} 
                      </div>
                      <div class="grid-item">
                        {taxWantPay.find(
                          (element) =>
                            item.id === element.id &&
                            item.loaiThueId === element.loaiThueId
                        ) ? (
                            <Checkbox
                              checked
                              disabled= {isTaxPay ? true : false}
                              onChange={() => handleClickTaxWantPay(item, false)}
                            ></Checkbox>
                          
                        ) : (
                          <Checkbox
                            onChange={() => handleClickTaxWantPay(item, true)}
                          ></Checkbox>
                        )}
                      </div>
                    </div>
                  </div>
                );
              })}
            <div className="content__grid-list-unpaid-tax-container box-container">
              <div class="grid-item box1"></div>
              <div class="grid-item box2" style={{ padding: "0 0px 0px 85px" }}>
                <h3>Tổng tiền : </h3>
              </div>
              <div class="grid-item box3">
                <h3>{tongTienCuoiCungPhaiNop.toLocaleString("de-DE")} VND</h3>
              </div>
              <div class="grid-item box4>" style={{ borderLeft: "none" }}></div>
            </div>
          </div>
        </div>
        <div className="content__payment-tax">
        <Tooltip placement="topLeft" color="orange" title = {taxWantPay?.length > 0 ? "" : "Vui lòng chọn thuế muốn thanh toán"} disabled = {taxWantPay?.length > 0 ? true : false} >
        <Button
            disabled= {taxWantPay?.length > 0 ? false : true}
            style={{ marginLeft: -28 }}
            type="primary"
            onClick={showModalVerifyTaxWantPay}
          >
            Thu thuế
          </Button>
          </Tooltip>
          <Button className="update_table" onClick={handleClickUpdateTable} type="primary">Cập nhật bản ghi</Button>
        </div>
        <Modal
          title="Xác nhận thu thuế"
          open={isModalOpenVerifyTaxWantPay}
          onOk={handleOkVerifyTaxWantPay}
          onCancel={handleCancelVerifyTaxWantPay}
        >
          <p>Bạn có chắc là muốn thu thuế không</p>
        </Modal>
      </div>
    
    ) :
    <div></div> }
    </>
  );
}
export default ListUnpaidTax;
