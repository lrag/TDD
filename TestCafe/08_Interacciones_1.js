//testcafe chrome 08_Interacciones.js --live

import { Selector } from 'testcafe';

fixture `Interacciones`

//Los test se ejecutan en el orden en el que están declarados

/*
test
    .page `http://localhost:8080/Ej00_AplicacionWeb/index.html`
    ('Test click', async t => { 
        await t.wait(2000)
        //const buttonPulsame = Selector('button').withText('Púlsame');
        //await t.click(buttonPulsame);
        
        await t.click("#btnPulsame")
        await t.wait(1000)
    });
*/
    
test
    .page `http://localhost:8080/Ej00_AplicacionWeb/index.html`
    ('Test click', async t => { 

        //El botón tarda en aparecer 4 segundos
        //const btn = Selector('#btn-1', { timeout: 3000 })
        //await t.click(btn)

        //Este botón tarda en aparecer
        //await t.click("#btn-1")
        await t.wait(1000)
    });


/*
test
    .page `http://www.wikipedia.es`
    ('Test interacciones', async t => {
        
        //<form action="/w/index.php" id="searchform">
        //    <div id="simpleSearch" data-search-loc="header-navigation">
        //        <input type="search" name="search" placeholder="Buscar en Wikipedia" autocapitalize="sentences" title="Buscar en este wiki [f]" accesskey="f" id="searchInput"/>
        //        <input type="hidden" name="title" value="Especial:Buscar"/>
        //        <input type="submit" name="fulltext" value="Buscar" title="Busca páginas con este texto." id="mw-searchButton" class="searchButton mw-fallbackSearchButton"/>
        //        <input type="submit" name="go" value="Ir" title="Ir a la página con este nombre exacto si existe" id="searchButton" class="searchButton"/>
        //    </div>
        //</form>
        
        //await t.wait(3000)
        //await t.typeText('#searchInput', "phyton")
        //await t.wait(2000)
        //await t.selectText('#searchInput').pressKey('delete')
        //await t.typeText('#searchInput', "java")
        //await t.wait(2000)
        //await t.click('#searchButton')

        await t.wait(3000)
               .typeText('#searchInput', "phyton")
               .wait(2000)
               .selectText('#searchInput')
               .pressKey('delete')
               .typeText('#searchInput', "java")
               .wait(2000)
               .click('#searchButton')
    });
*/