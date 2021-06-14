//testcafe chrome 06_Skip.js --live

import { Selector } from 'testcafe';

fixture `Segundo fixture`

//Los test se ejecutan en el orden en el que están declarados

test
    .page `https://www.google.es`
    ('Test 1', async t => {
        await t.wait(5000)
    });

test
    .skip
    .page `https://www.wikipedia.org`
    ('Test 2', async t => {
        await t.wait(5000)
    });

test
    .page `https://www.stackoverflow.com`
    ('Test 3', async t => {
        await t.wait(5000)
    });