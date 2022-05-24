const getProductId=()=>{
    const data = document.getElementsByClassName("adding");
    var list = [];
    for (const dat of data) {
        dat.addEventListener('click',  (event) => {
            event.preventDefault();
            const route = dat.getAttribute('href');
            list.push(route);
            showNumber(list);
        });
    }
}


const showNumber=(list)=>{
    const placeToPlace=document.getElementById("shop-number");
    placeToPlace.innerHTML = "";
    const htmlCard = document.createElement("div");
    htmlCard.innerHTML = `<div><p>${list.length}</p>
</div>`;
    placeToPlace.appendChild(htmlCard);
}


const init =  () => {showNumber(getProductId());
}
init();
