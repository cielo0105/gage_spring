const changeMoney = (value) => {
  if(!value) return;
  value = Number(value.toString().replace(/,/g, ""));
  const numbers = [
    numbering(value % 100000000, 10000),
    numbering(value % 10000, 1000),
  ];

  return setUnitText(numbers)
    .filter((number) => !!number)
    .join(" ");
};

const numbering = (value, division) => {
  const result = Math.floor(value / division);
  return result === 0 ? null : result % division;
};

function setUnitText(numbers) {
  const unit = ["천", "억"];
  return numbers.map((number, index) =>
    !!number ? numberFormat(number) + unit[unit.length - 1 - index] : number
  );
}

const NUMBER_FORMAT_REGX = /\B(?=(\d{3})+(?!\d))/g;

const numberFormat = (value) => {
  return value.toString().replace(NUMBER_FORMAT_REGX, ",");
};
