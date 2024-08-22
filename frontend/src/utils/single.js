const url = "http://localhost:5151";
const urlNoPort = "http://localhost";

const jsonHeader = {
    "Content-Type": "application/json",
    "Access-Control-Allow-Origin": `${urlNoPort}:3131`
}


export { url, urlNoPort , jsonHeader};