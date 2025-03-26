import { TestBed } from '@angular/core/testing';
import { ListadoDiscosComponent } from './listadoDiscos.component';
import { ServicioDiscos } from '../../modelo/servicios/servicio.discos';
import { of } from 'rxjs';
import { provideRouter } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';

describe('ListadoDiscosComponent', () => {

  let servicioDiscosDouble : jasmine.SpyObj<ServicioDiscos>

  let disco1 = {
        id     : 1,
        titulo : "The dark side of the moon",
        grupo  : "",
        genero : "",
        year   : 0,
        notas  : ""
    }

  beforeEach(async () => {
    servicioDiscosDouble = jasmine.createSpyObj('ServicioDiscos', ['listar']);
    servicioDiscosDouble.listar.and.returnValue(of([]));

    await TestBed.configureTestingModule({
      providers: [
        { provide: ServicioDiscos, useValue: servicioDiscosDouble }, 
        provideRouter([]),
      ]
    }).compileComponents();
  });

  it('should create the app', () => {
    const fixture = TestBed.createComponent(ListadoDiscosComponent);
    const app = fixture.componentInstance;
    expect(app).toBeTruthy();
  });

  it(`Tiene un array con un disco`, () => {
    servicioDiscosDouble.listar.and.returnValue(of([disco1]));

    const fixture = TestBed.createComponent(ListadoDiscosComponent);
    const listadoDiscosComp = fixture.componentInstance;
    expect(listadoDiscosComp.discos.length).toEqual(1)
  });

  it(`Tiene un array con tres discos`, () => {
    servicioDiscosDouble.listar.and.returnValue(of([disco1, disco1, disco1]));

    const fixture = TestBed.createComponent(ListadoDiscosComponent);
    const listadoDiscosComp = fixture.componentInstance;
    expect(listadoDiscosComp.discos.length).toEqual(3)
  });

  it('tiene el título correcto', () => {
    const fixture = TestBed.createComponent(ListadoDiscosComponent);
    fixture.detectChanges();
    const compiled = fixture.nativeElement as HTMLElement;
    //expect(compiled.querySelector('h3')?.textContent).toEqual("Listado de discos");
    expect(compiled.querySelector('h3')?.textContent?.trim()).toEqual("Listado de discos");
  });

  it('tiene una tabla con dos filas', () => {
    servicioDiscosDouble.listar.and.returnValue(of([disco1, disco1]));
    const fixture = TestBed.createComponent(ListadoDiscosComponent);
    fixture.detectChanges();
    const compiled = fixture.nativeElement as HTMLElement;
    //Atentos al ALL
    expect(compiled.querySelectorAll('#tablaDiscos tr')?.length).toEqual(2);
  });

});
