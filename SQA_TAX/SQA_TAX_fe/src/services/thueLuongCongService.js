import { post } from "../utils/request";

export const saveKeKhaiThueLuongCong = async (data,path) => {
  try {
    const res = await post(data,path )
    return res
  } catch (error) {
    return []
  }
}