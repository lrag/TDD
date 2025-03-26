import { Component } from '@angular/core';
import { Disco } from '../../modelo/entidades/disco';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { ServicioDiscos } from '../../modelo/servicios/servicio.discos';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';

@Component({
  selector: 'app-formulario-discos',
  standalone: true,
  imports: [ReactiveFormsModule, RouterLink],
  templateUrl: './formularioDiscos.component.html',
})
export class FormularioDiscosComponent {

  public formulario:FormGroup

  public constructor(
      private formBuilder   :FormBuilder,
      private servicioDiscos:ServicioDiscos,
      private router        :Router,
      private activatedRoute:ActivatedRoute
    ){

    this.formulario = formBuilder.group({
      id     : [''],
      titulo : ['', [ Validators.required ]],
      grupo  : ['', [ Validators.required ]],
      year   : ['', [ Validators.required, Validators.pattern('^[0-9]{4}$') ]],
      genero : [''],
      notas  : ['']    
    }) 

    let idDiscoSel:number = activatedRoute.snapshot.params["id-disco"]
    if(idDiscoSel){
      servicioDiscos.buscarPorId(idDiscoSel)
      .subscribe({
        next: disco => this.formulario.setValue(disco),
        error: err => console.log(err)
      })  
    }
  }

  public insertar():void {

    console.log(this.formulario.value)

    this.formulario.markAllAsTouched()
    
    if(this.formulario.invalid){
      console.log("Datos invalidos")
      return
    }

    let disco:Disco = this.formulario.value

    console.log("DISCO A INSERTAR: "+disco)

    this.servicioDiscos.insertar(disco)
      .subscribe({
        next: () => this.router.navigateByUrl('/listadoDiscos'),
        error: err => console.log(err)
      }) 
  }

  public vaciar():void {
    this.formulario.reset() //setValue(new Disco())
  }

}
