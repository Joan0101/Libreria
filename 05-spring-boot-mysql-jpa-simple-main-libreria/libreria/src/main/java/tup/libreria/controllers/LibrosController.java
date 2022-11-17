/**
 * Esta clase UserController pertenece al paquete controllers.
 * En principio, solo debería atender a los requests HTTP,
 * y según sea GET, POST, u otro método, analizar el contenido
 * del request, y decidir a qué método llamar. 
 * En principio, esta clase no debería hacer el trabajo. No.
 * Lo que debería hacer es llamar al método encargado de hacer
 * el trabajo y pasarle los parámetros necesarios. 
 * Ese método llamado debería pertenecer a una clase del paquete services. 
 * Pero nosotros no tenemos ese paquete, porque este es un ejemplo muy libros.
 * Veremos que esta clase hace el trabajo, lo que no debería ser así.
 * Entonces, recordar que estamos dejando de lado un principio
 * muy importante, para no complicar este ejemplo.
 */
package tup.libreria.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tup.libreria.models.Libros;
import tup.libreria.repositories.LibrosRepository;  // ESTA CLASE MANEJA LO QUE RECIBE EL REQUEST, DECIDE QUIEN TIENE QUE HACER EL TRABAJO Y SE LO PASA (clase de gustavo)

/**
 * La anotación @RestController es la combinación de @Controller
 * y @ResponseBody.
 * Está anotando la clase, o sea que todos los métodos la heredan y
 * no es necesario anotar cada uno de ellos. Todos tendrán la semántica
 * de @ResponseBody. Esto significa que la String retornada es la response, no
 * el nombre de una vista.
 */
@RestController

@RequestMapping("")
public class LibrosController {
  
  /**
   * Qué es un bean en Java
   * https://stackoverflow.com/a/3295517/2740402
   * Un JavaBean es solo un estándar. Es una clase regular de Java, excepto que
   * sigue ciertas convenciones:
   * - Todas las propiedades son privadas (usan getters y setters).
   * - Tiene un constructor público sin argumentos.
   * - Implementa la interfaz Serializable.
   * Eso es todo. Es solo una convención.
   * 
   * La anotación @Autowired significa que Spring va a inyectar en esta clase un
   * bean
   * llamado userRepository.
   * No hay en este proyecto una clase UserRepository. Solo hay una
   * interfaz UserRepository. Y esta interfaz lo único que hace es extender
   * CrudRepository. No declara ni campos ni métodos. Nosotros no hacemos nada,
   * todo lo hace Spring por nosotros.
   * Esta es la inyección de dependencia. Nosotros lo único que hacemos es
   * declarar la variable userRepository de tipo UserRepository, y ponerle
   * la anotación Autowired. Y listo. Ya tenemos en esta clase UserController
   * la variable userRepository correctamente configurada e inicializada, de
   * manera que la podemos usar sin más.
   * Notar que tampoco hemos programado los métodos que estamos llamando. Esos
   * métodos fueron generados automáticamente por la anotación Data de Lombok,
   * que pusimos en la clase User.
   */
  @Autowired
  private LibrosRepository userRepository;
    
  @PostMapping("/add") // Map ONLY POST Requests
  public String addNewUser(@RequestParam String genero, @RequestParam String libro, @RequestParam double precio) {
    //Añade a través de un método POST añade un libro tomando en cuenta los parámetros: “genero”, “libro” y “precio”
    // @RequestParam means it is a parameter from the GET or POST request
    
    Libros n = new Libros();
    n.setGenero(genero);
    n.setLibro(libro);
    n.setPrecio(precio);
    
    userRepository.save(n);
    return "Mision completada!";
  }

  @PostMapping("/delete/{id}") // Map ONLY POST Requests
  public String deleteUserById(@PathVariable Long id) {
    //Elimina de la base de datos una fila correspondiente a la id del libro seleccionado
    // @RequestParam means it is a parameter from the GET or POST request
    
    userRepository.deleteById(id);
    return "Borradisimo";
  }

  @GetMapping("/{id}")
  public String findUsersById(@PathVariable Long id) {
    // @PathVariable indica que el parámetro id, de tipo Long, es una
    // variable que viene en la URI.
    // Muestra los datos de un libro seleccionado a traves de la Id correspondiente 
    /**
     * https://docs.spring.io/spring-data/commons/docs/current/api/org/springframework/data/repository/CrudRepository.html#findById-ID-
     * Optional<T> findById(ID id)
     */
    return userRepository.findById(id).toString();
  }

