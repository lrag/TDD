import { TestBed } from '@angular/core/testing';
import { ServicioDiscos } from './servicio.discos';
import { provideHttpClient } from '@angular/common/http';
import {HttpClientTestingModule, HttpTestingController, provideHttpClientTesting} from "@angular/common/http/testing";


describe('ServicioDiscos', () => {

  let disco = {
    id     : 2,
    titulo : "Back in black",
    grupo  : "ac/dc",
    genero : "",
    year   : 0,
    notas  : ""
  }

  let servicioDiscos: ServicioDiscos
  let httpTestingController: HttpTestingController

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [
        ServicioDiscos
      ]
    });
    servicioDiscos = TestBed.inject(ServicioDiscos)
    httpTestingController = TestBed.inject(HttpTestingController)
  });

  it('should be created', () => {
    expect(servicioDiscos).toBeTruthy()
  });

  it('Debe buscar por id', () => {

    let discoEsperado = {
      id     : 2,
      titulo : "Back in black",
      grupo  : "AC/DC",
      genero : "",
      year   : 0,
      notas  : ""
    }

    servicioDiscos.buscarPorId(2).subscribe({
        next: d => expect(d).toEqual(discoEsperado)
      })

    let peticion = httpTestingController.expectOne("http://localhost:8080/discos/2")
    expect(peticion.request.method).toBe('GET');
    peticion.flush(disco);
  });  

});
