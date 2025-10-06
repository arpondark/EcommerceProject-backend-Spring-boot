# E-Commerce Project - API Documentation

## Default Credentials

For easy testing and access, use these default credentials:

### Admin Account
- **Username:** `admin`
- **Email:** `admin@example.com`
- **Password:** `admin123`
- **Role:** ROLE_ADMIN

### Regular User Account
- **Username:** `user1`
- **Email:** `user1@example.com`
- **Password:** `user123`
- **Role:** ROLE_USER

> **Note:** These are default credentials for development/testing purposes. Make sure to change them in production environments.

## API Endpoints

### Health Check Endpoints
| HTTP Method | Endpoint | Description | Access |
|-------------|----------|-------------|--------|
| GET | `/api/health` | Health check endpoint for AWS monitoring | Public |
| GET | `/api/ping` | Simple ping endpoint | Public |

### Authentication Endpoints
| HTTP Method | Endpoint | Description | Access |
|-------------|----------|-------------|--------|
| POST | `/api/auth/signin` | User login | Public |
| POST | `/api/auth/signup` | User registration | Public |
| GET | `/api/auth/username` | Get current username | Authenticated |
| GET | `/api/auth/user` | Get user details | Authenticated |

### Category Endpoints
| HTTP Method | Endpoint | Description | Access |
|-------------|----------|-------------|--------|
| GET | `/api/public/categories` | Get all categories (paginated) | Public |
| POST | `/api/public/categories` | Create new category | Public |
| PUT | `/api/public/categories/{categoryId}` | Update category | Public |
| DELETE | `/api/admin/categories/{categoryId}` | Delete category | Admin |

**Query Parameters for GET categories:**
- `pageNumber` (default: 0)
- `pageSize` (default: 10)
- `sortBy` (default: categoryId)
- `sortOrder` (default: asc)

### Product Endpoints
| HTTP Method | Endpoint | Description | Access |
|-------------|----------|-------------|--------|
| POST | `/api/admin/categories/{categoryId}/product` | Add product to category | Admin |
| GET | `/api/public/products` | Get all products (paginated) | Public |
| GET | `/api/public/categories/{categoryId}/products` | Get products by category | Public |
| GET | `/api/public/products/keyword/{keyword}` | Search products by keyword | Public |
| PUT | `/api/admin/products/{productId}` | Update product | Admin |
| DELETE | `/api/admin/products/{productId}` | Delete product | Admin |
| PUT | `/api/products/{productId}/image` | Update product image | Authenticated |

**Query Parameters for GET products:**
- `pageNumber` (default: 0)
- `pageSize` (default: 10)
- `sortBy` (default: productId)
- `sortOrder` (default: asc)

### Cart Endpoints
| HTTP Method | Endpoint | Description | Access |
|-------------|----------|-------------|--------|
| POST | `/api/carts/products/{productId}/quantity/{quantity}` | Add product to cart | Authenticated |
| GET | `/api/carts` | Get all carts | Authenticated |
| GET | `/api/carts/users/cart` | Get user's cart | Authenticated |
| PUT | `/api/cart/products/{productId}/quantity/{operation}` | Update product quantity in cart | Authenticated |
| DELETE | `/api/carts/{cartId}/product/{productId}` | Delete product from cart | Authenticated |

**Note:** For update quantity, use `operation` parameter: 
- `delete` = decrease quantity by 1
- Any other value = increase quantity by 1

### Address Endpoints
| HTTP Method | Endpoint | Description | Access |
|-------------|----------|-------------|--------|
| POST | `/api/addresses` | Create new address | Authenticated |
| GET | `/api/addresses` | Get all addresses | Authenticated |
| GET | `/api/addresses/{addressId}` | Get address by ID | Authenticated |
| GET | `/api/users/addresses` | Get current user's addresses | Authenticated |
| PUT | `/api/addresses/{addressId}` | Update address | Authenticated |
| DELETE | `/api/addresses/{addressId}` | Delete address | Authenticated |

### Order Endpoints
| HTTP Method | Endpoint | Description | Access |
|-------------|----------|-------------|--------|
| POST | `/api/order/users/payments/{paymentMethod}` | Place order with payment | Authenticated |

**Request Body for Order:**
```json
{
  "addressId": "long",
  "pgName": "string",
  "pgPaymentId": "string",
  "pgStatus": "string",
  "pgResponseMessage": "string"
}
```

## General Information

- **Base URL:** `http://localhost:8080` (default)
- **Authentication:** JWT token-based authentication (cookie-based)
- **Content-Type:** `application/json`
- **Pagination:** Most list endpoints support pagination with query parameters

## Roles
- **ROLE_USER**: Regular user access
- **ROLE_SELLER**: Seller access
- **ROLE_ADMIN**: Administrator access
