SELECT 'CREATE DATABASE checkup_extension'
WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = 'checkup_extension')\gexec
