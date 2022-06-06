import {dataHandler} from "./dataHandler.js";
const URL="http://localhost:8888";





const getProductId=async ()=> {
    const data = document.getElementsByClassName("adding");
    var list = [];
    for (const dat of data) {
        dat.addEventListener('click', async (event) => {
            event.preventDefault();
           const productId=dat.dataset.id;
          await makePostRequest(productId);
          list.push(productId);
            showNumber(list);
        });
    }

}
const makePostRequest=async (productId)=> {
    const payload = {
        id: productId
    };
    const response = await fetch(URL + "/api/add-to-cart", {
        method: "POST",
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(payload)

    });
    const data = await response.json();
    await console.log(data);

}

const showNumber=(list)=>{
    const placeToPlace=document.getElementById("shop-number");
    console.log(placeToPlace);
    placeToPlace.innerHTML = "";
    const htmlCard = document.createElement("div");
    htmlCard.innerHTML = `<div><p>${list.length}</p>
</div>`;
placeToPlace.appendChild(htmlCard)}
const init=async ()=>{
    await getProductId();
}
init().then();


