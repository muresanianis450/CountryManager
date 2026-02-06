# CountryManager (Spring Boot)

---

### Changes added on 06.02.2026🔍

# 1.Country(model)

Role: Represents one country record that Jackson can serialize/deserialize from/to JSON.

- **Fields**: `id`,`country`,`capital`,`population`
- `Lombok` generates getters/setters + no-args constructor so Jackson can create objects.

# 2. JacksonConfig (configuration)

Role: Creates an `ObjectMapper` **bean** so Spring can inject it where needed.

- Without this, Spring Boot didn't auto-provide an ObjectMapper in my current setup, so the app failed at startup


# 3. DataService (service)

Role: Reads and writes countries from a JSON file and contains the update logic.

- `getAllCountries()`: reads the JSON array from disk and converts it into `List<Country>`
- `updateCountry(id, updatedCountry)`
  - 1. Loads all countries
  - 2. Finds the one with matching `id`
  - 3. Updates its fields(`country`,`capital`,`population`)
  - 4. Writes the updates list back to the JSON file (persists change)
  - 5. Returns the updated country

# 4. CountryController (REST API layer)

Role: Exposes HTTP endpoints that call `DataService`.

**Endpoints**:
- `GET /countries` -> returns all countries
- `GET /countries/{id}` -> returns one country with `id`
- `PUT /countries/{id}` -> updates one country using JSON body


# How to run and test (step-by-step)

## Step 0 - Start the app

Run `CountryManagerApplication`

## Step 1 - Get all countries

Open in browser:

```txt
http://localhost:8080/countries
```


## Step 2 - Get a country by ID

Open in browser:

```txt
http://localhost:8080/countries/10
```

## Step 3 - Update a country (PUT)

You **cannot** do this from the browser URL bar. You must send a PUT request

**PowerShell command:**
```Shell
Invoke-RestMethod -Method PUT `
  -Uri "http://localhost:8080/countries/10" `
  -ContentType "application/json" `
  -Body '{"country":"Czechia","capital":"Prague","population":11000000}'
```

## Step 4 - Verify the update

Now open again:

```txt
http://localhost:8080/countries/10
```

