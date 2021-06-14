




console.log("Inicio")

async function movida(){
    console.log("MOVIDA")
    return "HOLA"
}


async function prueba(){

    let cosa = await movida()
    
    console.log(cosa)
    console.log("FIN")
    
}

prueba()

