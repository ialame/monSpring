export default class MyButton extends HTMLElement{
    constructor() {
        super();
        this.shadow = this.attachShadow({mode:'closed'})
        this.shadow.innerHTML= `
                <style>
                    button{
                        border: none;
                        background: #000;
                        color: #FFF;
                        border-radius: 3px;
                        padding: 3px 10px;
                    }
                </style>
                <slot name="outbutton"></slot>
                <button> <slot name="inbutton"/> </button>
        `
    }
}