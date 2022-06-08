const URL="http://localhost:8888";









const actionOnPressMinus=async ()=> {
    const data = document.getElementsByClassName("decrease");
    for (const dat of data) {
        dat.addEventListener('click', async (event) => {
            event.preventDefault();
            const productId=dat.dataset.id;
            await DecreaseNrOfProd(productId);
            const past=dat.parentNode.parentNode.children[2].textContent;
            dat.parentNode.parentNode.children[2].textContent=parseInt(past)-1;
            const oldPrice=dat.parentNode.parentNode.parentNode.children[4].children[1].textContent;
            const info=dat.parentNode.parentNode.parentNode.children[3].children[1].textContent;
            let justPrice="";
            for (const infoElement of info) {
                if(!isNaN(infoElement))justPrice+=infoElement;
            }
            if(parseInt(oldPrice)-parseInt(justPrice)>0){
            dat.parentNode.parentNode.parentNode.children[4].children[1].textContent=parseInt(oldPrice)-parseInt(justPrice);
                console.log(dat.parentNode.parentNode.parentNode.parentNode.parentNode.parentNode.parentNode.children[2].children);

            }
            else{
                dat.parentNode.parentNode.parentNode.parentNode.removeChild(dat.parentNode.parentNode.parentNode)

            }

        });
    }
}
const DecreaseNrOfProd=async (productId)=> {
    const payload = {
        id: productId
    };
    const response = await fetch(URL + "/api/decrease-cart", {
        method: "POST",
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(payload)

    });
    const data = await response.json();
    await console.log(data);

}






const entr=async ()=>{
    await actionOnPressMinus();
}
entr().then();

