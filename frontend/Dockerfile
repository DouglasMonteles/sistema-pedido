# BUILD
FROM node:12.7-alpine AS build
WORKDIR /opt/frontend
COPY package.json ./
RUN npm install
COPY . .
RUN npm run build

# RUN
FROM nginx:1.17.1-alpine
COPY default.conf /etc/nginx/conf.d/default.conf
COPY --from=build /opt/frontend/dist/frontend /usr/share/nginx/html
EXPOSE 4200
