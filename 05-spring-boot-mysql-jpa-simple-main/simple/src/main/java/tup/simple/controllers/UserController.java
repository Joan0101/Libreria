/**
 * Esta clase UserController pertenece al paquete controllers.
 * En principio, solo debería atender a los requests HTTP,
 * y según sea GET, POST, u otro método, analizar el contenido
 * del request, y decidir a qué método llamar. 
 * En principio, esta clase no debería hacer el trabajo. No.
 * Lo que debería hacer es llamar al método encargado de hacer
 * el trabajo y pasarle los parámetros necesarios. 
 * Ese método llamado debería pertenecer a una clase del paquete services. 
 * Pero nosotros no tenemos ese paquete, porque este es un ejemplo muy simple.
 * Veremos que esta clase hace el trabajo, lo que no debería ser así.
 * Entonces, recordar que estamos dejando de lado un principio
 * muy importante, para no complicar este ejemplo.
 */
package tup.simple.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tup.simple.models.User;
import tup.simple.repositories.UserRepository;  // ESTA CLASE MANEJA LO QUE RECIBE EL REQUEST, DECIDE QUIEN TIENE QUE HACER EL TRABAJO Y SE LO PASA (clase de gustavo)

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
public class UserController {
  
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
  private UserRepository userRepository;

  @PostMapping("/add") // Map ONLY POST Requests
  public String addNewUser(@RequestParam String genero, @RequestParam String libro, @RequestParam double precio) {
    // @RequestParam means it is a parameter from the GET or POST request

    User n = new User();
    n.setGenero(genero);
    n.setLibro(libro);
    n.setPrecio(precio);
    
    userRepository.save(n);
    return "Saved";
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

  
  @GetMapping("/libros")
  
  public String getAlllibros() {
    // This returns a JSON or XML with the libros
   
    Iterable<User> iterable = userRepository.findAll();
    /**
     * Lo que viene a continuación se llama text block, 
     * y es tipo String. El Manual de Java los describe en 
     * la sección 3.10.6 Text Blocks.
     * 
     * La variable resp es de tipo String, y le vamos a asignar un bloque de texto.
     * Ese bloque de texto es todo que lo que está contenido entre los dos
     * delimitadores: el de apertura y el de cierre.
     * El delimitador de apertura es la triple comilla """ que está a la
     * derecha del igual.
     * El delimitador de cierre es la triple comilla """ que está al final.
     * Todo es seguido por el punto y coma, porque es el final de una sentencia.
     * 
     * No es buen estilo incluir cadenas largas en un archivo de código fuente.
     * Esto lo hago solo para no introducir una complicación que no agregaría
     * nada a los conceptos que estoy discutiendo ahora.
     * 
     * Comenzamos por poner unos estilos CSS, para que la tabla quede más linda.
     * 
     * Cuando terminamos con los estilos, arrancamos con el HTML de la
     * tabla misma. Lo primero que hacemos es generar una fila y en las
     * celdas de esa fila poner los encabezados, que son los nombres de
     * las columnas o campos de la tabla que está en la base de datos.
     */
    
    String boton = """
      
      <a href= http://localhost:8080/ target="_blank" class=button >Comprar</a>

      <style>
        .button{
          text-decoration:none;
          color: green;
        }
      </style>
 
    """;

    String resp = """
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
              border: 1px solid #ddd;
              padding: 8px;
            }
            #libros tr:nth-child(even){background-color: #f2f2f2;}
            #libros tr:hover {background-color: #c50000;}
            #libros th {
              padding-top: 12px;
              padding-bottom: 12px;
              text-align: left;
              background-color: #04AA6D;
              color: white;
            }
              #libros {
                margin: 0 auto;
                display: inline-block;
              }

          </style>
          
          <table id ='libros'>
            <tr>
              <th>ID</th>
              <th>Genero</th>
              <th>Libro</th>
              <th>Precio</th>
              <th>Presione para comprar </th>
            </tr> 
            
        """; 
        
    for (User libros : iterable) { // los datos asignados a la tabla
      resp += "<tr>"
          + "<td>" + libros.getId() + "</td>"
          + "<td>" + libros.getGenero() + "</td>"
          + "<td>" + libros.getLibro() + "</td>"
          + "<td>" + libros.getPrecio() + "</td>"
          + "<td>" + boton + "</td>"
          + "</tr>";
    } 
     return resp + "</table>";
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
      <h1>LIBRERIA 9 LIBROS</h1>
      <h1>⭐⭐⭐⭐⭐⭐⭐  </h1>
      </div>
      <div class="par">
      <p>
      
      </p>
      </div>  
      </div>   
      <div class="btns">
      <a href= http://localhost:8080/libros target="_blank" class=btns_more >Comprar</a>
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
        border-radius: 50px;
        padding: 8px 12px;
        color: #BF307F;
        font-size: 16px;
        text-transform: uppercase;
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
}