  @GetMapping("") 
  public String main() {
      //muestra la pagina principal del proyecto
      // CAMBIAR LO QUE VIENE SIENDO EL COLOR DE LIBRERIA 9 LIBROS POR UN AMARILLO LINDO
    String bienvenido = """
      <div class= 'container'>
      <div class="container_content">
      <div class="container_content_inner">
      <div class="title">
      <h1>Libreria 9 libros</h1>
      <h1>⭐⭐⭐⭐⭐⭐⭐  </h1>
      <h1>⭐⭐⭐⭐⭐⭐⭐ </h1>
      <h1>⭐⭐⭐⭐⭐⭐⭐  </h1>
      <h1>⭐⭐⭐⭐⭐⭐⭐  </h1>
      <h1>⭐⭐⭐⭐⭐⭐⭐ </h1>
      <h1>LIBRERIA 9 LIBROS</h1>
      <h1>⭐⭐⭐⭐⭐⭐⭐  </h1>
      <h1><a href= "http://localhost:8080/libros" class=btns_more >E N T R A R !</a>  </h1>
      <h1>  </h1>
      </div>
      <div class="par">   
      <p>
      </p>
      </div>  
      </div>   
      <div class="btns">
      </div>  
      </div>  
       <div class="container_outer_img">
       <div class="img-inner">
       <img src='https://img.freepik.com/premium-vector/seamless-pattern-books-glasses-bookmarks-stars-hand-drawn-vector-illustrations-colored-cartoon-ornament-reading-design-fabric-textile-background-wallpaper-print-decor_534604-552.jpg'  alt="" class="container_img"/>
             </div>
           </div>
        </div>
        <h1> </h1>
        <h1>Aca no hay nada 🤓🤓🤓🤓🤓🤓</h1>
        <h1>Aca no hay nada 🤓🤓🤓🤓🤓🤓</h1>
        <h1>Aca no hay nada 🤓🤓🤓🤓🤓🤓</h1>
        <h1>Aca no hay nada 🤓🤓🤓🤓🤓🤓</h1>
      </div>

      <style>
      body {
        background: #4AE4FF;
         font-family: lato;
      }
   

      * {
        margin: 0;
        padding: 0;
        box-sizing: border-box;
      }
      
      :root {
        --secondary-color: #151226;
        --contrast-color: #BF307F;
      }
      .overlay {
        position: absolute;
        top: 0;
        left: 0;
        right: 0;
        bottom: 0;
        height: 100%;
        z-index: -10;
        background-color: var(--contrast-color);
      }
      
      .container {
        display: flex;
        height: 100vh;
        justify-content: space-around;
        align-items: center;
        color: #fff;
        animation: expand .8s ease forwards;
        background-color: var(--secondary-color);
        position: relative;
        font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;
        transition: all .8s ease;
      }
      
      .container_content {
       width: 50%;
      }
      
      .container_content_inner {
        width: 80%;
        margin-left: 80px;
      }
      
      .container_outer_img {
        margin: 50px;
        width: 50%;
        overflow: hidden;
      }   
          
      .container_img {
        width: 100%;
        animation: slideIn 1.5s ease-in-out forwards;
      }
      
      .par {
        height: auto;
        overflow: hidden;
      }
      
      p{
        line-height: 28px;
        transform: translateY(300px);
        animation: slideUp .8s ease-in-out forwards .8s;
      }
      
      .btns {
        height: 100%;
        position: relative;
        width: 150px;
        overflow: hidden;
      }
      
      .btns_more {
        background: transparent;
        border: 1px solid var(--contrast-color);
        border-radius: 70px;
        padding: 8px 12px;
        color: #BF307F;
        font-size: 36px;
        position: relative;
        margin-top: 0px;
        outline: none;
        transform: translateY(50px);
        animation: slideUp .8s ease-in-out  forwards 1s;
      }
      
      .title {
        overflow: hidden;
        height: auto;
      }
      
      h1 {
          font-size: 90px;
          color: var(--contrast-color);
          margin-bottom: 0px;
          margin-top: 50px;
          transform: translateY(100px);
          animation: slideUp .8s ease forwards .5s;
      }
      
      @keyframes slideIn {
        0% {
          transform: translateX(500px) scale(.2);
        }
        100% {
          transform: translateX(0px) scale(1);
        }
      }
      
      @keyframes slideUp {
        0% {
          transform: translateY(300px);
        }
        100% {
          transform: translateY(0px);
        }
      }
      
      @keyframes expand {
        0% {
          transform: translateX(1400px);
        }
        100% {
          transform: translateX(0px);
        }
      }
      
        </style>
        """;

    
    return bienvenido;
  }
  
