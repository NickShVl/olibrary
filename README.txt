You need to generate your own private and public RSA keys or other keys

commands:
openssl genpkey -out private.pem -algorithm RSA -pkeyopt rsa_keygen_bits:2048
openssl rsa -in private.pem -pubout -out public.pem

after it copy this into src/main/resources/certs