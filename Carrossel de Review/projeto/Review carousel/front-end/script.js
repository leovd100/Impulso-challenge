
const leftButton = document.querySelector(".left-button")
const rightButton = document.querySelector(".right-button")
const bgPhoto = document.querySelector(".photo")
const name = document.querySelector(".name")
const job = document.querySelector(".job")
const surprise = document.querySelector(".surprise_me")
const url = "http://127.0.0.1:5000"




async function getElements(url){
    const response = await fetch(`${url}/users`)
    const jsonResponse = await response.json()
    return jsonResponse
}


let countPage = 0


getElements(url).then(element => {

    const updateData = (value) => {    
        if(countPage <= -1){
            countPage = Object.keys(element).length -1
        }else if (countPage > (Object.keys(element).length -1)){
            countPage = 0
        }else {
            countPage = value
        }
    

        bgPhoto.style.backgroundImage = `url(${element[countPage].photo})`
        name.innerHTML = element[countPage].name
        job.innerHTML = element[countPage].job
    }

    leftButton.addEventListener("click", () => {
        updateData((countPage -= 1))
    })

    rightButton.addEventListener("click", () => {
        updateData((countPage += 1))

    })


    surprise.addEventListener("click", (e)=> {
        const random = Math.floor(Math.random() * Object.keys(element).length)
        updateData(random)
    })

})