
const URL="http://localhost:8888";

const deletFrom= async () =>{

const removeButton = document.getElementsByClassName("delete");
for(let r = 0; r< removeButton.length; r++){
    let rem = removeButton[r];
    rem.addEventListener("click", async (ev) =>{
            ev.preventDefault();
            let buttonClicked = ev.target;
        const productId=rem.dataset.id;
        await deleteStuff(productId);
        rem.parentNode.parentNode.parentNode.parentNode.removeChild(rem.parentNode.parentNode.parentNode)
        await updateCartTotal();

        }


    );

}}

const deleteStuff=async (productId)=> {
    const payload = {
        id: productId
    };
    const response = await fetch(URL + "/api/delete-product", {
        method: "POST",
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(payload)

    });
    const data = await response.json();
    await console.log(data);

}

async function updateCartTotal(){
    let cartItem = document.getElementsByClassName('cart_item_price');
    let prices = [];
    let quantity=0;
    for(let cart of cartItem){
        let values = cart.childNodes[3];
        let price = values.innerText;
        let quan=cart.previousElementSibling.childNodes[3].childNodes[3].textContent;
        quantity+=quan;
        console.log(quan);
        console.log(price)
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
    const nav=document.getElementById("shop-number");
    nav.textContent=quantity;
}
deletFrom().then();