const URL="http://localhost:8888";


const saveButtton=async ()=>{
    const but=document.getElementById("crazy");
    but.addEventListener("click", async () => {
        const url=URL+"/api/cart-products";
        const products=document.querySelectorAll(".clearfix");
        const list=[];
        for(let product of products){
             let qutity=product.children[0].children[2].children[1].children[1].textContent;
    list.push([product.dataset.id,qutity])

        }
await sendIds(list);
    })
}

const sendIds=async (IDS)=>{
    const payload = {
        id: IDS
    };
    const response = await fetch(URL + "/api/cart-products", {
        method: "POST",
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(payload)
    });
    const data = await response.json();
    await console.log(data);
}
const start=async()=>{
    await saveButtton();
}
start().then();