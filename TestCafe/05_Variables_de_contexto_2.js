//testcafe chrome 05_Variables_de_contexto_2.js

import { Selector } from 'testcafe';
fixture `Fixture variables del contexto del fixture`
    .before(async ctx  => {
        ctx.valor = 123;
    })
    .after(async ctx  => {
        console.log(ctx.nuevoValor); // > abc
    });

test('Test1', async t => {
    console.log(t.fixtureCtx.valor); // > 123
});

test('Test2', async t => {
    t.fixtureCtx.nuevoValor = 'abc';
});