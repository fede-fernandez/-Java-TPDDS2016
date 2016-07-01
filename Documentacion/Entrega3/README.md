
### Cambios de la Entrega anterior (Entrega 2)
- **MODIFICADO**: `Rubro` ahora es una clase

ANTES: Un atributo de la clase `LocalComercial` es el `Rubro`, el cual era una interface de Java, de esta interface se implementaban (se creaban clases) los diferentes rubros que se dieron en el enunciado: `LibreriaEscolar` y `KioscoDeDiarios`.

DESPUÉS: `Rubro` es una clase, con el radioDeCercania y el nombre como atributos. Si se desea crear un nuevo rubro, sólo se debe hacer una instancia de esta clase.

VENTAJAS: Antes se necesitaba crear una clase por cada nuevo rubro, si en el futuro se agregaban nuevos rubros, se deberían agregar una clase por cada nuevo rubro, por lo que si la cantidad de rubros empieza a aumentar demasiado, el proyecto quedaría con muchas clases. De esta nueva forma se evita la creación de una clase y pasa a ser un objeto.

DESVENTAJAS: Nuestro proyecto ahora no conoce el nombre o el radioDeCercania por defecto, se debe instanciar el objeto y especificar dichos parámetros.

---

- **REMOVIDO**: se remueven las `etiquetas`, se modelan las `palabrasClave`

ANTES: Un `PuntoDeInteres` tenía un método `getEtiquetas`, cuyas etiquetas no estaban en ningún lado, era un método que usaba una lista a la que se iban insertando palabras desde diferentes clases, rompiendo el encapsulamiento.

DESPUÉS: Un `PuntoDeInteres` tiene un atributo `palabrasClave` el cual es una lista con las palabras características del punto de interés que van a servir para la búsqueda.
Se pueden agregar palabras clave a un punto de interés y a la hora de la búsqueda se hace un filtro de las mismas para obtener un punto de interés que contenga una de ellas.

VENTAJAS: Respecto del diseño anterior, ahora es más sencillo operar con las palabras clave de un punto de interés, se dispone de un método `agregarPalabraClave`, por lo que permite mas flexibilidad a la hora de que aparezcan nuevas palabras claves (esto fue de gran ayuda para la Entrega 3).

DESVENTAJAS: Se repiten datos que ya se conocen para ser agregados a las palabras clave, por ejemplo, un LocalComercial tiene un Rubro con su nombre de rubro, este rubro también se agrega a la lista de palabras claves, por lo que quedaría en dos lados: en el clase rubro con su atributo, y en la lista de palabras clave.

---

- **REMOVIDO**: comentarios dentro de la clase `HorarioDeAtencion` que no eran necesarios, la clase es expresiva y declarativa.

---

- **REMOVIDO**: métodos synchronized ya que no había concurrencia en la entrega.

---

- **REMOVIDO**: Clase `Terminal`
Se removió la clase Terminal ya que tenía mucha responsabilidad, esto es debido a que la terminal era el usuario, y si se modelaba al usuario se estaría modelando como el "Sistema", lo cual no es correcto.

---

- **MODIFICADO**: Clase `Mapa`
Se removió todo el comportamiento de búsqueda dentro de Mapa, ya que tenía responsabilidades que no le correspondían. Un `Mapa` ahora es sólamente una base de conocimientos de puntos de interés que se encuentran localmente, es una lista con operaciones correspondientes a una lista.

---

- **NUEVO**: Clase `Buscador`
El Buscador conoce los orígenes de datos locales (el Mapa) y los externos (buscadores externos) y a la hora de buscar consulta a ambos.

---

- **NUEVO**: Los servicios de búsqueda externos impostores ahora devuelven datos hardcodeados (Un JSON o una clase del servicio externo, dependiendo el servicio), para que se pueda testear la funcionalidad de los Adapters.

---

- **NUEVO**: Se crea el Adapter del servicio de envio de mails (aunque todavía no se tiene conocimiento de dicho servicio)

---

- **REMOVIDO**: A la hora de hacer una búsqueda y se utilizan servicios externos, los resultados que arrojen ya no actualizan nuestros puntos de interés locales. Esto se había implementado porque el enunciado daba a entender que se debía a hacer esto, pero al final el ayudante decidió que no había que hacerlo.

---

- **REMOVIDO**: Clases `BancoBuscadorExterno`, `CGPBuscadorExterno` porque tenían comportamiento repetido, se abstrajo dicho comportamiento a una clase `BuscadorExterno.

---

- **NUEVO**: A la hora de consultar el servicio externo de bancos se utiliza también la búsqueda por rubro.


---

## Decisiones de diseño (Entrega 3)



