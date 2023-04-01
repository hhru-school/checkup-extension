FROM node:16

WORKDIR /app/

COPY package.json package-lock.json ./
RUN npm install --silent

COPY . ./
RUN npm run build

EXPOSE 8080
CMD ["npx", "-y", "http-server", "./build/", "--gzip", "--brotli"]
