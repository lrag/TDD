//testcafe chrome 05_Variables_de_contexto_1.js

import { Selector } from 'testcafe';

fixture `Fixture variables de contexto del test`
    .page("http://www.wikipedia.org")
    .beforeEach(async t  => {
        //TestController tiene una propiedad llamada ctx (contexto) en la que podemos guardar valores
        t.ctx.valor = 42;
    });

test
    ('Test1', async t => {
        console.log("Test1: "+t.ctx.valor); //42
    })
    .after(async t => {
        console.log("Despues del test1: "+t.ctx.valor); 
        t.ctx.valor = "cuarenta y dos"
    });

test
    ('Test2', async t => {
        console.log("Test2: "+t.ctx.valor); 
        t.ctx.valor = "cuarenta y dos!!!"
    })
    .after(async t => {
         console.log("Despues del test2: "+t.ctx.valor); 
    });
