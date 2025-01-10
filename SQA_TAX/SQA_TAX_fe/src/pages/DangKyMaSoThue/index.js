import React, { useState, useEffect, useContext } from "react";
import { ConfigProvider, Input, Select } from "antd";
import "./styles.css";
import TaxWantPay from "../../context/taxWantPay";

const DangKyMST = () => {
  const { taxWantPay, setTaxWantPay } = useContext(TaxWantPay);
  const [popupTitle, setPopupTitle] = useState("");
  const [popupMessage, setPopupMessage] = useState("");

  // Sử dụng useState để lưu trữ thông tin đăng ký
  const [hoTen, setHoTen] = useState("");
  const [ngaySinh, setNgaySinh] = useState("");
  const [gioiTinh, setGioiTinh] = useState("");
  const [loaiGiayTo, setLoaiGiayTo] = useState("cccd"); // Mặc định là chứng minh nhân dân
  const [soGiayTo, setSoGiayTo] = useState("");
  const [ngayCap, setNgayCap] = useState("");
  const [noiCap, setNoiCap] = useState("");
  useEffect(() => {
    setTaxWantPay([]);
  },[]);
  const [diaChiHoKhau, setDiaChiHoKhau] = useState({
    soNhaDuong: "",
    xaPhuong: "",
    quanHuyen: "",
    tinhThanhPho: "",
    quocGia: "",
  });
  const [diaChiCuTru, setDiaChiCuTru] = useState({
    soNhaDuong: "",
    xaPhuong: "",
    quanHuyen: "",
    tinhThanhPho: "",
  });
  const today = new Date().toISOString().split("T")[0];
  const [dienThoai, setDienThoai] = useState("");
  const [email, setEmail] = useState("");
  const [coQuanChiTra, setCoQuanChiTra] = useState("");
  const [tinhThanhPhoOptions, setTinhThanhPhoOptions] = useState([]);
  const [quanHuyenOptions, setQuanHuyenOptions] = useState({
    cuTru: [],
    hoKhau: [],
  });
  const [xaPhuongOptions, setXaPhuongOptions] = useState({
    cuTru: [],
    hoKhau: [],
  });
  const [quocGiaOptions, setQuocGiaOptions] = useState([]);
  const [errors, setErrors] = useState({});
  const [registrationSuccess, setRegistrationSuccess] = useState(false);
  // Simulate API call to fetch province/city data
  const fetchTinhThanhPho = async () => {
    // Replace this with your API call to fetch province/city data
    const response = await fetch(
      "http://localhost:8080/api/v1/province/getAll"
    );
    const data = await response.json();
    // Assuming the response data is an array of objects with 'name' and 'value' properties
    setTinhThanhPhoOptions(data);
  };

  // Simulate API call to fetch district data
  const fetchQuanHuyen = async (selectedTinhThanhPho, type) => {
    // Replace this with your API call to fetch district data based on selected province/city
    const response = await fetch(
      "http://localhost:8080/api/v1/district/getByProvinceId?" +
        new URLSearchParams({
          provinceId: selectedTinhThanhPho,
        })
    );
    const data = await response.json();

    if (type === "cutru") {
      setDiaChiCuTru((prev) => ({ ...prev, xaPhuong: "", quanHuyen: "" }));
      setQuanHuyenOptions((prev) => ({
        ...prev,
        cuTru: data,
      }));
    } else {
      setDiaChiHoKhau((prev) => ({ ...prev, xaPhuong: "", quanHuyen: "" }));
      setQuanHuyenOptions((prev) => ({
        ...prev,
        hoKhau: data,
      }));
    }
    // Assuming the response data is an array of objects with 'name' and 'value' properties
  };

  // Simulate API call to fetch commune/ward data
  const fetchXaPhuong = async (selectedQuanHuyen, type) => {
    console.log(selectedQuanHuyen);
    // Replace this with your API call to fetch commune/ward data based on selected district
    const response = await fetch(
      "http://localhost:8080/api/v1/ward/getByDistrictId?" +
        new URLSearchParams({
          districtId: selectedQuanHuyen,
        })
    );
    const data = await response.json();
    // Assuming the response data is an array of objects with 'name' and 'value' properties
    if (type === "cutru") {
      setDiaChiCuTru((prev) => ({ ...prev, xaPhuong: "" }));
      setXaPhuongOptions((prev) => ({
        ...prev,
        cuTru: data,
      }));
    } else {
      setDiaChiHoKhau((prev) => ({ ...prev, xaPhuong: "" }));
      setXaPhuongOptions((prev) => ({
        ...prev,
        hoKhau: data,
      }));
    }
  };

  // Simulate API call to fetch country data
  // const fetchQuocGia = async () => {
  //   // Replace this with your API call to fetch country data
  //   const response = await fetch("your_api_endpoint");
  //   const data = await response.json();
  //   // Assuming the response data is an array of objects with 'name' and 'value' properties
  //   setQuocGiaOptions(data);
  // };
  // Hàm xóa lỗi khi click vào ô nhập
  const handleInputClick = (fieldName) => {
    setErrors((prevErrors) => ({ ...prevErrors, [fieldName]: "" }));
  };
  const handleNgaySinhChange = (e) => {
    const selectedDate = e.target.value;
    setNgaySinh(selectedDate);

    if (selectedDate > today) {
      setErrors((prevErrors) => ({
        ...prevErrors,
        ngaySinh: "Ngày tháng năm sinh không hợp lệ",
      }));
    } else {
      setErrors((prevErrors) => ({ ...prevErrors, ngaySinh: "" }));
    }
  };
  const handleNgayCapChange = (e) => {
    const selectedDate = e.target.value;
    setNgayCap(selectedDate);

    // Kiểm tra nếu ngày cấp lớn hơn ngày hiện tại
    const today = new Date().toISOString().split("T")[0];
    if (selectedDate > today) {
      setErrors((prevErrors) => ({
        ...prevErrors,
        ngayCap: "Ngày cấp không hợp lệ",
      }));
    } else {
      setErrors((prevErrors) => ({ ...prevErrors, ngayCap: "" }));
    }
  };
  useEffect(() => {
    fetchTinhThanhPho();
    // fetchQuocGia();
  }, []); // Empty dependency array to run the effect only once when component mounts
  // Hàm kiểm tra định dạng email
  const validateEmail = (email) => {
    // Biểu thức chính quy để kiểm tra định dạng email
    const regex =
      /^[a-zA-Z0-9]+([.]?[a-zA-Z0-9]+)*@[a-zA-Z0-9]+([.-]?[a-zA-Z0-9]+)*\.[a-zA-Z]{2,}$/;
    return regex.test(email);
  };

  function isValidString(inputString) {
    // Biểu thức chính quy này chỉ cho phép chứa chữ cái có hoặc không có dấu
    const regex = /^[a-zA-ZÀ-Ỹà-ỹ\s]*$/;
    return regex.test(inputString);
  }
  function validateNumber(inputValue) {
    // Kiểm tra nếu inputValue không phải là chuỗi hoặc không phải là số
    if (typeof inputValue !== "string" || isNaN(inputValue)) {
      return false;
    }

    // Loại bỏ khoảng trắng và kiểm tra độ dài chuỗi
    const trimmedValue = inputValue.trim();
    if (trimmedValue.length !== 12) {
      return false;
    }

    // Sử dụng biểu thức chính quy để kiểm tra xem chuỗi chỉ chứa số hay không
    const numberPattern = /^[0-9]+$/;
    return numberPattern.test(trimmedValue);
  }
  function validateSdt(inputValue) {
    // Kiểm tra nếu inputValue không phải là chuỗi hoặc không phải là số
    if (typeof inputValue !== "string" || isNaN(inputValue)) {
      return false;
    }

    // Loại bỏ khoảng trắng và kiểm tra độ dài chuỗi
    const trimmedValue = inputValue.trim();
    if (trimmedValue.length !== 10) {
      return false;
    }

    // Sử dụng biểu thức chính quy để kiểm tra xem chuỗi chỉ chứa số hay không
    const numberPattern = /^[0-9]+$/;
    return numberPattern.test(trimmedValue);
  }
  // Hàm xử lý khi form được submit
  const handleSubmit = async (e) => {
    e.preventDefault();

    const newErrors = {};

    if (hoTen.trim() === "") {
      newErrors.hoTen = "Vui lòng nhập họ và tên";
    }
    if (isValidString(hoTen.trim()) == false) {
      newErrors.hoTen = "Họ tên không đúng định dạng";
    }

    if (ngaySinh.trim() === "") {
      newErrors.ngaySinh = "Vui lòng chọn ngày sinh";
    }

    if (gioiTinh.trim() === "") {
      newErrors.gioiTinh = "Vui lòng chọn giới tính";
    }

    if (soGiayTo.trim() === "") {
      newErrors.soGiayTo = "Vui lòng nhập số CCCD";
    } else {
      if (validateNumber(soGiayTo.trim()) == false) {
        newErrors.soGiayTo = "Số CCCD không hợp lệ";
      }
    }

    if (ngayCap.trim() === "") {
      newErrors.ngayCap = "Vui lòng chọn ngày cấp";
    }

    if (noiCap.trim() === "") {
      newErrors.noiCap = "Vui lòng nhập nơi cấp";
    }

    if (diaChiHoKhau.soNhaDuong.trim() === "") {
      newErrors.diaChiHoKhau = "Vui lòng nhập địa chỉ hộ khẩu";
    }

    if (diaChiHoKhau.tinhThanhPho === "") {
      newErrors.tinhThanhPhoHoKhau = "Vui lòng chọn tỉnh/thành phố";
    }

    if (diaChiHoKhau.quanHuyen === "") {
      newErrors.quanHuyenHoKhau = "Vui lòng chọn quận/huyện";
    }

    if (diaChiHoKhau.xaPhuong === "") {
      newErrors.xaPhuongHoKhau = "Vui lòng chọn xã/phường";
    }

    if (diaChiCuTru.soNhaDuong === "") {
      newErrors.diaChiCuTru = "Vui lòng nhập địa chỉ cư trú";
    }

    if (diaChiCuTru.tinhThanhPho === "") {
      newErrors.tinhThanhPhoCuTru = "Vui lòng chọn tỉnh/thành phố";
    }

    if (diaChiCuTru.quanHuyen === "") {
      newErrors.quanHuyenCuTru = "Vui lòng chọn quận/huyện";
    }

    if (diaChiCuTru.xaPhuong === "") {
      newErrors.xaPhuongCuTru = "Vui lòng chọn xã/phường";
    }

    if (dienThoai.trim() === "") {
      newErrors.dienThoai = "Vui lòng nhập số điện thoại";
    } else {
      if (!validateSdt(dienThoai)) {
        newErrors.dienThoai = "Số điện thoại không hợp lệ";
      }
    }

    if (email.trim() === "") {
      newErrors.email = "Vui lòng nhập địa chỉ email";
    } else {
      if (!validateEmail(email)) {
        newErrors.email = "Email không hợp lệ";
      }
    }

    if (coQuanChiTra.trim() === "") {
      newErrors.coQuanChiTra = "Vui lòng nhập cơ quan chi trả";
    }
    console.log(
     diaChiHoKhau.xaPhuong
    );
    setErrors(newErrors);
    if (Object.keys(newErrors).length === 0) {
      // Thực hiện hành động submit ở đây
      // Tạo đối tượng mới với tên trường đã thay đổi
      const dataToSend = {
        hoVaTen: hoTen,
        gioiTinh: gioiTinh,
        ngaySinh: ngaySinh,
        loaiGiayTo: loaiGiayTo,
        soGiayTo: soGiayTo,
        ngayCap: ngayCap,
        noiCap: noiCap,

        dchk_soNhaDuongXom: diaChiHoKhau.soNhaDuong,
        dchk_tinhThanhPho: JSON.parse(diaChiHoKhau.tinhThanhPho).name,
        dchk_QuanHuyen: JSON.parse(diaChiHoKhau.quanHuyen).name,
        dchk_xaPhuong: JSON.parse(diaChiHoKhau.xaPhuong).name,

        dcct_soNhaDuongXom: diaChiCuTru.soNhaDuong,
        dcct_tinhThanhPho: JSON.parse(diaChiCuTru.tinhThanhPho).name,
        dcct_QuanHuyen: JSON.parse(diaChiCuTru.quanHuyen).name,
        dcct_xaPhuong: JSON.parse(diaChiCuTru.xaPhuong).name,

        sdt: dienThoai,
        email: email,
        taxAgency: coQuanChiTra,
        // Thêm các trường khác nếu cần
      };

      try {
        // Gửi dữ liệu lên máy chủ
        const response = await fetch("http://localhost:8080/api/v1/dangKy", {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify(dataToSend),
        });

        const responseData = await response.json();
        console.log(responseData);
        // Xử lý phản hồi từ máy chủ nếu cần
        if (response.ok) {
          setHoTen("");
          setNgaySinh("");
          setGioiTinh("");
          setLoaiGiayTo("cccd");
          setSoGiayTo("");
          setNgayCap("");
          setNoiCap("");
          setDiaChiHoKhau({
            soNhaDuong: "",
            xaPhuong: "",
            quanHuyen: "",
            tinhThanhPho: "",
            quocGia: "",
          });
          setDiaChiCuTru({
            soNhaDuong: "",
            xaPhuong: "",
            quanHuyen: "",
            tinhThanhPho: "",
          });
          setDienThoai("");
          setEmail("");
          setCoQuanChiTra("");
          setErrors({});

          setPopupTitle("Đăng kí thành công!");
          setPopupMessage("Mã số thuế: " + responseData);

          setRegistrationSuccess(true);

          console.log("Đã gửi dữ liệu đăng ký lên server thành công!");
        } else {
          setPopupTitle("Đăng kí thất bại!");
          setPopupMessage(responseData.message);
          setRegistrationSuccess(true);
          if (responseData.code === "01") {
            newErrors.email = "Email đã được đăng kí.";
          }
          if (responseData.code === "02") {
            newErrors.dienThoai = "Số điện thoại đã được đăng kí";
          }
          if (responseData.code === "03") {
            newErrors.soGiayTo = "Số CCCD đã được đăng kí MST.";
          }
          // if (responseData.code === "04") {
          //   newErrors.soGiayTo = "Dữ liệu không hợp lệ.";
          // }
        }
      } catch (error) {
        console.error(
          "Đã xảy ra lỗi khi gửi dữ liệu đăng ký lên server:",
          error
        );
      }
    }
  };

  return (
    <div>
      <h2 style={{ marginLeft: 50 }}>Đăng Ký Mã Số Thuế</h2>
      <form onSubmit={handleSubmit}>
        <div style={{ minHeight: "75px" }}>
          <label>Họ và tên:</label>
          <Input
            autoFocus
            type="text"
            value={hoTen}
            onChange={(e) => setHoTen(e.target.value)}
            onClick={() => handleInputClick("hoTen")}
          />
          {errors.hoTen && (
            <span className="error-message">{errors.hoTen}</span>
          )}
        </div>
        <div style={{ minHeight: "75px" }}>
          <label>Ngày tháng năm sinh:</label>
          <Input
            type="date"
            value={ngaySinh}
            onChange={handleNgaySinhChange}
            onClick={() => handleInputClick("ngaySinh")}
            max={today} // Chỉ cho phép chọn ngày đến ngày hiện tại
          />
          {errors.ngaySinh && (
            <span className="error-message">{errors.ngaySinh}</span>
          )}
        </div>

        <div style={{ minHeight: "75px" }}>
          <label>Giới tính:</label>
          <Select
            style={{}}
            placeholder="Chọn giới tính"
            onClick={() => handleInputClick("gioiTinh")}
            value={gioiTinh || undefined}
            onChange={(e) => setGioiTinh(e)}
            options={[
              {
                value: "nam",
                label: "Nam",
              },
              {
                value: "nu",
                label: "Nữ",
              },
            ]}
          />
          {errors.gioiTinh && (
            <span style={{ display: "block" }} className="error-message">
              {errors.gioiTinh}
            </span>
          )}
        </div>

        <div style={{ minHeight: "75px" }}>
          <label>Giấy tờ cá nhân:</label>
          <Select
            placeholder="Chọn giấy tờ tùy thân"
            onClick={() => handleInputClick("loaiGiayTo")}
            value={loaiGiayTo || undefined}
            onChange={(e) => setLoaiGiayTo(e)}
          >
            <option value="cccd">Căn cước công dân</option>
          </Select>
          {errors.loaiGiayTo && (
            <span className="error-message">{errors.loaiGiayTo}</span>
          )}
        </div>

        <div style={{ minHeight: "75px" }}>
          <label>{loaiGiayTo === "cccd" ? "Số CCCD:" : "Số CMND:"}</label>
          <Input
            onClick={() => handleInputClick("soGiayTo")}
            type="text"
            value={soGiayTo}
            onChange={(e) => setSoGiayTo(e.target.value)}
            placeholder={loaiGiayTo === "cccd" ? "Số CCCD" : "Số CMND"}
          />
          {errors.soGiayTo && (
            <span className="error-message">{errors.soGiayTo}</span>
          )}
        </div>

        <div style={{ minHeight: "75px" }}>
          <label>Ngày cấp:</label>
          <Input
            onClick={() => handleInputClick("ngayCap")}
            type="date"
            value={ngayCap}
            onChange={handleNgayCapChange}
            placeholder="Ngày cấp"
            max={new Date().toISOString().split("T")[0]} // Đặt giá trị lớn nhất là ngày hiện tại
          />
          {errors.ngayCap && (
            <span className="error-message">{errors.ngayCap}</span>
          )}
        </div>

        <div style={{ minHeight: "75px" }}>
          <label>Nơi cấp:</label>
          <Input
            onClick={() => handleInputClick("noiCap")}
            type="text"
            value={noiCap}
            onChange={(e) => setNoiCap(e.target.value)}
            placeholder="Nơi cấp"
          />
          {errors.noiCap && (
            <span className="error-message">{errors.noiCap}</span>
          )}
        </div>

        <div>
          <h3>Địa chỉ theo hộ khẩu:</h3>

          <label>Tỉnh, thành phố:</label>
          <div style={{ minHeight: "75px" }}>
            <Select
              style={{ width: "190px" }}
              placeholder="Chọn tỉnh/thành phố"
              onClick={() => handleInputClick("tinhThanhPhoHoKhau")}
              value={diaChiHoKhau.tinhThanhPho || undefined}
              onChange={(e) => {
                setDiaChiHoKhau((prev) => ({ ...prev, tinhThanhPho: e }));
                setXaPhuongOptions((prev) => ({
                  ...prev,
                  hoKhau: [],
                }));
                if (JSON.parse(e)?.code) {
                  fetchQuanHuyen(JSON.parse(e)?.code, "hokhau");
                }
              }}
            >
              {tinhThanhPhoOptions.map((tinhThanhPho, index) => (
                <option
                  key={index}
                  label={tinhThanhPho.name}
                  value={JSON.stringify(tinhThanhPho)}
                >
                  {tinhThanhPho.name}
                </option>
              ))}
            </Select>
            {errors.tinhThanhPhoHoKhau && (
              <span style={{ display: "block" }} className="error-message">
                {errors.tinhThanhPhoHoKhau}
              </span>
            )}
          </div>

          <label>Quận, huyện:</label>
          <div style={{ minHeight: "75px" }}>
            <Select
              style={{ width: "190px" }}
              placeholder="Chọn quận/huyện"
              onClick={() => handleInputClick("quanHuyenHoKhau")}
              value={diaChiHoKhau.quanHuyen || undefined}
              onChange={(e) => {
                setDiaChiHoKhau({ ...diaChiHoKhau, quanHuyen: e });
                if (JSON.parse(e)?.code) {
                  fetchXaPhuong(JSON.parse(e)?.code, "hokhau");
                }
              }}
            >
              {quanHuyenOptions.hoKhau.map((quanHuyen, index) => (
                <option
                  key={index}
                  label={quanHuyen.name}
                  value={JSON.stringify(quanHuyen)}
                >
                  {quanHuyen.name}
                </option>
              ))}
            </Select>
            {errors.quanHuyenHoKhau && (
              <span style={{ display: "block" }} className="error-message">
                {errors.quanHuyenHoKhau}
              </span>
            )}
          </div>

          <label>Xã, phường:</label>
          <div style={{ minHeight: "75px" }}>
            <Select
              style={{ width: "190px" }}
              placeholder="Chọn xã/phường"
              onClick={() => handleInputClick("xaPhuongHoKhau")}
              value={diaChiHoKhau.xaPhuong || undefined}
              onChange={(e) =>
                setDiaChiHoKhau({ ...diaChiHoKhau, xaPhuong: e })
              }
            >
              {xaPhuongOptions.hoKhau.map((xaPhuong, index) => (
                <option key={index} value={JSON.stringify(xaPhuong)}>
                  {xaPhuong.name}
                </option>
              ))}
            </Select>
            {errors.xaPhuongHoKhau && (
              <span style={{ display: "block" }} className="error-message">
                {errors.xaPhuongHoKhau}
              </span>
            )}
          </div>

          <div style={{ minHeight: "75px" }}>
            <label>Số nhà/đường phố, thôn, xóm:</label>
            <Input
              onClick={() => handleInputClick("diaChiHoKhau")}
              type="text"
              value={diaChiHoKhau.soNhaDuong}
              onChange={(e) =>
                setDiaChiHoKhau({ ...diaChiHoKhau, soNhaDuong: e.target.value })
              }
              placeholder="Số nhà/đường phố, thôn, xóm"
            />
            {errors.diaChiHoKhau && (
              <span className="error-message">{errors.diaChiHoKhau}</span>
            )}
          </div>
        </div>
        <div>
          <h3>Địa chỉ cư trú:</h3>

          <div style={{ minHeight: "75px" }}>
            <label>Tỉnh, thành phố:</label>
            <Select
              style={{ width: "190px" }}
              placeholder="Chọn tỉnh, thành phố"
              onClick={() => handleInputClick("tinhThanhPhoCuTru")}
              value={diaChiCuTru.tinhThanhPho || undefined}
              onChange={(e) => {
                setDiaChiCuTru((prev) => ({ ...prev, tinhThanhPho: e }));
                setXaPhuongOptions((prev) => ({
                  ...prev,
                  hoKhau: [],
                }));
                if (JSON.parse(e)?.code) {
                  fetchQuanHuyen(JSON.parse(e)?.code, "cutru");
                }
              }}
            >
              {tinhThanhPhoOptions.map((tinhThanhPho, index) => (
                <option
                  key={index}
                  label={tinhThanhPho.name}
                  value={JSON.stringify(tinhThanhPho)}
                >
                  {tinhThanhPho.name}
                </option>
              ))}
            </Select>
            {errors.tinhThanhPhoCuTru && (
              <span style={{ display: "block" }} className="error-message">
                {errors.tinhThanhPhoCuTru}
              </span>
            )}
          </div>

          <div style={{ minHeight: "75px" }}>
            <label>Quận, huyện:</label>
            <Select
              style={{ width: "190px" }}
              placeholder="Chọn quận, huyện"
              onClick={() => handleInputClick("quanHuyenCuTru")}
              value={diaChiCuTru.quanHuyen || undefined}
              onChange={(e) => {
                setDiaChiCuTru({ ...diaChiCuTru, quanHuyen: e });
                if (JSON.parse(e)?.code) {
                  fetchXaPhuong(JSON.parse(e)?.code, "cutru");
                }
              }}
            >
              {quanHuyenOptions.cuTru.map((quanHuyen, index) => (
                <option
                  key={index}
                  label={quanHuyen.name}
                  value={JSON.stringify(quanHuyen)}
                >
                  {quanHuyen.name}
                </option>
              ))}
            </Select>
            {errors.quanHuyenCuTru && (
              <span style={{ display: "block" }} className="error-message">
                {errors.quanHuyenCuTru}
              </span>
            )}
          </div>

          <div style={{ minHeight: "75px" }}>
            <label>Xã, phường:</label>
            <Select
              style={{ width: "190px" }}
              placeholder="Chọn xã, phường"
              onClick={() => handleInputClick("xaPhuongCuTru")}
              value={diaChiCuTru.xaPhuong || undefined}
              onChange={(e) => setDiaChiCuTru({ ...diaChiCuTru, xaPhuong: e })}
            >
              {xaPhuongOptions.cuTru.map((xaPhuong, index) => (
                <option key={index} value={JSON.stringify(xaPhuong)}>
                  {xaPhuong.name}
                </option>
              ))}
            </Select>
            {errors.xaPhuongCuTru && (
              <span style={{ display: "block" }} className="error-message">
                {errors.xaPhuongCuTru}
              </span>
            )}
          </div>
          <div style={{ minHeight: "75px" }}>
            <label>Số nhà/đường phố, thôn, xóm:</label>
            <Input
              onClick={() => handleInputClick("diaChiCuTru")}
              type="text"
              value={diaChiCuTru.soNhaDuong || undefined}
              onChange={(e) =>
                setDiaChiCuTru({ ...diaChiCuTru, soNhaDuong: e.target.value })
              }
              placeholder="Số nhà/đường phố, thôn, xóm"
            />
            {errors.diaChiCuTru && (
              <span className="error-message">{errors.diaChiCuTru}</span>
            )}
          </div>
        </div>

        <div style={{ minHeight: "75px" }}>
          <label>Điện thoại liên hệ:</label>
          <Input
            onClick={() => handleInputClick("dienThoai")}
            type="tel"
            value={dienThoai}
            onChange={(e) => setDienThoai(e.target.value)}
          />
          {errors.dienThoai && (
            <span className="error-message">{errors.dienThoai}</span>
          )}
        </div>
        <div style={{ minHeight: "75px" }}>
          <label>Email liên hệ:</label>
          <Input
            type="text"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            onClick={() => handleInputClick("email")}
          />
          {errors.email && (
            <span className="error-message">{errors.email}</span>
          )}
        </div>

        <div style={{ minHeight: "75px" }}>
          <label>Cơ quan chi trả thu nhập tại thời điểm đăng ký:</label>
          <Input
            onClick={() => handleInputClick("coQuanChiTra")}
            type="text"
            value={coQuanChiTra}
            onChange={(e) => setCoQuanChiTra(e.target.value)}
          />
          {errors.coQuanChiTra && (
            <span className="error-message">{errors.coQuanChiTra}</span>
          )}
        </div>
        <div style={{ display: "flex", justifyContent: "flex-end" }}>
          <button
            style={{ margin: "20px 0px 30px 0px", fontSize: 15, width: 100 }}
            type="submit"
          >
            Đăng ký
          </button>
        </div>
      </form>

      {registrationSuccess && (
        <div className="popup">
          <div className="popup-content">
            <h2>{popupTitle}</h2>
            <p>{popupMessage}</p>
            <button onClick={() => setRegistrationSuccess(false)}>Đóng</button>
          </div>
        </div>
      )}
    </div>
  );
};

export default DangKyMST;
