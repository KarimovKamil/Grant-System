FROM node:8.10.0-alpine

RUN mkdir -p /usr/src/app
WORKDIR /usr/src/app

COPY package.json /usr/src/app
RUN yarn

COPY . /usr/src/app
RUN yarn build

EXPOSE 8080
CMD node server.js  
