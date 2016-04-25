#2016-vn-group-12

##Entrega 1

###Diagrama de Clases
![Diagrama de Clases](http://i.imgur.com/BnWd0yY.png)
[Ampliar](http://i.imgur.com/BnWd0yY.png)

![Diagrama de Clases](http://i.imgur.com/cc9PscP.png)
[Ampliar](http://i.imgur.com/cc9PscP.png)


###Decisiones de diseño

Nos centramos mas que nada en cualidades de diseño :

- Simplicidad: nos concentramos en el enunciado lo que corresponda a la entrega, no agregamos funcionalidades que no pedía (por ejemplo obtener información del punto de interés).
- 
-Flexibilidad: La extensibilidad fue clave, se puede agregar un punto de interés distinto ( uno que no tenga comportamiento pedido al enunciado)  y no habría problema, bastaría con crear una clase aparte que herede de la clase abstracta “punto de interés”y agregar su comportamiento. Lo mismo aplica para rubros ( en ese caso lo implementamos como interface).

-Mantenibilidad: Si por ejemplo en el futuro se quiere hacer alguna modificación al método “BuscarPorTextoLibre” se puede hacer tranquilamente, ya que nos centramos que se cohesivo.
-Consideramos que nuestras clases están desacopladas, no hay una clase sistema la cual le habla a todas las clases, tratamos siempre de crear buenas abstracciones.

Creo que por lo menos en esta entrega no experimentamos el síndrome de “la frazada corta” que las cualidades que decidimos centrarnos, no hacen que disminuyan otras.
