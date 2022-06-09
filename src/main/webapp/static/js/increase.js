const URL="http://localhost:8888";









const actionOnPressAdd=async ()=> {
    const data = document.getElementsByClassName("add");

    for (const dat of data) {
        dat.addEventListener('click', async (event) => {
            event.preventDefault();
            const productId=dat.dataset.id;
            await increaseNrOfProd(productId);
            const past=dat.parentNode.parentNode.parentNode.parentNode.children[2].children[1].children[1].textContent;
            dat.parentNode.parentNode.parentNode.parentNode.children[2].children[1].children[1].textContent=parseInt(past)+1;
            const oldPrice=dat.parentNode.parentNode.parentNode.parentNode.children[4].children[1].textContent;
           const info=dat.parentNode.parentNode.parentNode.parentNode.children[3].children[1].textContent;
           let justPrice="";
           for (const infoElement of info) {
                if(!isNaN(infoElement))justPrice+=infoElement;
            }
            dat.parentNode.parentNode.parentNode.parentNode.children[4].children[1].textContent=parseInt(oldPrice)+parseInt(justPrice);
updateCartTotl();
const nav=document.getElementById("shop-number");
nav.textContent=parseInt(nav.textContent)+1;
        });
    }
}
async function updateCartTotl(){
    let cartItem = document.getElementsByClassName('cart_item_price');
    let prices = [];
    for(let cart of cartItem){
        let values = cart.childNodes[3];
        let price = values.innerText;
        let quan=cart.previousElementSibling.childNodes[3].childNodes[3].textContent;
        let priceString = "";
        for(let i=0; i< price.length; i++){
            if(!/^[a-zA-Z]+$/.test(price[i]) ){
                priceString += price[i]*quan;
            }
        }
        prices.push(priceString.slice(0, -1));
    }
    let finalP = document.getElementById("total");
    if(prices.length < 2){
        finalP.innerText = prices[0];
    }
    else{
    let sum = 0;
    sum = prices.reduce((partialSum, a) => partialSum + (a*1), 0);
    finalP.innerText = sum;}
}
const increaseNrOfProd=async (productId)=> {
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





const initialize=async ()=>{
    await actionOnPressAdd();
}
initialize().then();


