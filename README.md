# Online Food Order System

A simple Spring Boot REST API using Spring Web, Spring Data JPA, validation, and an H2 database.

## Requirements

- Java 17 or newer
- Maven 3.9 or newer

## Run

```powershell
mvn spring-boot:run
```

The API runs at `http://localhost:8080/api/orders`.

## API endpoints

| Method | URL | Purpose |
|---|---|---|
| GET | `/api/orders` | Get all orders |
| GET | `/api/orders/{id}` | Get one order |
| POST | `/api/orders` | Add an order |
| PUT | `/api/orders/{id}` | Replace an order |
| PATCH | `/api/orders/{id}` | Update selected fields |
| DELETE | `/api/orders/{id}` | Delete an order |
| GET | `/api/orders/search/customer?name=vinay` | Search by customer |
| GET | `/api/orders/search/food?item=pizza` | Search by food item |

## Example POST body

```json
{
  "customerName": "Vinay",
  "foodItem": "Veg Pizza",
  "quantity": 2,
  "price": 299.00
}
```

## Example PATCH body

```json
{
  "quantity": 3,
  "price": 399.00
}
```

## Database

H2 creates a local database automatically in the `data` folder. To inspect it, open:

`http://localhost:8080/h2-console`

Use these login details:

- JDBC URL: `jdbc:h2:file:./data/foodorders`
- User: `sa`
- Password: leave empty

The `food_orders` table is generated automatically. The repository includes simple query methods for searching by customer name and food item.