  @GetMapping("/libros")
  //Puede accederse al presionar el botón “entrar” de la página ("")
  //Muestra una tabla con todos los libros y sus datos correspondientes

  
  public String getAlllibros() {
    // This returns a JSON or XML with the libros
   
    Iterable<Libros> iterable = userRepository.findAll();
    
    String boton = """
      
      
      <div class="center"><a href="http://localhost:8080/libros/descripciones" class="button">Descripciones</a></div>
      <style>
      body {
        background-color: #fafafa;
        font-family: sans-serif;
        font-weight: 300;
      }
      
      .button {
        background: rgba(240, 56, 114, 0.8);
        backdrop-filter: blur(5px);
        border-radius: 6px;
        box-shadow: 2px 2px 1px rgba(0,0,0,0.4);
        color: black;
        display: block;
        font-size: 1.5em;
        font-weight: bold;
        overflow: hidden;
        padding: 30px 50px;
        position: relative;
        text-decoration: none;
        transition: all 200ms ease-out;
        text-align: center;
      }
      
      .button:hover {
        box-shadow: 3px 3px 5px rgba(0,0,0,0.5);
      }
      
      .center {
        left: 50%;
        right: 50%;
        position: relative;
        padding: 30px 700px;
        transform: translate(-50%,-50%);
      }
      
      .ripple {
        background-color: rgba(255,255,255,0.3);
        opacity: 1;
        position: absolute;
        -webkit-transform: scale(0);
        -moz-transform: scale(0);
        transform: scale(0);
        transition: all 500ms ease-out;
      }
      
      </style>
 
    """;

    String tabla = """
          <style>
          @charset "UTF-8";
          @import url(https://fonts.googleapis.com/css?family=Open+Sans:300,400,700);
          
          body {
            
            
            background-size: cover;
            
              background-image: linear-gradient(rgba(0, 0, 0, 0.5), rgba(0, 0, 0, 0.5)), url("https://w.wallhaven.cc/full/m9/wallhaven-m9md18.jpg");
          

          }
          
          
          .blue { color: #185875; }
          .yellow { color: #FFF842; }
          
          .container th h1 { // CABECERA
              font-weight: bold;
              font-size: 28px;
              text-align: left;
              color: #FF2C5C;
          }
          
          .container td { // contenido
              font-weight: 300;
              color: #CD5E74;
              font-size: 20px;
              -webkit-box-shadow: 0 2px 2px -2px #0E1119;
               -moz-box-shadow: 0 2px 2px -2px #0E1119;
                    box-shadow: 0 2px 2px -2px #0E1119;
          }
          
          .container {
              text-align: left;
              overflow: hidden;
              width: 80%;
              margin: 0 auto;
              display: table;
              padding: 0 0 8em 0;
            
          }
          
          .container td, .container th {
              padding-bottom: 2%;
              padding-top: 2%;
              padding-left:2%;  
          }
          
          /* Background-color of the odd rows Y SE TRABAJA EL BLUR */
          .container tr:nth-child(odd) { 
              background: rgba(27, 33, 49, 0.8);
              backdrop-filter: blur(2px);
          }
          
          /* Background-color of the even rows */
          .container tr:nth-child(even) {
              background: rgba(38, 50, 78, 0.8);
              backdrop-filter: blur(2px);
          }
          
          .container th {
              background-color: #1F2739;
          }
          
          .container td:first-child { color: #FF327C; } // Numeros de id
          
          .container tr:hover {
             background-color: #FF5500;
            -webkit-box-shadow: 0 6px 6px -6px #00487A;
               -moz-box-shadow: 0 6px 6px -6px #00487A;
                    box-shadow: 0 6px 6px -6px #00487A;
          }
          
          .container td:hover {
            background-color: #003B61;
            color: #FF327C; // color letras hover
            font-weight: bold;
            
            box-shadow: #002842 -1px 1px, #002842 -2px 2px, #002842 -3px 3px, #002842 -4px 4px, #002842 -5px 5px, #002842 -6px 6px;
            transform: translate3d(6px, -6px, 0);
            
            transition-delay: 0s;
              transition-duration: 0.4s;
              transition-property: all;
            transition-timing-function: line;
          }
          
          @media (max-width: 800px) {
          .container td:nth-child(4),
          .container th:nth-child(4) { display: none; }
          }

          
          </style>


<table id ='libros'>
<table class="container">
  <thead>
    <tr>
      <th><h1>ID</h1></th>
      <th><h1>Genero</h1></th>
      <th><h1>Libro</h1></th>
      <th><h1>Precio</h1></th>
    </tr>
        	
        """; 
        
    for (Libros libros : iterable) {
     
      tabla += "<tr>"
          + "<td>" + libros.getId() + "</td>"
          + "<td>" + libros.getGenero() + "</td>"
          + "<td>" + libros.getLibro() + "</td>"
          + "<td>" + libros.getPrecio() + "</td>"
          + "</tr>";
          
    } 


     return tabla + "</table>" + boton;
  }


