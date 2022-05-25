import {dataHandler} from "./dataHandler";

let saveButton = document.getElementById("#submit");
let filerObject = document.getElementById("#dropDown");
let container = document.getElementById("#container");



function upload() {
    saveButton.addEventListener( 'click', ev => {
        let category = document.getElementById("#category").innerText;
        let supplier = document.getElementById("#supplier").innerText;
        container.innerText = '';
       dataHandler.filterProducts(category, supplier).then(products =>
           container.innerHTML = `<div class="container">
               <div class="card">
                   <strong th:text="${filerObject}">Category Title</strong>
               </div>
               <div id="products" class="row">
                   <div class="col col-sm-12 col-md-6 col-lg-4" th:each="prod,iterStat : ${products}">
                       <div class="card">
                           <img class="" src="http://placehold.it/400x250/000/fff" th:attr="src='/static/img/product_' + ${prod.id} + '.jpg'" alt="" />
                           <div class="card-header">
                               <h4 class="card-title" th:text="${prod.name}">Product name</h4>
                               <p class="card-text" th:text="${prod.description}">Product description... </p>
                           </div>
                           <div class="card-body">
                               <div class="card-text">
                                   <p class="lead" th:text="${prod.getPrice()}">100 USD</p>
                               </div>
                               <div class="card-text">
                                   <a class="btn btn-success" href="#">Add to cart</a>
                               </div>
                           </div>
                       </div>
                   </div>
               </div>`
       )
        ;

        ev.preventDefault();


    })

}

