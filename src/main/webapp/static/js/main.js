import {dataHandler} from "./dataHandler";

let saveButton = document.getElementById("submit");
let filerObject = document.getElementById("dropDown");
let container = document.getElementById("container");


saveButton.addEventListener('click', ev => {
    ev.preventDefault();
    let category = document.getElementById("category").innerText;
    let supplier = document.getElementById("supplier").innerText;
    console.log(category);
    console.log(supplier);
    container.innerText = '';
    dataHandler.filterProducts(category, supplier).then(products => {
            products.forEach(product => {
                const cont = `<div class="container">
               <div class="card">
                   <strong>${category}</strong>
               </div>
               <div id="products" class="row">
                   <div class="col col-sm-12 col-md-6 col-lg-4">
                       <div class="card">
                           <img class="" src="http://placehold.it/400x250/000/fff" alt="" />
                           <div class="card-header">
                               <h4 class="card-title">${product['name']}</h4>
                               <p class="card-text">${product['description']}</p>
                           </div>
                           <div class="card-body">
                               <div class="card-text">
                                   <p class="lead">${product['currency']}</p>
                               </div>
                               <div class="card-text">
                                   <a class="btn btn-success" href="#">Add to cart</a>
                               </div>
                           </div>
                       </div>
                   </div>
               </div>`
                container.append(cont);
                console.log(product)
            })

        }
    )
    ;
})


