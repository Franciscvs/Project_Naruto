# Proyecto de Naruto by Esteban García y Franicsco Puentes

Proyecto de consola con menú para gestionar ninjas y misiones.

## Requisitos
- JDK 17+
- (Opcional) Maven 3.8+

## Ejecutar con Maven
```bash
mvn -q -f pom.xml clean compile exec:java
```

## Ejecutar sin Maven
```bash
javac -encoding UTF-8 $(find src/main/java -name "*.java")
java -cp src/main/java naruto.app.App
```

## Funcionalidades del menú
1. Listar ninjas  
2. Crear ninja (Builder)  
3. Crear ninja (Factory por aldea)  
4. Entrenar ninja  
5. Simular combate  
6. Listar misiones  
7. Crear misión  
8. Exportar (Texto/JSON)  
9. Salir
