import { get, post } from "../utils/request";

export const getAllCategoryTax = async (option) => {
  try {
    const res = await get(option);
    const data = await res.json();
    return data;
  } catch (err) {
    console.log(err);
    return [];
  }
};
