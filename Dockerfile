FROM node:20-alpine

RUN mkdir -p /home/app
WORKDIR /home/app

COPY app/* /home/app

EXPOSE 3000

RUN npm install

CMD [ "node", "server.js" ]
