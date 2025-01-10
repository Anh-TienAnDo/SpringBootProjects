import { Card, Select } from "antd"
import ThueTienLuongCong from "../ThueTienLuongCong"
import { useContext, useEffect, useState } from "react";
import ThueChuyenNhuongBDS from "../ThueChuyenNhuongBDS";
import ThueTrungThuong from "../ThueTrungThuong";
import ThueChuyenNhuongBanQuyen from "../ThueChuyenNhuongBanQuyen";
import ThueDauTuVon from "../ThueDauTuVon";
import ThueNhuongQuyenThuongMai from "../ThueNhuongQuyenThuongMai";
import ThueQuaTang from "../ThueQuaTang";
import { getAllLoaiThue } from "../../services/loaiThueService";
import TaxWantPay from "../../context/taxWantPay";

function LoaiToKhai() {
    const { taxWantPay, setTaxWantPay } = useContext(TaxWantPay);
    const [loaiToKhai, setLoaiToKhai] = useState();
    const [danhSachLoaiToKhai, setDanhSachLoaiToKhai] = useState();
    useEffect(() => {
        setTaxWantPay([])
        const fetch = async () => {
            const res = await getAllLoaiThue('api/v1/loai-thue/getAll')
            setDanhSachLoaiToKhai(res)
        }
        fetch()
    },[])
    
    const options = danhSachLoaiToKhai?.map( (item) => {
        const data = {
            label: "Tờ khai " + item.name.toLowerCase(),
            value: item.id
        }
        return data
    })

    const handleChange = (value) => {
        setLoaiToKhai(value)
    }
    return (
        <>
            <Card title="Chọn thông tin loại tờ khai" style={{ marginBottom: 20 }}>
                <Select options={options} placeholder="-- Chọn loại tờ khai --" onChange={handleChange} style={{ width: "100%" }} />
            </Card>
            {loaiToKhai === 1 && (
                <ThueTienLuongCong loai_thue_id={1}/>
            )}
            {loaiToKhai === 2 && (
                <ThueChuyenNhuongBDS loai_thue_id={2}/>
            )}
            {loaiToKhai === 3 && (
                <ThueDauTuVon loai_thue_id={3}/>
            )}
            {loaiToKhai === 4 && (
                <ThueQuaTang loai_thue_id={4}/>
            )}
            {loaiToKhai === 5 && (
                <ThueNhuongQuyenThuongMai loai_thue_id={5}/>
            )}
            {loaiToKhai === 6 && (
                <ThueTrungThuong loai_thue_id={6}/>
            )}
            {loaiToKhai === 7 && (
                <ThueChuyenNhuongBanQuyen loai_thue_id={7}/>
            )}
        </>
    )
}
export default LoaiToKhai