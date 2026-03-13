# 🔌 Documentación de Endpoints - SGCC API

Guía completa de todos los endpoints disponibles en la API SGCC.

---

## 📋 Tabla de Contenidos

1. [Health Check](#health-check)
2. [Locations (Ubicaciones)](#locations-ubicaciones)
3. [Meters (Medidores)](#meters-medidores)
4. [Meter Readings (Lecturas de Consumo)](#meter-readings-lecturas-de-consumo)
5. [Services (Tipos de Servicios)](#services-tipos-de-servicios)
6. [Tenants (Inquilinos)](#tenants-inquilinos)
7. [Invoices (Facturas)](#invoices-facturas)
8. [Allocations (Asignaciones de Gastos)](#allocations-asignaciones-de-gastos)
9. [SubMeters (Medidores Secundarios)](#submeters-medidores-secundarios)
10. [SubMeter Readings (Lecturas de Sub-medidores)](#submeter-readings-lecturas-de-sub-medidores)
11. [Reports (Reportes de Inquilinos)](#reports-reportes-de-inquilinos)

---

## Health Check

Verifica el estado y disponibilidad de la API.

### Obtener Estado de la API
```http
GET /health-check
```

**Respuesta Exitosa (200 OK)**
```json
{
  "success": true,
  "statusCode": 200,
  "message": "Api SGCC is running",
  "timestamp": "2026-01-19T10:30:45.123456",
  "headers": {}
}
```

---

## Locations (Ubicaciones)

Gestiona ubicaciones (edificios, complejos residenciales, etc.)

### Obtener todas las ubicaciones (paginado)
```http
GET /locations?page=0&size=20&sort=id,desc
```

**Parámetros de Query:**
- `page` (opcional): Número de página (default: 0)
- `size` (opcional): Elementos por página (default: 20)
- `sort` (opcional): Ordenamiento (field,asc|desc)

**Respuesta (200 OK)**
```json
{
  "content": [
    {
      "id": 1,
      "name": "Edificio A",
      "address": "Calle Principal 123",
      "city": "Madrid",
      "postalCode": "28001",
      "createdAt": "2026-01-15T10:00:00",
      "updatedAt": "2026-01-19T10:30:00"
    }
  ],
  "totalElements": 5,
  "totalPages": 1,
  "currentPage": 0,
  "pageSize": 20
}
```

### Obtener ubicación por ID
```http
GET /locations/{id}
```

**Parámetros:**
- `id` (requerido): ID de la ubicación

**Respuesta (200 OK)**
```json
{
  "id": 1,
  "name": "Edificio A",
  "address": "Calle Principal 123",
  "city": "Madrid",
  "postalCode": "28001",
  "createdAt": "2026-01-15T10:00:00",
  "updatedAt": "2026-01-19T10:30:00"
}
```

### Crear nueva ubicación
```http
POST /locations
Content-Type: application/json

{
  "name": "Edificio B",
  "address": "Avenida Secundaria 456",
  "city": "Barcelona",
  "postalCode": "08001"
}
```

**Respuesta (201 Created)**
```json
{
  "id": 2,
  "name": "Edificio B",
  "address": "Avenida Secundaria 456",
  "city": "Barcelona",
  "postalCode": "08001",
  "createdAt": "2026-01-19T11:00:00",
  "updatedAt": "2026-01-19T11:00:00"
}
```

### Actualizar ubicación
```http
PUT /locations/{id}
Content-Type: application/json

{
  "name": "Edificio B Renovado",
  "address": "Avenida Secundaria 456",
  "city": "Barcelona",
  "postalCode": "08001"
}
```

**Respuesta (200 OK)**
```json
{
  "id": 2,
  "name": "Edificio B Renovado",
  "address": "Avenida Secundaria 456",
  "city": "Barcelona",
  "postalCode": "08001",
  "createdAt": "2026-01-19T11:00:00",
  "updatedAt": "2026-01-19T12:00:00"
}
```

### Eliminar ubicación
```http
DELETE /locations/{id}
```

**Respuesta (204 No Content)**

---

## Meters (Medidores)

Administra medidores de servicios (agua, electricidad, gas, etc.)

### Obtener todos los medidores (paginado)
```http
GET /meters?page=0&size=20
```

**Respuesta (200 OK)**
```json
{
  "content": [
    {
      "id": 1,
      "code": "MET-001",
      "type": "WATER",
      "locationId": 1,
      "serialNumber": "SN-001-2026",
      "installDate": "2026-01-01",
      "status": "ACTIVE",
      "createdAt": "2026-01-10T09:00:00",
      "updatedAt": "2026-01-19T10:30:00"
    }
  ],
  "totalElements": 10,
  "totalPages": 1,
  "currentPage": 0,
  "pageSize": 20
}
```

### Obtener medidor por ID
```http
GET /meters/{id}
```

**Respuesta (200 OK)**
```json
{
  "id": 1,
  "code": "MET-001",
  "type": "WATER",
  "locationId": 1,
  "serialNumber": "SN-001-2026",
  "installDate": "2026-01-01",
  "status": "ACTIVE",
  "createdAt": "2026-01-10T09:00:00",
  "updatedAt": "2026-01-19T10:30:00"
}
```

### Obtener medidores por ubicación
```http
GET /meters/by-location/{locationId}?page=0&size=20
```

**Parámetros:**
- `locationId` (requerido): ID de la ubicación

**Respuesta (200 OK)**
```json
{
  "content": [
    {
      "id": 1,
      "code": "MET-001",
      "type": "WATER",
      "locationId": 1,
      "serialNumber": "SN-001-2026",
      "installDate": "2026-01-01",
      "status": "ACTIVE"
    }
  ],
  "totalElements": 3,
  "totalPages": 1
}
```

### Crear nuevo medidor
```http
POST /meters
Content-Type: application/json

{
  "code": "MET-002",
  "type": "ELECTRICITY",
  "locationId": 1,
  "serialNumber": "SN-002-2026",
  "installDate": "2026-01-05"
}
```

**Respuesta (201 Created)**
```json
{
  "id": 2,
  "code": "MET-002",
  "type": "ELECTRICITY",
  "locationId": 1,
  "serialNumber": "SN-002-2026",
  "installDate": "2026-01-05",
  "status": "ACTIVE",
  "createdAt": "2026-01-19T11:00:00",
  "updatedAt": "2026-01-19T11:00:00"
}
```

### Actualizar medidor
```http
PUT /meters/{id}
Content-Type: application/json

{
  "code": "MET-002-UPD",
  "type": "ELECTRICITY",
  "locationId": 1,
  "serialNumber": "SN-002-2026",
  "installDate": "2026-01-05",
  "status": "MAINTENANCE"
}
```

**Respuesta (200 OK)**

### Eliminar medidor
```http
DELETE /meters/{id}
```

**Respuesta (204 No Content)**

---

## Meter Readings (Lecturas de Consumo)

Registra lecturas de medidores para cálculo de consumos.

### Obtener todas las lecturas (paginado)
```http
GET /meter-readings?page=0&size=20
```

**Respuesta (200 OK)**
```json
{
  "content": [
    {
      "id": 1,
      "meterId": 1,
      "meterCode": "MET-001",
      "readingValue": 1234.50,
      "readingDate": "2026-01-15T10:00:00",
      "notes": "Lectura regular",
      "createdAt": "2026-01-15T10:30:00",
      "updatedAt": "2026-01-15T10:30:00"
    }
  ],
  "totalElements": 25,
  "totalPages": 2
}
```

### Obtener lectura por ID
```http
GET /meter-readings/{id}
```

**Respuesta (200 OK)**
```json
{
  "id": 1,
  "meterId": 1,
  "meterCode": "MET-001",
  "readingValue": 1234.50,
  "readingDate": "2026-01-15T10:00:00",
  "notes": "Lectura regular",
  "createdAt": "2026-01-15T10:30:00",
  "updatedAt": "2026-01-15T10:30:00"
}
```

### Crear nueva lectura
```http
POST /meter-readings
Content-Type: application/json

{
  "meterId": 1,
  "readingValue": 1250.75,
  "readingDate": "2026-01-19T10:00:00",
  "notes": "Lectura mensual"
}
```

**Respuesta (201 Created)**
```json
{
  "id": 26,
  "meterId": 1,
  "meterCode": "MET-001",
  "readingValue": 1250.75,
  "readingDate": "2026-01-19T10:00:00",
  "notes": "Lectura mensual",
  "createdAt": "2026-01-19T11:00:00",
  "updatedAt": "2026-01-19T11:00:00"
}
```

### Actualizar lectura
```http
PUT /meter-readings/{id}
Content-Type: application/json

{
  "readingValue": 1251.00,
  "readingDate": "2026-01-19T10:00:00",
  "notes": "Lectura corregida"
}
```

**Respuesta (200 OK)**

### Eliminar lectura
```http
DELETE /meter-readings/{id}
```

**Respuesta (204 No Content)**

---

## Services (Tipos de Servicios)

Define y gestiona los tipos de servicios disponibles.

### Obtener todos los servicios (paginado)
```http
GET /services?page=0&size=20
```

**Respuesta (200 OK)**
```json
{
  "content": [
    {
      "id": 1,
      "name": "Agua",
      "description": "Servicio de agua potable",
      "tarifaActual": 8.50,
      "createdAt": "2026-01-01T00:00:00",
      "updatedAt": "2026-01-19T10:30:00"
    },
    {
      "id": 2,
      "name": "Electricidad",
      "description": "Servicio de electricidad",
      "tarifaActual": 0.85,
      "createdAt": "2026-01-01T00:00:00",
      "updatedAt": "2026-01-19T10:30:00"
    }
  ],
  "totalElements": 4,
  "totalPages": 1
}
```

### Obtener servicio por ID
```http
GET /services/{id}
```

### Crear servicio
```http
POST /services
Content-Type: application/json

{
  "name": "Gas Natural",
  "code": "GAS",
  "description": "Servicio de gas natural",
  "unitOfMeasure": "m³"
}
```

### Actualizar servicio
```http
PUT /services/{id}
Content-Type: application/json

{
  "name": "Gas Natural Actualizado",
  "code": "GAS",
  "description": "Servicio de gas natural",
  "unitOfMeasure": "m³"
}
```

### Eliminar servicio
```http
DELETE /services/{id}
```

---

## Tenants (Inquilinos)

Gestiona inquilinos y residentes de las ubicaciones.

### Obtener todos los inquilinos (paginado)
```http
GET /tenants?page=0&size=20
```

**Respuesta (200 OK)**
```json
{
  "content": [
    {
      "id": 1,
      "firstName": "Juan",
      "lastName": "Pérez",
      "email": "juan.perez@email.com",
      "phoneNumber": "+34612345678",
      "locationId": 1,
      "apartmentNumber": "101",
      "status": "ACTIVE",
      "createdAt": "2026-01-10T09:00:00",
      "updatedAt": "2026-01-19T10:30:00"
    }
  ],
  "totalElements": 15,
  "totalPages": 1
}
```

### Obtener inquilino por ID
```http
GET /tenants/{id}
```

### Obtener inquilinos por ubicación
```http
GET /tenants/location/{locationId}?page=0&size=20
```

**Parámetros:**
- `locationId` (requerido): ID de la ubicación

**Respuesta (200 OK)**
```json
{
  "content": [
    {
      "id": 1,
      "firstName": "Juan",
      "lastName": "Pérez",
      "email": "juan.perez@email.com",
      "phoneNumber": "+34612345678",
      "locationId": 1,
      "apartmentNumber": "101",
      "status": "ACTIVE"
    },
    {
      "id": 2,
      "firstName": "María",
      "lastName": "García",
      "email": "maria.garcia@email.com",
      "phoneNumber": "+34623456789",
      "locationId": 1,
      "apartmentNumber": "102",
      "status": "ACTIVE"
    }
  ],
  "totalElements": 5,
  "totalPages": 1
}
```

### Crear nuevo inquilino
```http
POST /tenants
Content-Type: application/json

{
  "firstName": "Carlos",
  "lastName": "López",
  "email": "carlos.lopez@email.com",
  "phoneNumber": "+34634567890",
  "locationId": 1,
  "apartmentNumber": "103"
}
```

**Respuesta (201 Created)**

### Actualizar inquilino
```http
PUT /tenants/{id}
Content-Type: application/json

{
  "firstName": "Carlos",
  "lastName": "López",
  "email": "carlos.lopez.nuevo@email.com",
  "phoneNumber": "+34634567890",
  "locationId": 1,
  "apartmentNumber": "103",
  "status": "ACTIVE"
}
```

### Eliminar inquilino
```http
DELETE /tenants/{id}
```

---

## Invoices (Facturas)

Gestiona facturas generadas basadas en consumos.

### Obtener todas las facturas (paginado)
```http
GET /invoices?page=0&size=20
```

**Respuesta (200 OK)**
```json
{
  "content": [
    {
      "id": 1,
      "invoiceNumber": "INV-2026-001",
      "tenantId": 1,
      "tenantName": "Juan Pérez",
      "amount": 125.50,
      "dueDate": "2026-02-15",
      "status": "PENDING",
      "createdAt": "2026-01-19T10:00:00",
      "updatedAt": "2026-01-19T10:30:00"
    }
  ],
  "totalElements": 12,
  "totalPages": 1
}
```

### Obtener factura por ID
```http
GET /invoices/{id}
```

### Crear nueva factura
```http
POST /invoices
Content-Type: application/json

{
  "invoiceNumber": "INV-2026-010",
  "tenantId": 2,
  "amount": 95.75,
  "dueDate": "2026-02-28",
  "description": "Consumo de agua enero"
}
```

**Respuesta (201 Created)**

### Crear factura con cálculo automático
```http
POST /invoices/calcular
Content-Type: application/json

{
  "meterId": 1,
  "periodStart": "2024-01-01",
  "periodEnd": "2024-01-31",
  "currency": "PEN"
}
```

**Respuesta (201 Created)**
```json
{
  "id": 1,
  "meterId": 1,
  "periodStart": "2024-01-01",
  "periodEnd": "2024-01-31",
  "totalAmount": 51.00,
  "currency": "PEN",
  "issuedAt": "2026-03-12T10:00:00"
}
```

### Actualizar factura
```http
PUT /invoices/{id}
Content-Type: application/json

{
  "amount": 96.00,
  "dueDate": "2026-03-01",
  "status": "PAID"
}
```

### Eliminar factura
```http
DELETE /invoices/{id}
```

---

## Allocations (Asignaciones de Gastos)

Distribuye gastos comunes entre inquilinos.

### Obtener todas las asignaciones (paginado)
```http
GET /allocations?page=0&size=20
```

**Respuesta (200 OK)**
```json
{
  "content": [
    {
      "id": 1,
      "locationId": 1,
      "invoiceId": 1,
      "tenantId": 1,
      "allocatedAmount": 50.25,
      "percentage": 25.5,
      "allocationType": "PROPORTIONAL",
      "createdAt": "2026-01-19T10:00:00",
      "updatedAt": "2026-01-19T10:30:00"
    }
  ],
  "totalElements": 20,
  "totalPages": 2
}
```

### Obtener asignación por ID
```http
GET /allocations/{id}
```

### Crear asignación
```http
POST /allocations
Content-Type: application/json

{
  "locationId": 1,
  "invoiceId": 1,
  "tenantId": 1,
  "allocatedAmount": 50.25,
  "percentage": 25.5,
  "allocationType": "PROPORTIONAL"
}
```

### Actualizar asignación
```http
PUT /allocations/{id}
Content-Type: application/json

{
  "allocatedAmount": 51.00,
  "percentage": 26.0,
  "allocationType": "PROPORTIONAL"
}
```

### Eliminar asignación
```http
DELETE /allocations/{id}
```

---

## SubMeters (Medidores Secundarios)

Gestiona medidores secundarios para subdivisiones.

### Obtener todos los sub-medidores (paginado)
```http
GET /sub-meters?page=0&size=20
```

**Respuesta (200 OK)**
```json
{
  "content": [
    {
      "id": 1,
      "code": "SUB-MET-001",
      "parentMeterId": 1,
      "serialNumber": "SN-SUB-001-2026",
      "description": "Sub-medidor apartamento 101",
      "installDate": "2026-01-05",
      "status": "ACTIVE",
      "createdAt": "2026-01-10T09:00:00",
      "updatedAt": "2026-01-19T10:30:00"
    }
  ],
  "totalElements": 8,
  "totalPages": 1
}
```

### Obtener sub-medidor por ID
```http
GET /sub-meters/{id}
```

### Crear sub-medidor
```http
POST /sub-meters
Content-Type: application/json

{
  "code": "SUB-MET-002",
  "parentMeterId": 1,
  "serialNumber": "SN-SUB-002-2026",
  "description": "Sub-medidor apartamento 102",
  "installDate": "2026-01-05"
}
```

### Actualizar sub-medidor
```http
PUT /sub-meters/{id}
Content-Type: application/json

{
  "code": "SUB-MET-002",
  "description": "Sub-medidor apartamento 102",
  "status": "ACTIVE"
}
```

### Eliminar sub-medidor
```http
DELETE /sub-meters/{id}
```

---

## SubMeter Readings (Lecturas de Sub-medidores)

Registra lecturas de sub-medidores.

### Obtener todas las lecturas de sub-medidores (paginado)
```http
GET /sub-meter-readings?page=0&size=20
```

**Respuesta (200 OK)**
```json
{
  "content": [
    {
      "id": 1,
      "subMeterId": 1,
      "subMeterCode": "SUB-MET-001",
      "readingValue": 500.25,
      "readingDate": "2026-01-15T10:00:00",
      "notes": "Lectura regular",
      "createdAt": "2026-01-15T10:30:00",
      "updatedAt": "2026-01-15T10:30:00"
    }
  ],
  "totalElements": 15,
  "totalPages": 1
}
```

### Obtener lectura de sub-medidor por ID
```http
GET /sub-meter-readings/{id}
```

### Crear lectura de sub-medidor
```http
POST /sub-meter-readings
Content-Type: application/json

{
  "subMeterId": 1,
  "readingValue": 510.50,
  "readingDate": "2026-01-19T10:00:00",
  "notes": "Lectura mensual"
}
```

**Respuesta (201 Created)**

### Actualizar lectura de sub-medidor
```http
PUT /sub-meter-readings/{id}
Content-Type: application/json

{
  "readingValue": 511.00,
  "readingDate": "2026-01-19T10:00:00",
  "notes": "Lectura corregida"
}
```

### Eliminar lectura de sub-medidor
```http
DELETE /sub-meter-readings/{id}
```

---

## Reports (Reportes de Inquilinos)

Genera reportes mensuales con totales por servicio para inquilinos.

### Obtener reporte mensual de inquilino
```http
GET /reports/tenants/{tenantId}/{month}/{year}
```

**Parámetros:**
- `tenantId` (requerido): ID del inquilino
- `month` (requerido): Mes del reporte (1-12)
- `year` (requerido): Año del reporte

**Respuesta (200 OK)**
```json
{
  "tenantId": 1,
  "tenantName": "Peluquería",
  "month": 1,
  "year": 2024,
  "reportDate": "2026-03-12",
  "totalGeneral": 40.80,
  "serviceTotals": [
    {
      "serviceId": 1,
      "serviceName": "LUZ",
      "totalAmount": 25.50,
      "currency": "PEN",
      "allocations": [
        {
          "allocationId": 1,
          "serviceName": "LUZ",
          "amount": 25.50,
          "percentage": 50.0,
          "periodStart": "2024-01-01",
          "periodEnd": "2024-01-31"
        }
      ]
    },
    {
      "serviceId": 2,
      "serviceName": "AGUA",
      "totalAmount": 15.30,
      "currency": "PEN",
      "allocations": [
        {
          "allocationId": 2,
          "serviceName": "AGUA",
          "amount": 15.30,
          "percentage": 30.0,
          "periodStart": "2024-01-01",
          "periodEnd": "2024-01-31"
        }
      ]
    }
  ]
}
```

### Obtener reporte del mes actual
```http
GET /reports/tenants/{tenantId}/current
```

**Parámetros:**
- `tenantId` (requerido): ID del inquilino

**Respuesta (200 OK)**
```json
{
  "tenantId": 1,
  "tenantName": "Peluquería",
  "month": 3,
  "year": 2026,
  "reportDate": "2026-03-12",
  "totalGeneral": 0.00,
  "serviceTotals": []
}
```

### Obtener servicios activos
```http
GET /reports/services/active
```

**Respuesta (200 OK)**
```json
[
  {
    "id": 1,
    "name": "LUZ",
    "description": "Servicio de Electricidad",
    "tarifaActual": 0.85,
    "createdAt": "2026-01-01T00:00:00"
  },
  {
    "id": 2,
    "name": "AGUA",
    "description": "Servicio de Agua",
    "tarifaActual": 8.50,
    "createdAt": "2026-01-01T00:00:00"
  }
]
```

---

## 🔐 Autenticación y Seguridad

Todos los endpoints requieren validación a través de:

- **API Key**: Incluida en header `X-API-Key`
- **System ID**: Incluida en header `X-System-Id`
- **JWT Token**: Incluida en header `Authorization: Bearer <token>` (para endpoints protegidos)

### Ejemplo de Request con Autenticación
```http
GET /locations
X-API-Key: uLCMTMdPOcdy4HTag9GpSTLSJAovBEFgbGrQR6R2fkMXL2mtKeyDRqi8lNOb5ar1fLN7HZGPttqtNZ5h4Vf8WmQuJinAqnWAqfNyNhPCVDN2xCkUFtGnkqO88vQz6HUr
X-System-Id: 1
Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...
```

---

## 📊 Códigos de Estado HTTP

| Código | Significado |
|--------|------------|
| **200** | OK - Solicitud exitosa |
| **201** | Created - Recurso creado exitosamente |
| **204** | No Content - Operación exitosa sin contenido |
| **400** | Bad Request - Solicitud inválida |
| **401** | Unauthorized - Autenticación requerida |
| **403** | Forbidden - Acceso denegado |
| **404** | Not Found - Recurso no encontrado |
| **500** | Internal Server Error - Error del servidor |

---

## 📝 Notas Importantes

- Todos los endpoints soportan **paginación**: `?page=0&size=20`
- Las respuestas están en formato **JSON**
- Los timestamps están en formato **ISO 8601** (UTC)
- Los IDs son valores **Long**
- La validación de entrada es **obligatoria**
- Los errores retornan estructura estándar con mensaje descriptivo

---

**Última actualización**: 2026-03-12
