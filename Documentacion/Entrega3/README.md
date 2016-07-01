
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
Se partió de la idea de que los tres procesos deben tener alguna característica en común para poder agruparlos, como eran procesos muy distintos se decidió aplicar el patrón de diseño *Command* porque se abstrae de lo que realiza el proceso, sólamente importa que conozca el mensaje ejecutar() para poder actuar.



Se modeló una clase `PlanificadorDeProcesos` la cual conoce los procesos a ejecutar. Para la implementación, surgieron varias ideas:
- Crear un hilo que se vaya fijando el tiempo y comparando si alguno de los horarios planificados para el proceso coincide con el mismo. Si coincidía, se ejecutaba dicho proceso. Esta opción requería investigar sobre threads en Java, sincronización y demás, como no se disponía de mucho tiempo para comprender estas tecnologías, se descartó por el momento.
- Utilizar el patrón Observer, donde de alguna manera le llegaría el mensaje "esHoraDeEjecutar(unProceso)" y lo ejecutaría, esta alternativa sirve para poder hacer mejores tests, ya que el resto de las alternativas requieren temporalidad.
- Utilizar una biblioteca externa llamada *Quartz*, que simplifica la ejecución de tareas a determinado tiempo. Al igual que la alternativa de hacer el thread, se descartó por falta de tiempo.
- Utilizar una biblioteca de Java, Executor, que ejecuta el método `run()` de una clase que implemente la interface *Runnable*. Se optó por esta alternativa, ya que era la más sencilla de aplicar y cumplía con lo pedido, tuvimos que adaptar el funcionamiento de Executor al proceso para que ejecute el método `run()` que solicita dicha interface.

No se pudo realizar el punto BONUS de la entrega ya que la alternativa optada requería más investigación, tal vez no se podía implementar en dicha alternativa, por lo que la opción de crear el hilo sería la más ideal, ya que nosotros estaríamos manejando el algoritmo de planeación de procesos.

En un principio, `Proceso` era una interface de Java, pero luego, al ver que había comportamiento en común en cuanto a los resultados de ejecución o manejo de errores, pasó a ser una clase abstracta con métodos que deleguen el manejo de errores a ot

Se utilizó el patrón State para modelar los estados de los procesos: EnEspera, Ejecutando, Fallido, Finalizado. Otras opciones tenidas en cuenta, que no eran mantenibles eran: crear un atributo booleano por cada estado, crear un atributo del tipo String que indique el estado, luego utilizar sentencias if para consultar el estado, lo que haría que quede muy acoplada la clase `Proceso`. Se optó por una alternativa más objetosa: cosificar los estados.

El uso de dicho patrón serviría para realizar el punto BONUS, ya que teniendo una cola de procesos, si alguno de ellos está en el estado EJECUCION, no debería haber otro proceso ejecutándose.
Otra alternativa a esto era el uso de semáforos, pero esto iba a ensuciar el código con sincronismo entre procesos y lo iba a hacer más difícil de comprender.

Para el manejo de errores se creó una interface `AccionEnCasoDeFallo` la cual es atributo de la clase `Proceso`, se decidió inyectar esta dependencia dentro del proceso, ya que permite más flexibilidad a la hora de configurar cómo se quiere actuar frente a la falla de cada proceso en específico, si se configuraba desde el planificador de procesos, se aplicaría el mismo manejo de error para todos los procesos por igual.
`NotificarAlAdministrador`, `ReintentarEjecucion`, `NoHacerNada` implementan dicha interface, nuevamente se aplicó el patrón State, ya que cada acción tenía comportamientos muy diferentes, por lo que cada una debe utilizar el método `ejecutar()`
El caso de `ReintentarEjecucion` permite que se pueda configurar si se desea notificar al administrador o no, por lo que la acción `NotificarAlAdministrador` es reutilizable para este caso.

Una alternativa a esta implementación podía ser: tener que cada acción sea un booleano, atributo dentro del proceso y dependiendo de esos valores, el proceso actúa frente al error. Código no mantenible, tiene mucho acoplamiento dentro de la clase Proceso.

En caso de que un proceso falle, no debe tirar ninguna excepción de Java, simplemente la clase `Proceso` posee el método fallar(), el cual activa la acción a realizar en caso de fallo.

Para los resultados de ejecución se colocaron como atributo dentro de cada Proceso, el cual conoce el mensaje finalizar(), se utilizó este mensaje ya que no hay forma de conocer si terminó un proceso utilizando la alternativa que elegimos. El mensaje finalizar() almacena los resultados de dicha ejecución.
Una alternativa que se planteó fue crear una clase llamada `ProcesosFinalizados`, la cual contendría cada proceso finalizado, junto con su resultado de ejecución. Finalmente no se decidió utilizar, porque existiría redundancia de datos o repetición de datos, ya que el planificador de procesos tiene una cola de procesos, cada proceso conoce su estado, por lo que de esa cola se podría filtrar cada proceso con el estado finalizado y se obtendrían así dichos procesos.




