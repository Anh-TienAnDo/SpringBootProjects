import { get, post } from "../utils/request";

export const getAllTaxPayer = async (option) => {
  try {
    const res = await get(option);
    const data = await res.json();
    return data;
  } catch (err) {
    console.log(err);
    return [];
  }
};

export const getTaxPayer = async (option) => {
  try {
    const res = await get(option);
    const data = await res.json();
    return data;
  } catch (err) {
    console.log(err);
    return null;
  }
};

export const send = async (taxpayer,option) => {
  try {
    const res = await post(taxpayer,option);
    const data = await res.json();
    return data;
  } catch (err) {
    console.log(err);
    return [];
  }
};
