//testcafe chrome 10_Screenshot.js --live
//testcafe chrome 10_Screenshot.js --live -s takeOnFails=true

import { Selector } from 'testcafe';

fixture `Screenshots`

test
    .page `http://localhost:8080/Ej00_AplicacionWeb/DragAndDropDemo.html`
    ('Test captura', async t => {
  
		const origen = Selector("#draggable")
		const destino = Selector("#droppable")

        await t
            .wait(4000)
            .takeScreenshot({
                path:     'captura1.png',
                fullPage: true
            })           
            .dragToElement(origen, destino)
            .takeScreenshot({
                path:     'captura2.png',
                fullPage: true
            })           
            .expect(destino.textContent).contains("Dropped!")
        
    });

    test
    .page `http://localhost:8080/Ej00_AplicacionWeb/DragAndDropDemo.html`
    ('Test captura error', async t => {
  
		const origen = Selector("#draggable")
		const destino = Selector("#droppable")

        await t
            .wait(4000)
            .takeScreenshot({
                path:     'captura1.png',
                fullPage: true
            })           
            .dragToElement(origen, destino)
            .takeScreenshot({
                path:     'captura2.png',
                fullPage: true
            })           
            .expect(destino.textContent).contains("ZASCA")
        
    });    