  @GetMapping("/libros/descripciones")
  //Muestra una página en la cual a través de tabs muestra las descripciones de los libros
  public String descripciones() {
    String descripciones = """

      <div class="tabs">

  <input type="radio" name="tabs" id="tabone" checked="checked"> 
  <label for="tabone">Señor de los anillos</label>
  <div class="tab">
    <h1>Descripcion</h1>
    <p>La novela narra el viaje del protagonista principal, Frodo Bolsón, hobbit de la Comarca, para destruir el Anillo Único y la consiguiente guerra que provocará el enemigo para recuperarlo, ya que es la principal fuente de poder de su creador, el Señor oscuro Sauron. «Tres Anillos para los Reyes Elfos bajo el cielo.</p>
    
  </div>
  
  <input type="radio" name="tabs" id="tabtwo"> 
  <label for="tabtwo">Harry Potter</label>
  <div class="tab">
    <h1>Descripcion</h1>
    <p>Harry Potter es una serie de novelas fantásticas escrita por la autora británica J. K. Rowling, en la que se describen las aventuras del joven aprendiz de magia y hechicería Harry Potter y sus amigos Hermione Granger y Ron Weasley, durante los años que pasan en el Colegio Hogwarts de Magia y Hechicería.</p>
  </div>

  <input type="radio" name="tabs" id="tabthree"> 
  <label for="tabthree">Fahrenheit451</label>
  <div class="tab">
    <h1>Descripcion</h1>
    <p>Ambientada en el siglo XXIV, Fahrenheit 451, cuenta la historia del protagonista Guy Montag. Al principio, Montag disfruta de su oficio de bombero es decir quemando libros conservados ilegalmente así como las casas de sus dueños. Sin embargo, de pronto, Montag empieza a cuestionar el valor de su profesión y a su vez, su vida. A lo largo de la novela, Montag lucha contra su existencia y acaba huyendo de esta sociedad opresiva, de censura; se une a un grupo subterráneo de intelectuales. Con sus amigos recién conocidos, Montag, testigo de la destrucción atómica de la ciudad, se dedica a reconstruir una literata y culta sociedad.</p>
  </div>

  <input type="radio" name="tabs" id="tabfour"> 
  <label for="tabfour">Robinson Crusoe</label>
  <div class="tab">
    <h1>Descripcion</h1>
    <p>Es un relato ficticio sobre un náufrago, basado en las aventuras de Alexander Selkik, un marino que naufrago cerca de la isla del archipiélago Juan Fernández frente a las islas de Chile, donde permaneció cinco años, relata la soledad y la construcción de una sociedad ideal al margen del mundo.</p>
  </div>
  
  <input type="radio" name="tabs" id="tabfive"> 
  <label for="tabfive">Las Aventuras de Tom Sawyer</label>
  <div class="tab">
    <h1>Descripcion</h1>
    <p>En una población a orillas del río Mississippi vive Tom Sawyer, un muchacho travieso, experto en saltarse las normas y escabullirse de la escuela para embarcarse en las aventuras más peligrosas y disparatadas.</p>
  </div>

  <input type="radio" name="tabs" id="tabsix"> 
  <label for="tabsix">Viaje al Centro de La Tierra</label>
  <div class="tab">
    <h1>Descripcion</h1>
    <p>El profesor Lidenbrock, que une a su condición de verdadero sabio una terquedad sin límites, descifra un viejo pergamino devolviendo pacientemente su sentido a los incomprensibles signos que en él se contienen. ¡Extraordinarios peligros de la lectura! El descifrado de aquel texto arrastrará inevitablemente al propio Lidenbrok, a su joven sobrino Axel y al valeroso cazador Hans Bjelke hasta el mismísimo centro de la Tierra, poblado de animales antediluvianos, tempestades terribles y otros riesgos sin mayor importancia.</p>
  </div>

  <input type="radio" name="tabs" id="tabseven"> 
  <label for="tabseven">La Metamorfosis</label>
  <div class="tab">
    <h1>Descripcion</h1>
    <p>Al despertar Gregorio Samsa una mañana, tras un sueño intranquilo, encontróse en su cama convertido en un monstruoso insecto.</p>
  </div>

  <input type="radio" name="tabs" id="tabeigth"> 
  <label for="tabeigth">Estudio en escarlata</label>
  <div class="tab">
    <h1>Descripcion</h1>
    <p>Un cadáver hallado en extrañas circunstancias pone en marcha los reflejos deductivos de Holmes, mientras la policía oficial se pierde en divagaciones equivocadas o arresta a inocentes ciudadanos. Un nuevo asesinato parece complicar la historia, pero a Holmes se la aclara.</p>
  </div>

  <input type="radio" name="tabs" id="tabnine"> 
  <label for="tabnine">Rebelion en la Granja</label>
  <div class="tab">
    <h1>Descripcion</h1>
    <p>Los animales de la granja de los Jones se sublevan contra sus dueños humanos y los vencen. Pero pronto surgen entre ellos rivalidades y envidias, y algunos se alían con los amos que derrocaron, traicionando su propia identidad y los intereses de su clase.</p>
  </div>


</div>


<a href=http://localhost:8080/libros>Volver</a>


      <style>
      
   a {
       display: inline-block;
       position: relative;
       top: 40px;
       left: 100px;
       transform: translate(-50%, -50%);
       color: #23252f;
       text-decoration: none;
       padding: 20px 45px;
       border-radius: 9px;
       background: #6EBDFF;
       box-shadow: 3px 3px 6px #4AE4FF, -3px -3px 6px #4AE4FF;
       transition: all .3s;
       /* shine effect */
       background-repeat: no-repeat;
       background-position: -135px -135px, 0 0;
       background-image: -webkit-linear-gradient( top left, rgba(255, 255, 255, 0.2) 0%, rgba(255, 255, 255, 0.2) 37%, rgba(255, 255, 255, 0.8) 45%, rgba(255, 255, 255, 0.0) 50%);
       background-size: 250% 250%, 100% 100%;
       transition: background-position 0s ease;
   }
   
   a:active {
       border-radius: 25px;
       border-radius: 9px;
       background: #4A94FF;
       box-shadow: inset 3px 3px 6px #296137, inset -3px -3px 6px #9fffd3;
   }
   
   a:hover {
       transform: translate(-50%, -50%) scale(1.03);
       background-position: 0 0, 0 0;
       transition-duration: 0.9s;
   }

h1 {
  color: #ABD3FF;
}

.tabs {
	display: flex;
	flex-wrap: wrap; // make sure it wraps
}
.tabs label {
	order: 1; // Put the labels first
	display: block;
	padding: 1rem 2rem;
	margin-right: 0.2rem;
	cursor: pointer;
  background: #90CAF9;
  font-weight: bold;
  transition: background ease 0.2s;
}
.tabs .tab {
  order: 99; // DESCRIPCION
  flex-grow: 1;
	width: 100%;
	display: none;
  padding: 1rem;
  font-weight: bold;
  backdrop-filter: blur(2px);
}
.tabs input[type="radio"] {
	display: none;
}
.tabs input[type="radio"]:checked + label {
	background: #fff;
}
.tabs input[type="radio"]:checked + label + .tab {
	display: block;
}

@media (max-width: 45em) {
  .tabs .tab,
  .tabs label {
    order: initial;
  }
  .tabs label {
    width: 100%;
    margin-right: 0;
    margin-top: 0.2rem;
  }
}

/**
 * Generic Styling
*/
body {
  background-image: url("https://www.todofondos.net/wp-content/uploads/fondo-de-pantalla-atardecer-bosque-minimo-4k-8k-naturaleza-scaled.jpg");
  min-height: 100vh;
	box-sizing: border-box;
	padding-top: 10vh;
  font-family: "HelveticaNeue-Light", "Helvetica Neue Light", "Helvetica Neue", Helvetica, Arial, "Lucida Grande", sans-serif; 
  font-weight: 300;
  line-height: 1.5;
  max-width: 60rem;
  margin: 0 auto;
  font-size: 112%;
}


      </style>

        """;

    return descripciones;
  }
      
  
}
