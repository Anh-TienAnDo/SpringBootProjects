const  FormatDateTime = (date) => {
  try {
    const day = date.getDate().toString().padStart(2, '0');
  const month = (date.getMonth() + 1).toString().padStart(2, '0'); // Tháng trong JavaScript được đếm từ 0, do đó cần cộng thêm 1
  const year = date.getFullYear();
  const hour = date.getHours().toString().padStart(2, '0');
  const minute = date.getMinutes().toString().padStart(2, '0');
  const second = date.getSeconds().toString().padStart(2, '0');

  return `${day}-${month}-${year} ${hour}:${minute}:${second}`;
  } catch (error) {
  }
}

export const  FormatTimeOnlyDMY = (date) => {
  try {
    date = new Date(date.toString());
    var dateFormat = date.toLocaleDateString('vi-VN', {
      day: '2-digit',
      month: '2-digit',
      year: 'numeric'
  });
    return dateFormat
  } 
  catch (error) {
  }
  
}


export default FormatDateTime
