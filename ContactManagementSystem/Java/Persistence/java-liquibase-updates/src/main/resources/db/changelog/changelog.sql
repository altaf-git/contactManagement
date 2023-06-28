CREATE EXTENSION IF NOT EXISTS pgcrypto;

CREATE TABLE IF NOT EXISTS contacts (uuid UUID PRIMARY KEY DEFAULT gen_random_uuid(),
  first_name VARCHAR(255),
  last_name VARCHAR(255),
  email VARCHAR(255),
  phone_number VARCHAR(255),
  created_on TIMESTAMP,
  created_by VARCHAR(255),
  modified_on TIMESTAMP,
  modified_by VARCHAR(255));