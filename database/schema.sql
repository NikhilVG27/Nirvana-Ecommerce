-- Nirvana PostgreSQL MVP schema. Spring Data JPA can create/update these tables
-- when the backend starts with spring.jpa.hibernate.ddl-auto=update.
CREATE TABLE IF NOT EXISTS products (
  product_id BIGSERIAL PRIMARY KEY,
  name VARCHAR(180) NOT NULL,
  category VARCHAR(40) NOT NULL,
  type VARCHAR(60) NOT NULL,
  price NUMERIC(10,2) NOT NULL CHECK (price > 0),
  old_price NUMERIC(10,2),
  rating NUMERIC(2,1),
  tag VARCHAR(40),
  color VARCHAR(80),
  sizes_csv VARCHAR(250),
  image_url VARCHAR(1000),
  description TEXT,
  stock INTEGER NOT NULL DEFAULT 0 CHECK (stock >= 0),
  active BOOLEAN NOT NULL DEFAULT TRUE,
  version BIGINT
);
CREATE INDEX IF NOT EXISTS idx_products_category_active ON products(category, active);
CREATE INDEX IF NOT EXISTS idx_products_name ON products(name);

-- Orders are persisted by the Spring Boot JPA entities. These definitions
-- are included for readers who prefer to create the schema manually.
CREATE TABLE IF NOT EXISTS customer_orders (
  id BIGSERIAL PRIMARY KEY,
  order_number VARCHAR(24) NOT NULL UNIQUE,
  customer_name VARCHAR(120) NOT NULL,
  email VARCHAR(180) NOT NULL,
  phone VARCHAR(20) NOT NULL,
  address VARCHAR(300) NOT NULL,
  city VARCHAR(100) NOT NULL,
  pincode VARCHAR(6) NOT NULL,
  payment_method VARCHAR(40) NOT NULL,
  status VARCHAR(30) NOT NULL,
  subtotal NUMERIC(12,2) NOT NULL,
  delivery_fee NUMERIC(12,2) NOT NULL,
  total NUMERIC(12,2) NOT NULL,
  created_at TIMESTAMPTZ NOT NULL
);
CREATE TABLE IF NOT EXISTS customer_order_items (
  id BIGSERIAL PRIMARY KEY,
  order_id BIGINT NOT NULL REFERENCES customer_orders(id) ON DELETE CASCADE,
  product_id BIGINT NOT NULL,
  product_name VARCHAR(180) NOT NULL,
  size VARCHAR(30) NOT NULL,
  quantity INTEGER NOT NULL CHECK (quantity > 0),
  unit_price NUMERIC(10,2) NOT NULL
);
CREATE INDEX IF NOT EXISTS idx_orders_email ON customer_orders(email);
CREATE INDEX IF NOT EXISTS idx_orders_created_at ON customer_orders(created_at);
