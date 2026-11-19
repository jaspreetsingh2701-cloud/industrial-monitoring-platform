# PostgreSQL Learning Guide

**Project:** Industrial Monitoring Platform  
**Environment:** Windows PowerShell + Docker Desktop + PostgreSQL 17 Alpine  
**Status:** Initial Docker setup and connection checks completed

This is a practical reference for the PostgreSQL setup we performed. It records the actual commands and queries, along with a small SQL quick reference for revision.

---

## 1. How PostgreSQL is running locally

We chose PostgreSQL in Docker instead of running the database directly as a Windows service.

| Item | Value |
|---|---|
| Docker image | `postgres:17-alpine` |
| Container name | `industrial-postgres` |
| Database | `industrial_monitoring` |
| Database user | `monitoring` |
| Host address | `localhost` |
| Host port | `5433` |
| Container port | `5432` |
| Docker volume | `industrial-postgres-data` |

### Image, container, and volume

- **Image:** The packaged PostgreSQL software we downloaded.
- **Container:** The running PostgreSQL instance created from that image.
- **Volume:** Persistent storage for database files. It keeps data when the container is stopped or removed.

Port mapping `5433:5432` means connections from Windows use port **5433**, while PostgreSQL listens on port **5432** inside the container.

---

## 2. Create the PostgreSQL container

Run this from **Windows PowerShell** at the repository root:

```powershell
docker run --name industrial-postgres `
  -e POSTGRES_USER=monitoring `
  -e POSTGRES_PASSWORD=CHANGE_ME_LOCAL_PASSWORD `
  -e POSTGRES_DB=industrial_monitoring `
  -p 5433:5432 `
  -v industrial-postgres-data:/var/lib/postgresql/data `
  -d postgres:17-alpine
```

Replace `CHANGE_ME_LOCAL_PASSWORD` with your own local development password. Do not commit real credentials.

### What these options do

| Option | Purpose |
|---|---|
| `--name industrial-postgres` | Gives the container a memorable name |
| `POSTGRES_USER` | Creates the initial database user |
| `POSTGRES_PASSWORD` | Sets that user's password |
| `POSTGRES_DB` | Creates the initial database |
| `-p 5433:5432` | Maps Windows host port 5433 to container port 5432 |
| `-v industrial-postgres-data:/var/lib/postgresql/data` | Stores database files in a named volume |
| `-d` | Runs the container in the background |
| `postgres:17-alpine` | Uses the PostgreSQL 17 Alpine image |

**Important:** The `POSTGRES_DB` variable creates the initial database when the data directory is first initialized. It is not a SQL query. These initialization variables do not recreate or reset an already-initialized database volume.

### Check the container

```powershell
# Show running containers.
docker ps

# Show all containers, including stopped ones.
docker ps -a

# Show downloaded PostgreSQL images.
docker images
```

---

## 3. Start, stop, and reconnect

Use these commands from PowerShell:

```powershell
# Stop PostgreSQL.
docker stop industrial-postgres

# Start the existing container again.
docker start industrial-postgres

# View container logs.
docker logs industrial-postgres

# Open the PostgreSQL command-line client.
docker exec -it industrial-postgres psql -U monitoring -d industrial_monitoring
```

The last command opens the `psql` prompt. You should see:

```text
industrial_monitoring=#
```

To leave `psql`, run:

```sql
\q
```

Stopping a container does not delete its data. The named volume stores the database files independently of the container lifecycle. Do not remove the volume unless you intentionally want to delete the persisted database data.

---

## 4. Validate the database connection

These are the checks we actually ran after connecting through `psql`.

### 4.1 Check current database and user

```sql
SELECT current_database(), current_user;
```

Observed result:

```text
   current_database    | current_user
-----------------------+--------------
 industrial_monitoring | monitoring
```

This confirmed that we were connected to the expected database as the expected user.

### 4.2 List databases

```sql
\l
```

We saw these databases:

- `industrial_monitoring`
- `postgres`
- `template0`
- `template1`

### 4.3 List tables in the current database

```sql
\dt
```

Initial output:

```text
Did not find any relations.
```

This was expected: the database existed, but we had not created any tables yet.

