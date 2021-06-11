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
}


fixture `Page model`

test
    .page `http://localhost:8080/Ej00_AplicacionWeb/formulario.html`
    ('Test', async t => {
    
    const fp = new FormularioPelicula()
    
    await t
        .typeText(fp.titulo, "2001")
        .typeText(fp.director, 'S.K.')
        .typeText(fp.genero, "CiFi")
        .typeText(fp.fechaEstreno, "1968")

    await fp.vaciar(t)

});