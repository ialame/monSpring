import SecondTimer from "/components/SecondTimer.js"
import Datepicker from "/components/Datepicker.js"
import MyButton from "./components/MyButton.js";

customElements.define('second-timer',SecondTimer)
window.customElements.define('date-picker',Datepicker,{extends:'input'})
customElements.define('my-button',MyButton)

document.querySelector('#add').addEventListener(
    'click',function(){
        //document.body.appendChild(new SecondTimer())
        //document.querySelector('second-timer').remove()
        //document.querySelector('second-timer').setAttribute('prefix','Demonstration')
        //document.body.appendChild(new Datepicker())
        const template = document.querySelector("#demo")
        const templateContent = template.content
        document.body.appendChild(templateContent.cloneNode(true))
    })
/*
import SecondTimer from "../static/components/SecondTimer.js";
import Datepicker from "../static/components/Datepicker.js"
import MyButton from "../static/components/MyButton.js";
customElements.define('second-timer',SecondTimer)
customElements.define('date-picker',Datepicker,{extends:'input'})
customElements.define('my-button',MyButton)
document.querySelector('#add').addEventListener(
    'click',function(){
        document.body.appendChild(new Datepicker())

    })*/
