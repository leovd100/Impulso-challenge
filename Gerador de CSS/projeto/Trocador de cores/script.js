const btn = document.querySelector(".btn")
const backGroud = document.querySelector("body")
const hex = [1,2,3,4,5,6,7,8,9,"A","B","C","D","E","F"]

const generateColor = () => {
    const info = document.querySelector(".info")
    let valueColor = "#"
    for(let i = 0; i< 6; i++){
        valueColor += hex[Math.floor(Math.random(0,hex.length)*10)].toString()
    }
    
    info.innerHTML = valueColor
    backGroud.style.backgroundColor  = valueColor
}