---

## 5. Create, insert, read, and drop a test table

We then ran the following SQL successfully.

### 5.1 Create a table

```sql
CREATE TABLE connection_test (
    id SERIAL PRIMARY KEY,
    message VARCHAR(100)
);
```

- `CREATE TABLE` creates a table.
- `id` is an auto-incrementing integer using PostgreSQL's `SERIAL` shorthand.
- `PRIMARY KEY` uniquely identifies each row.
- `VARCHAR(100)` stores text up to 100 characters.

### 5.2 Insert a record

```sql
INSERT INTO connection_test (message)
VALUES ('PostgreSQL connection successful');
```

Observed output: `INSERT 0 1` — one row was inserted.

### 5.3 Read the record

```sql
SELECT * FROM connection_test;
```

Observed result:

```text
 id |             message
----+----------------------------------
  1 | PostgreSQL connection successful
```

### 5.4 Drop the test table

```sql
DROP TABLE connection_test;
```

Observed output: `DROP TABLE`.

We deliberately removed the test table after confirming that create, insert, and read operations worked. Therefore, running `\dt` now should again show no relations.

---

## 6. Useful psql commands

These are **psql client commands**, not regular SQL statements. They start with a backslash.

| Command | Purpose |
|---|---|
| `\l` | List databases |
| `\c industrial_monitoring` | Connect to a database |
| `\dt` | List tables in the current schema |
| `\dt *.*` | List tables across schemas |
| `\d table_name` | Describe a table's columns and constraints |
| `\du` | List database roles/users |
| `\dn` | List schemas |
| `\conninfo` | Show current connection details |
| `\q` | Exit psql |

---

## 7. Useful SQL queries for revision

The following examples use a separate practice table. Run them only when you want to create that table.

### Create a practice table

```sql
CREATE TABLE devices (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    device_type VARCHAR(50),
    is_active BOOLEAN DEFAULT TRUE
);
```

### Insert rows

```sql
INSERT INTO devices (name, device_type)
VALUES
    ('Boiler Sensor 01', 'Temperature'),
    ('Motor Sensor 01', 'Vibration');
```

### Read all rows or selected columns

```sql
SELECT * FROM devices;

SELECT name, device_type
FROM devices;
```

### Filter and sort

```sql
SELECT *
FROM devices
WHERE is_active = TRUE;

SELECT *
FROM devices
ORDER BY name ASC;
```

### Update a row

```sql
UPDATE devices
SET is_active = FALSE
WHERE id = 1;
```

### Delete a row

```sql
DELETE FROM devices
WHERE id = 2;
```

### Count rows

```sql
SELECT COUNT(*) FROM devices;
```

### Remove the practice table

```sql
DROP TABLE devices;
```

**Reminder:** Always check the `WHERE` condition before running `UPDATE` or `DELETE`. Without a `WHERE` clause, every row can be affected.

---

## 8. SQL vs psql vs Docker commands

| Command type | Where to run it | Example |
|---|---|---|
| Docker commands | Windows PowerShell | `docker ps` |
| psql meta-commands | Inside the `psql` prompt | `\dt`, `\l`, `\q` |
| SQL statements | Inside the `psql` prompt or a SQL client | `SELECT * FROM devices;` |

A common point of confusion: `\dt` is a psql shortcut, while `SELECT * FROM devices;` is SQL.

---

## 9. Current project status

- [x] PostgreSQL 17 Alpine image available locally.
- [x] Docker container `industrial-postgres` created and started.
- [x] Database `industrial_monitoring` created during container initialization.
- [x] Connected using the `monitoring` user.
- [x] Verified current database and user.
- [x] Listed databases.
- [x] Confirmed the database initially had no tables.
- [x] Created, inserted into, queried, and dropped a test table.
- [ ] Connect the Spring Boot Device Service to PostgreSQL.
- [ ] Replace the in-memory repository with Spring Data JPA persistence.

**Next learning step:** Configure the Device Service datasource and persist Device records using Spring Data JPA and Hibernate.

---

*This guide records the commands and results from our local learning setup. The practice SQL examples are additional revision material; they were not part of the connection validation we performed.*
