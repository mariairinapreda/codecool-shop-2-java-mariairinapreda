const URL="http://localhost:8888";









const actionOnPressAdd=async ()=> {
    const data = document.getElementsByClassName("add");
    for (const dat of data) {
        dat.addEventListener('click', async (event) => {
            event.preventDefault();
            const productId=dat.dataset.id;
            await increaseNrOfProd(productId);
            const past=dat.parentNode.parentNode.children[2].textContent;
            const now=parseInt(past)+1;
            dat.parentNode.parentNode.children[2].textContent=now;
            const oldPrice=dat.parentNode.parentNode.parentNode.children[4].children[1].textContent;
           const info=dat.parentNode.parentNode.parentNode.children[3].children[1].textContent;
           let justPrice="";
           for (const infoElement of info) {
                if(!isNaN(infoElement))justPrice+=infoElement;
            }
            dat.parentNode.parentNode.parentNode.children[4].children[1].textContent=parseInt(oldPrice)+parseInt(justPrice);
            console.log(dat.parentNode.parentNode.parentNode);

        });
    }
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


