<p align="center">
  <a href="https://spring.io/projects/spring-boot" target="blank"><img src="https://spring.io/img/projects/spring-boot.svg" width="120" alt="Spring Logo" /></a>
</p>

# SGCC API - Sistema de Gestión de Consumos y Cobros

Una **API RESTful robusta** construida con Spring Boot 4.0 para la gestión integral de medidores de servicios, lecturas de consumo, facturación y asignaciones de gastos en edificios multi-inquilino.

> Este README está basado en [TEMPLATE.md](./TEMPLATE.md) - Plantilla estándar para documentar proyectos Spring Boot.
> Si deseas documentar otro proyecto, consulta [TEMPLATE_GUIDE.md](./TEMPLATE_GUIDE.md)

## 🚀 Características Principales

* **Gestión Integral de Medidores**: Administración completa del ciclo de vida de medidores (agua, electricidad, gas, etc.)
* **Registro de Lecturas**: Sistema de captura y seguimiento de lecturas periódicas para cálculo de consumos
* **Gestión de Ubicaciones**: Soporte para múltiples ubicaciones/edificios con medidores y inquilinos asociados
* **Facturación Automática**: Generación de facturas basadas en consumos medidos
* **Distribución de Gastos**: Sistema de asignaciones inteligente para distribuir costos entre inquilinos
* **Sub-medidores**: Gestión de medidores secundarios para desglose granular de consumos
* **Auditoría**: Registro detallado de cambios en las entidades (quién, qué, cuándo)
* **Seguridad Multicapa**: API Key, System ID y autenticación JWT integrados

## 🛠 Tech Stack

