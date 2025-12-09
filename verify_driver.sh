#!/bin/bash
BASE_URL="http://localhost:8080"

# 1. Create Ride
echo "Creating Ride..."
RESPONSE=$(curl -s -X POST "$BASE_URL/api/v1/rides" \
     -u "passenger:password" \
     -H "Content-Type: application/json" \
     -d '{"pickupLocation": "Airport", "dropLocation": "Downtown"}')
echo "Response: $RESPONSE"

# Simple parsing for ID (assumes "id":"VALUE" format)
RIDE_ID=$(echo $RESPONSE | grep -o '"id":"[^"]*"' | head -n1 | cut -d'"' -f4)
echo "Ride ID: $RIDE_ID"

if [ -z "$RIDE_ID" ]; then
  echo "Failed to create ride"
  exit 1
fi

echo -e "\n"

# 2. List Pending Rides
echo "Listing Pending Rides (Driver)..."
PENDING=$(curl -s -X GET "$BASE_URL/api/v1/driver/rides/requests" -u "driver:password")
echo "Pending: $PENDING"
echo -e "\n"

# 3. Accept Ride
echo "Accepting Ride $RIDE_ID (Driver)..."
ACCEPTED=$(curl -s -X POST "$BASE_URL/api/v1/driver/rides/$RIDE_ID/accept" -u "driver:password")
echo "Accepted: $ACCEPTED"
echo -e "\n"

# 4. Verify - List Pending Rides again
echo "Listing Pending Rides again (Should not contain $RIDE_ID)..."
curl -s -X GET "$BASE_URL/api/v1/driver/rides/requests" -u "driver:password"
echo -e "\n"
