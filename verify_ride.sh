#!/bin/bash

# Base URL
BASE_URL="http://localhost:8080"

# 1. Register a user (if not exists)
echo "Registering user..."
curl -X POST "$BASE_URL/register" \
     -H "Content-Type: application/json" \
     -d '{"username": "testuser", "password": "password", "role": "PASSENGER"}'
echo -e "\n"

# 2. Login (This endpoint returns a string "Login successful!", it doesn't seem to return a JWT token in AuthController)
# Since the current AuthController is very basic and doesn't issue tokens, 
# and SecurityConfig doesn't configure JWT filter,
# standard Spring Security session management (JSESSIONID) might be in play if we use form login,
# OR Basic Auth if configured.
# However, without a SecurityFilterChain, default is Basic Auth for everything.
# Let's try to hit the ride endpoint using Basic Auth with the registered credentials.

echo "Creating a ride..."
curl -v -X POST "$BASE_URL/api/v1/rides" \
     -u "testuser:password" \
     -H "Content-Type: application/json" \
     -d '{"pickupLocation": "Central Park", "dropLocation": "Times Square"}'

echo -e "\nDone."
