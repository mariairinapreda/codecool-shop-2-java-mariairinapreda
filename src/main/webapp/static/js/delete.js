
const removeButton = document.getElementsByClassName("delete");
for(let r = 0; r< removeButton.length; r++){
    let rem = removeButton[r];
    rem.addEventListener("click", async (ev) =>{
            ev.preventDefault();
            let buttonClicked = ev.target;
            buttonClicked.parentElement.parentElement.parentElement.remove();
            await updateCartTotal();
    }


    );

}

async function updateCartTotal(){
    let cartItem = document.getElementsByClassName('cart_item_price');
    let prices = [];
    for(let cart of cartItem){
            let values = cart.childNodes[3];
            let price = values.innerText;
            console.log(price)
            let priceString = "";
            for(let i=0; i< price.length; i++){
                if(!/^[a-zA-Z]+$/.test(price[i]) ){
                    priceString += price[i];
                }
            }
        prices.push(priceString.slice(0, -1));
    }
    let finalP = document.getElementById("total");
    if(prices.length < 2){
        finalP.innerText = prices[0];
    }
    let sum = 0;
    sum = prices.reduce((partialSum, a) => partialSum + (a*1), 0);
    finalP.innerText = sum;
}