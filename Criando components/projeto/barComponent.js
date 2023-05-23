class MyBar extends HTMLElement{
    constructor(){
        super()

        const shadow = this.attachShadow({
            mode : "open"
        })


        const raiz = document.createElement('header')
        raiz.setAttribute('class', 'header-component')

        const title = document.createElement("div")
        title.setAttribute("class", "title-header")

        title.textContent = this.getAttribute("title")

        raiz.appendChild(title)

        const style = document.createElement("style")
        style.textContent = `
        
            .header-component{
                display: flex;
                align-items: center;
                justify-content: space-between;
                height: 5rem;
                background: linear-gradient(-45deg,${this.getAttribute("first-color") || "#000"}, ${this.getAttribute("second-color") || "#fff"});
                margin-top: 35px;
                box-shadow: 0px 0px 18px 1px black;
            }

            .title-header{
                font-family: 'Arial';
                font-size: 32px;
                margin: 10px;
                color: ${this.getAttribute("color-text") || "000"}
            }
        
            
        `
        shadow.appendChild(style)
        shadow.appendChild(raiz)

    }
}


customElements.define('component-bar', MyBar)