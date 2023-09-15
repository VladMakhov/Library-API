const http = require("http")
const fs = require("fs").promises;

const HOST = "localhost"
const PORT = 3000

const server = http.createServer((req, res) => {
    fs.readFile(__dirname + "/index.html")
        .then(contents => {
            res.setHeader("Content-Type", "text/html");
            res.writeHead(200);
            res.end(contents);
        })
})

server.listen(PORT, HOST, (error) => {
    error ? console.log(error) : console.log(`listening on ${HOST}:${PORT}`)
})