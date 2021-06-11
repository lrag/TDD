
//npm install -g testcafe


//testcafe chrome 01_PrimerTest.js --live

import { Selector } from 'testcafe';

//let f = fixture("Primer test")
//f.page("https://www.wikipedia.org")

//fixture("Primer test")
//    .page("https://www.wikipedia.org")


fixture `Primer fixture`
    .page `https://www.wikipedia.org`

test('Test 1', async testController => {    
    
    //Utilzando el testController podemos
    //-ejecutar acciones
    //-manejar dialogs del navegador
    //-ejecutar assertos
    //-invocar 'wait'

});

