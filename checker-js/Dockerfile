FROM node:18.15.0

RUN apt update && apt-get install -y libnss3 libnspr4 libatk1.0-0 libatk-bridge2.0-0 libcups2 libdrm2 libxcomposite-dev libxdamage1 libxfixes3 libxrandr2 libgbm1 libxkbcommon0 libasound2

WORKDIR /app

COPY . .

RUN npm ci --cache .npm
