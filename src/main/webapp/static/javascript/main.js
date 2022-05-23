const getProductid=()=>{
    const data = document.getElementsByClassName("adding");
    console.log(data);
    for (const navLink of data) {
        console.log("nothing");
        navLink.addEventListener('click', async (event) => {
            event.preventDefault();
            const route = navLink.getAttribute('href');});
    }

}




const init =  () => {getProductid();
}
init();
