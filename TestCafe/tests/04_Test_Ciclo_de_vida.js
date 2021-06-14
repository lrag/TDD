//testcafe chrome 04_Test_Ciclo_de_vida.js

import { Selector } from 'testcafe';

fixture `ciclo de vida`
    .page `https://www.stackoverflow.com`
    //before y after reciben el contexto
    .before( async ctx => {
        console.log("Inicialización")
    })
    .after( async ctx => {
        console.log("Finalización")
    })    
    //beforeEach y afterEach reciben el testController
    .beforeEach( async t => {
        console.log("Antes del test")
    })
    .afterEach( async t => {
        console.log("Despues del test")
    });   
    
test
    ('Test 1', async t => {
        console.log("TEST 1")
        await t.wait(4000)
    });
    
test
    ('Test 2', async t => {
        console.log("TEST 2")
        await t.wait(4000)
    });
 
    
test
    ('Test 3', async t => {
        console.log("TEST 3")
        await t.wait(4000)
    });

test
    .before( async ctx => {
        console.log("Antes del test 3")
    })
    .after( async ctx => {
        console.log("Despues del test 3")
    })  
    ('Test 3', async t => {
        console.log("TEST 3")
        await t.wait(4000)
    });

    