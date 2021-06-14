//testcafe chrome 09_Asertos.js --live

import { Selector } from 'testcafe';

fixture `Asertos`

/*
Deep Equal
Not Deep Equal
Ok
Not Ok
Contains
Not Contains
Type of
Not Type of
Greater than
Greater than or Equal to
Less than
Less than or Equal to
Within
Not Within
Match
Not Match
*/

test
    .page `http://localhost:8080/Ej00_WEB/index.html`
    ('Test asertos', async t => {
  
        let nombre = "aaa"
        let mail   = "bbb"

        await t.wait(2000)
               .typeText('[name="nombre"]', nombre)
               .wait(1000)
               .typeText('[name="mail"]', mail)
               .wait(1000)
               .click('#btnEnviar')

        await t.expect(Selector("#nombre").textContent).eql(nombre)
        await t.expect(Selector("#mail").textContent).eql(mail)

    });
