import { TestBed } from '@angular/core/testing';
import { AppComponent } from './app.component';

describe('AppComponent', () => {

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AppComponent],
    }).compileComponents();
  });

  it('should create the app', () => {
    const fixture = TestBed.createComponent(AppComponent);
    const appComponent = fixture.componentInstance;
    expect(appComponent).toBeTruthy();
  });

  it(`ha de tener el valor correcto`, () => {
    const fixture = TestBed.createComponent(AppComponent);
    const appComponent = fixture.componentInstance;
    expect(appComponent.valor).toEqual('ESTE ES EL VALOR');
  });

  it('debería tener el título de página correcto TOCOTO', () => {
    const fixture = TestBed.createComponent(AppComponent);
    fixture.detectChanges();
    const compiled = fixture.nativeElement as HTMLElement;
    expect(compiled.querySelector('h2')?.textContent).toContain('DiscoGest 3000');
    expect(compiled.querySelector('#valor')?.textContent).toContain('VALOR');
  });
});
