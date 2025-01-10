"use strict";

Object.defineProperty(exports, "__esModule", {
  value: true
});
exports["default"] = void 0;

var _routes = require("../../routes");

var _reactRouterDom = require("react-router-dom");

function AllRoute() {
  var elements = (0, _reactRouterDom.useRoutes)(_routes.routes);
  return elements;
}

var _default = AllRoute;
exports["default"] = _default;