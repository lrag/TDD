//testcafe chrome 12_Test_Aplicacion_3.js --live

import { Selector, t } from 'testcafe';

fixture `test aplicación 3`
    .page `http://localhost:8080/Ej00_AplicacionWeb/login.html`
    .beforeEach( async t => {
        await t
            .wait(3000)
            .typeText("[name=login]","harry")
            .typeText("[name=pw]","callahan")
            .wait(1000)
            .click("#btnEntrar")
            .wait(1000)
    })

test
    .page `http://localhost:8080/Ej00_AplicacionWeb/seguro/SVClientes`
    ('Test insertar', async t => {

    const nombre    = "Ringo Starr"
    const direccion = "C/Tal, 123"
    const telefono  = "123 456 789"

    const formularioClientes = new FormularioCliente()

    await t
        .click("#btnNuevo")
        .wait(1000)
    await formularioClientes.rellenar(nombre, direccion, telefono)
    await t.wait(1000)
    await formularioClientes.insertar()

    const ultimaFila = Selector("#tablaClientes").child("tr").nth(-1)

    const properties = {
        textContentTrim: el => el.innerText.trim()
    };
    const tdNombre    = ultimaFila.child("td").addCustomDOMProperties(properties)
    const tdDireccion = ultimaFila.child("td").nth(1).addCustomDOMProperties(properties)
    const tdTelefono  = ultimaFila.child("td").nth(2).addCustomDOMProperties(properties)

    await t.expect(nombre).eql(await tdNombre.textContentTrim)
    await t.expect(direccion).eql(await tdDireccion.textContentTrim)
    await t.expect(telefono).eql(await tdTelefono.textContentTrim)

    await t.wait(2000)

});

test
    .page `http://localhost:8080/Ej00_AplicacionWeb/seguro/SVClientes`
    ('Test modificar', async t => {

    const formularioClientes = new FormularioCliente()

    const nombre    = "John McClane"
    const direccion = "C/Pascual, 123"
    const telefono  = "987 654 321"

    const linkPrimerCliente = Selector("#tablaClientes").child("tr").child("td").child("a")
    
    await t
        .click(linkPrimerCliente)
        .wait(1000)

    await formularioClientes.vaciar()
    await formularioClientes.rellenar(nombre, direccion, telefono)
    await t.wait(1000)
    await formularioClientes.modificar()

    const ultimaFila = Selector("#tablaClientes").child("tr")

    const properties = {
        textContentTrim: el => el.innerText.trim()
    };
    const tdNombre    = ultimaFila.child("td").addCustomDOMProperties(properties)
    const tdDireccion = ultimaFila.child("td").nth(1).addCustomDOMProperties(properties)
    const tdTelefono  = ultimaFila.child("td").nth(2).addCustomDOMProperties(properties)

    await t.expect(nombre).eql(await tdNombre.textContentTrim)
    await t.expect(direccion).eql(await tdDireccion.textContentTrim)
    await t.expect(telefono).eql(await tdTelefono.textContentTrim)

    await t.wait(2000)

});

test
    .page `http://localhost:8080/Ej00_AplicacionWeb/seguro/SVClientes`
    ('Test borrar', async t => {

    const linkPrimerCliente = Selector("#tablaClientes").child("tr").child("td").child("a")
    const href = await linkPrimerCliente.getAttribute("href")
    const idCliente = href.split("=").pop()
    console.log("======================================")
    console.log("ID:"+idCliente)
    
    await t
        .click(linkPrimerCliente)
        .wait(1000)
        .click("#btnBorrar")

    const filas = Selector("#tablaClientes").child("tr")

    var numFilas = await filas.count;
    let encontrado = false
    for(let a = 0; a < numFilas; a++) {
        const fila = filas.nth(a);
        const hrefFila = await fila.child("td").child("a").getAttribute("href")
        const idClienteFila = hrefFila.split("=").pop()
        if(idClienteFila == idCliente){
            encontrado = true
        }
    }

    await t.expect(encontrado).notOk()

    await t.wait(2000)

});

class FormularioCliente {
    constructor () {
        this.btnInsertar  = Selector('#btnInsertar');
        this.btnModificar = Selector('#btnModificar');
        this.btnBorrar    = Selector('#btnBorrar');
        this.idCliente    = Selector('#idCliente');
        this.nombre       = Selector('#nombre');
        this.direccion    = Selector('#direccion');
        this.telefono     = Selector('#telefono');
    }
    async rellenar( nombre, direccion, telefono){
        //podemos decir que 't' es implícito
        await t
            .typeText(this.nombre, nombre)
            .typeText(this.direccion, direccion)
            .typeText(this.telefono, telefono)
    }
    async vaciar() {
        await t
        .selectText(this.nombre).pressKey('delete')
        .selectText(this.direccion).pressKey('delete')
        .selectText(this.telefono).pressKey('delete')
    }
    async insertar(){
        await t.click(this.btnInsertar)
    }
    async modificar(){
        await t.click(this.btnModificar)
    }
    async borrar(){
        await t.click(this.btnBorrar)
    }
}