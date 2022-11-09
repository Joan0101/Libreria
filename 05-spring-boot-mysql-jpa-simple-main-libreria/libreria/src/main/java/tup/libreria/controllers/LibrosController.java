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
    // @RequestParam means it is a parameter from the GET or POST request
    
    userRepository.deleteById(id);
    return "Deleted";
  }

  @GetMapping("/{id}")
  public String findUsersById(@PathVariable Long id) {
    // @PathVariable indica que el parámetro id, de tipo Long, es una
    // variable que viene en la URI.
    /**
     * https://docs.spring.io/spring-data/commons/docs/current/api/org/springframework/data/repository/CrudRepository.html#findById-ID-
     * Optional<T> findById(ID id)
     */
    return userRepository.findById(id).toString();
  }

  @GetMapping("") 
  public String main() {
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
      <h1><a href= http://localhost:8080/libros target="_blank" class=btns_more >E N T R A R !</a>  </h1>
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
  
  public String getAlllibros() {
    // This returns a JSON or XML with the libros
   
    Iterable<Libros> iterable = userRepository.findAll();
    
    String boton = """
      
      <a href= http://localhost:8080/libros/descripciones target="_blank" class=button >VER DESCRIPCIONES</a>
      <style>
        .button{
          text-decoration:none;
          color: green;
        }
      </style>
 
    """;

    String tabla = """
          <style>
            #libro {"
              font-family: Arial, Helvetica, sans-serif;
              border-collapse: collapse;
              width: 100%;
              
            }
            body{
              background-image: url("https://static8.depositphotos.com/1457895/952/v/450/depositphotos_9529877-stock-illustration-books-background.jpg");
            }
            #libros td, #libros th {
              border: 3px solid #ddd;
              padding: 13px;
            }
            #libros tr:nth-child(n){background-color: #F9FF6E;}
            #libros tr:hover {background-color: #F66E25;}
            #libros th {
              padding-top: 20px;
              padding-bottom: 20px;
              text-align: left;
              background-color: #F6DD25;
              color: black;
            }
              .center {
                margin-left: auto;
                margin-right: auto;
                
              }

          </style>
          
          <table id ='libros'>
            <tr>
              <th>ID</th>
              <th>Genero</th>
              <th>Libro</th>
              <th>Precio</th>
              <th>Descripcion </th>
            </tr> 
            
        """; 
        
    for (Libros libros : iterable) {
     
      tabla += "<tr>"
          + "<td>" + libros.getId() + "</td>"
          + "<td>" + libros.getGenero() + "</td>"
          + "<td>" + libros.getLibro() + "</td>"
          + "<td>" + libros.getPrecio() + "</td>"
          + "<td>" + boton + "</td>"
          + "</tr>";
          
    } 
    
     return tabla + "</table>";
  }

  @GetMapping("/libros/descripciones")
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
  <label for="tabthree">Harry Potter</label>
  <div class="tab">
    <h1>Descripcion</h1>
    <p>Harry Potter es una autopor la autotás autopor la autotás autopor la autotás autopor la autotás autopor la autotás autopor la autotás autopor la autotás autopor la autotás autopor la autotás autopor la autotás autopor la autotás autopor la autotás autopor la autotás autopor la autotásticas escrita por la autotásticas escrita por la autotásticas escrita por la autotásticas escrita por la autotásticas escrita por la autotásticas escrita por la autotásticas escrita por la autotásticas escrita por la autotásticas escrita por la autora británica J. K. Rowling, en la que se describen las aventuras del joven aprendiz de magia y hechicería Harry Potter y sus amigos Hermione Granger y Ron Weasley, durante los años que pasan en el Colegio Hogwarts de Magia y Hechicería.</p>
  </div>

  <input type="radio" name="tabs" id="tabtwo"> 
  <label for="tabtwo">Harry Potter</label>
  <div class="tab">
    <h1>Descripcion</h1>
    <p>Harry Potter es una serie de novelas fantásticas escrita por la autora británica J. K. Rowling, en la que se describen las aventuras del joven aprendiz de magia y hechicería Harry Potter y sus amigos Hermione Granger y Ron Weasley, durante los años que pasan en el Colegio Hogwarts de Magia y Hechicería.</p>
  </div>
  
  <input type="radio" name="tabs" id="tabtwo"> 
  <label for="tabtwo">Harry Potter</label>
  <div class="tab">
    <h1>Descripcion</h1>
    <p>Harry Potter es una serie de novelas fantásticas escrita por la autora británica J. K. Rowling, en la que se describen las aventuras del joven aprendiz de magia y hechicería Harry Potter y sus amigos Hermione Granger y Ron Weasley, durante los años que pasan en el Colegio Hogwarts de Magia y Hechicería.</p>
  </div>

  <input type="radio" name="tabs" id="tabtwo"> 
  <label for="tabtwo">Harry Potter</label>
  <div class="tab">
    <h1>Descripcion</h1>
    <p>Harry Potter es una serie de novelas fantásticas escrita por la autora británica J. K. Rowling, en la que se describen las aventuras del joven aprendiz de magia y hechicería Harry Potter y sus amigos Hermione Granger y Ron Weasley, durante los años que pasan en el Colegio Hogwarts de Magia y Hechicería.</p>
  </div>

  <input type="radio" name="tabs" id="tabtwo"> 
  <label for="tabtwo">Harry Potter</label>
  <div class="tab">
    <h1>Descripcion</h1>
    <p>Harry Potter es una serie de novelas fantásticas escrita por la autora británica J. K. Rowling, en la que se describen las aventuras del joven aprendiz de magia y hechicería Harry Potter y sus amigos Hermione Granger y Ron Weasley, durante los años que pasan en el Colegio Hogwarts de Magia y Hechicería.</p>
  </div>

  <input type="radio" name="tabs" id="tabtwo"> 
  <label for="tabtwo">Harry Potter</label>
  <div class="tab">
    <h1>Descripcion</h1>
    <p>Harry Potter es una serie de novelas fantásticas escrita por la autora británica J. K. Rowling, en la que se describen las aventuras del joven aprendiz de magia y hechicería Harry Potter y sus amigos Hermione Granger y Ron Weasley, durante los años que pasan en el Colegio Hogwarts de Magia y Hechicería.</p>
  </div>

  <input type="radio" name="tabs" id="tabtwo"> 
  <label for="tabtwo">Harry Potter</label>
  <div class="tab">
    <h1>Descripcion</h1>
    <p>Harry Potter es una serie de novelas fantásticas escrita por la autora británica J. K. Rowling, en la que se describen las aventuras del joven aprendiz de magia y hechicería Harry Potter y sus amigos Hermione Granger y Ron Weasley, durante los años que pasan en el Colegio Hogwarts de Magia y Hechicería.</p>
  </div>


</div>


<a href=http://localhost:8080/libros>Volver</a>


      <style>
      body {
        background: #4AE4FF;
         font-family: lato;
   }
   
   
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
  order: 99; // Put the tabs last
  flex-grow: 1;
	width: 100%;
	display: none;
  padding: 1rem;
  background: #fff;
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
  background: #eee;
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
