//testcafe chrome 11_Modelos_de_pagina.js --live

import { Selector } from 'testcafe';

class FormularioPelicula {
    constructor () {
        this.btnInsertar  = Selector('#btnInsertar');
        this.btnModificar = Selector('#btnModificar');
        this.btnBorrar    = Selector('#btnBorrar');
        this.idPelicula   = Selector('[name="idPelicula"]');
        this.titulo       = Selector('[name="titulo"]');
        this.director     = Selector('[name="director"]');
        this.genero       = Selector('[name="genero"]');
        this.fechaEstreno = Selector('[name="fechaEstreno"]');
    }
    async vaciar (t) {
        await t
            .selectText(this.titulo).pressKey('delete')
            .selectText(this.director).pressKey('delete')
            .selectText(this.genero).pressKey('delete')
            .selectText(this.fechaEstreno).pressKey('delete')
    }
    async insertar(t){
        await t.click(this.btnInsertar)
    }
}

fixture `Page model`

test
    .page `http://localhost:8080/Ej00_AplicacionWeb/formulario.html`
    ('Test', async t => {
    
        //Si lo declaramos fuera se comparte entre todos los test
    const fp = new FormularioPelicula()
    
    await t
        .wait(2000)
        .typeText(fp.titulo, "2001")
        .typeText(fp.director, 'S.K.')
        .typeText(fp.genero, "CiFi")
        .typeText(fp.fechaEstreno, "1968")
        .wait(1000)

    await fp.vaciar(t)
    await t.wait(2000)
    await fp.insertar(t)

});