* **Framework**: [Spring Boot](https://spring.io/projects/spring-boot) (Java)
* **Base de Datos**: PostgreSQL
* **ORM**: Spring Data JPA + Hibernate
* **Autenticación**: JWT (jjwt)
* **Mapeo de DTOs**: MapStruct
* **Documentación**: SpringDoc OpenAPI (Swagger)
* **Lenguaje**: Java 25
* **Build Tool**: Gradle

---

El siguiente diagrama muestra las relaciones principales entre las entidades del sistema:

```
┌─────────────────────────────────────────────────────────────────────┐
│                         LOCATION (Ubicación)                         │
│                      (Edificio, Complejo, etc.)                      │
└────────────────────────────┬────────────────────────────────────────┘
                             │
                ┌────────────┴────────────┐
                │                         │
        ┌───────▼──────────┐    ┌────────▼────────┐
        │ TENANT           │    │ METER           │
        │ (Inquilino)      │    │ (Medidor)       │
        │                  │    │ (Agua, Luz...)  │
        └──────────────────┘    └────────┬────────┘
                                         │
                              ┌──────────▼──────────┐
                              │ METER_READING       │
                              │ (Lectura Consumo)   │
                              └─────────┬───────────┘
                                        │
                         ┌──────────────┴──────────────┐
                         │                             │
                    ┌────▼────┐              ┌────────▼────┐
                    │ INVOICE │              │ ALLOCATION  │
                    │(Factura)│              │ (Gasto Asig)│
                    └─────────┘              └─────────────┘
                    
┌─────────────────────────────────────────────────────────────────────┐
│     SUB_METER (Medidor Secundario) → SUB_METER_READING (Lecturas)   │
└─────────────────────────────────────────────────────────────────────┘
```

### 📌 Descripción de Entidades Clave

| Entidad | Descripción | Relaciones |
|---------|-------------|-----------|
| **Location** | Ubicación física (edificio, complejo residencial) | Contiene múltiples Meters y Tenants |
| **Meter** | Medidor de servicios | Pertenece a Location, genera MeterReadings |
| **MeterReading** | Lectura puntual de consumo | Vinculada a Meter, base para Invoice |
| **Tenant** | Inquilino o residente | Asociado a Location, receptor de Invoices |
| **Invoice** | Factura de cobro | Base en MeterReadings, vinculada a Tenant |
| **Allocation** | Distribución de gastos | Asigna porción de Invoice a Tenant |
| **Service** | Tipo de servicio | Define servicios (agua, electricidad, gas) |
| **SubMeter** | Medidor secundario | Medidor adicional bajo un Meter padre |
| **SubMeterReading** | Lectura de sub-medidor | Consumo registrado de SubMeter |

### 🔗 Flujos de Datos Principales

**Flujo de Lectura de Consumo:**
```
Location → Meter → MeterReading → Invoice (cálculo)
```

**Flujo de Asignación de Gastos:**
```
Invoice → Allocation → Tenant (distribución)
```

**Flujo de Sub-medidores:**
```
Meter → SubMeter → SubMeterReading (desglose)
```

## 🔐 Seguridad

La API implementa múltiples capas de seguridad:

### Autenticación y Autorización

* **API Key**: Validación de credencial de cliente en header `X-API-Key`
* **System ID**: Identificador del sistema cliente en header `X-System-Id`
* **JWT Token**: Autenticación basada en tokens JWT en header `Authorization: Bearer <token>`

### Validación y Auditoría

* **Validación de Entrada**: Todos los datos de entrada se validan usando Jakarta Validation
* **Auditoría de Cambios**: Registro automático de modificaciones mediante aspectos AOP
* **Configuración de Seguridad** (application-dev.yaml):
  ```yaml
  security:
    systemId: 1
    apiKey: [API_KEY_AQUI]
    jwt:
      secret: [JWT_SECRET_AQUI]
      expiration: 3600  # 1 hora
  ```

## 💾 Base de Datos

### Configuración por Perfil

#### Perfil Development
```yaml
URL: jdbc:postgresql://localhost:5432/sgcc_v3
Usuario: sgcc_user
Contraseña: sgcc_password
DDL Mode: validate
Pool: HikariCP (max: 10, min: 2)
```

#### Perfil Production
```yaml
URL: Variables de entorno configurables
  - DB_HOST
  - DB_PORT
  - DB_NAME
  - DB_USER
  - DB_PASSWORD
Pool: HikariCP (max: 20, min: 5)
Optimizaciones: SQL sin formato, logging mínimo
```

### Dialecto
* PostgreSQL Dialect (Hibernate)
* Connection Pool: HikariCP para máximo rendimiento

## 🔧 Configuración e Instalación

### Requisitos Previos
* **Java 25+**
* **PostgreSQL 12+**
* **Gradle 8.x** (incluido en el proyecto como wrapper)

### Pasos de Instalación

1. **Clonar el repositorio**
   ```bash
   git clone https://github.com/tu-usuario/spring-boot-api-sgcc.git
   cd spring-boot-api-sgcc
   ```

2. **Crear la base de datos**
   ```sql
   CREATE USER sgcc_user WITH PASSWORD 'sgcc_password';
   CREATE DATABASE sgcc_v3 OWNER sgcc_user;
   ```

3. **Configurar Variables de Entorno** (Opcional para desarrollo)
   ```bash
   # Para producción, establecer:
   export DB_HOST=your-db-host
   export DB_PORT=5432
   export DB_NAME=sgcc_v3
   export DB_USER=your-user
   export DB_PASSWORD=your-password
   ```

## ▶️ Ejecución

```bash
# Desarrollo
./gradlew bootRun --args='--spring.profiles.active=dev'

# Producción
./gradlew bootRun --args='--spring.profiles.active=prod'

# Compilar JAR
./gradlew build
java -jar build/libs/spring-boot-api-sgcc-0.0.1-SNAPSHOT.jar
```

## 📚 Documentación

Para ver la lista completa de endpoints, ejemplos de JSON y respuestas, consulta:
👉 **[API_ENDPOINTS.md](./API_ENDPOINTS.md)**

La documentación interactiva está disponible en Swagger UI durante el desarrollo:
* **Development**: `http://localhost:8080/swagger-ui.html`
* **Production**: Deshabilitado por seguridad
* **OpenAPI Spec**: `http://localhost:8080/v1/api-docs`

## 🧪 Tests

```bash
# Ejecutar tests unitarios
./gradlew test

# Tests con coverage (si está configurado)
./gradlew test jacocoTestReport
```

## 📄 Licencia

Este proyecto está bajo la licencia [Apache License 2.0](LICENSE).


