"use strict";

Object.defineProperty(exports, "__esModule", {
  value: true
});
exports.updateRoom = exports.deleteRoom = exports.getListRoom = exports.postRoom = void 0;

var _request = require("../utils/request");

var postRoom = function postRoom(data, options) {
  var res;
  return regeneratorRuntime.async(function postRoom$(_context) {
    while (1) {
      switch (_context.prev = _context.next) {
        case 0:
          _context.next = 2;
          return regeneratorRuntime.awrap((0, _request.post)(data, options));

        case 2:
          res = _context.sent;
          return _context.abrupt("return", res);

        case 4:
        case "end":
          return _context.stop();
      }
    }
  });
};

exports.postRoom = postRoom;

var getListRoom = function getListRoom(options) {
  var res, data;
  return regeneratorRuntime.async(function getListRoom$(_context2) {
    while (1) {
      switch (_context2.prev = _context2.next) {
        case 0:
          _context2.next = 2;
          return regeneratorRuntime.awrap((0, _request.get)(options));

        case 2:
          res = _context2.sent;
          _context2.next = 5;
          return regeneratorRuntime.awrap(res.json());

        case 5:
          data = _context2.sent;
          console.log(data);
          return _context2.abrupt("return", data);

        case 8:
        case "end":
          return _context2.stop();
      }
    }
  });
};

exports.getListRoom = getListRoom;

var deleteRoom = function deleteRoom(id, options) {
  var res;
  return regeneratorRuntime.async(function deleteRoom$(_context3) {
    while (1) {
      switch (_context3.prev = _context3.next) {
        case 0:
          _context3.next = 2;
          return regeneratorRuntime.awrap((0, _request.del)(id, options));

        case 2:
          res = _context3.sent;
          return _context3.abrupt("return", res);

        case 4:
        case "end":
          return _context3.stop();
      }
    }
  });
};

exports.deleteRoom = deleteRoom;

var updateRoom = function updateRoom(id, options) {
  var res;
  return regeneratorRuntime.async(function updateRoom$(_context4) {
    while (1) {
      switch (_context4.prev = _context4.next) {
        case 0:
          _context4.next = 2;
          return regeneratorRuntime.awrap((0, _request.update)("rooms/".concat(id), options));

        case 2:
          res = _context4.sent;
          return _context4.abrupt("return", res);

        case 4:
        case "end":
          return _context4.stop();
      }
    }
  });
};

exports.updateRoom = updateRoom;