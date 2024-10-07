import LayoutDefault from "../LayoutDefault";
import ReceiptTax from "../pages/Receipt";
import TaxPay from "../pages/TaxPay";
import TaxPayerInfor from "../components/TaxPayerInfor";
import UnpaidTax from "../components/UnpaidTax";
import LoaiToKhai from "../pages/LoaiToKhai";
import DangKyMST from "../pages/DangKyMaSoThue";
import Login from "../pages/Login/Login";
export const routes = [
  {
    path: "/",
    element: <LayoutDefault />,
    children: [
      {
        path: "/thu-thue",
        element: <TaxPay />,
        children: [
          {
            path: "thong-tin-nguoi-dong-thue",
            element: <TaxPayerInfor />,
          },
          {
            path: "thue-chua-dong",
            element: <UnpaidTax />,
          },
        ],
      },
      {
        path: "ke-khai-thue",
        element: <LoaiToKhai />,
      },
      {
        path: "/dang-ky-ma-so-thue",
        element: <DangKyMST />,
      },
    ],
  },
  {
    path: "/dang-nhap",
    element: <Login></Login>,
  },
];
