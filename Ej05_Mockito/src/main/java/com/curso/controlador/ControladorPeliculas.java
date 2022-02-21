package com.curso.controlador;

import java.util.ArrayList;
import java.util.List;

import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.curso.modelo.entidad.Pelicula;
import com.curso.servicios.ServicioPeliculasProxy;

@Controller
@RequestMapping("peliculas")
public class ControladorPeliculas {

	//@Autowired
	private ServicioPeliculasProxy servicioPeliculasProxy;
	
	//Esto lo quitaremos cuando ya tengamos el verdadero ServicioPeliculasProxy
	{
		servicioPeliculasProxy = Mockito.mock(ServicioPeliculasProxy.class);
		//Métodos a simular:
		//void insertar(Pelicula pelicula);
		//Pelicula buscar(Integer idPelicula);
		//List<Pelicula> listar();
		Mockito
			.when(servicioPeliculasProxy.buscar(Mockito.anyInt()))
				.thenReturn(new Pelicula(101,"Die Hard","John McTiernan","Accion",1988));
		
		List<Pelicula> peliculas = new ArrayList<>();
		peliculas.add(new Pelicula(1,"2001","Stanley Kubrik","Ci-Fi",1968));
		peliculas.add(new Pelicula(2,"Alien","Ridley Scott","Ci-Fi",1979));
		peliculas.add(new Pelicula(3,"Die Hard","John McTiernan","Accion",1988));
		peliculas.add(new Pelicula(4,"Young Frankenstein","Mel Brooks","Comedia",1974));
		peliculas.add(new Pelicula(5,"Bullit","Peter Yates","Policiaco",1969));
		peliculas.add(new Pelicula(6,"El bueno, el feo y el malo","Sergio Leone","Western",1968));

		Mockito
			.when(servicioPeliculasProxy.listar())
				.thenReturn(peliculas);
		
		Mockito.doAnswer(new Answer<Void>() {
			public Void answer(InvocationOnMock invocation) throws Throwable {
				System.out.println("Insertando:"+invocation.getArgument(0));
				return null;
			}
		}).when(servicioPeliculasProxy).insertar(Mockito.any(Pelicula.class));
		
	}
	
	@PostMapping(path="insertar")
	public ModelAndView insertar(@ModelAttribute("pelicula")Pelicula pelicula) {
		try {
			servicioPeliculasProxy.insertar(pelicula);
		} catch (Exception e) {
			ModelAndView mav = new ModelAndView("formularioPeliculas");
			mav.addObject("mensaje","El titulo es obligatorio");
			return mav;
		}
		return new ModelAndView("redirect:verListado");
	}
	
	@PostMapping(path="modificar")
	public ModelAndView modificar(@ModelAttribute("pelicula")Pelicula pelicula) {
		return null;
	}
	
	@PostMapping(path="borrar")
	public ModelAndView borrar(@ModelAttribute("pelicula")Pelicula pelicula) {
		return null;
	}

	@GetMapping(path="verListado")
	public ModelAndView verListadoPeliculas() {
		ModelAndView mav = new ModelAndView("listadoPeliculas");
		mav.addObject("listaPeliculas", servicioPeliculasProxy.listar());
		return mav;
	}

	@GetMapping(path="verFormulario")
	public ModelAndView verFormularioPeliculas() {
		ModelAndView mav = new ModelAndView("formularioPeliculas");
		mav.addObject("pelicula", new Pelicula());
		return mav;
	}

	@GetMapping(path="seleccionar")
	public ModelAndView seleccionar(@RequestParam("id")Integer idPelicula) {
		ModelAndView mav = new ModelAndView("formularioPeliculas");
		mav.addObject("pelicula", servicioPeliculasProxy.buscar(idPelicula));
		return mav;
	}
	
}









