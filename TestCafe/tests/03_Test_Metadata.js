//testcafe chrome 03_Test_Metadata.js --live 
//testcafe chrome 03_Test_Metadata.js --live --test-meta tipo=tipo1
//testcafe chrome 03_Test_Metadata.js --live --test-meta tipo=tipo2

import { Selector } from 'testcafe';

fixture `fixture`
    .meta({ descripcion: 'bla bla bla' })
        
test
    .meta({ 
        descripcion: 'bla bli bla',
        tipo       : 'tipo1' 
    })
    .page `https://www.stackoverflow.com`
    ('Test con metadata 1', async t => {
        await t.wait(3000)
    });
    
test
    .meta({ 
        descripcion: 'bla bli bla',
        tipo       : 'tipo2' 
    })
    .page `https://www.wikipedia.org`
    ('Test con metadata 2', async t => {
        await t.wait(3000)
    });

