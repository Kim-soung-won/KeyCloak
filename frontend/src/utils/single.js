const url = "http://localhost:5151";
const serviceAUrl = "http://localhost:5252";
const urlNoPort = "http://localhost";

// const url = "http://175.45.204.119:5151";
// const serviceAUrl = "http://175.45.204.119:5252";
// const urlNoPort = "http://175.45.204.119";

const jsonHeader = {
    "Content-Type": "application/json",
    "Access-Control-Allow-Origin": `${urlNoPort}:3131`
}


export { url, urlNoPort , jsonHeader, serviceAUrl };