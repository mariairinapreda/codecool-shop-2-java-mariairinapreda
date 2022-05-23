function getProductId(){
    const data = document.querySelectorAll(".adding");

    data.forEach(dat=>console.log(dat));
    for (const dat of data) {
        console.log("nothing");
        dat.addEventListener('click',  (event) => {
            event.preventDefault();
            const route = dat.getAttribute('href');});
    }

}


getProductId();

const init =  () => {getProductId();
}
init();
