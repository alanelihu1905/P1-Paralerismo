P1 — Concurrencia en Java con Semáforos y Monitores
Introducción: el problema de la condición de carrera

En programación concurrente, cuando varios hilos intentan acceder y modificar al mismo tiempo un recurso compartido, aparece el problema conocido como condición de carrera. Esto sucede porque operaciones que parecen atómicas, como contador = contador + 1, en realidad se dividen en varias instrucciones: leer, incrementar y escribir. Si dos hilos intercalan su ejecución, el resultado final puede ser incorrecto, produciendo valores inconsistentes.

Este error es difícil de detectar porque no siempre ocurre; depende del orden en que el sistema operativo planifique los hilos, lo que lo vuelve aleatorio y complicado de depurar. En aplicaciones críticas (bancos, sistemas de control, bases de datos, etc.), los efectos pueden ser graves.

Para evitarlo se necesita la exclusión mutua, es decir, garantizar que solo un hilo a la vez pueda ejecutar la parte crítica del código. En este proyecto se probó la solución de este problema con dos enfoques en Java: usando semáforos binarios y monitores (synchronized).

Semáforos y monitores

Un semáforo es una variable entera con dos operaciones atómicas: acquire() (P) y release() (V). Un semáforo binario funciona como un candado: si un hilo entra en la sección crítica, los demás esperan hasta que se libere. En Java se utiliza la clase Semaphore de java.util.concurrent, que permite controlar permisos y asegura exclusión mutua.

Un monitor es un mecanismo de más alto nivel. En Java, se implementa con la palabra clave synchronized, que asegura que un solo hilo a la vez ejecute métodos marcados así. Además, los monitores incluyen mecanismos de espera y notificación (wait() y notify()), útiles para sincronización compleja.

La diferencia principal es que los semáforos son más generales y flexibles, mientras que los monitores en Java son más simples y eficientes para exclusión mutua básica.

Código fuente

La práctica incluye cuatro archivos:
ContadorSemaforo.java → contador protegido por semáforo.
MainSemaforo.java → clase principal que ejecuta la versión con semáforo.
ContadorMonitor.java → contador protegido con monitor (synchronized).
MainMonitor.java → clase principal que ejecuta la versión con monitor.

Resultados de ejecución

Ejecución con Semáforo
=== Ejecución con Semáforo ===
Esperado = 800000
Obtenido = 800000
Tiempo   = 11374 ms

Ejecución con Monitor
=== Ejecución con Monitor ===
Esperado = 800000
Obtenido = 800000
Tiempo   = 58 ms

Conclusiones

Ambos mecanismos resolvieron la condición de carrera y el valor obtenido fue el esperado en todos los casos. Sin embargo, los tiempos muestran una diferencia importante: el semáforo tardó alrededor de 11734 ms, mientras que el monitor ejecutó en solo 58 ms.

Esto ocurre porque el semáforo maneja colas y control más general, lo cual introduce sobrecarga. En cambio, el monitor usa el candado intrínseco del objeto, optimizado en la JVM para operaciones rápidas de exclusión mutua.

La conclusión es que:

Monitores (synchronized) son más simples y eficientes para proteger recursos únicos.

Semáforos son más flexibles y útiles cuando se necesitan permisos múltiples o patrones como productor–consumidor.

Ambas técnicas son fundamentales para el desarrollo de software concurrente confiable, y la elección depende del tipo de problema que se desee resolver.
