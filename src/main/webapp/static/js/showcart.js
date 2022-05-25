



const someFunction=()=>{
    console.log("wefi;g");
}


const getData = async (route) => {
    const response = await fetch(route);
    return await response.json();
}

const initial = async () => {
    await action();
}


initial().then();