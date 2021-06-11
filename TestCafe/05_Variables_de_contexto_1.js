//testcafe chrome 05_Variables_de_contexto.js

import { Selector } from 'testcafe';

fixture `Fixture variables de contexto del test`
    .page("http://www.wikipedia.org")
    .beforeEach(async t  => {
        t.ctx.valor = 42;
    });

test
    ('Test1', async t => {
        console.log("Antes del test1: "+t.ctx.valor); 
    })
    .after(async t => {
         console.log("Despues del test1: "+t.ctx.valor); 
         t.ctx.valor = "cuarenta y dos"
    });

test
    ('Test2', async t => {
        console.log("Antes del test2: "+t.ctx.valor); 
        t.ctx.valor = "cuarenta y dos!!!"
    })
    .after(async t => {
         console.log("Despues del test2: "+t.ctx.valor); 
    });
