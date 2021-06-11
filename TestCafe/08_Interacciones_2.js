//testcafe chrome 08_Interacciones_2.js --live

import { Selector } from 'testcafe';
import { ClientFunction } from 'testcafe';

fixture `Interacciones 2`

test
    .page `http://localhost:8080/Ej00_AplicacionWeb/DragAndDropDemo.html`
    ('Test drag and drop', async t => {
  
		const origen = Selector("#draggable")
		const destino = Selector("#droppable")

        console.log(await destino.textContent)
        
        /*
        await t.wait(4000)
        await t.dragToElement(origen, destino)
        await t.expect(destino.textContent).contains("Dropped!")
        */

        await t
            .wait(1000)
            .dragToElement(origen, destino)
            .expect(destino.textContent).contains("Dropped!")
        
        console.log(await destino.textContent)

    });


test
    .page `http://localhost:8080/Ej00_AplicacionWeb/DoubleClickDemo.html`
    ('Test doble click', async t => {
  
		const elemento = Selector("#message")
        
        console.log(await elemento.getAttribute("class"))
        
        await t.wait(4000)
        await t.doubleClick(elemento)
        await t.expect(elemento.getAttribute("class")).eql("dbl")

        console.log(await elemento.getAttribute("class"))

    });    


test
    .page `http://localhost:8080/Ej00_AplicacionWeb/index.html`
    ('Test javascript', async t => {
  
        //import { ClientFunction } from 'testcafe';
		const f1 = ClientFunction( () => saludar() );
        await f1()

        const f2 = ClientFunction( (s1,s2) => sumar(s1,s2) )
        let suma = await f2(10,20)
        console.log(suma)

    });  