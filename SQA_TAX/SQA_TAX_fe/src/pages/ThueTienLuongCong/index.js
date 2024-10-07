import {
  Button,
  Card,
  Col,
  DatePicker,
  Form,
  Input,
  InputNumber,
  Row,
  Select,
  Spin,
  Radio,
  notification,
  Collapse,
} from "antd";
import { useEffect, useRef, useState } from "react";
import { getDate } from "../../helpers/getTimeCurrent";
import { getTaxPayer } from "../../services/taxPayer";
import { saveKeKhaiThueLuongCong } from "../../services/thueLuongCongService";
import { FormatTimeOnlyDMY } from "../../utils/FormatDateTime";
const { RangePicker } = DatePicker;

function ThueTienLuongCong({ loai_thue_id }) {
  const [notificationApi, contextHolder] = notification.useNotification();
  const [form] = Form.useForm();
  const [loading, setLoading] = useState(false);
  const [taxPayer, setTaxPayer] = useState(null);
  const rules = [
    {
      required: true,
      message: "Không được để trống",
    },
  ];
  const handleFinish = async (values) => {
    let path = `api/v1/to-khai-thue-tien-luong-cong/submit`;
    values.thuNhapTuNgay = getDate(values.date[0].$d);
    values.thuNhapDenNgay = getDate(values.date[1].$d);
    values.loaiThueId = loai_thue_id;
    const res = await saveKeKhaiThueLuongCong(values, path);
    if (!res.message) {
      setLoading(false);
      form.resetFields();
      setTaxPayer(null);
      notificationApi.success({
        message: "Kê khai thành công",
        duration: 3,
      });
    } else {
      setLoading(false);
      notificationApi.error({
        message: "Kê khai không thành công",
        duration: 3,
      });
    }
  };

  const handelGetInforTaxPayer = async (e) => {
    const fetch = async () => {
      const res = await getTaxPayer(
        `api/v1/tax-payer/getByMaSoThue?mst=${e.target.value}`
      );
      setTaxPayer(res);
      console.log(res);
    };
    fetch();
  };

  return (
    <>
      {contextHolder}
      <Card title="Khai báo thuế thu nhập từ tiền lương công">
        <Spin spinning={loading}>
          <Form layout="vertical" onFinish={handleFinish} form={form}>
            <Row gutter={[20, 10]}>
              <Col span={24}>
                <Form.Item
                  label="Mã số thuế"
                  name="mst"
                  rules={[
                    {
                      validator: async (_, value) => {
                        if (value) {
                          const res = await getTaxPayer(
                            `api/v1/tax-payer/getByMaSoThue?mst=${value}`
                          );
                          setTaxPayer(res);
                          if (res) {
                            return Promise.resolve();
                          } else {
                            return Promise.reject("Không tồn tại");
                          }
                        }
                      },
                    },
                    ...rules,
                  ]}
                >
                  <Input onBlur={(e) => handelGetInforTaxPayer(e)} />
                </Form.Item>
              </Col>
              {taxPayer && (
                <>
                  <Col span={24}>
                    <Collapse
                      items={[
                        {
                          key: "1",
                          label: "Thông tin người kê khai",
                          children: (
                            <div className="content__tax-payer-infor">
                              <table className="content__table">
                                <thead>
                                  <tr>
                                    <th>Thông Tin</th>
                                    <th>Chi Tiết</th>
                                  </tr>
                                </thead>
                                <tbody>
                                  {taxPayer && (
                                    <>
                                      <tr>
                                        <td>Mã số thuế</td>
                                        <td>{taxPayer.mst}</td>
                                      </tr>
                                      <tr>
                                        <td>Căn cước công dân</td>
                                        <td>{taxPayer.cccd}</td>
                                      </tr>
                                      <tr>
                                        <td>Tên người nộp thuế</td>
                                        <td>{taxPayer.hoVaTen}</td>
                                      </tr>
                                      <tr>
                                        <td>Ngày sinh</td>
                                        <td>{FormatTimeOnlyDMY(taxPayer.ngaySinh)}</td>
                                      </tr>
                                      <tr>
                                        <td>Giới tính</td>
                                        <td>{taxPayer.gioiTinh}</td>
                                      </tr>
                                      <tr>
                                        <td>Thành phố</td>
                                        <td>{taxPayer.dchk_tinhThanhPho}</td>
                                      </tr>
                                      <tr>
                                        <td>Quận/huyện</td>
                                        <td>{taxPayer.dchk_QuanHuyen}</td>
                                      </tr>
                                      <tr>
                                        <td>Điện thoại</td>
                                        <td>{taxPayer.sdt}</td>
                                      </tr>
                                    </>
                                  )}
                                </tbody>
                              </table>
                            </div>
                          ),
                        },
                      ]}
                    ></Collapse>
                  </Col>
                </>
              )}
              <Col span={24}>
                <Form.Item
                  label="Thu nhập chịu thuế"
                  name="thuNhapChiuThue"
                  rules={[
                    {
                      validator: async (_, value) => {
                        if (value) {
                          if (value > 0) {
                            return Promise.resolve();
                          } else {
                            return Promise.reject("Không được nhận giá trị âm");
                          }
                        }
                      },
                    },
                    ...rules,
                  ]}
                >
                  <InputNumber
                    addonAfter="VNĐ"
                    style={{ width: "100%" }}
                  />
                </Form.Item>
              </Col>
              <Col span={24}>
                <Form.Item
                  label="Thu nhập được miễn giảm"
                  name="thuNhapDuocMienGiam"
                  rules={[
                    {
                      validator: async (_, value) => {
                        if (value) {
                          if (value > 0) {
                            return Promise.resolve();
                          } else {
                            return Promise.reject("Không được nhận giá trị âm");
                          }
                        }
                      },
                    },
                    ...rules,
                  ]}
                >
                  <InputNumber
                    addonAfter="VNĐ"
                    style={{ width: "100%" }}
                  />
                </Form.Item>
              </Col>
              <Col span={24}>
                <Form.Item
                  label="Khấu trừ cho bản thân"
                  name="khauTruChoBanThan"
                  rules={[
                    {
                      validator: async (_, value) => {
                        if (value) {
                          if (value > 0) {
                            return Promise.resolve();
                          } else {
                            return Promise.reject("Không được nhận giá trị âm");
                          }
                        }
                      },
                    },
                    ...rules,
                  ]}
                >
                  <InputNumber
                    addonAfter="VNĐ"
                    style={{ width: "100%" }}
                  />
                </Form.Item>
              </Col>
              <Col span={24}>
                <Form.Item
                  label="Khấu trừ người phụ thuộc"
                  name="khauTruNguoiPhuThuoc"
                  rules={[
                    {
                      validator: async (_, value) => {
                        if (value) {
                          if (value > 0) {
                            return Promise.resolve();
                          } else {
                            return Promise.reject("Không được nhận giá trị âm");
                          }
                        }
                      },
                    },
                    ...rules,
                  ]}
                >
                  <InputNumber
                    addonAfter="VNĐ"
                    style={{ width: "100%" }}
                  />
                </Form.Item>
              </Col>
              <Col span={24}>
                <Form.Item
                  label="Khấu trừ cho từ thiện"
                  name="khauTruChoTuThien"
                  rules={[
                    {
                      validator: async (_, value) => {
                        if (value) {
                          if (value > 0) {
                            return Promise.resolve();
                          } else {
                            return Promise.reject("Không được nhận giá trị âm");
                          }
                        }
                      },
                    },
                    ...rules,
                  ]}
                >
                  <InputNumber
                    min
                    addonAfter="VNĐ"
                    style={{ width: "100%" }}
                  />
                </Form.Item>
              </Col>
              <Col span={24}>
                <Form.Item
                  label="Khấu trừ cho bảo hiểm"
                  name="khauTruChoBaoHiem"
                  rules={[
                    {
                      validator: async (_, value) => {
                        if (value) {
                          if (value > 0) {
                            return Promise.resolve();
                          } else {
                            return Promise.reject("Không được nhận giá trị âm");
                          }
                        }
                      },
                    },
                    ...rules,
                  ]}
                >
                  <InputNumber
                    addonAfter="VNĐ"
                    style={{ width: "100%" }}
                  />
                </Form.Item>
              </Col>
              <Col span={24}>
                <Form.Item
                  label="Trạng thái cư trú"
                  name={"cuTru"}
                  rules={rules}
                >
                  <Radio.Group>
                    <Radio value={true}> Cư trú </Radio>
                    <Radio value={false}> Không cư trú </Radio>
                  </Radio.Group>
                </Form.Item>
              </Col>
              <Col span={24}>
                <Form.Item
                  label="Khoảng thời gian thu nhập"
                  name="date"
                  rules={rules}
                >
                  <RangePicker style={{ width: "100%" }} />
                </Form.Item>
              </Col>
              <Col span={24}>
                <Form.Item>
                  <Button type="primary" htmlType="submit">
                    Nộp khai báo
                  </Button>
                </Form.Item>
              </Col>
            </Row>
          </Form>
        </Spin>
      </Card>
    </>
  );
}
export default ThueTienLuongCong;
