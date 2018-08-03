FROM node:8.11.3-alpine as build-stage
WORKDIR /app
COPY package.json .
RUN yarn
COPY . .
RUN yarn build

FROM nginx:1.14.0-alpine as production-stage
COPY --from=build-stage /app/dist /usr/src/app/dist
COPY --from=build-stage /app/client.conf /etc/nginx/conf.d/default.conf
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
