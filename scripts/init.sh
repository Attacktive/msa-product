#!/usr/bin/env zsh

curl 'http://localhost:8080/products' -v -H 'Content-Type: application/json' -d '{ "name": "Laptop", "description": "A simple laptop good enough for development", "price": 1234567, "stock": 6 }'
curl 'http://localhost:8080/products' -v -H 'Content-Type: application/json' -d '{ "name": "Mechanical Keyboard", "description": null, "price": 222222, "stock": 39 }'
curl 'http://localhost:8080/products' -v -H 'Content-Type: application/json' -d '{ "name": "Bluetooth Mouse", "description": "🐭", "price": 33333, "stock": 102 }'
curl 'http://localhost:8080/products' -v -H 'Content-Type: application/json' -d '{ "name": "Trackpad", "description": "", "price": 123456, "stock": 11 }'
