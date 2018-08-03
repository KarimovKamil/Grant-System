FROM node:8.11.3-alpine
WORKDIR /app
COPY package.json .
RUN yarn
COPY . .
RUN yarn build
EXPOSE 3000
CMD yarn start -H 0.0.0.0