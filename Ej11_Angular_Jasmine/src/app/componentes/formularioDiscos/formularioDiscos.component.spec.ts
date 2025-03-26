import { TestBed } from '@angular/core/testing';
import { ServicioDiscos } from '../../modelo/servicios/servicio.discos';
import { of } from 'rxjs';
import { provideRouter, Router } from '@angular/router';
import { FormularioDiscosComponent } from './formularioDiscos.component';
import { Disco } from '../../modelo/entidades/disco';
import { routes } from '../../app.routes';

describe('FormularioDiscosComponent', () => {

  let servicioDiscosDouble : jasmine.SpyObj<ServicioDiscos>
  let router:Router

  let disco1:Disco = new Disco(
        0,
        "The dark side of the moon",
        "Pink Floyd",
        1973,
        "Rock",
        "Óptimo"
    )

  let disco2:Disco = new Disco(
      1234,
      "The dark side of the moon",
      "Pink Floyd",
      1973,
      "Rock",
      "Óptimo"
  )    

  beforeEach(async () => {
    servicioDiscosDouble = jasmine.createSpyObj('ServicioDiscos', ['insertar']);
    servicioDiscosDouble.insertar.and.returnValue(of({}));

    await TestBed.configureTestingModule({
      providers: [
        { provide: ServicioDiscos, useValue: servicioDiscosDouble }, 
        provideRouter(routes)
      ]
    }).compileComponents();

    router = TestBed.inject(Router)

  });

  it('should create the app', () => {
    const fixture = TestBed.createComponent(FormularioDiscosComponent);
    const app = fixture.componentInstance;
    expect(app).toBeTruthy();
  });

  it('tiene el título correcto', () => {
    const fixture = TestBed.createComponent(FormularioDiscosComponent);
    fixture.detectChanges();
    const compiled = fixture.nativeElement as HTMLElement;
    //expect(compiled.querySelector('h3')?.textContent).toEqual("Listado de discos");
    expect(compiled.querySelector('h3')?.textContent?.trim()).toEqual("Formulario de Discos");
  });

  it('inserta correctamente el disco si los datos son validos', () => {

    servicioDiscosDouble.insertar.and.callFake( disco => {
        disco.id = 1
        return of(disco)
      })

    spyOn(router, 'navigateByUrl').and.callThrough()

    const fixture = TestBed.createComponent(FormularioDiscosComponent);
    
    const comp = fixture.componentInstance;
    comp.formulario.setValue(disco1)

    comp.insertar()

    expect(servicioDiscosDouble.insertar).toHaveBeenCalledTimes(1)
    expect(router.navigateByUrl).toHaveBeenCalledTimes(1)
    expect(router.navigateByUrl).toHaveBeenCalledWith('/listadoDiscos')

  });



});
