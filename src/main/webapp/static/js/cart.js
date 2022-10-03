
const URL = "http://localhost:8888";


const getProductId = async () => {
    const data = document.getElementsByClassName("adding");
    var list = [];
    for (const dat of data) {
        dat.addEventListener('click', async (event) => {
            event.preventDefault();
            const productId = dat.dataset.id;

            await makePostRequest(productId);
            list.push(productId);
            const nav = document.getElementById("shop-number");
            if (nav.textContent !== "") {
                console.log(nav.textContent);
                let numb = parseInt(nav.textContent);
                nav.textContent = numb + 1;
            } else nav.textContent = 1;
        });
    }
}

const makePostRequest = async (productId) => {
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


const init = async () => {
    await getProductId();
}
init().then();