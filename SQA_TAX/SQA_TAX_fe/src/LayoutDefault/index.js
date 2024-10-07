/* eslint-disable no-unused-vars */
import { Layout, Flex, Button, Collapse, Image, Table } from "antd";
import { Outlet, useNavigate } from "react-router-dom";
import {
  MenuUnfoldOutlined,
  SearchOutlined,
  BellOutlined,
  AppstoreOutlined,
  UserOutlined,
} from "@ant-design/icons";
import logo from "../image/logo-short.png";
import logo_tax from "../image/logo-tax.jpeg";
import { useContext, useEffect, useState } from "react";
import DropDownNotify from "./DropDownNotify";
import MenuSider from "../components/MenuSider";
import { Link, useLocation } from "react-router-dom";
import "./style.scss";
import { render } from "@testing-library/react";
import RenderFirst from "../context/renderFirst";
import TaxWantPay from "../context/taxWantPay";
const { Footer, Sider, Content } = Layout;

function LayoutDefault() {
  const { taxWantPay, setTaxWantPay } = useContext(TaxWantPay);
  const navigate = useNavigate()
  const [collapsed, setCollapsed] = useState(false);
  const [pageTitle, setPageTitle] = useState("");
  const {renderFirst, setRenderFirst} = useContext(RenderFirst)

  // useEffect( () => {
  //   console.log(renderFirst)
  //   if(!renderFirst){
  //     console.log(renderFirst)
  //     setRenderFirst(true)
  //     navigate('/dang-nhap')
  //   }
  // },[])
  const [token] = useState(JSON.parse(localStorage.getItem("info")));

  useEffect(() => {
    if(!token){
      navigate("/dang-nhap")
    }
  }, [token]);
  const location = useLocation();
  const userInfo = localStorage.getItem("info")
    ? JSON.parse(localStorage.getItem("info"))
    : null;

  useEffect(() => {
    setTaxWantPay([])
    switch (location.pathname) {
      case "/":
        setPageTitle("Trang chủ");
        break;
      case "/receipt":
        setPageTitle("Hóa đơn thuế thu nhập cá nhân");
        break;
      case "/thu-thue":
        setPageTitle("Thu thuế");
        break;
      case "/ke-khai-thue":
        setPageTitle("Kê Khai Thuế");
        break;
      case "/dang-ky-ma-so-thue":
        setPageTitle("Đăng Ký Mã Số Thuế");
        break;
      default:
        break;
    }
  }, [location]);

  return (
    <>
    {token && (
        <Layout className="layout-default">
        <header className="header">
          <Link to="/">
            <div
              className={
                "header__logo " + (collapsed && "header__logo-collapsed")
              }
            >
              <img src={collapsed ? logo : logo_tax} alt="anh loi" />
            </div>
          </Link>
          <div className="header__nav">
            <div className="header__nav-left">
              <div
                className="header__collapse"
                onClick={() => {
                  setCollapsed(!collapsed);
                }}
              >
                <Button
                  className="header__menu-fold"
                  icon={<MenuUnfoldOutlined />}
                ></Button>
              </div>
            </div>
  
            <div className="header__nav-center">
              <h1>{pageTitle}</h1>
            </div>
  
            <div className="header__nav-right">
              <div style={{
                display: 'flex',
                margin: 'auto'
              }}>
                {userInfo ? `Xin chào ${userInfo.name}` : null}
              </div>
              <div className="header__bell">
                <UserOutlined />
              </div>
              <div className="header__app-store">
                <AppstoreOutlined />
              </div>
            </div>
          </div>
        </header>
  
        <Layout>
          <Sider width={"280px"} theme="light" collapsed={collapsed}>
            <MenuSider />
          </Sider>
          <Content className="content">
            <Outlet />
          </Content>
        </Layout>
      </Layout>
      )
    }
    </>
  );
}
export default LayoutDefault;
