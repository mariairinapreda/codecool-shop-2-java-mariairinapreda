let dataHandler = {
    getProducts: async function () {
        return await this.getData(`/`)
    },

    filterProducts: async function (category, supplier) {
        let url = `/api/?category=`+category+`&supplier=`+supplier

        return await this.getData(url);
    },

    getData: async function (url) {
        let response = await fetch(url, {
            method: "GET",
        });
        if (response.ok) {
            return await response.json();
        }
    },
    apiPost: async function (url, payload) {
        return await fetch(url, {
            method: "POST",
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(payload)
        })
            .then(res => res.json())
    },
    apiPut: async function(url, payload){
        return await fetch(url, {
            method: "PUT",
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify(payload)
        })
            .then(res => res.json())
    },
    apiDelete: async function(url){
        return await fetch(url, {
            method: "DELETE",
        })
    }

}
export {dataHandler};

// client auth 703813286336-7s4ughf1qd4vtq6a3opkmi2h8g9d0ite.apps.googleusercontent.com
// client secret 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