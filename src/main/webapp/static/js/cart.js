const getProductId=()=>{
    const data = document.getElementsByClassName("adding");
    var list = [];
    for(const dat of data) {
        dat.addEventListener('click',  (event) => {
            event.preventDefault();
            const route = dat.getAttribute('href');
            list.push(route);
            showNumber(list);
        });
    }

}
const action=async ()=>{
    const divs=document.getElementById("icon");
    divs.addEventListener("click", async () => {
        const placeToPlaces = document.getElementById("data");
        placeToPlaces.innerHTML = `<div class="modal" tabindex="-1">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">Cart content</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <p>Modal body text goes here.</p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary">Save changes</button>
      </div>
    </div>
  </div>
</div>`;


    })}


const getData = async (route) => {
    const response = await fetch(route);
    return await response.json();
}

const showNumber=(list)=>{
    const placeToPlace=document.getElementById("shop-number");
    placeToPlace.innerHTML = "";
    const htmlCard = document.createElement("div");
    htmlCard.innerHTML = `<div><p>${list.length}</p>
</div>`;}
const init=()=>{
    getProductId();
    action().then();
}
init().then();
