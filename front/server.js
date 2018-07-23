const express = require('express');
const path = require('path');

const routes = require('./routes');

const render = () => path.join(__dirname + '/dist/index.html');

const app = express();
app.use(express.static('dist'));

routes.forEach(route => app.get(route, (request, response) => response.sendFile(render())));

app.listen(8080, () => console.log("Server is up!"));
