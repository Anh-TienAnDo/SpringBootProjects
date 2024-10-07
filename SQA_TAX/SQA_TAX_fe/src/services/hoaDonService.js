import { post } from "../utils/request";

export const saveReceipt = async (receipt,option) => {
  try {
    const res = await post(receipt,option);
    const data = await res.json();
    return data;
  } catch (err) {
    console.log(err);
    return [];
  }
};