
const ConfigNumber = {
    SEPARATOR: ' ',
    UNIT: 'đồng',

    DIGITS: [
        'không', 'một', 'hai', 'ba', 'bốn',
        'năm', 'sáu', 'bảy', 'tám', 'chín'
    ],
    UNITS: [
        [], ['nghìn'], ['triệu'], ['tỉ'],
        ['nghìn', 'tỉ'], ['triệu', 'tỉ'], ['tỉ', 'tỉ']
    ],

    NEGATIVE_SIGN: '-',
    POINT_SIGN: '.',
    DIGITS_PER_PART: 3,
    FILLED_DIGIT: '0',

    NEGATIVE_TEXT: 'âm',
    POINT_TEXT: 'chấm',
    ODD_TEXT: 'lẻ',
    TEN_TEXT: 'mười',
    HUNDRED_TEXT: 'trăm',

    ONE_TONE_TEXT: 'mốt',
    FOUR_TONE_TEXT: 'tư',
    FIVE_TONE_TEXT: 'lăm',
    TEN_TONE_TEXT: 'mươi'
};

export default ConfigNumber

