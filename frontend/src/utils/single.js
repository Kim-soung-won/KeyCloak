const urlNoPort = "http://localhost";
// const urlNoPort = "http://175.45.204.119";



const url = `${urlNoPort}:5151`;
const serviceAUrl = `${urlNoPort}:5252`;

const jsonHeader = {
    "Content-Type": "application/json",
    "Access-Control-Allow-Origin": `${urlNoPort}:3131`
}


export { url, urlNoPort , jsonHeader, serviceAUrl };