import { Menu } from "antd";
import {Link} from 'react-router-dom'
import {
  AccountBookOutlined,
} from "@ant-design/icons";
function getItem(label, key, icon, children, type) {
  return {
    key,
    icon,
    children,
    label,
    type,
  };
}

const items = [
  getItem(<Link to="/">Trang chủ</Link>, "sub1", <AccountBookOutlined />, ),
  getItem(<Link to="/dang-ky-ma-so-thue">Đăng ký mã số thuế</Link>, "sub2", <AccountBookOutlined />, ),
  getItem(<Link to="/ke-khai-thue">Kê khai thuế</Link>, "sub3", <AccountBookOutlined />, ),
  getItem(<Link to="/thu-thue">Thu thuế</Link>, "sub4", <AccountBookOutlined />, ),
];

function MenuSider() {
  return (
    <Menu
      mode="inline"
      items={items}
      defaultSelectedKeys={["sub1"]}
    />
  );
}

export default MenuSider;
