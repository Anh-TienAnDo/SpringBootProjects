import React, { StrictMode } from "react";
import ReactDOM from "react-dom/client";
import App from "./App";
import reportWebVitals from "./reportWebVitals";
import { BrowserRouter } from "react-router-dom";
import { AuthenTaxPayerProvider } from "./context/afterAuthenTaxPayer";
import { SearchProvider } from "./context/search";
import { UnpaidTaxProvider } from "./context/unpaidTax";
import { TaxWantPayProvider } from "./context/taxWantPay";
import { TaxPayerProvider } from "./context/taxPayer";
import RenderFirst, { RenderFirstProvider } from "./context/renderFirst";
// import { createStore } from "redux";
const root = ReactDOM.createRoot(document.getElementById("root"));
// const store = createStore(allReducers);
root.render(
  <StrictMode>
    <RenderFirstProvider>
    <TaxPayerProvider>
      <TaxWantPayProvider>
        <UnpaidTaxProvider>
          <SearchProvider>
            <AuthenTaxPayerProvider>
              <BrowserRouter>
                <App />
              </BrowserRouter>
            </AuthenTaxPayerProvider>
          </SearchProvider>
        </UnpaidTaxProvider>
      </TaxWantPayProvider>
    </TaxPayerProvider>
    </RenderFirstProvider>
  </StrictMode>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
