//testcafe chrome 07_Selectores.js --live

import { Selector } from 'testcafe';

fixture `Selectores`
    .page `http://localhost:8080/Ej00_WEB/index.html`

//Los test se ejecutan en el orden en el que están declarados

test
    .page `http://localhost:8080/Ej00_ApliacionWeb/index.html`
    ('Test byName', async t => {
  
        const inputUsername = Selector(() => {
            return document.getElementsByName('username')[0];
        });

        console.log(await inputUsername.count)
        console.log(await inputUsername.exists)
        console.log(await inputUsername.getAttribute("id"))
        
        const inputPassword = Selector('[name="password"]');
        console.log(await inputPassword.count)
        console.log(await inputPassword.exists)

    });

test
    .page `http://localhost:8080/Ej00_ApliacionWeb/index.html`
    ('Test byId', async t => {  
        
        const inputUsername = Selector('#user');
        console.log(await inputUsername.count)
        console.log(await inputUsername.exists)
        
        const inputPassword = Selector('#pass');
        console.log(await inputPassword.count)
        console.log(await inputPassword.exists)
    });

test
    .page `http://localhost:8080/Ej00_ApliacionWeb/index.html`
    ('Test byClassName', async t => {  
        
        const inputUsername = Selector('.in-user');
        console.log(await inputUsername.count)
        console.log(await inputUsername.exists)
        
        const inputPassword = Selector('.in-pass');
        console.log(await inputPassword.count)
        console.log(await inputPassword.exists)
    });

test
    .page `http://localhost:8080/Ej00_ApliacionWeb/index.html`
    ('Test byTagName', async t => {  
        
        const labels = Selector('label')   
        
        console.log(await labels.count)
        console.log(await labels.exists)
    });    

test
    .page `http://localhost:8080/Ej00_ApliacionWeb/index.html`
    ('Test cadena de selectores', async t => {  
        
        //html > body > form > div > label

        const inputUsername = Selector('html')
            .child('body') //Hijos directos    
            .child('form')     
            .child('div')    
            .child('input')  
            
        console.log(await inputUsername.tagName)
        console.log(await inputUsername.getAttribute('id'))

        const inputPassword = Selector('html')
            .child('body') //Hijos directos    
            .child('form')     
            .child('div')
            .nth(1)  
            .child('input')  
            
        console.log(await inputPassword.tagName)
        console.log(await inputPassword.getAttribute('id'))

    });

    
test
    .page `http://localhost:8080/Ej00_ApliacionWeb/index.html`
    ('Test cadena de selectores 2', async t => {  
        
        const inputUsername = Selector('#loginForm')
            .find('.in-user') //descendientes       
        
        console.log(await inputUsername.count)
        console.log(await inputUsername.exists)
    });

    
test
    .page `http://localhost:8080/Ej00_ApliacionWeb/index.html`
    ('Test cadena de selectores 2', async t => {  
        /*
        <div id="lista-items">
            <ul>
                <li><input type="text" value="Item 1" autofocus>Uno</li>
                <li><input type="text" value="Item 2" disabled>Dos</li>
                <li><input type="text" value="Item 3">Tres</li>
            </ul>
        </div>
        */
        const primero = Selector('#lista-items')
            .child("ul")
            .child("li")
            .nth(0)     
        console.log(await primero.innerText)

        const ultimo = Selector('#lista-items')
            .child("ul")
            .child("li")
            .nth(-1)     
        console.log(await ultimo.innerText)

    });    


    test
    .page `http://localhost:8080/Ej00_ApliacionWeb/index.html`
    ('Test custom DOM properties', async t => {  

        const properties = {
            innerHTML: el => el.innerHTML
        };

        const primero = Selector('#lista-items')
            .child("ul")
            .child("li")
            .nth(0)
            .addCustomDOMProperties( properties )     
        console.log(await primero.innerHTML)

        const ultimo = Selector('#lista-items')
            .child("ul")
            .child("li")
            .nth(-1)     
            .addCustomDOMProperties( properties )     
        console.log(await ultimo.innerHTML)

        //Las custom DOM properties se propagan
        const lista = Selector('#lista-items').addCustomDOMProperties( properties )     
        
        const primeroBis = lista
            .child("ul")
            .child("li")
            .nth(0)
        console.log(await primeroBis.innerHTML)

        const ultimoBis = lista
            .child("ul")
            .child("li")
            .nth(-1)      
        console.log(await ultimoBis.innerHTML)

    });


