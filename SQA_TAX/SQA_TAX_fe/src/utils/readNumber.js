
import Config from '../config/ConfigNumber.js';
import INumberData from './INumberData';

export function readTwoDigits(b, c, hasHundred) {
    const output = [];

    switch (b) {
        case 0: {
            if (hasHundred && c === 0)
                break;
            if (hasHundred)
                output.push(Config.ODD_TEXT);
            output.push(Config.DIGITS[c]);
            break;
        }

        case 1: {
            output.push(Config.TEN_TEXT);
            if (c === 5)
                output.push(Config.FIVE_TONE_TEXT);
            else if (c !== 0)
                output.push(Config.DIGITS[c]);
            break;
        }

        default: {
            output.push(Config.DIGITS[b], Config.TEN_TONE_TEXT);
            if (c === 1)
                output.push(Config.ONE_TONE_TEXT);
            else if (c === 4 && b !== 4)
                output.push(Config.FOUR_TONE_TEXT);
            else if (c === 5)
                output.push(Config.FIVE_TONE_TEXT);
            else if (c !== 0)
                output.push(Config.DIGITS[c]);
            break;
        }
    }

    return output;
}

export function readThreeDigits(a, b, c, readZeroHundred) {
    const output = [];

    if (a !== 0 || readZeroHundred)
        output.push(Config.DIGITS[a], Config.HUNDRED_TEXT);

    output.push(...readTwoDigits(b, c, a !== 0 || readZeroHundred));

    return output;
}

export function parseNumberData(numberStr) {

    if (typeof numberStr !== 'string') {
        numberStr = String(numberStr); // Chuyển đổi an toàn hoặc bạn có thể return ở đây
    }

    const isNegative = numberStr[0] === Config.NEGATIVE_SIGN;
    let rawStr = isNegative ? numberStr.substring(1) : numberStr;
    let pointPos = rawStr.indexOf(Config.POINT_SIGN);

    let pos = 0;
    while (rawStr[pos] === Config.FILLED_DIGIT)
        pos++;
    rawStr = rawStr.substring(pos);

    if (pointPos !== -1) {
        let lastPos = rawStr.length - 1;
        while (rawStr[lastPos] === Config.FILLED_DIGIT)
            lastPos--;
        rawStr = rawStr.substring(0, lastPos + 1);
    }

    pointPos = rawStr.indexOf(Config.POINT_SIGN);
    const beforePointLength = pointPos === -1 ? rawStr.length : pointPos;
    let needZeroCount = 0;
    const modZeroCount = beforePointLength % Config.DIGITS_PER_PART;
    if (modZeroCount !== 0)
        needZeroCount = Config.DIGITS_PER_PART - modZeroCount;

    let fullStr = '';
    for (let i = 0; i < needZeroCount; i++)
        fullStr += Config.FILLED_DIGIT;
    fullStr += rawStr;

    const digits = [];
    const digitsAfterPoint = [];

    pointPos = fullStr.indexOf(Config.POINT_SIGN);
    for (let i = 0; i < fullStr.length; i++)
        if (i !== pointPos) {
            const digit = parseInt(fullStr[i]);
            if (isNaN(digit))
                throw new Error('Số không hợp lệ');
            if (pointPos === -1 || i < pointPos)
                digits.push(digit);
            else
                digitsAfterPoint.push(digit);
        }

    const result = { isNegative, digits, digitsAfterPoint };
    return result;
}

export function readVietnameseNumber(numberData) {

    numberData = parseNumberData(numberData)

    const output = [];
    const partCount = Math.round(numberData.digits.length / Config.DIGITS_PER_PART);

    for (let i = 0; i < partCount; i++) {
        const a = numberData.digits[i * Config.DIGITS_PER_PART];
        const b = numberData.digits[i * Config.DIGITS_PER_PART + 1];
        const c = numberData.digits[i * Config.DIGITS_PER_PART + 2];

        const isFirstPart = i === 0;
        const isSinglePart = partCount === 1;
        if (a !== 0 || b !== 0 || c !== 0 || isSinglePart)
            output.push(
                ...readThreeDigits(a, b, c, !isFirstPart),
                ...Config.UNITS[partCount - i - 1]);
    }

    if (numberData.digitsAfterPoint.length !== 0)
        output.push(Config.POINT_TEXT);
    switch (numberData.digitsAfterPoint.length) {
        case 0:
            break;
        case 1: case 2: {
            const b = numberData.digitsAfterPoint[0];
            const c = numberData.digitsAfterPoint[1];
            output.push(...readTwoDigits(b, c, true));
            break;
        }
        case 3: {
            const a = numberData.digitsAfterPoint[0];
            const b = numberData.digitsAfterPoint[1];
            const c = numberData.digitsAfterPoint[2];
            output.push(...readThreeDigits(a, b, c, true));
            break;
        }
        default: {
            for (let i = 0; i < numberData.digitsAfterPoint.length; i++)
                output.push(Config.DIGITS[numberData.digitsAfterPoint[i]]);
        }
    }

    if (numberData.isNegative)
        output.unshift(Config.NEGATIVE_TEXT);
    output.push(Config.UNIT);

    return output.join(Config.SEPARATOR);
}

