//Descargar node.js
//https://nodejs.org/es/

//Al instalar Node.js se instala NPM

//Abrimos un terminal...
//npm install -g testcafe


//testcafe chrome 01_PrimerTest.js --live

import { Selector } from 'testcafe';

//'fixture' es una función que al invocarla nos devuelve un objeet del prototipo 'fixture'

//let f = fixture("Primer test")
//f.page("https://www.wikipedia.org")

//fixture("Primer test")
//    .page("https://www.wikipedia.org")


fixture `Primer fixture`
    .page `https://www.wikipedia.org`

//Para declarar un test invocaremos la funcion 'test' pasando dos parámetros
//-el nombre del test
//-una función asíncrona que guarde el código que hay que ejecutar durante la prueba
//La función asíncroa recibirá por parámetro el 'testControllero

test('Test 1', async function(testController) {    
    
    //Utilzando el testController podemos
    //-ejecutar acciones
    //-manejar dialogs del navegador
    //-ejecutar assertos
    //-invocar 'wait'

});

//Con una función flecha...
test('Test 2', async testController => {    
    
    //Utilzando el testController podemos
    //-ejecutar acciones
    //-manejar dialogs del navegador
    //-ejecutar assertos
    //-invocar 'wait'

});



