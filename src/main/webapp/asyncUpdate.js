window.onload = setupEventHandlers;
var string1;
var id;

//setting up the event handlers for the button
function setupEventHandlers() {

    for (let i = 1; i <= 25; i++) {
        var string = "ajaxBt"+i.toString();
        var element = document.getElementById(string);
        if(typeof(element) != 'undefined' && element != null){
            string1 = "ajaxBt"+i.toString();
            id = i;
        } else{
        }
    }

    var ajaxBtn = document.getElementById(string1);
    ajaxBtn.onclick = handleAjaxBtn;
    setInterval(function(){
        const start = new Date().toLocaleTimeString();
        console.log("When the button was pressed: "+start);

        ajaxBtn.click();
        counter++
    },60000);  // this will make it click again every 60000 miliseconds
}

//Event handlers for the buttons
function handleAjaxBtn(){
    var xmlhrObj = new XMLHttpRequest();
    xmlhrObj.open("GET", "http://localhost:8080/Weather_Forecast_war/asyncUpdate?type=1&id="+id, true);
    xmlhrObj.onreadystatechange = function(){
        var div = document.getElementById("reqResponse");
        div.innerHTML = xmlhrObj.responseText;
    };

    xmlhrObj.send();
}
