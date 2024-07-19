FROM node:20-alpine

RUN mkdir -p /home/app
WORKDIR /home/app

COPY app/* /home/app

EXPOSE 8080

RUN npm install

CMD [ "node", "server.js" ]
