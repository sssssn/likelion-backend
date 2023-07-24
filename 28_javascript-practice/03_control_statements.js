// Object 는 Map 과 유사
const person = {
    firstName: 'SN',
    lastName: 'Kwon',
    'phone number': '010-1234-5678'
}

// for ... in
// 객체를 키를 기준으로 순회
for (const key in person) {
    console.log(key)
    console.log(person[key])
}

const numbers = [10, 20, 30]

// for ... of
for (const number of numbers) {
    console.log(number)
}