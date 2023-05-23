let btn = document.querySelector(".search")
let inputSearch = document.querySelector(".inputSearch")
let imgProduct = document.querySelector(".img__product")

let productInfo = document.querySelector(".info__product")
let containerCard = document.querySelector(".card")

btn.addEventListener("click", (e) => {
    e.preventDefault()
    let response = logJSONData()
    let find = false
    
    response.then(elements => {
      elements.forEach(element => {
          if(element.name.toUpperCase() == inputSearch.value.toUpperCase()){
            containerCard.appendChild(cardGameFactory(element.img))
            productInfo.innerHTML = element.name
            find = true
          }
      }); 
    })

    if (!find){

        containerCard.removeChild(document.querySelector(".card_game"))
        productInfo.innerHTML = "Item não encontrado"
    }


})

async function logJSONData() {
  const response =  await fetch("http://localhost:3001/products");
  const jsonData = await response.json();
  return jsonData
}



function cardGameFactory(url){
  let cardGame = document.createElement("div")
  let imageGame = document.createElement("div")
  cardGame.className = "card_game"
  imageGame.className = "img__product"
  imageGame.style.backgroundImage = `url("${url}")`
  cardGame.appendChild(imageGame)
  return cardGame


}