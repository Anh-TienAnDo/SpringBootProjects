"use strict";

Object.defineProperty(exports, "__esModule", {
  value: true
});
exports.postBooking = exports.getListBooking = void 0;

var _request = require("../utils/request");

var getListBooking = function getListBooking(option) {
  var res, data;
  return regeneratorRuntime.async(function getListBooking$(_context) {
    while (1) {
      switch (_context.prev = _context.next) {
        case 0:
          _context.next = 2;
          return regeneratorRuntime.awrap((0, _request.get)(option));

        case 2:
          res = _context.sent;
          _context.next = 5;
          return regeneratorRuntime.awrap(res.json());

        case 5:
          data = _context.sent;
          return _context.abrupt("return", data);

        case 7:
        case "end":
          return _context.stop();
      }
    }
  });
};

exports.getListBooking = getListBooking;

var postBooking = function postBooking(data, option) {
  var res;
  return regeneratorRuntime.async(function postBooking$(_context2) {
    while (1) {
      switch (_context2.prev = _context2.next) {
        case 0:
          _context2.next = 2;
          return regeneratorRuntime.awrap((0, _request.post)(data, option));

        case 2:
          res = _context2.sent;
          return _context2.abrupt("return", res);

        case 4:
        case "end":
          return _context2.stop();
      }
    }
  });
};

exports.postBooking = postBooking;